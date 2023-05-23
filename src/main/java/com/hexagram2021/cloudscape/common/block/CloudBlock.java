package com.hexagram2021.cloudscape.common.block;

import com.hexagram2021.cloudscape.common.tag.CSEntityTypeTags;
import com.hexagram2021.cloudscape.common.tag.CSItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

@SuppressWarnings("deprecation")
public class CloudBlock extends Block {
	private static final float HORIZONTAL_PARTICLE_MOMENTUM_FACTOR = 0.083333336F;
	private static final float IN_BLOCK_HORIZONTAL_SPEED_MULTIPLIER = 0.9F;
	private static final float IN_BLOCK_VERTICAL_SPEED_MULTIPLIER = 1.5F;
	private static final float NUM_BLOCKS_TO_FALL_INTO_BLOCK = 2.5F;
	private static final VoxelShape FALLING_COLLISION_SHAPE = Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, 0.9D, 1.0D);
	private static final double MINIMUM_FALL_DISTANCE_FOR_SOUND = 4.0D;
	private static final double MINIMUM_FALL_DISTANCE_FOR_BIG_SOUND = 7.0D;

	public CloudBlock(BlockBehaviour.Properties props) {
		super(props);
	}

	@Override
	public boolean skipRendering(@NotNull BlockState current, BlockState neighbor, @NotNull Direction direction) {
		return neighbor.is(this) || super.skipRendering(current, neighbor, direction);
	}

	@Override @NotNull
	public VoxelShape getOcclusionShape(@NotNull BlockState blockState, @NotNull BlockGetter level, @NotNull BlockPos blockPos) {
		return Shapes.empty();
	}

	@Override
	public void entityInside(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Entity entity) {
		if (!(entity instanceof LivingEntity) || entity.getFeetBlockState().is(this)) {
			entity.makeStuckInBlock(blockState, new Vec3(
					IN_BLOCK_HORIZONTAL_SPEED_MULTIPLIER, IN_BLOCK_VERTICAL_SPEED_MULTIPLIER, IN_BLOCK_HORIZONTAL_SPEED_MULTIPLIER
			));
			if (level.isClientSide) {
				Random random = level.getRandom();
				boolean flag = entity.xOld != entity.getX() || entity.zOld != entity.getZ();
				if (flag && random.nextBoolean()) {
					level.addParticle(
							ParticleTypes.SNOWFLAKE, entity.getX(), blockPos.getY() + 1, entity.getZ(),
							Mth.randomBetween(random, -1.0F, 1.0F) * HORIZONTAL_PARTICLE_MOMENTUM_FACTOR,
							0.05D,
							Mth.randomBetween(random, -1.0F, 1.0F) * HORIZONTAL_PARTICLE_MOMENTUM_FACTOR
					);
				}
			}
		}

		if (!level.isClientSide) {
			if (entity.isOnFire() && (level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING) || entity instanceof Player) && entity.mayInteract(level, blockPos)) {
				level.destroyBlock(blockPos, false);
			}

			entity.setSharedFlagOnFire(false);
		}
	}

	@Override
	public void fallOn(@NotNull Level level, @NotNull BlockState blockState, @NotNull BlockPos blockPos, @NotNull Entity entity, float height) {
		if (!((double)height < MINIMUM_FALL_DISTANCE_FOR_SOUND) && entity instanceof LivingEntity livingEntity) {
			LivingEntity.Fallsounds fallSounds = livingEntity.getFallSounds();
			SoundEvent soundevent = (double)height < MINIMUM_FALL_DISTANCE_FOR_BIG_SOUND ? fallSounds.small() : fallSounds.big();
			entity.playSound(soundevent, 1.0F, 1.0F);
		}
	}

	@Override @NotNull
	public VoxelShape getCollisionShape(@NotNull BlockState blockState, @NotNull BlockGetter level, @NotNull BlockPos blockPos, @NotNull CollisionContext context) {
		if (context instanceof EntityCollisionContext entitycollisioncontext) {
			Entity entity = entitycollisioncontext.getEntity();
			if (entity != null) {
				if (entity.fallDistance > NUM_BLOCKS_TO_FALL_INTO_BLOCK) {
					return FALLING_COLLISION_SHAPE;
				}

				boolean flag = entity instanceof FallingBlockEntity;
				if (flag || canEntityWalkOnCloud(entity) && context.isAbove(Shapes.block(), blockPos, false) && !context.isDescending()) {
					return super.getCollisionShape(blockState, level, blockPos, context);
				}
			}
		}

		return Shapes.empty();
	}

	@Override @NotNull
	public VoxelShape getVisualShape(@NotNull BlockState blockState, @NotNull BlockGetter level, @NotNull BlockPos blockPos, @NotNull CollisionContext context) {
		return Shapes.empty();
	}

	public static boolean canEntityWalkOnCloud(Entity entity) {
		if (entity.getType().is(CSEntityTypeTags.CLOUD_WALKABLE_MOBS)) {
			return true;
		}
		return entity instanceof LivingEntity livingEntity && livingEntity.getItemBySlot(EquipmentSlot.FEET).is(CSItemTags.CLOUD_WALKABLE_ITEMS);
	}

	@Override
	public boolean isPathfindable(@NotNull BlockState blockState, @NotNull BlockGetter level, @NotNull BlockPos blockPos, @NotNull PathComputationType pathComputationType) {
		return true;
	}
}

package com.hexagram2021.cloudscape.common.register;

import com.google.common.collect.ImmutableList;
import com.hexagram2021.cloudscape.common.block.CloudBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.hexagram2021.cloudscape.Cloudscape.MODID;

public final class CSBlocks {
	public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

	private CSBlocks() {}

	private static String changeNameTo(String name, String postfix) {
		if(name.endsWith("_block")) {
			name = name.replaceAll("_block", postfix);
		} else if(name.endsWith("_bricks")) {
			name = name.replaceAll("_bricks", "_brick" + postfix);
		} else if(name.endsWith("_planks")) {
			name = name.replaceAll("_planks", postfix);
		} else {
			name = name + postfix;
		}
		return name;
	}

	private static <T extends Block> void registerStairs(BlockEntry<T> fullBlock) {
		String name = changeNameTo(fullBlock.getId().getPath(), "_stairs");
		TO_STAIRS.put(fullBlock.getId(), new BlockEntry<>(
				name,
				fullBlock.getProperties(),
				p -> new StairBlock(fullBlock::defaultBlockState, p)
		));
	}

	private static <T extends Block> void registerSlab(BlockEntry<T> fullBlock) {
		String name = changeNameTo(fullBlock.getId().getPath(), "_slab");
		TO_SLAB.put(fullBlock.getId(), new BlockEntry<>(
				name,
				fullBlock.getProperties(),
				p -> new SlabBlock(p.isSuffocating((state, world, pos) ->
						fullBlock.defaultBlockState().isSuffocating(world, pos) && state.getValue(SlabBlock.TYPE) == SlabType.DOUBLE
				).isRedstoneConductor((state, world, pos) ->
						fullBlock.defaultBlockState().isRedstoneConductor(world, pos) && state.getValue(SlabBlock.TYPE) == SlabType.DOUBLE
				))
		));
	}

	private static <T extends Block> void registerWall(BlockEntry<T> fullBlock) {
		String name = changeNameTo(fullBlock.getId().getPath(), "_wall");
		TO_WALL.put(fullBlock.getId(), new BlockEntry<>(
				name,
				fullBlock.getProperties(),
				WallBlock::new
		));
	}

	private static <T extends Block> void registerFence(BlockEntry<T> fullBlock) {
		String name = changeNameTo(fullBlock.getId().getPath(), "_fence");
		TO_FENCE.put(fullBlock.getId(), new BlockEntry<>(
				name,
				BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).color(fullBlock.get().defaultMaterialColor()),
				FenceBlock::new
		));
	}

	private static <T extends Block> void registerFenceGate(BlockEntry<T> fullBlock) {
		String name = changeNameTo(fullBlock.getId().getPath(), "_fence_gate");
		TO_FENCE_GATE.put(fullBlock.getId(), new BlockEntry<>(
				name,
				BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE).color(fullBlock.get().defaultMaterialColor()),
				FenceGateBlock::new
		));
	}

	private static <T extends Block> void registerDoor(BlockEntry<T> fullBlock) {
		String name = changeNameTo(fullBlock.getId().getPath(), "_door");
		TO_DOOR.put(fullBlock.getId(), new BlockEntry<>(
				name,
				BlockBehaviour.Properties.copy(Blocks.OAK_DOOR).color(fullBlock.get().defaultMaterialColor()),
				DoorBlock::new
		));
	}

	private static <T extends Block> void registerTrapDoor(BlockEntry<T> fullBlock) {
		String name = changeNameTo(fullBlock.getId().getPath(), "_trapdoor");
		TO_TRAPDOOR.put(fullBlock.getId(), new BlockEntry<>(
				name,
				BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR).color(fullBlock.get().defaultMaterialColor()),
				TrapDoorBlock::new
		));
	}

	private static <T extends Block> void registerWoodPressurePlate(BlockEntry<T> fullBlock) {
		String name = changeNameTo(fullBlock.getId().getPath(), "_pressure_plate");
		TO_PRESSURE_PLATE.put(fullBlock.getId(), new BlockEntry<>(
				name,
				BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE).color(fullBlock.get().defaultMaterialColor()),
				(props) -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, props)
		));
	}

	private static <T extends Block> void registerWoodButton(BlockEntry<T> fullBlock) {
		String name = changeNameTo(fullBlock.getId().getPath(), "_button");
		TO_BUTTON.put(fullBlock.getId(), new BlockEntry<>(
				name,
				BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).color(fullBlock.get().defaultMaterialColor()),
				WoodButtonBlock::new
		));
	}

	private static <T extends Block> void registerSign(BlockEntry<T> fullBlock, WoodType woodType) {
		String name1 = changeNameTo(fullBlock.getId().getPath(), "_sign");
		String name2 = changeNameTo(fullBlock.getId().getPath(), "_wall_sign");
		TO_SIGN.put(fullBlock.getId(), new Tuple<>(new BlockEntry<>(
				name1,
				BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD).color(fullBlock.get().defaultMaterialColor()),
				(props) -> new StandingSignBlock(props, woodType)
		), new BlockEntry<>(
				name2,
				BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD).color(fullBlock.get().defaultMaterialColor()),
				(props) -> new WallSignBlock(props, woodType)
		)));
	}

	private static <T extends Block> void addColoredBlockToList(List<BlockEntry<?>> list, DyeColor color, BlockEntry<T> colorlessBlock,
																Function<DyeColor, BlockBehaviour.Properties> properties,
																Function<BlockBehaviour.Properties, T> factory) {
		list.add(new BlockEntry<>(color.getName() + "_" + colorlessBlock.getId().getPath(), properties.apply(color), factory));
	}

	/* 不会吧，不会真有mod会添加或修改原版DyeColor吧，我怕了还不行吗 */
	private static final List<DyeColor> DYE_COLORS = ImmutableList.of(
			DyeColor.WHITE, DyeColor.ORANGE, DyeColor.MAGENTA, DyeColor.LIGHT_BLUE,
			DyeColor.YELLOW, DyeColor.LIME, DyeColor.PINK, DyeColor.GRAY,
			DyeColor.LIGHT_GRAY, DyeColor.CYAN, DyeColor.PURPLE, DyeColor.BLUE,
			DyeColor.BROWN, DyeColor.GREEN, DyeColor.RED, DyeColor.BLACK
	);
	private static <T extends Block> List<BlockEntry<?>> registerAllColors(BlockEntry<T> colorlessBlock,
																		   Function<DyeColor, BlockBehaviour.Properties> properties,
																		   Function<BlockBehaviour.Properties, T> factory) {
		List<BlockEntry<?>> list = new ArrayList<>();
		for(DyeColor dc: DYE_COLORS) {
			addColoredBlockToList(list, dc, colorlessBlock, properties, factory);
		}
		TO_COLORS.put(colorlessBlock.getId(), list);
		return list;
	}

	public static final class NaturalDecoration {
		private NaturalDecoration() {}

		public static final BlockEntry<Block> CLOUD_BLOCK = BlockEntry.typedBlock(
				"cloud", BlockBehaviour.Properties.of(Material.POWDER_SNOW).strength(0.25F).sound(SoundType.POWDER_SNOW).dynamicShape(), CloudBlock::new
		);

		private static void init() {

		}
	}

	public static void init(IEventBus bus) {
		REGISTER.register(bus);
		NaturalDecoration.init();
	}

	public static final Map<ResourceLocation, BlockEntry<SlabBlock>> TO_SLAB = new HashMap<>();
	public static final Map<ResourceLocation, BlockEntry<StairBlock>> TO_STAIRS = new HashMap<>();
	public static final Map<ResourceLocation, BlockEntry<WallBlock>> TO_WALL = new HashMap<>();
	public static final Map<ResourceLocation, BlockEntry<FenceBlock>> TO_FENCE = new HashMap<>();
	public static final Map<ResourceLocation, BlockEntry<FenceGateBlock>> TO_FENCE_GATE = new HashMap<>();
	public static final Map<ResourceLocation, BlockEntry<DoorBlock>> TO_DOOR = new HashMap<>();
	public static final Map<ResourceLocation, BlockEntry<TrapDoorBlock>> TO_TRAPDOOR = new HashMap<>();
	public static final Map<ResourceLocation, BlockEntry<PressurePlateBlock>> TO_PRESSURE_PLATE = new HashMap<>();
	public static final Map<ResourceLocation, BlockEntry<ButtonBlock>> TO_BUTTON = new HashMap<>();
	public static final Map<ResourceLocation, Tuple<BlockEntry<StandingSignBlock>, BlockEntry<WallSignBlock>>> TO_SIGN = new HashMap<>();
	public static final Map<ResourceLocation, List<BlockEntry<?>>> TO_COLORS = new HashMap<>();

	public static final class BlockEntry<T extends Block> implements Supplier<T>, ItemLike {
		private final RegistryObject<T> regObject;
		private final BlockBehaviour.Properties properties;

		public static BlockEntry<Block> simple(String name, BlockBehaviour.Properties properties) {
			return new BlockEntry<>(name, properties, Block::new);
		}
		public static <TB extends Block> BlockEntry<TB> typedBlock(String name,
																   BlockBehaviour.Properties properties,
																   Function<BlockBehaviour.Properties, TB> make) {
			return new BlockEntry<>(name, properties, make);
		}

		public BlockEntry(String name, BlockBehaviour.Properties properties, Function<BlockBehaviour.Properties, T> make) {
			this.properties = properties;
			this.regObject = REGISTER.register(name, () -> make.apply(properties));
		}

		@Override
		public T get() {
			return this.regObject.get();
		}

		public BlockState defaultBlockState() {
			return this.get().defaultBlockState();
		}

		public ResourceLocation getId() {
			return this.regObject.getId();
		}

		public BlockBehaviour.Properties getProperties() {
			return this.properties;
		}

		@Override @NotNull
		public Item asItem() {
			return this.get().asItem();
		}
	}
}

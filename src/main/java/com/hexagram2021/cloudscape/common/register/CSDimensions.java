package com.hexagram2021.cloudscape.common.register;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

import static com.hexagram2021.cloudscape.Cloudscape.MODID;

public class CSDimensions {
	private static final ResourceKey<Level> THE_CLOUDSCAPE = ResourceKey.create(
			Registry.DIMENSION_REGISTRY, new ResourceLocation(MODID, "the_cloudscape")
	);
	public static final ResourceKey<DimensionType> THE_CLOUDSCAPE_TYPE = ResourceKey.create(
			Registry.DIMENSION_TYPE_REGISTRY, new ResourceLocation(MODID, "the_cloudscape")
	);

	public static void init() {
	}
}

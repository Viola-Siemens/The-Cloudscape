package com.hexagram2021.cloudscape.common.tag;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

import static com.hexagram2021.cloudscape.Cloudscape.MODID;

public class CSBiomeTags {

	private static TagKey<Biome> create(String name) {
		return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(MODID, name));
	}
}

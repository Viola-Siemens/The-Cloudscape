package com.hexagram2021.cloudscape.common.tag;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static com.hexagram2021.cloudscape.Cloudscape.MODID;

public class CSBlockTags {

	private static TagKey<Block> create(String name) {
		return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(MODID, name));
	}
}

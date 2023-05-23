package com.hexagram2021.cloudscape.common.tag;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

import static com.hexagram2021.cloudscape.Cloudscape.MODID;

public class CSEntityTypeTags {
	public static final TagKey<EntityType<?>> CLOUD_WALKABLE_MOBS = create("cloud_walkable_mobs");

	private static TagKey<EntityType<?>> create(String name) {
		return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(MODID, name));
	}
}

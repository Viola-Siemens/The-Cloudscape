package com.hexagram2021.cloudscape.common.tag;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static com.hexagram2021.cloudscape.Cloudscape.MODID;

public class CSItemTags {
	public static final TagKey<Item> CLOUD_WALKABLE_ITEMS = create("cloud_walkable_items");

	private static TagKey<Item> create(String name) {
		return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(MODID, name));
	}
}

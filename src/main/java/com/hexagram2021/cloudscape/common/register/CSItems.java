package com.hexagram2021.cloudscape.common.register;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.hexagram2021.cloudscape.Cloudscape.MODID;

public final class CSItems {
	public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

	private CSItems() {}

	public static void init(IEventBus bus) {
		REGISTER.register(bus);
	}
}

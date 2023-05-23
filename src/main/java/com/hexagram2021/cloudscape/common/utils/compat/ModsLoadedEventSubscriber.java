package com.hexagram2021.cloudscape.common.utils.compat;

import net.minecraftforge.fml.ModList;

public class ModsLoadedEventSubscriber {
	public static boolean EMERALDCRAFT = false;

	private ModsLoadedEventSubscriber() {}

	public static void compatModLoaded() {
		ModList modList = ModList.get();
		if (modList.isLoaded("emeraldcraft")) {
			EMERALDCRAFT = true;
		}
	}

	public static void SolveCompat() {
		if(EMERALDCRAFT) {

		}
	}
}

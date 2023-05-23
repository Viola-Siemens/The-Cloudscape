package com.hexagram2021.cloudscape.common;

import com.hexagram2021.cloudscape.common.register.*;
import com.hexagram2021.cloudscape.common.utils.compat.ModsLoadedEventSubscriber;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.function.Consumer;

public class CSContent {
	public static void modConstruction(IEventBus bus, Consumer<Runnable> runLater) {
		ModsLoadedEventSubscriber.compatModLoaded();
		CSDimensions.init();
		CSBlocks.init(bus);
		CSItems.init(bus);
		CSBiomes.init(bus);

		runLater.accept(ModsLoadedEventSubscriber::SolveCompat);
	}
}

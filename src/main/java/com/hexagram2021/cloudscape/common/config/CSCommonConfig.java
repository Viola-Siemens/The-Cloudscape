package com.hexagram2021.cloudscape.common.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class CSCommonConfig {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	private static final ForgeConfigSpec SPEC;

	public static final ForgeConfigSpec.BooleanValue GENERATE_CIRRUS_PLATEAU;
	public static final ForgeConfigSpec.BooleanValue GENERATE_CUMULUS_WASTELAND;
	public static final ForgeConfigSpec.BooleanValue GENERATE_FAIRYLAND_GARDEN;
	public static final ForgeConfigSpec.BooleanValue GENERATE_NIMBUS_JUNGLE;
	public static final ForgeConfigSpec.BooleanValue GENERATE_RAINBOW_FOREST;
	public static final ForgeConfigSpec.BooleanValue GENERATE_SKYLAND_PLAINS;
	public static final ForgeConfigSpec.BooleanValue GENERATE_STRATUS_SWAMP;

	static {
		BUILDER.push("cloudscape-common-config");
			BUILDER.comment("You can determine whether each biome can generate in the world or not.");
			BUILDER.push("biomes-generation");
				GENERATE_CIRRUS_PLATEAU = BUILDER.define("GENERATE_CIRRUS_PLATEAU", true);
				GENERATE_CUMULUS_WASTELAND = BUILDER.define("GENERATE_CUMULUS_WASTELAND", true);
				GENERATE_FAIRYLAND_GARDEN = BUILDER.define("GENERATE_FAIRYLAND_GARDEN", true);
				GENERATE_NIMBUS_JUNGLE = BUILDER.define("GENERATE_NIMBUS_JUNGLE", true);
				GENERATE_RAINBOW_FOREST = BUILDER.define("GENERATE_RAINBOW_FOREST", true);
				GENERATE_SKYLAND_PLAINS = BUILDER.define("GENERATE_SKYLAND_PLAINS", true);
				GENERATE_STRATUS_SWAMP = BUILDER.define("GENERATE_STRATUS_SWAMP", true);
			BUILDER.pop();
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

	public static void registerConfig() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SPEC);
	}
}

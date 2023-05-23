package com.hexagram2021.cloudscape.common.register;

import com.hexagram2021.cloudscape.common.config.CSCommonConfig;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import static com.hexagram2021.cloudscape.Cloudscape.MODID;

public class CSBiomeKeys {
	public static final BiomeKey CIRRUS_PLATEAU = registerKey(CSCommonConfig.GENERATE_CIRRUS_PLATEAU::get, "cirrus_plateau");
	public static final BiomeKey CUMULUS_WASTELAND = registerKey(CSCommonConfig.GENERATE_CUMULUS_WASTELAND::get, "cumulus_wasteland");
	public static final BiomeKey FAIRYLAND_GARDEN = registerKey(CSCommonConfig.GENERATE_FAIRYLAND_GARDEN::get, "fairyland_garden");
	public static final BiomeKey NIMBUS_JUNGLE = registerKey(CSCommonConfig.GENERATE_NIMBUS_JUNGLE::get, "nimbus_jungle");
	public static final BiomeKey RAINBOW_FOREST = registerKey(CSCommonConfig.GENERATE_RAINBOW_FOREST::get, "rainbow_forest");
	public static final BiomeKey SKYLAND_PLAINS = registerKey(CSCommonConfig.GENERATE_SKYLAND_PLAINS::get, "skyland_plains");
	public static final BiomeKey STRATUS_SWAMP = registerKey(CSCommonConfig.GENERATE_STRATUS_SWAMP::get, "stratus_swamp");

	private static BiomeKey registerKey(Supplier<Boolean> generate, String biomeName) {
		return new BiomeKey(ResourceKey.create(ForgeRegistries.Keys.BIOMES, new ResourceLocation(MODID, biomeName)), generate);
	}

	public record BiomeKey(ResourceKey<Biome> key, Supplier<Boolean> generate) {}
}

package com.hexagram2021.cloudscape.common.register;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.hexagram2021.cloudscape.Cloudscape.MODID;

public final class CSBiomes {
	private static final DeferredRegister<Biome> REGISTER = DeferredRegister.create(ForgeRegistries.BIOMES, MODID);

	private CSBiomes() {}

	public static final RegistryObject<Biome> CIRRUS_PLATEAU = registerBiome(CSBiomeKeys.CIRRUS_PLATEAU, CSBiomes::cirrusPlateau);
	public static final RegistryObject<Biome> CUMULUS_WASTELAND = registerBiome(CSBiomeKeys.CUMULUS_WASTELAND, CSBiomes::cumulusWasteland);
	public static final RegistryObject<Biome> FAIRYLAND_GARDEN = registerBiome(CSBiomeKeys.FAIRYLAND_GARDEN, CSBiomes::fairylandGarden);
	public static final RegistryObject<Biome> NIMBUS_JUNGLE = registerBiome(CSBiomeKeys.NIMBUS_JUNGLE, CSBiomes::nimbusJungle);
	public static final RegistryObject<Biome> RAINBOW_FOREST = registerBiome(CSBiomeKeys.RAINBOW_FOREST, CSBiomes::rainbowForest);
	public static final RegistryObject<Biome> SKYLAND_PLAINS = registerBiome(CSBiomeKeys.SKYLAND_PLAINS, CSBiomes::skylandPlains);
	public static final RegistryObject<Biome> STRATUS_SWAMP = registerBiome(CSBiomeKeys.STRATUS_SWAMP, CSBiomes::stratusSwamp);

	//TODO

	private static Biome cirrusPlateau() {
		return null;
	}

	private static Biome cumulusWasteland() {
		return null;
	}

	private static Biome fairylandGarden() {
		return null;
	}

	private static Biome nimbusJungle() {
		return null;
	}

	private static Biome rainbowForest() {
		return null;
	}

	private static Biome skylandPlains() {
		return null;
	}

	private static Biome stratusSwamp() {
		return null;
	}

	public static void init(IEventBus bus) {
		REGISTER.register(bus);

		registerBiomesToDictionary();
	}

	private static RegistryObject<Biome> registerBiome(CSBiomeKeys.BiomeKey biomeKey, Supplier<Biome> make) {
		return REGISTER.register(biomeKey.key().location().getPath(), make);
	}

	public static final BiomeDictionary.Type CLOUDSCAPE = BiomeDictionary.Type.getType("CLOUDSCAPE");

	private static void registerBiomesToDictionary() {
		addBiome(CSBiomeKeys.CIRRUS_PLATEAU.key(),
				BiomeDictionary.Type.SLOPE, BiomeDictionary.Type.PLATEAU, BiomeDictionary.Type.DRY, BiomeDictionary.Type.PLAINS, CLOUDSCAPE);
		addBiome(CSBiomeKeys.CUMULUS_WASTELAND.key(),
				BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SANDY, CLOUDSCAPE);
		addBiome(CSBiomeKeys.FAIRYLAND_GARDEN.key(),
				BiomeDictionary.Type.RARE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.MAGICAL, CLOUDSCAPE);
		addBiome(CSBiomeKeys.NIMBUS_JUNGLE.key(),
				BiomeDictionary.Type.HOT, BiomeDictionary.Type.WET, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.DENSE, CLOUDSCAPE);
		addBiome(CSBiomeKeys.RAINBOW_FOREST.key(),
				BiomeDictionary.Type.FOREST, BiomeDictionary.Type.DENSE, CLOUDSCAPE);
		addBiome(CSBiomeKeys.SKYLAND_PLAINS.key(),
				BiomeDictionary.Type.PLAINS, CLOUDSCAPE);
		addBiome(CSBiomeKeys.STRATUS_SWAMP.key(),
				BiomeDictionary.Type.WET, BiomeDictionary.Type.SWAMP, CLOUDSCAPE);
	}

	private static void addBiome(ResourceKey<Biome> biomeKey, BiomeDictionary.Type... types) {
		BiomeDictionary.addTypes(biomeKey, types);
	}
}

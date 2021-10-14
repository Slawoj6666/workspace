
package net.mcreator.evilspyware.world.biomes;

import net.minecraft.world.gen.trunk.GiantTrunkPlacer;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.foliage.MegaPineFoliagePlacer;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.Biome;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.block.Blocks;

import net.mcreator.evilspyware.EvilSpyWareMod;

import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;

public class DiscordServerBiome {
	private static Biome theBiome;
	private static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> SURFACE_BUILDER = SurfaceBuilder.DEFAULT
			.withConfig(new TernarySurfaceConfig(EvilSpyWareMod.Discord_BLOCK.getDefaultState(), EvilSpyWareMod.Discord_BLOCK.getDefaultState(),
					EvilSpyWareMod.Discord_BLOCK.getDefaultState()));
	public static void init() {
		Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, EvilSpyWareMod.DiscordServer_KEY.getValue(), SURFACE_BUILDER);
		BiomeEffects effects = new BiomeEffects.Builder().fogColor(12638463).waterColor(-16776961).waterFogColor(-16776961).skyColor(12638463)
				.grassColor(-16776961).foliageColor(-16776961).build();
		GenerationSettings.Builder genSettingsBuilder = new GenerationSettings.Builder();
		genSettingsBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
				Feature.TREE
						.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.SPRUCE_LOG.getDefaultState()),
								new SimpleBlockStateProvider(Blocks.SPRUCE_LEAVES.getDefaultState()),
								new MegaPineFoliagePlacer(UniformIntDistribution.of(0), UniformIntDistribution.of(0),
										UniformIntDistribution.of(13, 4)),
								new GiantTrunkPlacer(13, 2, 14), new TwoLayersFeatureSize(1, 1, 2)).build()))
						.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
						.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(1, 0.1f, 1))));
		DefaultBiomeFeatures.addLandCarvers(genSettingsBuilder);
		DefaultBiomeFeatures.addDefaultOres(genSettingsBuilder);
		DefaultBiomeFeatures.addDefaultFlowers(genSettingsBuilder);
		DefaultBiomeFeatures.addDefaultGrass(genSettingsBuilder);
		genSettingsBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.TREES_GIANT_SPRUCE);
		genSettingsBuilder.surfaceBuilder(SURFACE_BUILDER);
		SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
		Biome.Builder biomeBuilder = new Biome.Builder();
		biomeBuilder.effects(effects);
		biomeBuilder.generationSettings(genSettingsBuilder.build());
		biomeBuilder.spawnSettings(spawnBuilder.build());
		biomeBuilder.temperatureModifier(Biome.TemperatureModifier.NONE);
		biomeBuilder.temperature(0.5F);
		biomeBuilder.downfall(0.5F);
		biomeBuilder.depth(0.1F);
		biomeBuilder.scale(0.2F);
		biomeBuilder.category(Biome.Category.NONE);
		biomeBuilder.precipitation(Biome.Precipitation.RAIN);
		theBiome = biomeBuilder.build();
		Registry.register(BuiltinRegistries.BIOME, EvilSpyWareMod.DiscordServer_KEY.getValue(), theBiome);
		OverworldBiomes.addContinentalBiome(EvilSpyWareMod.DiscordServer_KEY, OverworldClimate.TEMPERATE, 10d);
	}
}

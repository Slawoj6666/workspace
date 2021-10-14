/*
 *    MCreator note:
 *
 *    If you lock base mod element files, you can edit this file and the proxy files
 *    and they won't get overwritten. If you change your mod package or modid, you
 *    need to apply these changes to this file MANUALLY.
 *
 *
 *    If you do not lock base mod element files in Workspace settings, this file
 *    will be REGENERATED on each build.
 *
 */
package net.mcreator.evilspyware;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraft.world.biome.Biome;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.Block;

import net.mcreator.evilspyware.world.biomes.DiscordServerBiome;
import net.mcreator.evilspyware.procedures.UnobtaniumswordBlockDestroyedWithToolProcedure;
import net.mcreator.evilspyware.item.UnobtaniumswordTool;
import net.mcreator.evilspyware.item.UnobtaniumpickaxeTool;
import net.mcreator.evilspyware.item.UnobtaniumarmorArmorMaterial;
import net.mcreator.evilspyware.item.UnobtaniumOreItem;
import net.mcreator.evilspyware.item.GunRangedItem;
import net.mcreator.evilspyware.item.BulletItem;
import net.mcreator.evilspyware.entity.DdEntity;
import net.mcreator.evilspyware.block.UnobtaniumOreBlockBlock;
import net.mcreator.evilspyware.block.DiscordBlock;
import net.mcreator.evilspyware.block.CATBlock;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.api.ModInitializer;

public class EvilSpyWareMod implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final Item UnobtaniumOre_ITEM = Registry.register(Registry.ITEM, id("unobtanium_ingots"), new UnobtaniumOreItem());
	public static final Item Bullet_ITEM = Registry.register(Registry.ITEM, id("bullet"), new BulletItem());
	public static final Item Gun_ITEM = Registry.register(Registry.ITEM, id("gun"), new GunRangedItem());
	public static final Block UnobtaniumOreBlock_BLOCK = Registry.register(Registry.BLOCK, id("unobtanium_ore_block"), new UnobtaniumOreBlockBlock());
	public static final BlockItem UnobtaniumOreBlock_ITEM = Registry.register(Registry.ITEM, id("unobtanium_ore_block"),
			new BlockItem(UnobtaniumOreBlock_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
	public static final Block Discord_BLOCK = Registry.register(Registry.BLOCK, id("discord"), new DiscordBlock());
	public static final BlockItem Discord_ITEM = Registry.register(Registry.ITEM, id("discord"),
			new BlockItem(Discord_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
	public static final Block CAT_BLOCK = Registry.register(Registry.BLOCK, id("cat"), new CATBlock());
	public static final BlockItem CAT_ITEM = Registry.register(Registry.ITEM, id("cat"),
			new BlockItem(CAT_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
	public static final Item Unobtaniumarmor_HELMET = Registry.register(Registry.ITEM, id("unobtaniumarmor_helmet"),
			UnobtaniumarmorArmorMaterial.HELMET);
	public static final Item Unobtaniumarmor_CHESTPLATE = Registry.register(Registry.ITEM, id("unobtaniumarmor_chestplate"),
			UnobtaniumarmorArmorMaterial.CHESTPLATE);
	public static final Item Unobtaniumarmor_LEGGINGS = Registry.register(Registry.ITEM, id("unobtaniumarmor_leggings"),
			UnobtaniumarmorArmorMaterial.LEGGINGS);
	public static final Item Unobtaniumarmor_BOOTS = Registry.register(Registry.ITEM, id("unobtaniumarmor_boots"),
			UnobtaniumarmorArmorMaterial.BOOTS);
	public static final Item Unobtaniumsword_ITEM = Registry.register(Registry.ITEM, id("unobtaniumsword"), UnobtaniumswordTool.INSTANCE);
	public static final Item Unobtaniumpickaxe_ITEM = Registry.register(Registry.ITEM, id("unobtaniumpickaxe"), UnobtaniumpickaxeTool.INSTANCE);
	public static final RegistryKey<Biome> DiscordServer_KEY = RegistryKey.of(Registry.BIOME_KEY, id("discord_server"));
	@Override
	public void onInitialize() {
		LOGGER.info("Initializing EvilSpyWareMod");
		new UnobtaniumswordBlockDestroyedWithToolProcedure();
		DiscordServerBiome.init();
		DdEntity.init();
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
		});
	}

	public static final Identifier id(String s) {
		return new Identifier("evil_spy_ware", s);
	}
}

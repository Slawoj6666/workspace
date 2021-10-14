
package net.mcreator.evilspyware.item;

import net.minecraft.recipe.Ingredient;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;

import net.mcreator.evilspyware.EvilSpyWareMod;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

public class UnobtaniumpickaxeTool {
	public static final ToolMaterial UNOBTANIUMPICKAXE_TOOL_MATERIAL = new ToolMaterial() {
		@Override
		public int getDurability() {
			return 10000;
		}

		@Override
		public float getMiningSpeedMultiplier() {
			return 13F;
		}

		@Override
		public float getAttackDamage() {
			return 2F;
		}

		@Override
		public int getMiningLevel() {
			return 6;
		}

		@Override
		public int getEnchantability() {
			return 120;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return Ingredient.ofItems(EvilSpyWareMod.UnobtaniumOre_ITEM);
		}
	};
	public static final Item INSTANCE = new PickaxeItem(UNOBTANIUMPICKAXE_TOOL_MATERIAL, 0, (float) -1.6,
			(new FabricItemSettings().group(ItemGroup.TOOLS).fireproof())) {
	};
}


package net.mcreator.evilspyware.item;

import net.minecraft.recipe.Ingredient;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;

import net.mcreator.evilspyware.EvilSpyWareMod;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

public class UnobtaniumswordTool {
	public static final ToolMaterial UNOBTANIUMSWORD_TOOL_MATERIAL = new ToolMaterial() {
		@Override
		public int getDurability() {
			return 10000;
		}

		@Override
		public float getMiningSpeedMultiplier() {
			return 4F;
		}

		@Override
		public float getAttackDamage() {
			return 13F;
		}

		@Override
		public int getMiningLevel() {
			return 1;
		}

		@Override
		public int getEnchantability() {
			return 2;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return Ingredient.ofItems(EvilSpyWareMod.UnobtaniumOre_ITEM);
		}
	};
	public static final Item INSTANCE = new SwordItem(UNOBTANIUMSWORD_TOOL_MATERIAL, 0, (float) -2,
			(new FabricItemSettings().group(ItemGroup.COMBAT).fireproof())) {
		@Override
		public boolean hasRecipeRemainder() {
			return true;
		}

		@Override
		public boolean canRepair(ItemStack stack, ItemStack ingredient) {
			return false;
		}
	};
}

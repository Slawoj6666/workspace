
package net.mcreator.evilspyware.item;

import net.minecraft.util.Identifier;
import net.minecraft.sound.SoundEvent;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.entity.EquipmentSlot;

import net.mcreator.evilspyware.EvilSpyWareMod;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class UnobtaniumarmorArmorMaterial implements ArmorMaterial {
	private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
	private static final int[] PROTECTION_VALUES = new int[]{11, 20, 15, 10};
	public static final Item HELMET = new ArmorItem(new UnobtaniumarmorArmorMaterial(), EquipmentSlot.HEAD,
			new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item CHESTPLATE = new ArmorItem(new UnobtaniumarmorArmorMaterial(), EquipmentSlot.CHEST,
			new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item LEGGINGS = new ArmorItem(new UnobtaniumarmorArmorMaterial(), EquipmentSlot.LEGS,
			new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item BOOTS = new ArmorItem(new UnobtaniumarmorArmorMaterial(), EquipmentSlot.FEET,
			new Item.Settings().group(ItemGroup.COMBAT));
	public int getDurability(EquipmentSlot equipmentSlot_1) {
		return BASE_DURABILITY[equipmentSlot_1.getEntitySlotId()] * 100;
	}

	public int getProtectionAmount(EquipmentSlot equipmentSlot_1) {
		return PROTECTION_VALUES[equipmentSlot_1.getEntitySlotId()];
	}

	public int getEnchantability() {
		return 90;
	}

	public SoundEvent getEquipSound() {
		return new SoundEvent(new Identifier(""));
	}

	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(EvilSpyWareMod.UnobtaniumOre_ITEM);
	}

	@Environment(EnvType.CLIENT)
	public String getName() {
		return "diamond_";
	}

	public float getToughness() {
		return 5F;
	}

	public float getKnockbackResistance() {
		return 1f;
	}
}

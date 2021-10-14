
package net.mcreator.evilspyware.item;

import net.minecraft.util.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class UnobtaniumOreItem extends Item {
	public UnobtaniumOreItem() {
		super(new FabricItemSettings().group(ItemGroup.MISC).maxCount(64).rarity(Rarity.COMMON));
	}

	@Override
	public int getMaxUseTime(ItemStack itemstack) {
		return 0;
	}

	@Override
	public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
		return (float) (1F);
	}

	@Environment(EnvType.CLIENT)
	@Override
	public boolean hasGlint(ItemStack stack) {
		return true;
	}

	@Override
	public int getEnchantability() {
		return 0;
	}
}

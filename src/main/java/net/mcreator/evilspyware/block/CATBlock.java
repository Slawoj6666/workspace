
package net.mcreator.evilspyware.block;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.loot.context.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Material;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.evilspyware.EvilSpyWareMod;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

import java.util.List;
import java.util.Collections;

public class CATBlock extends Block {
	public CATBlock() {
		super(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.GRAVEL).strength(1f, 10f).luminance(0));
	}

	@Override
	public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
		List<ItemStack> dropsOriginal = super.getDroppedStacks(state, builder);
		if (!dropsOriginal.isEmpty())
			return dropsOriginal;
		return Collections.singletonList(new ItemStack(EvilSpyWareMod.UnobtaniumOre_ITEM));
	}
}

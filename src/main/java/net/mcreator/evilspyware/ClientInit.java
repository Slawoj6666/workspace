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

import net.minecraft.util.registry.Registry;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.client.render.RenderLayer;

import net.mcreator.evilspyware.entity.render.DdEntityRenderer;
import net.mcreator.evilspyware.client.particle.UnobtaniumparticleParticle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ClientModInitializer;

@Environment(EnvType.CLIENT)
public class ClientInit implements ClientModInitializer {
	public static final DefaultParticleType Unobtaniumparticle_PARTICLE = Registry.register(Registry.PARTICLE_TYPE,
			"evil_spy_ware:unobtaniumparticle", FabricParticleTypes.simple(false));
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(EvilSpyWareMod.UnobtaniumOreBlock_BLOCK, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(EvilSpyWareMod.Discord_BLOCK, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(EvilSpyWareMod.CAT_BLOCK, RenderLayer.getCutoutMipped());
		DdEntityRenderer.clientInit();
		ParticleFactoryRegistry.getInstance().register(Unobtaniumparticle_PARTICLE, UnobtaniumparticleParticle.CustomParticleFactory::new);
		HudRenderCallback.EVENT.register((matrices, tickDelta) -> {
		});
		ClientTickEvents.END_CLIENT_TICK.register((client) -> {
		});
	}
}

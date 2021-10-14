
package net.mcreator.evilspyware.entity.render;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.Identifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.model.ModelPart;

import net.mcreator.evilspyware.entity.DdEntity;

import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

public class DdEntityRenderer extends MobEntityRenderer<DdEntity, DdEntityRenderer.Modelmodel2> {
	public DdEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
		super(entityRenderDispatcher, new Modelmodel2(), 0.5f);
	}

	public static void clientInit() {
		EntityRendererRegistry.INSTANCE.register(DdEntity.ENTITY, (dispatcher, context) -> new DdEntityRenderer(dispatcher));
	}

	@Override
	public Identifier getTexture(DdEntity entity) {
		return new Identifier("evil_spy_ware:textures/texture.png");
	}
	// Made with Blockbench 3.9.3
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelmodel2 extends EntityModel<DdEntity> {
		private final ModelPart body;
		private final ModelPart left_leg;
		private final ModelPart right_leg;
		private final ModelPart head;
		private final ModelPart bb_main;
		public Modelmodel2() {
			textureWidth = 32;
			textureHeight = 32;
			body = new ModelPart(this);
			body.setPivot(0.0F, 24.0F, 0.0F);
			body.setTextureOffset(0, 0).addCuboid(-2.0F, -8.0F, -3.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);
			left_leg = new ModelPart(this);
			left_leg.setPivot(0.0F, 24.0F, 0.0F);
			left_leg.setTextureOffset(3, 2).addCuboid(1.0F, -3.0F, -3.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
			right_leg = new ModelPart(this);
			right_leg.setPivot(0.0F, 24.0F, 0.0F);
			right_leg.setTextureOffset(3, 2).addCuboid(-2.0F, -3.0F, -3.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
			head = new ModelPart(this);
			head.setPivot(0.0F, 24.0F, 0.0F);
			bb_main = new ModelPart(this);
			bb_main.setPivot(0.0F, 24.0F, 0.0F);
			bb_main.setTextureOffset(0, 9).addCuboid(-2.0F, -11.0F, -3.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			body.render(matrixStack, buffer, packedLight, packedOverlay);
			left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
			right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
			head.render(matrixStack, buffer, packedLight, packedOverlay);
			bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
			modelRenderer.pitch = x;
			modelRenderer.yaw = y;
			modelRenderer.roll = z;
		}

		public void setAngles(DdEntity e, float f, float f1, float f2, float f3, float f4) {
			this.head.yaw = f3 / (180F / (float) Math.PI);
			this.head.pitch = f4 / (180F / (float) Math.PI);
			this.left_leg.pitch = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.right_leg.pitch = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		}
	}
}

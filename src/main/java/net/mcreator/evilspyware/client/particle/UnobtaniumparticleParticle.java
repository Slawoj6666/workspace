
package net.mcreator.evilspyware.client.particle;

import net.minecraft.particle.DefaultParticleType;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.Particle;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

@Environment(EnvType.CLIENT)
public class UnobtaniumparticleParticle extends SpriteBillboardParticle {
	private final SpriteProvider spriteProvider;
	protected UnobtaniumparticleParticle(ClientWorld clientWorld, double x, double y, double z, double vx, double vy, double vz,
			SpriteProvider spriteProvider) {
		super(clientWorld, x, y, z, vx, vy, vz);
		this.spriteProvider = spriteProvider;
		this.setBoundingBoxSpacing((float) 0.2, (float) 0.2);
		this.scale *= (float) 1;
		this.maxAge = 7;
		this.gravityStrength = (float) 0;
		this.collidesWithWorld = true;
		this.velocityX = vx * 1;
		this.velocityY = vy * 1;
		this.velocityZ = vz * 1;
		this.setSprite(spriteProvider);
	}

	@Override
	public ParticleTextureSheet getType() {
		return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void tick() {
		super.tick();
	}
	@Environment(EnvType.CLIENT)
	public static class CustomParticleFactory implements ParticleFactory<DefaultParticleType> {
		private final SpriteProvider spriteProvider;
		public CustomParticleFactory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}

		public Particle createParticle(DefaultParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed,
				double zSpeed) {
			return new UnobtaniumparticleParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteProvider);
		}
	}
}

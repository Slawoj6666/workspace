
package net.mcreator.evilspyware.item;

import net.minecraft.world.World;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.UseAction;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.Hand;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundCategory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.Entity;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.MinecraftClient;

import net.mcreator.evilspyware.EvilSpyWareMod;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

import java.util.Random;

public class GunRangedItem extends Item {
	public static final EntityType<CustomProjectileEntity> ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE,
			new Identifier("evil_spy_ware", "gun_projectile"),
			FabricEntityTypeBuilder.<CustomProjectileEntity>create(SpawnGroup.MISC, CustomProjectileEntity::new)
					.dimensions(EntityDimensions.fixed(0.5F, 0.5F)).build());
	public GunRangedItem() {
		super(new Item.Settings().group(ItemGroup.COMBAT).maxDamage(100000));
		if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
			EntityRendererRegistry.INSTANCE.register(ENTITY_TYPE, (dispatcher, context) -> {
				return new FlyingItemEntityRenderer(dispatcher, MinecraftClient.getInstance().getItemRenderer());
			});
		}
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BOW;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		user.setCurrentHand(hand);
		return TypedActionResult.success(user.getStackInHand(hand));
	}

	public int getMaxUseTime(ItemStack stack) {
		return 72000;
	}

	@Override
	public void usageTick(World world, LivingEntity entityLiving, ItemStack itemstack, int remainingUseTicks) {
		if (!world.isClient() && entityLiving instanceof ServerPlayerEntity) {
			ServerPlayerEntity entity = (ServerPlayerEntity) entityLiving;
			double x = entity.getX();
			double y = entity.getY();
			double z = entity.getZ();
			if (true) {
				ItemStack stack = RangedWeaponItem.getHeldProjectile(entity, e -> e.getItem() == EvilSpyWareMod.Bullet_ITEM);
				if (stack == ItemStack.EMPTY) {
					for (int i = 0; i < entity.inventory.main.size(); i++) {
						ItemStack teststack = entity.inventory.main.get(i);
						if (teststack != null && teststack.getItem() == EvilSpyWareMod.Bullet_ITEM) {
							stack = teststack;
							break;
						}
					}
				}
				if (entity.abilities.creativeMode || stack != ItemStack.EMPTY) {
					CustomProjectileEntity entityarrow = shoot(world, entity, new Random(31100L), 5f, 50, 5);
					itemstack.damage(1, entity, e -> e.sendToolBreakStatus(entity.getActiveHand()));
					if (entity.abilities.creativeMode) {
						entityarrow.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
					} else {
						if (new ItemStack(EvilSpyWareMod.Bullet_ITEM).isDamageable()) {
							if (stack.damage(1, new Random(31100L), entity)) {
								stack.decrement(1);
								stack.setDamage(0);
								if (stack.isEmpty())
									entity.inventory.removeOne(stack);
							}
						} else {
							stack.decrement(1);
							if (stack.isEmpty())
								entity.inventory.removeOne(stack);
						}
					}
				}
				entity.stopUsingItem();
			}
		}
	}
	public static class CustomProjectileEntity extends PersistentProjectileEntity implements FlyingItemEntity {
		public CustomProjectileEntity(EntityType<? extends CustomProjectileEntity> type, World world) {
			super(type, world);
		}

		public CustomProjectileEntity(EntityType<? extends CustomProjectileEntity> type, double x, double y, double z, World world) {
			super(type, x, y, z, world);
		}

		public CustomProjectileEntity(EntityType<? extends CustomProjectileEntity> type, LivingEntity entity, World world) {
			super(type, entity, world);
		}

		@Override
		@Environment(EnvType.CLIENT)
		public ItemStack getStack() {
			return new ItemStack(EvilSpyWareMod.Bullet_ITEM);
		}

		@Override
		protected ItemStack asItemStack() {
			return new ItemStack(EvilSpyWareMod.Bullet_ITEM);
		}

		@Override
		protected void onHit(LivingEntity entity) {
			super.onHit(entity);
			entity.setStuckArrowCount(entity.getStuckArrowCount() - 1);
		}

		@Override
		public void tick() {
			super.tick();
			double x = this.getX();
			double y = this.getY();
			double z = this.getZ();
			World world = this.world;
			Entity entity = this.getOwner();
			Entity imediatesourceentity = this;
			if (this.inGround) {
				this.remove();
			}
		}
	}
	public static CustomProjectileEntity shoot(World world, LivingEntity entity, Random random, float power, double damage, int knockback) {
		CustomProjectileEntity arrow = new CustomProjectileEntity(ENTITY_TYPE, entity, world);
		arrow.setVelocity(entity.getRotationVector().x, entity.getRotationVector().y, entity.getRotationVector().z, power * 2, 0);
		arrow.setSilent(true);
		arrow.setCritical(true);
		arrow.setDamage(damage);
		arrow.setPunch(knockback);
		world.spawnEntity(arrow);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		world.playSound((PlayerEntity) null, (double) x, (double) y, (double) z, new SoundEvent(new Identifier("entity.arrow.shoot")),
				SoundCategory.PLAYERS, 1, 1F / (random.nextFloat() * 0.5F + 1) + (power / 2));
		return arrow;
	}

	public static CustomProjectileEntity shoot(LivingEntity entity, LivingEntity target) {
		CustomProjectileEntity arrow = new CustomProjectileEntity(ENTITY_TYPE, entity, entity.world);
		double d0 = target.getX() + (double) target.getStandingEyeHeight() - 1.1;
		double d1 = target.getY() - entity.getX();
		double d3 = target.getZ() - entity.getZ();
		arrow.setVelocity(d1, d0 - arrow.getY() + (double) MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 5f * 2, 12.0F);
		arrow.setSilent(true);
		arrow.setDamage(50);
		arrow.setPunch(5);
		arrow.setCritical(true);
		entity.world.spawnEntity(arrow);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		entity.world.playSound((PlayerEntity) null, (double) x, (double) y, (double) z, new SoundEvent(new Identifier("entity.arrow.shoot")),
				SoundCategory.PLAYERS, 1, 1F / (new Random().nextFloat() * 0.5F + 1));
		return arrow;
	}
}

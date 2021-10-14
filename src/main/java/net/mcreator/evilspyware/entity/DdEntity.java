
package net.mcreator.evilspyware.entity;

import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.World;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.sound.SoundEvent;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityDimensions;

import net.mcreator.evilspyware.EvilSpyWareMod;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;

@SuppressWarnings("deprecation")
public class DdEntity extends HostileEntity {
	public static final EntityType<DdEntity> ENTITY = Registry.register(Registry.ENTITY_TYPE, EvilSpyWareMod.id("dd"),
			FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, DdEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.8f)).trackRangeBlocks(64)
					.forceTrackedVelocityUpdates(true).trackedUpdateRate(3).build());
	protected DdEntity(EntityType<? extends DdEntity> entityType, World world) {
		super(entityType, world);
		this.setAiDisabled(false);
		this.experiencePoints = 200;
	}

	public static void init() {
		FabricDefaultAttributeRegistry.register(ENTITY,
				DdEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
						.add(EntityAttributes.GENERIC_ARMOR, 0.1).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6));
		Registry.register(Registry.ITEM, EvilSpyWareMod.id("dd_spawn_egg"),
				new SpawnEggItem(ENTITY, -1, -1, new FabricItemSettings().group(ItemGroup.MISC)));
		BiomeModifications.create(new Identifier("evil_spy_ware", "dd_entity_spawn")).add(ModificationPhase.ADDITIONS, BiomeSelectors.all(),
				ctx -> ctx.getSpawnSettings().addSpawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(ENTITY, 20, 4, 4)));
	}

	@Override
	protected void initGoals() {
		super.initGoals();
		this.goalSelector.add(1, new MeleeAttackGoal(this, 1.2, false));
		this.goalSelector.add(2, new WanderAroundGoal(this, 1));
		this.targetSelector.add(3, new RevengeGoal(this));
		this.goalSelector.add(4, new LookAroundGoal(this));
		this.goalSelector.add(5, new SwimGoal(this));
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return new SoundEvent(new Identifier("entity.generic.hurt"));
	}

	@Override
	protected SoundEvent getDeathSound() {
		return new SoundEvent(new Identifier("entity.generic.death"));
	}
}

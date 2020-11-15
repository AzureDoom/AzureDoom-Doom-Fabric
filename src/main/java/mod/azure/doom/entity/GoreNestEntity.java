package mod.azure.doom.entity;

import java.util.Random;

import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.annotation.Nullable;
import mod.azure.doom.util.registry.MobEntityRegister;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GoreNestEntity extends DemonEntity implements IAnimatable {

	public GoreNestEntity(EntityType<? extends GoreNestEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}

	private AnimationFactory factory = new AnimationFactory(this);

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (this.dead) {
			if (world.isClient) {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("death", true));
				return PlayState.CONTINUE;
			}
		}
		event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<GoreNestEntity>(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

	public static boolean spawning(EntityType<GoreNestEntity> p_223337_0_, World p_223337_1_, SpawnReason reason,
			BlockPos p_223337_3_, Random p_223337_4_) {
		return p_223337_1_.getDifficulty() != Difficulty.PEACEFUL;
	}

	@Override
	protected void updatePostDeath() {
		++this.deathTime;
		if (this.deathTime == 80) {
			this.remove();
			HellknightEntity fireballentity = MobEntityRegister.HELLKNIGHT.create(world);
			fireballentity.refreshPositionAndAngles(this.getX() + 2.0D, this.getY() + 0.5D, this.getZ() + 2.0D, 0, 0);
			world.spawnEntity(fireballentity);

			PossessedScientistEntity fireballentity1 = MobEntityRegister.POSSESSEDSCIENTIST.create(world);
			fireballentity1.refreshPositionAndAngles(this.getX() + -2.0D, this.getY() + 0.5D, this.getZ() + -2.0D, 0,
					0);
			world.spawnEntity(fireballentity1);

			ImpEntity fireballentity11 = MobEntityRegister.IMP.create(world);
			fireballentity11.refreshPositionAndAngles(this.getX() + 1.0D, this.getY() + 0.5D, this.getZ() + 1.0D, 0, 0);
			world.spawnEntity(fireballentity11);

			NightmareImpEntity fireballentity111 = MobEntityRegister.NIGHTMARE_IMP.create(world);
			fireballentity111.refreshPositionAndAngles(this.getX() + -1.0D, this.getY() + 0.5D, this.getZ() + -1.0D, 0,
					0);
			world.spawnEntity(fireballentity111);
		}

	}

	public static DefaultAttributeContainer.Builder createMobAttributes() {
		return LivingEntity.createLivingAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 50.0D)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15D).add(EntityAttributes.GENERIC_MAX_HEALTH, 5.0D)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.0D).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.0D);
	}

	@Nullable
	@Override
	public EntityData initialize(ServerWorldAccess serverWorldAccess, LocalDifficulty difficulty,
			SpawnReason spawnReason, EntityData entityData, CompoundTag entityTag) {
		entityData = super.initialize(serverWorldAccess, difficulty, spawnReason, entityData, entityTag);
		return entityData;
	}

	@Override
	public void tickMovement() {
		if (this.world.isClient) {
			this.world.addParticle(DustParticleEffect.RED, this.getParticleX(0.5D), this.getRandomBodyY() - 0.25D,
					this.getParticleZ(0.5D), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(),
					(this.random.nextDouble() - 0.5D) * 2.0D);
			this.world.addParticle(ParticleTypes.SOUL, this.getParticleX(0.2D), this.getRandomBodyY(),
					this.getParticleZ(0.5D), 0.0D, 0D, 0D);
		}
		super.tickMovement();
	}

	protected boolean shouldDrown() {
		return false;
	}

	protected boolean shouldBurnInDay() {
		return false;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public boolean shouldRender(double distance) {
		return true;
	}
}
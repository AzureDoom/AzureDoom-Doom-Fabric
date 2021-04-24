package mod.azure.doom.entity.tierboss;

import java.util.List;
import java.util.Random;
import java.util.SplittableRandom;

import org.jetbrains.annotations.Nullable;

import mod.azure.doom.entity.DemonEntity;
import mod.azure.doom.entity.projectiles.entity.ArchvileFiring;
import mod.azure.doom.util.ModSoundEvents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class IconofsinEntity extends DemonEntity implements IAnimatable {

	private static final TrackedData<Boolean> SHOOTING = DataTracker.registerData(IconofsinEntity.class,
			TrackedDataHandlerRegistry.BOOLEAN);

	private final ServerBossBar bossBar = (ServerBossBar) (new ServerBossBar(this.getDisplayName(),
			BossBar.Color.PURPLE, BossBar.Style.PROGRESS)).setDarkenSky(true).setThickenFog(true);

	public IconofsinEntity(EntityType<IconofsinEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}

	public static boolean spawning(EntityType<IconofsinEntity> p_223337_0_, World p_223337_1_, SpawnReason reason,
			BlockPos p_223337_3_, Random p_223337_4_) {
		return p_223337_1_.getDifficulty() != Difficulty.PEACEFUL;
	}

	private AnimationFactory factory = new AnimationFactory(this);

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (event.isMoving()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("walking", true));
			return PlayState.CONTINUE;
		}
		if (this.dataTracker.get(SHOOTING) && !(this.dead || this.getHealth() < 0.01 || this.isDead())) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("summoned", false));
			return PlayState.CONTINUE;
		}
		if ((this.dead || this.getHealth() < 0.01 || this.isDead())) {
			if (world.isClient) {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("death", false));
				return PlayState.CONTINUE;
			}
		}
		if (event.isMoving() && this.getHealth() < 500.0D) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("walking_nohelmet", true));
			return PlayState.CONTINUE;
		}
		if (this.getHealth() < 500.0D) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("idle_nohelmet", true));
			return PlayState.CONTINUE;
		}
		event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<IconofsinEntity>(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

	@Override
	protected void updatePostDeath() {
		++this.deathTime;
		if (this.deathTime == 50) {
			this.remove();
			if (world.isClient) {
			}
		}
	}

	@Environment(EnvType.CLIENT)
	public boolean isShooting() {
		return (Boolean) this.dataTracker.get(SHOOTING);
	}

	public void setShooting(boolean shooting) {
		this.dataTracker.set(SHOOTING, shooting);
	}

	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(SHOOTING, false);
	}

	@Override
	public boolean handleFallDamage(float fallDistance, float damageMultiplier) {
		return false;
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	protected void tickCramming() {
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.add(8, new LookAroundGoal(this));
		this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.8D));
		this.initCustomGoals();
	}

	protected void initCustomGoals() {
		this.goalSelector.add(2, new IconofsinEntity.ShootFireballGoal(this));
		this.targetSelector.add(2, new FollowTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.add(2, new FollowTargetGoal<>(this, MerchantEntity.class, true));
	}

	static class ShootFireballGoal extends Goal {
		private final IconofsinEntity parentEntity;
		public int cooldown;

		public ShootFireballGoal(IconofsinEntity parentEntity) {
			this.parentEntity = parentEntity;
		}

		public boolean canStart() {
			return this.parentEntity.getTarget() != null;
		}

		public void start() {
			this.cooldown = 0;
		}

		public void resetTask() {
			this.parentEntity.setShooting(false);
		}

		public void tick() {
			LivingEntity livingEntity = this.parentEntity.getTarget();
			if (livingEntity.squaredDistanceTo(this.parentEntity) < 4096.0D && this.parentEntity.canSee(livingEntity)) {
				++this.cooldown;
				Random rand = new Random();
				double d = Math.min(livingEntity.getY(), parentEntity.getY());
				double e1 = Math.max(livingEntity.getY(), parentEntity.getY()) + 1.0D;
				float f2 = (float) MathHelper.atan2(livingEntity.getZ() - parentEntity.getZ(),
						livingEntity.getX() - parentEntity.getX());
				int j;
				if (this.cooldown == 35) {
					if (parentEntity.distanceTo(livingEntity) > 11.0D) {
						float h2;

						SplittableRandom random = new SplittableRandom();
						boolean r = random.nextInt(1, 101) <= 20;
						if (r) {
							for (j = 15; j < 55; ++j) {
								h2 = f2 + (float) j * 3.1415927F * 0.4F;
								parentEntity.spawnFlames(
										parentEntity.getX() + (double) MathHelper.cos(h2) * rand.nextDouble() * 11.5D,
										parentEntity.getZ() + (double) MathHelper.sin(h2) * rand.nextDouble() * 11.5D,
										d, e1, h2, 0);
								parentEntity.spawnFlames(
										parentEntity.getX() + (double) MathHelper.cos(h2) * rand.nextDouble() * 11.5D,
										parentEntity.getZ() + (double) MathHelper.sin(h2) * rand.nextDouble() * 11.5D,
										d, e1, h2, 0);
								parentEntity.spawnFlames(
										parentEntity.getX() + (double) MathHelper.cos(h2) * rand.nextDouble() * 11.5D,
										parentEntity.getZ() + (double) MathHelper.sin(h2) * rand.nextDouble() * 11.5D,
										d, e1, h2, 0);
								parentEntity.spawnFlames(
										parentEntity.getX() + (double) MathHelper.cos(h2) * rand.nextDouble() * 11.5D,
										parentEntity.getZ() + (double) MathHelper.sin(h2) * rand.nextDouble() * 11.5D,
										d, e1, h2, 0);
								parentEntity.spawnFlames(
										parentEntity.getX() + (double) MathHelper.cos(h2) * rand.nextDouble() * 11.5D,
										parentEntity.getZ() + (double) MathHelper.sin(h2) * rand.nextDouble() * 11.5D,
										d, e1, h2, 0);
							}
						} else {
							this.parentEntity.doDamage();
						}
						this.parentEntity.setAttacking(this.cooldown >= 15);
						this.cooldown = -35;
					}
				}
			} else if (this.cooldown > 0) {
				--this.cooldown;
			}
		}
	}

	public void doDamage() {
		float q = 4.0F;
		int k = MathHelper.floor(this.getX() - (double) q - 1.0D);
		int l = MathHelper.floor(this.getX() + (double) q + 1.0D);
		int t = MathHelper.floor(this.getY() - (double) q - 1.0D);
		int u = MathHelper.floor(this.getY() + (double) q + 1.0D);
		int v = MathHelper.floor(this.getZ() - (double) q - 1.0D);
		int w = MathHelper.floor(this.getZ() + (double) q + 1.0D);
		List<Entity> list = this.world.getOtherEntities(this,
				new Box((double) k, (double) t, (double) v, (double) l, (double) u, (double) w));
		Vec3d vec3d = new Vec3d(this.getX(), this.getY(), this.getZ());
		for (int x = 0; x < list.size(); ++x) {
			Entity entity = (Entity) list.get(x);
			double y = (double) (MathHelper.sqrt(entity.squaredDistanceTo(vec3d)) / q);
			if (y <= 1.0D) {
				if (entity instanceof LivingEntity) {
					entity.damage(DamageSource.magic(this, this.getTarget()), 7);
				}
			}
		}
	}

	public void spawnFlames(double x, double z, double maxY, double y, float yaw, int warmup) {
		BlockPos blockPos = new BlockPos(x, y, z);
		boolean bl = false;
		double d = -0.75D;
		do {
			BlockPos blockPos2 = blockPos.down();
			BlockState blockState = this.world.getBlockState(blockPos2);
			if (blockState.isSideSolidFullSquare(this.world, blockPos2, Direction.UP)) {
				if (!this.world.isAir(blockPos)) {
					BlockState blockState2 = this.world.getBlockState(blockPos);
					VoxelShape voxelShape = blockState2.getCollisionShape(this.world, blockPos);
					if (!voxelShape.isEmpty()) {
						d = voxelShape.getMax(Direction.Axis.Y);
					}
				}
				bl = true;
				break;
			}
			blockPos = blockPos.down();
		} while (blockPos.getY() >= MathHelper.floor(maxY) - 1);

		if (bl) {
			ArchvileFiring fang = new ArchvileFiring(this.world, x, (double) blockPos.getY() + d, z, yaw, warmup, this);
			fang.setFireTicks(age);
			fang.isInvisible();
			this.world.spawnEntity(fang);
		}
	}

	public static DefaultAttributeContainer.Builder createMobAttributes() {
		return LivingEntity.createLivingAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100.0D)
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 1000.0D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 30.0D)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D)
				.add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0D)
				.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1000.0D);
	}

	@Override
	protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
		return 18.70F;
	}

	protected boolean shouldDrown() {
		return false;
	}

	protected boolean shouldBurnInDay() {
		return false;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSoundEvents.ICON_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return ModSoundEvents.ICON_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSoundEvents.ICON_DEATH;
	}

	protected SoundEvent getStepSound() {
		return SoundEvents.ENTITY_SKELETON_STEP;
	}

	public void onStartedTrackingBy(ServerPlayerEntity player) {
		super.onStartedTrackingBy(player);
		this.bossBar.addPlayer(player);
	}

	public void onStoppedTrackingBy(ServerPlayerEntity player) {
		super.onStoppedTrackingBy(player);
		this.bossBar.removePlayer(player);
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(this.getStepSound(), 0.15F, 1.0F);
	}

	@Override
	public void readCustomDataFromTag(CompoundTag tag) {
		super.readCustomDataFromTag(tag);
		if (this.hasCustomName()) {
			this.bossBar.setName(this.getDisplayName());
		}
	}

	@Override
	public void setCustomName(@Nullable Text name) {
		super.setCustomName(name);
		this.bossBar.setName(this.getDisplayName());
	}

	@Override
	protected void mobTick() {
		super.mobTick();
		this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
	}

	@Override
	public void tick() {
		super.tick();
		if (this.isAlive()) {
			if (this.isInsideWall()) {
				this.noClip = true;
			}
			if (!this.isInsideWall()) {
				this.noClip = false;
			}
		}
	}

	@Override
	public int getArmor() {
		float health = this.getHealth();
		return (health < 950 && health >= 900 ? 27
				: health < 900 && health >= 850 ? 24
						: health < 850 && health >= 800 ? 21
								: health < 800 && health >= 750 ? 18
										: health < 750 && health >= 700 ? 15
												: health < 700 && health >= 650 ? 12
														: health < 650 && health >= 600 ? 9
																: health < 600 && health >= 550 ? 6
																		: health < 550 && health >= 500 ? 3
																				: health < 500 ? 0 : 30);
	}

	@Override
	public void tickMovement() {
		super.tickMovement();
		if (this.getHealth() > 500.0D) {
			if (!this.world.isClient) {
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1000000, 1));
			}
		}
		if (this.getHealth() < 500.0D) {
			if (!this.world.isClient) {
				this.removeStatusEffect(StatusEffects.STRENGTH);
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 10000000, 2));
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 10000000, 1));
			}
		}
		if (!this.world.getDimension().isRespawnAnchorWorking()) {
			if (!this.world.isClient) {
				this.setGlowing(true);
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 10000000, 3));
			}
		}
	}
}
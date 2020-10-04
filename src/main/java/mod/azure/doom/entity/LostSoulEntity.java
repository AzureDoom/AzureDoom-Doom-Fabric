package mod.azure.doom.entity;

import java.util.EnumSet;
import java.util.Random;

import org.jetbrains.annotations.Nullable;

import mod.azure.doom.util.ModSoundEvents;
import mod.azure.doom.util.packets.EntityPacket;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.Packet;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.explosion.Explosion;

public class LostSoulEntity extends DemonEntity implements Monster {

	public int explosionPower = 1;
	public int flameTimer;
	@Nullable
	private BlockPos bounds;

	public LostSoulEntity(EntityType<? extends LostSoulEntity> type, World worldIn) {
		super(type, worldIn);
		this.moveControl = new LostSoulEntity.GhastMoveControl(this);
	}

	public boolean handleFallDamage(float fallDistance, float damageMultiplier) {
		return false;
	}

	protected void fall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition) {
	}

	public void travel(Vec3d movementInput) {
		if (this.isTouchingWater()) {
			this.updateVelocity(0.02F, movementInput);
			this.move(MovementType.SELF, this.getVelocity());
			this.setVelocity(this.getVelocity().multiply(0.800000011920929D));
		} else if (this.isInLava()) {
			this.updateVelocity(0.02F, movementInput);
			this.move(MovementType.SELF, this.getVelocity());
			this.setVelocity(this.getVelocity().multiply(0.5D));
		} else {
			float f = 0.91F;
			if (this.onGround) {
				f = this.world.getBlockState(new BlockPos(this.getX(), this.getY() - 1.0D, this.getZ())).getBlock()
						.getSlipperiness() * 0.91F;
			}

			float g = 0.16277137F / (f * f * f);
			f = 0.91F;
			if (this.onGround) {
				f = this.world.getBlockState(new BlockPos(this.getX(), this.getY() - 1.0D, this.getZ())).getBlock()
						.getSlipperiness() * 0.91F;
			}

			this.updateVelocity(this.onGround ? 0.1F * g : 0.02F, movementInput);
			this.move(MovementType.SELF, this.getVelocity());
			this.setVelocity(this.getVelocity().multiply((double) f));
		}

		this.method_29242(this, false);
	}

	public boolean isClimbing() {
		return false;
	}

	@Override
	public Packet<?> createSpawnPacket() {
		return EntityPacket.createPacket(this);
	}

	public static DefaultAttributeContainer.Builder createMobAttributes() {
		return LivingEntity.createLivingAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 50.0D)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15D).add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D)
				.add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.0D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0D);
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(7, new LostSoulEntity.LookAtTargetGoal());
		this.goalSelector.add(4, new LostSoulEntity.ChargeTargetGoal());
		this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0D));
		this.targetSelector.add(2, new FollowTargetGoal<>(this, PlayerEntity.class, true));
	}

	public static boolean canSpawn(EntityType<PainEntity> type, WorldAccess world, SpawnReason spawnReason,
			BlockPos pos, Random random) {
		return world.getDifficulty() != Difficulty.PEACEFUL && random.nextInt(20) == 0
				&& canMobSpawn(type, world, spawnReason, pos, random);
	}

	public boolean isCharging() {
		return true;
	}

	public void setCharging(boolean charging) {
		return;
	}

	@Nullable
	public BlockPos getBounds() {
		return this.bounds;
	}

	class ChargeTargetGoal extends Goal {
		public ChargeTargetGoal() {
			this.setControls(EnumSet.of(Goal.Control.MOVE));
		}

		public boolean canStart() {
			if (LostSoulEntity.this.getTarget() != null && !LostSoulEntity.this.getMoveControl().isMoving()
					&& LostSoulEntity.this.random.nextInt(7) == 0) {
				return LostSoulEntity.this.squaredDistanceTo(LostSoulEntity.this.getTarget()) > 4.0D;
			} else {
				return false;
			}
		}

		public boolean shouldContinue() {
			return LostSoulEntity.this.getMoveControl().isMoving() && LostSoulEntity.this.isCharging()
					&& LostSoulEntity.this.getTarget() != null && LostSoulEntity.this.getTarget().isAlive();
		}

		public void start() {
			LivingEntity livingEntity = LostSoulEntity.this.getTarget();
			Vec3d vec3d = livingEntity.getCameraPosVec(1.0F);
			LostSoulEntity.this.moveControl.moveTo(vec3d.x, vec3d.y, vec3d.z, 1.0D);
			LostSoulEntity.this.setCharging(true);
			LostSoulEntity.this.playSound(ModSoundEvents.LOST_SOUL_AMBIENT, 1.0F, 1.0F);
		}

		public void stop() {
			LostSoulEntity.this.setCharging(false);
		}

		public void tick() {
			LivingEntity livingEntity = LostSoulEntity.this.getTarget();
			if (LostSoulEntity.this.getBoundingBox().intersects(livingEntity.getBoundingBox())) {
				LostSoulEntity.this.tryAttack(livingEntity);
				LostSoulEntity.this.setCharging(false);
			} else {
				double d = LostSoulEntity.this.squaredDistanceTo(livingEntity);
				if (d < 9.0D) {
					Vec3d vec3d = livingEntity.getCameraPosVec(1.0F);
					LostSoulEntity.this.moveControl.moveTo(vec3d.x, vec3d.y, vec3d.z, 1.0D);
				}
			}

		}
	}

	@Override
	public void tick() {
		super.tick();
		flameTimer = (flameTimer + 1) % 8;
	}

	class LookAtTargetGoal extends Goal {
		public LookAtTargetGoal() {
			this.setControls(EnumSet.of(Goal.Control.MOVE));
		}

		public boolean canStart() {
			return !LostSoulEntity.this.getMoveControl().isMoving() && LostSoulEntity.this.random.nextInt(7) == 0;
		}

		public boolean shouldContinue() {
			return false;
		}

		public void tick() {
			BlockPos blockPos = LostSoulEntity.this.getBounds();
			if (blockPos == null) {
				blockPos = LostSoulEntity.this.getBlockPos();
			}

			for (int i = 0; i < 3; ++i) {
				BlockPos blockPos2 = blockPos.add(LostSoulEntity.this.random.nextInt(15) - 7,
						LostSoulEntity.this.random.nextInt(11) - 5, LostSoulEntity.this.random.nextInt(15) - 7);
				if (LostSoulEntity.this.world.isAir(blockPos2)) {
					LostSoulEntity.this.moveControl.moveTo((double) blockPos2.getX() + 0.5D,
							(double) blockPos2.getY() + 0.5D, (double) blockPos2.getZ() + 0.5D, 0.25D);
					if (LostSoulEntity.this.getTarget() == null) {
						LostSoulEntity.this.getLookControl().lookAt((double) blockPos2.getX() + 0.5D,
								(double) blockPos2.getY() + 0.5D, (double) blockPos2.getZ() + 0.5D, 180.0F, 20.0F);
					}
					break;
				}
			}

		}
	}

	static class GhastMoveControl extends MoveControl {
		private final LostSoulEntity ghast;
		private int collisionCheckCooldown;

		public GhastMoveControl(LostSoulEntity ghast) {
			super(ghast);
			this.ghast = ghast;
		}

		public void tick() {
			if (this.state == MoveControl.State.MOVE_TO) {
				if (this.collisionCheckCooldown-- <= 0) {
					this.collisionCheckCooldown += this.ghast.getRandom().nextInt(5) + 2;
					Vec3d vec3d = new Vec3d(this.targetX - this.ghast.getX(), this.targetY - this.ghast.getY(),
							this.targetZ - this.ghast.getZ());
					double d = vec3d.length();
					vec3d = vec3d.normalize();
					if (this.willCollide(vec3d, MathHelper.ceil(d))) {
						this.ghast.setVelocity(this.ghast.getVelocity().add(vec3d.multiply(0.1D)));
					} else {
						this.state = MoveControl.State.WAIT;
					}
				}

			}
		}

		private boolean willCollide(Vec3d direction, int steps) {
			Box box = this.ghast.getBoundingBox();

			for (int i = 1; i < steps; ++i) {
				box = box.offset(direction);
				if (!this.ghast.world.isSpaceEmpty(this.ghast, box)) {
					return false;
				}
			}

			return true;
		}
	}

	protected void explode() {
		this.world.createExplosion(this, this.getX(), this.getBodyY(0.0625D), this.getZ(), 1.0F,
				Explosion.DestructionType.NONE);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSoundEvents.PAIN_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return ModSoundEvents.PAIN_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSoundEvents.PAIN_DEATH;
	}

	@Override
	public EntityGroup getGroup() {
		return EntityGroup.UNDEAD;
	}

	@Override
	protected float getSoundVolume() {
		return 1.0F;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public boolean shouldRender(double distance) {
		return true;
	}

	public int getFlameTimer() {
		return flameTimer;
	}

}
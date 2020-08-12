package mod.azure.doom.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Random;

import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.annotation.Nullable;
import mod.azure.doom.entity.ai.goal.RangedCyberdemonAttackGoal;
import mod.azure.doom.entity.projectiles.RocketEntity;
import mod.azure.doom.item.ammo.Rocket;
import mod.azure.doom.util.DoomItems;
import mod.azure.doom.util.ModSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class CyberdemonEntity extends DemonEntity implements RangedAttackMob {

	private final RangedCyberdemonAttackGoal<CyberdemonEntity> aiArrowAttack = new RangedCyberdemonAttackGoal<>(this,
			1.0D, 20, 15.0F);
	private final MeleeAttackGoal meleeAttackGoal = new MeleeAttackGoal(this, 1.2D, false) {
		public void stop() {
			super.stop();
			CyberdemonEntity.this.setAttacking(false);
		}

		public void start() {
			super.start();
			CyberdemonEntity.this.setAttacking(true);
		}
	};

	public CyberdemonEntity(EntityType<CyberdemonEntity> entityType, World worldIn) {
		super(entityType, worldIn);
		this.updateAttackType();
	}

	public static boolean spawning(EntityType<BaronEntity> p_223337_0_, World p_223337_1_, SpawnReason reason,
			BlockPos p_223337_3_, Random p_223337_4_) {
		return p_223337_1_.getDifficulty() != Difficulty.PEACEFUL;
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.add(6, new LookAroundGoal(this));
		this.targetSelector.add(1, new RevengeGoal(this, new Class[0]));
		this.targetSelector.add(2, new FollowTargetGoal<>(this, PlayerEntity.class, true));
	}

	public static DefaultAttributeContainer.Builder createMobAttributes() {
		return LivingEntity.createLivingAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 50.0D)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15D).add(EntityAttributes.GENERIC_MAX_HEALTH, 40.0D)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15.0D);
	}

	@Override
	protected void initEquipment(LocalDifficulty difficulty) {
		super.initEquipment(difficulty);
		this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(DoomItems.CYBERDEMONATTACK));
	}

	@Nullable
	@Override
	public EntityData initialize(ServerWorldAccess serverWorldAccess, LocalDifficulty difficulty,
			SpawnReason spawnReason, EntityData entityData, CompoundTag entityTag) {
		entityData = super.initialize(serverWorldAccess, difficulty, spawnReason, entityData, entityTag);
		this.initEquipment(difficulty);
		if (this.getEquippedStack(EquipmentSlot.HEAD).isEmpty()) {
			LocalDate localDate = LocalDate.now();
			int i = localDate.get(ChronoField.DAY_OF_MONTH);
			int j = localDate.get(ChronoField.MONTH_OF_YEAR);
			if (j == 10 && i == 31 && this.random.nextFloat() < 0.25F) {
				this.equipStack(EquipmentSlot.HEAD,
						new ItemStack(this.random.nextFloat() < 0.1F ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
				this.armorDropChances[EquipmentSlot.HEAD.getEntitySlotId()] = 0.0F;
			}
		}

		return entityData;
	}

	protected boolean shouldDrown() {
		return false;
	}

	protected boolean shouldBurnInDay() {
		return false;
	}

	public void readCustomDataFromTag(CompoundTag tag) {
		super.readCustomDataFromTag(tag);
		this.updateAttackType();
	}

	public void equipStack(EquipmentSlot slot, ItemStack stack) {
		super.equipStack(slot, stack);
		if (!this.world.isClient) {
			this.updateAttackType();
		}

	}

	public void updateAttackType() {
		if (this.world != null && !this.world.isClient) {
			this.goalSelector.remove(this.meleeAttackGoal);
			this.goalSelector.remove(this.aiArrowAttack);
			ItemStack itemStack = this
					.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, DoomItems.CYBERDEMONATTACK));
			if (itemStack.getItem() == DoomItems.CYBERDEMONATTACK) {
				int i = 20;
				if (this.world.getDifficulty() != Difficulty.HARD) {
					i = 40;
				}

				this.aiArrowAttack.setAttackInterval(i);
				this.goalSelector.add(4, this.aiArrowAttack);
			} else {
				this.goalSelector.add(4, this.meleeAttackGoal);
			}

		}
	}

	public void attack(LivingEntity target, float pullProgress) {
		ItemStack itemStack = this.getArrowType(
				this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, DoomItems.CYBERDEMONATTACK)));
		RocketEntity persistentProjectileEntity = this.createArrowProjectile(itemStack, pullProgress);
		double d = target.getX() - this.getX();
		double e = target.getBodyY(0.3333333333333333D) - persistentProjectileEntity.getY();
		double f = target.getZ() - this.getZ();
		double g = (double) MathHelper.sqrt(d * d + f * f);
		persistentProjectileEntity.setVelocity(d, e + g * 0.20000000298023224D, f, 1.6F,
				(float) (14 - this.world.getDifficulty().getId() * 4));
		this.playSound(ModSoundEvents.ROCKET_FIRING, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
		this.world.spawnEntity(persistentProjectileEntity);
	}

	protected RocketEntity createArrowProjectile(ItemStack arrow, float damageModifier) {
		return CyberdemonEntity.createArrowProjectile(this, arrow, damageModifier);
	}

	public boolean canUseRangedWeapon(RangedWeaponItem weapon) {
		return weapon == DoomItems.CYBERDEMONATTACK;
	}

	public static RocketEntity createArrowProjectile(LivingEntity entity, ItemStack stack, float damageModifier) {
		Rocket arrowItem = (Rocket) ((Rocket) (stack.getItem() instanceof Rocket ? stack.getItem() : DoomItems.ROCKET));
		RocketEntity persistentProjectileEntity = arrowItem.createArrow(entity.world, stack, entity);
		persistentProjectileEntity.applyEnchantmentEffects(entity, damageModifier);

		return persistentProjectileEntity;
	}

	@Override
	protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
		return 4.70F;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSoundEvents.CYBERDEMON_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return ModSoundEvents.CYBERDEMON_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSoundEvents.CYBERDEMON_DEATH;
	}

	protected SoundEvent getStepSound() {
		return ModSoundEvents.CYBERDEMON_STEP;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(this.getStepSound(), 0.15F, 1.0F);
	}

}
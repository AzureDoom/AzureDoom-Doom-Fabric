package mod.azure.doom.entity.projectiles.entity;

import mod.azure.doom.util.ModSoundEvents;
import mod.azure.doom.util.packets.EntityPacket;
import mod.azure.doom.util.registry.ProjectilesEntityRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class ChaingunMobEntity extends ExplosiveProjectileEntity {

	public int explosionPower = 1;
	protected int timeInAir;
	protected boolean inAir;
	private int ticksInAir;
	private float directHitDamage = 3F;

	public ChaingunMobEntity(EntityType<? extends ChaingunMobEntity> p_i50160_1_, World p_i50160_2_) {
		super(p_i50160_1_, p_i50160_2_);
	}

	public ChaingunMobEntity(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ,
			float directHitDamage) {
		super(ProjectilesEntityRegister.CHAINGUN_MOB, shooter, accelX, accelY, accelZ, worldIn);
		this.directHitDamage = directHitDamage;
	}

	public ChaingunMobEntity(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
		super(ProjectilesEntityRegister.CHAINGUN_MOB, x, y, z, accelX, accelY, accelZ, worldIn);
	}

	@Override
	public void writeCustomDataToTag(CompoundTag compound) {
		super.writeCustomDataToTag(compound);
		compound.putShort("life", (short) this.ticksInAir);
	}

	@Override
	public void readCustomDataFromTag(CompoundTag compound) {
		super.readCustomDataFromTag(compound);
		this.ticksInAir = compound.getShort("life");
	}

	@Override
	protected boolean isBurning() {
		return false;
	}

	public void setDirectHitDamage(float directHitDamage) {
		this.directHitDamage = directHitDamage;
	}

	@Override
	public Packet<?> createSpawnPacket() {
		return EntityPacket.createPacket(this);
	}

	@Override
	public boolean hasNoGravity() {
		if (this.isSubmergedInWater()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) {
		super.onEntityHit(entityHitResult);
		if (!this.world.isClient) {
			Entity entity = entityHitResult.getEntity();
			Entity entity2 = this.getOwner();
			entity.damage(DamageSource.magic(this, entity2), directHitDamage);
			if (entity2 instanceof LivingEntity) {
				this.dealDamage((LivingEntity) entity2, entity);
			}
		}
		this.playSound(ModSoundEvents.CHAINGUN_SHOOT, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
	}
}
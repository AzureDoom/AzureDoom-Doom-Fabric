package mod.azure.doom.entity.projectiles;

import mod.azure.doom.util.registry.ModEntityTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class BarrelEntity extends Entity {

	private LivingEntity causingEntity;

	public BarrelEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}

	protected void explode() {
		this.world.createExplosion(this, this.getX(), this.getBodyY(0.0625D), this.getZ(), 4.0F, true,
				Explosion.DestructionType.NONE);
	}

	public BarrelEntity(World worldIn, double x, double y, double z, LivingEntity igniter) {
		this(ModEntityTypes.BARREL, worldIn);
		this.updatePosition(x, y, z);
		double d = world.random.nextDouble() * 6.2831854820251465D;
		this.setVelocity(-Math.sin(d) * 0.02D, 0.20000000298023224D, -Math.cos(d) * 0.02D);
		this.prevX = x;
		this.prevY = y;
		this.prevZ = z;
		this.causingEntity = igniter;
	}

	public LivingEntity getCausingEntity() {
		return this.causingEntity;
	}

	@Override
	protected void initDataTracker() {
	}

	public void tick() {
		this.remove();
		if (!this.world.isClient) {
			this.explode();
		}
	}

	@Override
	protected void writeCustomDataToTag(CompoundTag tag) {
	}

	@Override
	protected void readCustomDataFromTag(CompoundTag tag) {
	}

	@Override
	public Packet<?> createSpawnPacket() {
		return new EntitySpawnS2CPacket(this);
	}
	
	@Override
    @Environment(EnvType.CLIENT)
	public boolean shouldRender(double distance) {
		return true;
	}

}
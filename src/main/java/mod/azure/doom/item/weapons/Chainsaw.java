package mod.azure.doom.item.weapons;

import java.util.List;

import io.netty.buffer.Unpooled;
import mod.azure.doom.DoomMod;
import mod.azure.doom.client.ClientInit;
import mod.azure.doom.util.enums.DoomTier;
import mod.azure.doom.util.registry.DoomItems;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Chainsaw extends Item {

	public Chainsaw() {
		super(new Item.Settings().group(DoomMod.DoomWeaponItemGroup).maxCount(1).maxDamage(601));
	}

	@Override
	public boolean hasGlint(ItemStack stack) {
		return false;
	}

	@Override
	public boolean canRepair(ItemStack toRepair, ItemStack repair) {
		return DoomTier.CHAINSAW.getRepairIngredient().test(repair) || super.canRepair(toRepair, repair);
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(new TranslatableText(
				"Fuel: " + (stack.getMaxDamage() - stack.getDamage() - 1) + " / " + (stack.getMaxDamage() - 1))
						.formatted(Formatting.ITALIC));
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		LivingEntity user = (LivingEntity) entityIn;
		PlayerEntity player = (PlayerEntity) entityIn;
		final Vec3d facing = Vec3d.fromPolar(user.getRotationClient()).normalize();
		if (player.getMainHandStack().isItemEqualIgnoreDamage(stack)
				&& stack.getDamage() < (stack.getMaxDamage() - 1)) {
			final Box aabb = new Box(entityIn.getBlockPos().up()).expand(1D, 1D, 1D).offset(facing.multiply(1D));
			entityIn.getEntityWorld().getOtherEntities(user, aabb).forEach(e -> doDamage(user, e));
			entityIn.getEntityWorld().getOtherEntities(user, aabb).forEach(e -> damageItem(user, stack));
			entityIn.getEntityWorld().getOtherEntities(user, aabb).forEach(e -> addParticle(e));
		}
		if (worldIn.isClient) {
			if (player.getMainHandStack().getItem() instanceof Chainsaw && ClientInit.reload.isPressed() && isSelected) {
				PacketByteBuf passedData = new PacketByteBuf(Unpooled.buffer());
				passedData.writeBoolean(true);
				ClientPlayNetworking.send(DoomMod.CHAINSAW, passedData);
			}
		}
	}

	public void reload(PlayerEntity user, Hand hand) {
		if (user.getStackInHand(hand).getItem() instanceof Chainsaw) {
			while (user.getStackInHand(hand).getDamage() != 0 && user.inventory.count(DoomItems.GAS_BARREL) > 0) {
				removeAmmo(DoomItems.BULLETS, user);
				user.getStackInHand(hand).damage(-200, user, s -> user.sendToolBreakStatus(hand));
				user.getStackInHand(hand).setCooldown(3);
			}
		}
	}

	private void removeAmmo(Item ammo, PlayerEntity playerEntity) {
		if (!playerEntity.isCreative()) {
			for (ItemStack item : playerEntity.inventory.main) {
				if (item.getItem() == DoomItems.GAS_BARREL) {
					item.decrement(1);
					break;
				}
			}
		}
	}

	private void doDamage(LivingEntity user, Entity target) {
		if (target instanceof LivingEntity) {
			target.timeUntilRegen = 0;
			target.damage(DamageSource.player((PlayerEntity) user), 2F);
		}
	}

	private void damageItem(LivingEntity user, ItemStack stack) {
		PlayerEntity player = (PlayerEntity) user;
		if (!player.abilities.creativeMode) {
			stack.setDamage(stack.getDamage() + 1);
		}
	}

	private void addParticle(Entity target) {
		if (target instanceof LivingEntity) {
			target.world.addParticle(DustParticleEffect.RED, target.getParticleX(0.5D), target.getRandomBodyY(),
					target.getParticleZ(0.5D), 0.0D, 0D, 0D);
		}
	}

}
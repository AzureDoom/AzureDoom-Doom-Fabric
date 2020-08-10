package mod.azure.doom.item.weapons;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import com.google.common.collect.Lists;

import mod.azure.doom.DoomMod;
import mod.azure.doom.entity.projectiles.ShotgunShellEntity;
import mod.azure.doom.item.ammo.ShellAmmo;
import mod.azure.doom.util.DoomItems;
import mod.azure.doom.util.ModSoundEvents;
import mod.azure.doom.util.enums.DoomTier;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SuperShotgun extends CrossbowItem {

	private boolean charged = false;
	private boolean loaded = false;

	public SuperShotgun() {
		super(new Item.Settings().group(DoomMod.DoomItemGroup).maxCount(1).maxDamage(9000));
	}

	@Override
	public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
		ItemStack stack = new ItemStack(this);
		stack.hasTag();
		stack.addEnchantment(Enchantments.MULTISHOT, 1);
		stack.addEnchantment(Enchantments.PIERCING, 10);
		stack.addEnchantment(Enchantments.QUICK_CHARGE, 1);
		if (group == DoomMod.DoomItemGroup) {
			stacks.add(stack);
		}
	}

	@Override
	public void onCraft(ItemStack stack, World world, PlayerEntity player) {
		stack.hasTag();
		stack.addEnchantment(Enchantments.MULTISHOT, 1);
		stack.addEnchantment(Enchantments.PIERCING, 10);
		stack.addEnchantment(Enchantments.QUICK_CHARGE, 1);
	}

	@Override
	public boolean canRepair(ItemStack stack, ItemStack ingredient) {
		return DoomTier.DOOM.getRepairIngredient().test(ingredient) || super.canRepair(stack, ingredient);
	}

	@Override
	public Predicate<ItemStack> getHeldProjectiles() {
		return getProjectiles();
	}

	@Override
	public Predicate<ItemStack> getProjectiles() {
		return itemStack -> itemStack.getItem() instanceof ShellAmmo;
	}

	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack itemStack = user.getStackInHand(hand);
		if (isCharged(itemStack)) {
			shootAll(world, user, hand, itemStack, getSpeed(itemStack), 1.0F);
			setCharged(itemStack, false);
			return TypedActionResult.consume(itemStack);
		} else if (!user.getArrowType(itemStack).isEmpty()) {
			if (!isCharged(itemStack)) {
				this.charged = false;
				this.loaded = false;
				user.setCurrentHand(hand);
			}

			return TypedActionResult.consume(itemStack);
		} else {
			return TypedActionResult.fail(itemStack);
		}
	}

	public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
		int i = this.getMaxUseTime(stack) - remainingUseTicks;
		float f = getPullProgress(i, stack);
		if (f >= 1.0F && !isCharged(stack) && loadProjectiles(user, stack)) {
			setCharged(stack, true);
			SoundCategory soundCategory = user instanceof PlayerEntity ? SoundCategory.PLAYERS : SoundCategory.HOSTILE;
			world.playSound((PlayerEntity) null, user.getX(), user.getY(), user.getZ(), ModSoundEvents.LOADING_END,
					soundCategory, 1.0F, 1.0F / (RANDOM.nextFloat() * 0.5F + 1.0F) + 0.2F);
		}

	}

	private static boolean loadProjectiles(LivingEntity shooter, ItemStack projectile) {
		int i = EnchantmentHelper.getLevel(Enchantments.MULTISHOT, projectile);
		int j = i == 0 ? 1 : 3;
		boolean bl = shooter instanceof PlayerEntity && ((PlayerEntity) shooter).abilities.creativeMode;
		ItemStack itemStack = shooter.getArrowType(projectile);
		ItemStack itemStack2 = itemStack.copy();

		for (int k = 0; k < j; ++k) {
			if (k > 0) {
				itemStack = itemStack2.copy();
			}

			if (itemStack.isEmpty() && bl) {
				itemStack = new ItemStack(DoomItems.SHOTGUN_SHELLS);
				itemStack2 = itemStack.copy();
			}

			if (!loadProjectile(shooter, projectile, itemStack, k > 0, bl)) {
				return false;
			}
		}

		return true;
	}

	private static boolean loadProjectile(LivingEntity shooter, ItemStack crossbow, ItemStack projectile,
			boolean simulated, boolean creative) {
		if (projectile.isEmpty()) {
			return false;
		} else {
			boolean bl = creative && projectile.getItem() instanceof ShellAmmo;
			ItemStack itemStack2;
			if (!bl && !creative && !simulated) {
				itemStack2 = projectile.split(1);
				if (projectile.isEmpty() && shooter instanceof PlayerEntity) {
					((PlayerEntity) shooter).inventory.removeOne(projectile);
				}
			} else {
				itemStack2 = projectile.copy();
			}

			putProjectile(crossbow, itemStack2);
			return true;
		}
	}

	public static boolean isCharged(ItemStack stack) {
		CompoundTag compoundTag = stack.getTag();
		return compoundTag != null && compoundTag.getBoolean("Charged");
	}

	public static void setCharged(ItemStack stack, boolean charged) {
		CompoundTag compoundTag = stack.getOrCreateTag();
		compoundTag.putBoolean("Charged", charged);
	}

	private static void putProjectile(ItemStack crossbow, ItemStack projectile) {
		CompoundTag compoundTag = crossbow.getOrCreateTag();
		ListTag listTag2;
		if (compoundTag.contains("ChargedProjectiles", 9)) {
			listTag2 = compoundTag.getList("ChargedProjectiles", 10);
		} else {
			listTag2 = new ListTag();
		}

		CompoundTag compoundTag2 = new CompoundTag();
		projectile.toTag(compoundTag2);
		listTag2.add(compoundTag2);
		compoundTag.put("ChargedProjectiles", listTag2);
	}

	private static List<ItemStack> getProjectiles(ItemStack crossbow) {
		List<ItemStack> list = Lists.newArrayList();
		CompoundTag compoundTag = crossbow.getTag();
		if (compoundTag != null && compoundTag.contains("ChargedProjectiles", 9)) {
			ListTag listTag = compoundTag.getList("ChargedProjectiles", 10);
			if (listTag != null) {
				for (int i = 0; i < listTag.size(); ++i) {
					CompoundTag compoundTag2 = listTag.getCompound(i);
					list.add(ItemStack.fromTag(compoundTag2));
				}
			}
		}

		return list;
	}

	private static void clearProjectiles(ItemStack crossbow) {
		CompoundTag compoundTag = crossbow.getTag();
		if (compoundTag != null) {
			ListTag listTag = compoundTag.getList("ChargedProjectiles", 9);
			listTag.clear();
			compoundTag.put("ChargedProjectiles", listTag);
		}

	}

	public static boolean hasProjectile(ItemStack crossbow, Item projectile) {
		return getProjectiles(crossbow).stream().anyMatch((s) -> {
			return s.getItem() == projectile;
		});
	}

	private static void shoot(World world, LivingEntity shooter, Hand hand, ItemStack crossbow, ItemStack projectile,
			float soundPitch, boolean creative, float speed, float divergence, float simulated) {
		if (!world.isClient) {
			boolean bl = projectile.getItem() == Items.FIREWORK_ROCKET;
			Object projectileEntity2;
			if (bl) {
				projectileEntity2 = new FireworkRocketEntity(world, projectile, shooter, shooter.getX(),
						shooter.getEyeY() - 0.15000000596046448D, shooter.getZ(), true);
			} else {
				projectileEntity2 = createArrow(world, shooter, crossbow, projectile);
				if (creative || simulated != 0.0F) {
					((PersistentProjectileEntity) projectileEntity2).pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
				}
			}

			if (shooter instanceof CrossbowUser) {
				CrossbowUser crossbowUser = (CrossbowUser) shooter;
				crossbowUser.shoot(crossbowUser.getTarget(), crossbow, (ProjectileEntity) projectileEntity2, simulated);
			} else {
				Vec3d vec3d = shooter.getOppositeRotationVector(1.0F);
				Quaternion quaternion = new Quaternion(new Vector3f(vec3d), simulated, true);
				Vec3d vec3d2 = shooter.getRotationVec(1.0F);
				Vector3f vector3f = new Vector3f(vec3d2);
				vector3f.rotate(quaternion);
				((ProjectileEntity) projectileEntity2).setVelocity((double) vector3f.getX(), (double) vector3f.getY(),
						(double) vector3f.getZ(), speed, divergence);
			}

			crossbow.damage(bl ? 3 : 1, shooter, (e) -> {
				e.sendToolBreakStatus(hand);
			});
			world.spawnEntity((Entity) projectileEntity2);
			world.playSound((PlayerEntity) null, shooter.getX(), shooter.getY(), shooter.getZ(),
					SoundEvents.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYERS, 1.0F, soundPitch);
		}
	}

	private static ShotgunShellEntity createArrow(World world, LivingEntity entity, ItemStack crossbow,
			ItemStack arrow) {
		ShellAmmo ShellAmmo = (ShellAmmo) ((ShellAmmo) (arrow.getItem() instanceof ShellAmmo ? arrow.getItem()
				: DoomItems.SHOTGUN_SHELLS));
		ShotgunShellEntity persistentProjectileEntity = ShellAmmo.createArrow(world, arrow, entity);
		if (entity instanceof PlayerEntity) {
			persistentProjectileEntity.setCritical(true);
		}

		persistentProjectileEntity.setSound(ModSoundEvents.SHOOT2);
		persistentProjectileEntity.setShotFromCrossbow(true);
		int i = EnchantmentHelper.getLevel(Enchantments.PIERCING, crossbow);
		if (i > 0) {
			persistentProjectileEntity.setPierceLevel((byte) i);
		}

		return persistentProjectileEntity;
	}

	public static void shootAll(World world, LivingEntity entity, Hand hand, ItemStack stack, float speed,
			float divergence) {
		List<ItemStack> list = getProjectiles(stack);
		float[] fs = getSoundPitches(entity.getRandom());

		for (int i = 0; i < list.size(); ++i) {
			ItemStack itemStack = (ItemStack) list.get(i);
			boolean bl = entity instanceof PlayerEntity && ((PlayerEntity) entity).abilities.creativeMode;
			if (!itemStack.isEmpty()) {
				if (i == 0) {
					shoot(world, entity, hand, stack, itemStack, fs[i], bl, speed, divergence, 0.0F);
				} else if (i == 1) {
					shoot(world, entity, hand, stack, itemStack, fs[i], bl, speed, divergence, -10.0F);
				} else if (i == 2) {
					shoot(world, entity, hand, stack, itemStack, fs[i], bl, speed, divergence, 10.0F);
				}
			}
		}

		postShoot(world, entity, stack);
	}

	private static float[] getSoundPitches(Random random) {
		boolean bl = random.nextBoolean();
		return new float[] { 1.0F, getSoundPitch(bl), getSoundPitch(!bl) };
	}

	private static float getSoundPitch(boolean flag) {
		float f = flag ? 0.63F : 0.43F;
		return 1.0F / (RANDOM.nextFloat() * 0.5F + 1.8F) + f;
	}

	private static void postShoot(World world, LivingEntity entity, ItemStack stack) {
		clearProjectiles(stack);
	}

	public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
		if (!world.isClient) {
			int i = EnchantmentHelper.getLevel(Enchantments.QUICK_CHARGE, stack);
			SoundEvent soundEvent = this.getQuickChargeSound(i);
			SoundEvent soundEvent2 = i == 0 ? ModSoundEvents.LOADING_END : null;
			float f = (float) (stack.getMaxUseTime() - remainingUseTicks) / (float) getPullTime(stack);
			if (f < 0.2F) {
				this.charged = false;
				this.loaded = false;
			}

			if (f >= 0.2F && !this.charged) {
				this.charged = true;
				world.playSound((PlayerEntity) null, user.getX(), user.getY(), user.getZ(), soundEvent,
						SoundCategory.PLAYERS, 0.5F, 1.0F);
			}

			if (f >= 0.5F && soundEvent2 != null && !this.loaded) {
				this.loaded = true;
				world.playSound((PlayerEntity) null, user.getX(), user.getY(), user.getZ(), soundEvent2,
						SoundCategory.PLAYERS, 0.5F, 1.0F);
			}
		}

	}

	public int getMaxUseTime(ItemStack stack) {
		return getPullTime(stack) + 3;
	}

	public static int getPullTime(ItemStack stack) {
		int i = EnchantmentHelper.getLevel(Enchantments.QUICK_CHARGE, stack);
		return i == 0 ? 25 : 25 - 5 * i;
	}

	public UseAction getUseAction(ItemStack stack) {
		return UseAction.CROSSBOW;
	}

	public SoundEvent getQuickChargeSound(int stage) {
		switch (stage) {
		case 1:
			return ModSoundEvents.QUICK1_1;
		case 2:
			return ModSoundEvents.QUICK2_1;
		case 3:
			return ModSoundEvents.QUICK3_1;
		default:
			return ModSoundEvents.LOADING_START;
		}
	}

	private static float getPullProgress(int useTicks, ItemStack stack) {
		float f = (float) useTicks / (float) getPullTime(stack);
		if (f > 1.0F) {
			f = 1.0F;
		}

		return f;
	}

	private static float getSpeed(ItemStack stack) {
		return stack.getItem() == DoomItems.SSG && hasProjectile(stack, Items.FIREWORK_ROCKET) ? 1.6F : 3.15F;
	}

	public int getRange() {
		return 8;
	}

}
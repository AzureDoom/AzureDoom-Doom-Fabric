package mod.azure.doom.item.powerup;

import java.util.List;

import mod.azure.doom.DoomMod;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class InvisibleSphereItem extends Item {

	public InvisibleSphereItem() {
		super(new Item.Settings().group(DoomMod.DoomItemGroup).maxCount(1));
	}

	@Override
	public void usageTick(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
		if (livingEntityIn instanceof PlayerEntity) {
			PlayerEntity playerentity = (PlayerEntity) livingEntityIn;
			if (!worldIn.isClient) {
				livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 1200, 1));
				if (!playerentity.abilities.creativeMode) {
					stack.decrement(1);
					if (stack.isEmpty()) {
						playerentity.inventory.removeOne(stack);
					}
				}
			}
		}
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.NONE;
	}

	@Override
	public int getMaxUseTime(ItemStack stack) {
		return 7000;
	}

	@Override
	public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
		user.getStackInHand(hand);
		return ActionResult.CONSUME;
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(new TranslatableText("\u00A7o" + "Turns the user invisible for 1 minute."));
		super.appendTooltip(stack, world, tooltip, context);
	}

}
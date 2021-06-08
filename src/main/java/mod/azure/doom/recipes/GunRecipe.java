package mod.azure.doom.recipes;

import mod.azure.doom.DoomMod;
import mod.azure.doom.util.registry.DoomItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class GunRecipe extends SpecialCraftingRecipe {
	private static final Ingredient FIREWORK_STAR = Ingredient.ofItems(Items.FIREWORK_STAR);
	private static final Ingredient GUNPOWDER = Ingredient.ofItems(Items.GUNPOWDER);
	private static final Ingredient IRON_NUGGET = Ingredient.ofItems(Items.IRON_NUGGET);

	public GunRecipe(Identifier identifier) {
		super(identifier);
	}

	public boolean matches(CraftingInventory craftingInventory, World world) {
		boolean bl = false;
		int i = 0;

		for (int j = 0; j < craftingInventory.size(); ++j) {
			ItemStack itemStack = craftingInventory.getStack(j);
			if (!itemStack.isEmpty()) {
				if (itemStack.getItem() instanceof DyeItem) {
					bl = true;
				} else if (IRON_NUGGET.test(itemStack)) {
					if (bl) {
						return false;
					}

					bl = true;
				} else if (GUNPOWDER.test(itemStack)) {
					++i;
					if (i > 3) {
						return false;
					}
				} else if (!FIREWORK_STAR.test(itemStack)) {
					return false;
				}
			}
		}

		return bl && i >= 1;
	}

	public ItemStack craft(CraftingInventory craftingInventory) {
		ItemStack itemStack = new ItemStack(DoomItems.PISTOL, 3);
		NbtCompound compoundTag = itemStack.getOrCreateSubTag("Fireworks");
		NbtList listTag = new NbtList();
		int i = 0;

		for (int j = 0; j < craftingInventory.size(); ++j) {
			ItemStack itemStack2 = craftingInventory.getStack(j);
			if (!itemStack2.isEmpty()) {
				if (GUNPOWDER.test(itemStack2)) {
					++i;
				} else if (FIREWORK_STAR.test(itemStack2)) {
					NbtCompound compoundTag2 = itemStack2.getSubTag("Explosion");
					if (compoundTag2 != null) {
						listTag.add(compoundTag2);
					}
				}
			}
		}

		compoundTag.putByte("Flight", (byte) i);
		if (!listTag.isEmpty()) {
			compoundTag.put("Explosions", listTag);
		}

		return itemStack;
	}

	@Environment(EnvType.CLIENT)
	public boolean fits(int width, int height) {
		return width * height >= 2;
	}

	public ItemStack getOutput() {
		return new ItemStack(DoomItems.PISTOL);
	}

	public RecipeSerializer<?> getSerializer() {
		return DoomMod.GUNS_RECIPE_SERIALIZER;
	}
}
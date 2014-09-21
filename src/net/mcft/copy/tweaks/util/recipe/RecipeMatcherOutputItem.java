package net.mcft.copy.tweaks.util.recipe;

import java.util.HashSet;
import java.util.Set;

import net.mcft.copy.tweaks.util.ItemUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class RecipeMatcherOutputItem implements IRecipeMatcher {
	
	private final Set<Item> items = new HashSet<Item>();
	
	public RecipeMatcherOutputItem(Object... items) {
		for (Object item : items)
			this.items.add(ItemUtils.getItemFromObject(item));
	}
	
	@Override
	public boolean matches(IRecipe recipe) {
		ItemStack output = recipe.getRecipeOutput();
		return ((output != null) && (items.contains(output.getItem())));
	}
	
}

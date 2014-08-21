package net.mcft.copy.tweaks.util.recipe;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class RecipeMatcherOutputItem implements IRecipeMatcher {
	
	private final Set<Item> items = new HashSet<Item>();
	
	public RecipeMatcherOutputItem(Object... items) {
		for (Object item : items)
			this.items.add(getItem(item));
	}
	
	@Override
	public boolean matches(IRecipe recipe) {
		ItemStack output = recipe.getRecipeOutput();
		return ((output != null) && (items.contains(output.getItem())));
	}
	
	private static Item getItem(Object item) {
		if (item instanceof Item) return (Item)item;
		else if (item instanceof Block) return Item.getItemFromBlock((Block)item);
		else if (item instanceof String) return (Item)Item.itemRegistry.getObject((String)item);
		else throw new IllegalArgumentException();
	}
	
}

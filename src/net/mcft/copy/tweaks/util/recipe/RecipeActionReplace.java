package net.mcft.copy.tweaks.util.recipe;

import java.util.Arrays;
import java.util.List;

import net.mcft.copy.core.util.StackUtils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipeActionReplace implements IRecipeAction {
	
	private final Object replace, with;
	
	public RecipeActionReplace(Object replace, Object with) {
		this.replace = replace;
		this.with = makeReplacement(with);
	}
	
	@Override
	public void doAction(RecipeIterator iterator, IRecipe recipe) {
		List items;
		
		if (recipe instanceof ShapedRecipes)
			items = Arrays.asList(((ShapedRecipes)recipe).recipeItems);
		else if (recipe instanceof ShapelessRecipes)
			items = ((ShapelessRecipes)recipe).recipeItems;
		else if (recipe instanceof ShapedOreRecipe)
			items = Arrays.asList(((ShapedOreRecipe)recipe).getInput());
		else if (recipe instanceof ShapelessOreRecipe)
			items = ((ShapelessOreRecipe)recipe).getInput();
		else return;
		
		for (int i = 0; i < items.size(); i++) {
			Object item = items.get(i);
			if (item == null) continue;
			if (matches(replace, item))
				items.set(i, with);
		}
	}
	
	private static boolean matches(Object match, Object item) {
		if (item instanceof List) {
			for (ItemStack i : (List<ItemStack>)item)
				if (!matches(match, i))
					return false;
			return true;
		} else if (item instanceof ItemStack)
			return matches(match, (ItemStack)item);
		else throw new IllegalArgumentException();
	}
	
	private static boolean matches(Object match, ItemStack item) {
		if (match instanceof Item)
			return (item.getItem() == match);
		else if (match instanceof Block)
			return (item.getItem() == Item.getItemFromBlock((Block)match));
		else if (match instanceof ItemStack)
			return StackUtils.matches((ItemStack)match, item);
		else if (match instanceof String)
			return Arrays.asList(OreDictionary.getOreNames()).contains(match);
		else throw new IllegalArgumentException();
	}
	
	private static Object makeReplacement(Object with) {
		if (with instanceof ItemStack)
			return with;
		else if (with instanceof Item)
			return new ItemStack((Item)with, 1, OreDictionary.WILDCARD_VALUE);
		else if (with instanceof Block)
			return new ItemStack((Block)with, 1, OreDictionary.WILDCARD_VALUE);
		else if (with instanceof String)
			return OreDictionary.getOres((String)with);
		else throw new IllegalArgumentException();
	}
	
}

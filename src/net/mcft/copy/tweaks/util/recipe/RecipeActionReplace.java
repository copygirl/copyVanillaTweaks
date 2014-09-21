package net.mcft.copy.tweaks.util.recipe;

import java.util.List;

import net.mcft.copy.core.util.StackUtils;
import net.mcft.copy.tweaks.util.RecipeUtils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeActionReplace implements IRecipeAction {
	
	private final Object replace, with;
	
	public RecipeActionReplace(Object replace, Object with) {
		this.replace = replace;
		this.with = makeReplacement(with);
	}
	
	@Override
	public void doAction(RecipeIterator iterator, IRecipe recipe) {
		List items = RecipeUtils.getRecipeInput(recipe);
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
				if (matches(match, i)) return true;
			return false;
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
		else if (match instanceof String) {
			for (ItemStack m : OreDictionary.getOres((String)match))
				if (StackUtils.matches(m, item)) return true;
			return false;
		} else throw new IllegalArgumentException();
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

package net.mcft.copy.tweaks.util.recipe;

import net.minecraft.item.crafting.IRecipe;

public interface IRecipeAction {
	
	public void doAction(RecipeIterator iterator, IRecipe recipe);
	
}

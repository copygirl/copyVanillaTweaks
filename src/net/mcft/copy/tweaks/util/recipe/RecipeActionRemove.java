package net.mcft.copy.tweaks.util.recipe;

import net.minecraft.item.crafting.IRecipe;

public class RecipeActionRemove implements IRecipeAction {
	
	public static final RecipeActionRemove instance = new RecipeActionRemove();
	
	private RecipeActionRemove() {  }
	
	@Override
	public void doAction(RecipeIterator iterator, IRecipe recipe) {
		iterator.remove();
	}
	
}

package net.mcft.copy.tweaks.util.recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

public class RecipeIterator {
	
	private final ListIterator<IRecipe> iterator;
	private final List<MatcherActionPair> actions;
	
	public RecipeIterator() {
		iterator = CraftingManager.getInstance().getRecipeList().listIterator();
		actions = new ArrayList<MatcherActionPair>();
	}
	
	public void registerAction(IRecipeMatcher matcher, IRecipeAction action) {
		actions.add(new MatcherActionPair(matcher, action));
	}
	
	public void go() {
		while (iterator.hasNext()) {
			IRecipe recipe = iterator.next();
			for (MatcherActionPair actionPair : actions)
				if (actionPair.matcher.matches(recipe))
					actionPair.action.doAction(this, recipe);
		}
	}
	
	public void remove() {
		iterator.remove();
	}
	
	
	private static class MatcherActionPair {
		
		public final IRecipeMatcher matcher;
		public final IRecipeAction action;
		
		public MatcherActionPair(IRecipeMatcher matcher, IRecipeAction action) {
			this.matcher = matcher;
			this.action = action;
		}
		
	}
	
}

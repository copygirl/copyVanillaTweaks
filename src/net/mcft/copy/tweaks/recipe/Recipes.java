package net.mcft.copy.tweaks.recipe;

import net.mcft.copy.tweaks.util.recipe.RecipeMutator;

public final class Recipes {
	
	private Recipes() {  }
	
	public static void registerStationMutation(RecipeMutator mutator, Object... itemExperiencePairs) {
		for (int i = 0; i < itemExperiencePairs.length; i += 2) {
			Object item = itemExperiencePairs[i];
			int experience = (Integer)itemExperiencePairs[i + 1];
			mutator.registerStationMutation(item, experience);
		}
	}
	
}

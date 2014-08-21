package net.mcft.copy.tweaks.util.recipe;

import net.minecraft.item.crafting.IRecipe;

public interface IRecipeMatcher {
	
	public boolean matches(IRecipe recipe);
	
}

package net.mcft.copy.tweaks.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipeUtils {
	
	/** Returns the recipe's input items. */
	public static List getRecipeInput(IRecipe recipe) {
		if (recipe instanceof ShapedRecipes)
			return Arrays.asList(((ShapedRecipes)recipe).recipeItems);
		else if (recipe instanceof ShapedOreRecipe)
			return Arrays.asList(((ShapedOreRecipe)recipe).getInput());
		else if (recipe instanceof ShapelessRecipes)
			return ((ShapelessRecipes)recipe).recipeItems;
		else if (recipe instanceof ShapelessOreRecipe)
			return ((ShapelessOreRecipe)recipe).getInput();
		else throw new IllegalArgumentException(recipe.getClass() + " is not supported.");
	}
	
	/** Returns the recipe's width. */
	public static int getRecipeWidth(IRecipe recipe) {
		if (recipe instanceof ShapedRecipes)
			return ((ShapedRecipes)recipe).recipeWidth;
		else if (recipe instanceof ShapedOreRecipe) {
			try { return shapedOreWidthField.getInt(recipe); }
			catch (Exception ex) { throw new RuntimeException(ex); }
		} else throw new IllegalArgumentException("Can't get recipe width of " + recipe.getClass() + ".");
	}
	/** Returns the recipe's height. */
	public static int getRecipeHeight(IRecipe recipe) {
		if (recipe instanceof ShapedRecipes)
			return ((ShapedRecipes)recipe).recipeHeight;
		else if (recipe instanceof ShapedOreRecipe) {
			try { return shapedOreHeightField.getInt(recipe); }
			catch (Exception ex) { throw new RuntimeException(ex); }
		} else throw new IllegalArgumentException("Can't get recipe height of " + recipe.getClass() + ".");
	}
	
	/** Returns if the recipe is a shaped recipe. */
	public static boolean isShaped(IRecipe recipe) {
		return ((recipe instanceof ShapedRecipes) ||
		        (recipe instanceof ShapedOreRecipe));
	}
	
	static Field shapedOreWidthField;
	static Field shapedOreHeightField;
	static {
		try {
			shapedOreWidthField = ShapedOreRecipe.class.getDeclaredField("width");
			shapedOreHeightField = ShapedOreRecipe.class.getDeclaredField("height");
			shapedOreWidthField.setAccessible(true);
			shapedOreHeightField.setAccessible(true);
		} catch (Exception ex) { throw new RuntimeException(ex); }
	}
	
}

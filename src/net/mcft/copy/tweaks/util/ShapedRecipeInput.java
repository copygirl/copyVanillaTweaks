package net.mcft.copy.tweaks.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.item.crafting.IRecipe;

// TODO: Move this to copycore.
public class ShapedRecipeInput {
	
	public final int width, height;
	public final Object[] input;
	
	public ShapedRecipeInput(int width, int height, Object[] input) {
		this.width = width;
		this.height = height;
		this.input = input;
	}
	public ShapedRecipeInput(IRecipe recipe) {
		this(RecipeUtils.getRecipeWidth(recipe),
		     RecipeUtils.getRecipeHeight(recipe),
		     RecipeUtils.getRecipeInput(recipe).toArray());
	}
	
	public static ShapedRecipeInput create(Object... input) {
		int recipeWidth = 0;
		int recipeHeight = 0;
		
		int inputIndex = 0;
		Map<Character, Object> inputMap = new HashMap<Character, Object>();
		
		while ((inputIndex < input.length) && (input[inputIndex] instanceof String)) {
			String line = (String)input[inputIndex++];
			if (line.isEmpty())
				throw new IllegalArgumentException("Empty string isn't valid.");
			if (recipeWidth <= 0) recipeWidth = line.length();
			else if (recipeWidth != line.length())
				throw new IllegalArgumentException("All strings must have the same length.");
			for (char chr : line.toCharArray())
				inputMap.put(chr, null);
			recipeHeight++;
		}
		if (recipeHeight <= 0)
			throw new IllegalArgumentException("At least one string must be supplied.");
		
		if (inputIndex >= input.length)
			throw new IllegalArgumentException("At least one mapping must be supplied.");
		if ((input.length - inputIndex) % 2 > 0)
			throw new IllegalArgumentException("Mappings have to be in pairs of two.");
		
		for (; inputIndex < input.length; inputIndex += 2) {
			if (!(input[inputIndex] instanceof Character))
				throw new IllegalArgumentException("First argument of a mapping needs to be a character.");
			char chr = (Character)input[inputIndex];
			if (!inputMap.containsKey(chr))
				throw new IllegalArgumentException("Mapping for unused character '" + chr + "'.");
			if (inputMap.get(chr) != null)
				throw new IllegalArgumentException("Duplicate mapping for character '" + chr + "'.");
			inputMap.put(chr, input[inputIndex + 1]);
		}
		
		for (Entry<Character, Object> entry : inputMap.entrySet())
			if ((entry.getKey() != ' ') && (entry.getValue() == null))
				throw new IllegalArgumentException("No mapping for character '" + entry.getKey() + "'.");
		
		Object[] recipeInput = new Object[recipeWidth * recipeHeight];
		for (int x = 0; x < recipeWidth; x++)
			for (int y = 0; y < recipeHeight; y++)
				recipeInput[x + y * recipeWidth] = inputMap.get(((String)input[y]).charAt(x));
		
		return new ShapedRecipeInput(recipeWidth, recipeHeight, recipeInput);
	}
	
	public static ShapedRecipeInput merge(ShapedRecipeInput... recipeInputs) {
		int resultWidth = 0;
		int resultHeight = 0;
		for (ShapedRecipeInput recipeInput : recipeInputs) {
			resultWidth = Math.max(resultWidth, recipeInput.width);
			resultHeight = Math.max(resultHeight, recipeInput.height);
		}
		Object[] resultInput = new Object[resultWidth * resultHeight];
		for (ShapedRecipeInput recipeInput : recipeInputs) {
			for (int x = 0; x < recipeInput.width; x++) {
				for (int y = 0; y < recipeInput.height; y++) {
					int recipeIndex = x + y * recipeInput.width;
					Object input = recipeInput.input[recipeIndex];
					if (input == null) continue;
					int resultIndex = x + (resultWidth - recipeInput.width) / 2 +
					                  (y + (resultHeight - recipeInput.height) / 2) * resultWidth;
					resultInput[resultIndex] = input;
				}
			}
		}
		return new ShapedRecipeInput(resultWidth, resultHeight, resultInput);
	}
	
}
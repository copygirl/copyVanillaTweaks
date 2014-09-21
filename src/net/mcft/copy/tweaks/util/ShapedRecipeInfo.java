package net.mcft.copy.tweaks.util;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ShapedRecipeInfo extends ShapedRecipeInput {
	
	public final ItemStack output;
	
	public ShapedRecipeInfo(int width, int height, Object[] input, ItemStack output) {
		super(width, height, input);
		this.output = output;
	}
	public ShapedRecipeInfo(IRecipe recipe, ItemStack output) {
		super(recipe);
		this.output = output;
	}
	public ShapedRecipeInfo(ShapedRecipeInput input, ItemStack output) {
		this(input.width, input.height, input.input, output);
	}
	
	public static ShapedRecipeInfo create(ItemStack output, Object... input) {
		return new ShapedRecipeInfo(ShapedRecipeInput.create(input), output);
	}
	
	public ShapedOreRecipe createOreRecipe() {
		List map = new LinkedList();
		List mappings = new LinkedList();
		
		char chr = 'a';
		for (int y = 0; y < height; y++) {
			String line = "";
			for (int x = 0; x < width; x++) {
				Object i = input[x + y * width];
				if (i != null) {
					if (i instanceof List)
						i = OreDictionary.getOreName(OreDictionary.getOreIDs((ItemStack)((List)i).get(0))[0]);
					line += ++chr;
					mappings.add(chr);
					mappings.add(i);
				} else line += " ";
			}
			map.add(line);
		}
		
		map.addAll(mappings);
		return new ShapedOreRecipe(output, map.toArray());
	}
	
}

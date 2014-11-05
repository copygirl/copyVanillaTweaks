package net.mcft.copy.tweaks.recipe;

import net.mcft.copy.tweaks.VanillaTweaksConfig;
import net.mcft.copy.tweaks.copyVanillaTweaks;
import net.mcft.copy.tweaks.util.recipe.RecipeMatcherOutputItem;
import net.mcft.copy.tweaks.util.recipe.RecipeMutator;
import net.minecraft.init.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Loader;

public final class FMPSawRecipes {
	
	private FMPSawRecipes() {  }
	
	public static void register(RecipeMutator mutator) {
		
		if (!Loader.isModLoaded("ForgeMicroblock") ||
		    !copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceSaws)) return;
		
		mutator.registerShapedMutation(new RecipeMatcherOutputItem("ForgeMicroblock:sawStone") {
				@Override public boolean matches(IRecipe recipe) {
					return (super.matches(recipe) && (recipe instanceof ShapedOreRecipe)); } },
				"   ",
				" oo", 'o', Items.flint);
		
		mutator.registerShapedStationMutation("ForgeMicroblock:sawIron", 2,
				"   ",
				" oo", 'o', "ingotIron");
		
		mutator.registerShapedStationMutation("ForgeMicroblock:sawDiamond", 4,
				"   ",
				" oo", 'o', "gemDiamond");
		
	}
	
}

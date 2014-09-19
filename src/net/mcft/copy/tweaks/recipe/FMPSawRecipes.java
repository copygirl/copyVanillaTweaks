package net.mcft.copy.tweaks.recipe;

import net.mcft.copy.tweaks.VanillaTweaksConfig;
import net.mcft.copy.tweaks.copyVanillaTweaks;
import net.mcft.copy.tweaks.util.StationUtils;
import net.mcft.copy.tweaks.util.recipe.RecipeActionRemove;
import net.mcft.copy.tweaks.util.recipe.RecipeIterator;
import net.mcft.copy.tweaks.util.recipe.RecipeMatcherOutputItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public final class FMPSawRecipes {
	
	private static final Item stoneRod   = (Item)Item.itemRegistry.getObject("ForgeMicroblock:stoneRod");
	private static final Item sawStone   = (Item)Item.itemRegistry.getObject("ForgeMicroblock:sawStone");
	private static final Item sawIron    = (Item)Item.itemRegistry.getObject("ForgeMicroblock:sawIron");
	private static final Item sawDiamond = (Item)Item.itemRegistry.getObject("ForgeMicroblock:sawDiamond");
	
	private FMPSawRecipes() {  }
	
	public static void register(RecipeIterator iterator) {
		
		if (!copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceSaws) ||
		    !Loader.isModLoaded("ForgeMicroblock")) return;
		
		iterator.registerAction(new RecipeMatcherOutputItem(sawStone, sawIron, sawDiamond) {
				@Override public boolean matches(IRecipe recipe) {
					return (super.matches(recipe) && (recipe instanceof ShapedOreRecipe)); }
			}, RecipeActionRemove.instance);
		
	}
	
	public static void add() {
		
		if (!copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceSaws) ||
		    !Loader.isModLoaded("ForgeMicroblock")) return;
		
		GameRegistry.addRecipe(new ShapedOreRecipe(sawStone,
				"/--",
				"/oo", 'o', Items.flint,
				       '/', "stickWood",
				       '-', stoneRod));
		
		StationUtils.addShaped(sawIron, 2,
				"/--",
				"/oo", 'o', "ingotIron",
				       '/', "stickWood",
				       '-', stoneRod);
		
		StationUtils.addShaped(sawDiamond, 4,
				"/--",
				"/oo", 'o', "gemDiamond",
				       '/', "stickWood",
				       '-', stoneRod);
		
	}
	
}

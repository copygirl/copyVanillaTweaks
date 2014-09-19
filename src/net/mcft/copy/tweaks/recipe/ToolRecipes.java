package net.mcft.copy.tweaks.recipe;

import net.mcft.copy.core.config.setting.Setting;
import net.mcft.copy.tweaks.VanillaTweaksConfig;
import net.mcft.copy.tweaks.copyVanillaTweaks;
import net.mcft.copy.tweaks.util.StationUtils;
import net.mcft.copy.tweaks.util.recipe.RecipeActionRemove;
import net.mcft.copy.tweaks.util.recipe.RecipeIterator;
import net.mcft.copy.tweaks.util.recipe.RecipeMatcherOutputItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public final class ToolRecipes {
	
	private ToolRecipes() {  }
	
	public static void register(RecipeIterator iterator) {
		
		registerRemove(iterator, VanillaTweaksConfig.replaceStoneTools,
		               Items.stone_sword, Items.stone_pickaxe, Items.stone_shovel, Items.stone_axe, Items.stone_hoe);
		registerRemove(iterator, VanillaTweaksConfig.replaceGoldTools,
		               Items.golden_sword, Items.golden_pickaxe, Items.golden_shovel, Items.golden_axe, Items.golden_hoe);
		registerRemove(iterator, VanillaTweaksConfig.replaceIronTools,
		               Items.iron_sword, Items.iron_pickaxe, Items.iron_shovel, Items.iron_axe, Items.iron_hoe);
		registerRemove(iterator, VanillaTweaksConfig.replaceDiamondTools,
		               Items.diamond_sword, Items.diamond_pickaxe, Items.diamond_shovel, Items.diamond_axe, Items.diamond_hoe);
		
	}
	
	public static void registerRemove(RecipeIterator iterator, Setting<Boolean> setting, Object... items) {
		if (copyVanillaTweaks.config.<Boolean>get(setting))
			iterator.registerAction(new RecipeMatcherOutputItem(items), RecipeActionRemove.instance);
	}
	
	public static void add() {
		
		// ====== STONE TOOLS ======
		if (copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceStoneTools)) {
			
			ToolMaterial.STONE.customCraftingMaterial = Items.flint;
			GameRegistry.addRecipe(new ShapedOreRecipe(Items.stone_sword,
					" x ",
					" o ",
					"s/s", 'o', "stone",
					       'x', Items.flint,
					       '/', "stickWood",
					       's', Items.string));
			GameRegistry.addRecipe(new ShapedOreRecipe(Items.stone_pickaxe,
					"xox",
					"s/s",
					" / ", 'o', "stone",
					       'x', Items.flint,
					       '/', "stickWood",
					       's', Items.string));
			GameRegistry.addRecipe(new ShapedOreRecipe(Items.stone_shovel,
					" x ",
					"s/s",
					" / ", 'x', Items.flint,
					       '/', "stickWood",
					       's', Items.string));
			GameRegistry.addRecipe(new ShapedOreRecipe(Items.stone_axe,
					"xo ",
					"x/ ",
					"s/s", 'o', "stone",
					       'x', Items.flint,
					       '/', "stickWood",
					       's', Items.string));
			GameRegistry.addRecipe(new ShapedOreRecipe(Items.stone_hoe,
					"xo ",
					"s/s",
					" / ", 'o', "stone",
					       'x', Items.flint,
					       '/', "stickWood",
					       's', Items.string));
			
		}
		// ====== GOLD TOOLS ======
		if (copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceGoldTools)) {
			
			StationUtils.addShaped(Items.golden_sword, 2,
					"o",
					"o",
					"/", 'o', "ingotGold",
					     '/', "stickWood");
			StationUtils.addShaped(Items.golden_pickaxe, 2,
					"ooo",
					" / ",
					" / ", 'o', "ingotGold",
					       '/', "stickWood");
			StationUtils.addShaped(Items.golden_shovel, 1,
					"o",
					"/",
					"/", 'o', "ingotGold",
					     '/', "stickWood");
			StationUtils.addShaped(Items.golden_axe, 1,
					"oo",
					"o/",
					" /", 'o', "ingotGold",
					      '/', "stickWood");
			StationUtils.addShaped(Items.golden_hoe, 1,
					"oo",
					" /",
					" /", 'o', "ingotGold",
					      '/', "stickWood");
			
		}
		// ====== IRON TOOLS ======
		if (copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceIronTools)) {
			
			StationUtils.addShaped(Items.iron_sword, 4,
					"o",
					"o",
					"/", 'o', "ingotIron",
					     '/', "stickWood");
			StationUtils.addShaped(Items.iron_pickaxe, 6,
					"ooo",
					" / ",
					" / ", 'o', "ingotIron",
					       '/', "stickWood");
			StationUtils.addShaped(Items.iron_shovel, 3,
					"o",
					"/",
					"/", 'o', "ingotIron",
					     '/', "stickWood");
			StationUtils.addShaped(Items.iron_axe, 3,
					"oo",
					"o/",
					" /", 'o', "ingotIron",
					      '/', "stickWood");
			StationUtils.addShaped(Items.iron_hoe, 2,
					"oo",
					" /",
					" /", 'o', "ingotIron",
					      '/', "stickWood");
			
		}
		// ====== DIAMOND TOOLS ======
		if (copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceDiamondTools)) {
			
			StationUtils.addShaped(Items.diamond_sword, 8,
					"o",
					"x",
					"/", 'o', "gemDiamond",
					     'x', "gemEmerald",
					     '/', "stickWood");
			StationUtils.addShaped(Items.diamond_pickaxe, 12,
					"oxo",
					" / ",
					" / ", 'o', "gemDiamond",
					       'x', "gemEmerald",
					       '/', "stickWood");
			StationUtils.addShaped(Items.diamond_shovel, 6,
					"o",
					"/",
					"/", 'o', "gemDiamond",
					     '/', "stickWood");
			StationUtils.addShaped(Items.diamond_axe, 6,
					"ox",
					"o/",
					" /", 'o', "gemDiamond",
					      'x', "gemEmerald",
					      '/', "stickWood");
			StationUtils.addShaped(Items.diamond_hoe, 4,
					"ox",
					" /",
					" /", 'o', "gemDiamond",
					      'x', "gemEmerald",
					      '/', "stickWood");
			
		}
		
	}
	
}

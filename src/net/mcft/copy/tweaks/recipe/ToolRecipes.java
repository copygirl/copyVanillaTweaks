package net.mcft.copy.tweaks.recipe;

import net.mcft.copy.tweaks.VanillaTweaksConfig;
import net.mcft.copy.tweaks.copyVanillaTweaks;
import net.mcft.copy.tweaks.util.recipe.RecipeMutator;
import net.minecraft.init.Items;
import net.minecraft.item.Item.ToolMaterial;

public final class ToolRecipes {
	
	private ToolRecipes() {  }
	
	public static void register(RecipeMutator mutator) {
		
		// ====== STONE TOOLS ======
		if (copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceStoneTools)) {
			ToolMaterial.STONE.customCraftingMaterial = Items.flint;
			mutator.registerShapedMutation(Items.stone_sword,
					" x ",
					" o ",
					"s s", 'o', "stone",
					       'x', Items.flint,
					       's', Items.string);
			mutator.registerShapedMutation(Items.stone_pickaxe,
					"xox",
					"s s",
					"   ", 'o', "stone",
					       'x', Items.flint,
					       's', Items.string);
			mutator.registerShapedMutation(Items.stone_shovel,
					" x ",
					"   ",
					"s s", 'x', Items.flint,
					       's', Items.string);
			mutator.registerShapedMutation(Items.stone_axe,
					"xo ",
					"x  ",
					"s s", 'o', "stone",
					       'x', Items.flint,
					       's', Items.string);
			mutator.registerShapedMutation(Items.stone_hoe,
					"xo ",
					"s s",
					"   ", 'o', "stone",
					       'x', Items.flint,
					       's', Items.string);
		}
		
		// ====== GOLD TOOLS ======
		if (copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceGoldTools))
			Recipes.registerStationMutation(mutator, Items.golden_sword, 2,
			                                         Items.golden_pickaxe, 2,
			                                         Items.golden_shovel, 1,
			                                         Items.golden_axe, 1,
			                                         Items.golden_hoe, 1);
		
		// ====== IRON TOOLS ======
		if (copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceIronTools))
			Recipes.registerStationMutation(mutator, Items.iron_sword, 4,
			                                         Items.iron_pickaxe, 6,
			                                         Items.iron_shovel, 3,
			                                         Items.iron_axe, 3,
			                                         Items.iron_hoe, 2);
		
		// ====== DIAMOND TOOLS ======
		if (copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceDiamondTools)) {
			mutator.registerShapedStationMutation(Items.diamond_sword, 8,
					" ",
					"o",
					" ", 'o', "gemEmerald");
			mutator.registerShapedStationMutation(Items.diamond_pickaxe, 12,
					" o ",
					"   ",
					"   ", 'o', "gemEmerald");
			mutator.registerStationMutation(Items.diamond_shovel, 6);
			mutator.registerShapedStationMutation(Items.diamond_axe, 6,
					" o",
					"  ",
					"  ", 'o', "gemEmerald");
			mutator.registerShapedStationMutation(Items.diamond_hoe, 4,
					" o",
					"  ",
					"  ", 'o', "gemEmerald");
		}
		
	}
	
}

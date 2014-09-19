package net.mcft.copy.tweaks.recipe;

import net.mcft.copy.core.config.setting.Setting;
import net.mcft.copy.tweaks.VanillaTweaksConfig;
import net.mcft.copy.tweaks.copyVanillaTweaks;
import net.mcft.copy.tweaks.util.StationUtils;
import net.mcft.copy.tweaks.util.recipe.RecipeActionRemove;
import net.mcft.copy.tweaks.util.recipe.RecipeIterator;
import net.mcft.copy.tweaks.util.recipe.RecipeMatcherOutputItem;
import net.minecraft.init.Items;

public final class ArmorRecipes {
	
	private ArmorRecipes() {  }
	
	public static void register(RecipeIterator iterator) {
		
		registerRemove(iterator, VanillaTweaksConfig.replaceGoldArmor,
		               Items.golden_helmet, Items.golden_chestplate, Items.golden_leggings, Items.golden_boots);
		registerRemove(iterator, VanillaTweaksConfig.replaceIronArmor,
		               Items.iron_helmet, Items.iron_chestplate, Items.iron_leggings, Items.iron_boots);
		registerRemove(iterator, VanillaTweaksConfig.replaceDiamondArmor,
		               Items.diamond_helmet, Items.diamond_chestplate, Items.diamond_leggings, Items.diamond_boots);
		
	}
	
	public static void registerRemove(RecipeIterator iterator, Setting<Boolean> setting, Object... items) {
		if (copyVanillaTweaks.config.<Boolean>get(setting))
			iterator.registerAction(new RecipeMatcherOutputItem(items), RecipeActionRemove.instance);
	}
	
	public static void add() {
		
		// ====== GOLD ARMOR ======
		if (copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceGoldArmor)) {
			
			StationUtils.addShaped(Items.golden_helmet, 2,
					"ooo",
					"oHo", 'o', "ingotGold",
					       'H', Items.leather_helmet);
			StationUtils.addShaped(Items.golden_chestplate, 3,
					"oCo",
					"ooo",
					"ooo", 'o', "ingotGold",
					       'C', Items.leather_chestplate);
			StationUtils.addShaped(Items.golden_leggings, 2,
					"ooo",
					"oLo",
					"o o", 'o', "ingotGold",
					       'L', Items.leather_leggings);
			StationUtils.addShaped(Items.golden_boots, 2,
					"oBo",
					"o o", 'o', "ingotGold",
					       'B', Items.leather_boots);
			
		}
		// ====== IRON ARMOR ======
		if (copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceIronArmor)) {
			
			StationUtils.addShaped(Items.iron_helmet, 4,
					"ooo",
					"oHo", 'o', "ingotIron",
					       'H', Items.leather_helmet);
			StationUtils.addShaped(Items.iron_chestplate, 8,
					"oCo",
					"ooo",
					"ooo", 'o', "ingotIron",
					       'C', Items.leather_chestplate);
			StationUtils.addShaped(Items.iron_leggings, 6,
					"ooo",
					"oLo",
					"o o", 'o', "ingotIron",
					       'L', Items.leather_leggings);
			StationUtils.addShaped(Items.iron_boots, 4,
					"oBo",
					"o o", 'o', "ingotIron",
					       'B', Items.leather_boots);
			
		}
		// ====== DIAMOND ARMOR ======
		if (copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceDiamondArmor)) {
			
			StationUtils.addShaped(Items.diamond_helmet, 8,
					"oxo",
					"oHo", 'o', "gemDiamond",
					       'x', "gemEmerald",
					       'H', Items.leather_helmet);
			StationUtils.addShaped(Items.diamond_chestplate, 16,
					"oCo",
					"oxo",
					"ooo", 'o', "gemDiamond",
					       'x', "gemEmerald",
					       'C', Items.leather_chestplate);
			StationUtils.addShaped(Items.diamond_leggings, 12,
					"oxo",
					"oLo",
					"o o", 'o', "gemDiamond",
					       'x', "gemEmerald",
					       'L', Items.leather_leggings);
			StationUtils.addShaped(Items.diamond_boots, 8,
					"oBo",
					"o o", 'o', "gemDiamond",
					       'B', Items.leather_boots);
			
		}
		
	}
	
}

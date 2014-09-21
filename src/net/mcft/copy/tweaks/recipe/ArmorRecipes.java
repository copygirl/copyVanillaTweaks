package net.mcft.copy.tweaks.recipe;

import net.mcft.copy.tweaks.VanillaTweaksConfig;
import net.mcft.copy.tweaks.copyVanillaTweaks;
import net.mcft.copy.tweaks.util.ItemUtils;
import net.mcft.copy.tweaks.util.StationUtils;
import net.mcft.copy.tweaks.util.recipe.RecipeMutator;
import net.minecraft.init.Items;

public final class ArmorRecipes {
	
	private ArmorRecipes() {  }
	
	public static void register(RecipeMutator mutator) {
		
		// ====== GOLD ARMOR ======
		if (copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceGoldArmor))
			registerArmorMutation(mutator, Items.golden_helmet, 2,
			                               Items.golden_chestplate, 4,
			                               Items.golden_leggings, 3,
			                               Items.golden_boots, 2);
		
		// ====== IRON ARMOR ======
		if (copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceIronArmor))
			registerArmorMutation(mutator, Items.iron_helmet, 4,
			                               Items.iron_chestplate, 8,
			                               Items.iron_leggings, 6,
			                               Items.iron_boots, 4);
		
		// ====== DIAMOND ARMOR ======
		if (copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceDiamondArmor)) {
			mutator.registerShapedStationMutation(Items.diamond_helmet, 8,
					" o ",
					" H ", 'o', "gemEmerald",
					       'H', Items.leather_helmet);
			mutator.registerShapedStationMutation(Items.diamond_chestplate, 16,
					" C ",
					" o ",
					"   ", 'o', "gemEmerald",
					       'C', Items.leather_chestplate);
			mutator.registerShapedStationMutation(Items.diamond_leggings, 12,
					" o ",
					" L ",
					"   ", 'o', "gemEmerald",
					'L', Items.leather_leggings);
			mutator.registerShapedStationMutation(Items.diamond_boots, 8,
					" B ",
					"   ", 'B', Items.leather_boots);
		}
		
	}

	public static void registerArmorMutation(RecipeMutator mutator, Object helmet, int helmetExperience,
	                                                                Object chestplate, int chestplateExperience,
	                                                                Object leggings, int leggingsExperience,
	                                                                Object boots, int bootsExperience) {
		mutator.registerShapedStationMutation(ItemUtils.getItemFromObject(helmet), helmetExperience,
				"   ",
				" H ", 'H', Items.leather_helmet);
		mutator.registerShapedStationMutation(ItemUtils.getItemFromObject(chestplate), chestplateExperience,
				" C ",
				"   ",
				"   ", 'C', Items.leather_chestplate);
		mutator.registerShapedStationMutation(ItemUtils.getItemFromObject(leggings), leggingsExperience,
				"   ",
				" L ",
				"   ", 'L', Items.leather_leggings);
		mutator.registerShapedStationMutation(ItemUtils.getItemFromObject(boots), bootsExperience,
				" B ",
				"   ", 'B', Items.leather_boots);
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

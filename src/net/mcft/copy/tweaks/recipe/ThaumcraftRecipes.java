package net.mcft.copy.tweaks.recipe;

import net.mcft.copy.tweaks.VanillaTweaksConfig;
import net.mcft.copy.tweaks.copyVanillaTweaks;
import net.mcft.copy.tweaks.util.recipe.RecipeMutator;
import cpw.mods.fml.common.Loader;

public final class ThaumcraftRecipes {
	
	private ThaumcraftRecipes() {  }
	
	public static void register(RecipeMutator mutator) {
		
		if (!Loader.isModLoaded("Thaumcraft") ||
		    !copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceThaumcraft)) return;
		
		// ====== WAND ======
		mutator.registerStationMutation("Thaumcraft:WandCasting", 5);
		
		// ====== THAUMIUM TOOLS ======
		Recipes.registerStationMutation(mutator,
				"Thaumcraft:ItemSwordThaumium", 4,
				"Thaumcraft:ItemPickThaumium", 6,
				"Thaumcraft:ItemShovelThaumium", 3,
				"Thaumcraft:ItemAxeThaumium", 3,
				"Thaumcraft:ItemHoeThaumium", 2);
		// ====== THAUMIUM ARMOR ======
		ArmorRecipes.registerArmorMutation(mutator,
				"Thaumcraft:ItemHelmetThaumium", 4,
				"Thaumcraft:ItemChestplateThaumium", 8,
				"Thaumcraft:ItemLeggingsThaumium", 6,
				"Thaumcraft:ItemBootsThaumium", 4);
		
		// ====== VOID TOOLS ======
		Recipes.registerStationMutation(mutator,
				"Thaumcraft:ItemSwordVoid", 8,
				"Thaumcraft:ItemPickVoid", 12,
				"Thaumcraft:ItemShovelVoid", 6,
				"Thaumcraft:ItemAxeVoid", 6,
				"Thaumcraft:ItemHoeVoid", 4);
		// ====== VOID ARMOR ======
		ArmorRecipes.registerArmorMutation(mutator,
				"Thaumcraft:ItemHelmetVoid", 8,
				"Thaumcraft:ItemChestplateVoid", 16,
				"Thaumcraft:ItemLeggingsVoid", 12,
				"Thaumcraft:ItemBootsVoid", 8);
		
	}
	
}

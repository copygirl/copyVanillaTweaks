package net.mcft.copy.tweaks.recipe;

import net.mcft.copy.tweaks.VanillaTweaksConfig;
import net.mcft.copy.tweaks.copyVanillaTweaks;
import net.mcft.copy.tweaks.util.recipe.RecipeMutator;
import cpw.mods.fml.common.Loader;

public final class BotaniaRecipes {
	
	private BotaniaRecipes() {  }
	
	public static void register(RecipeMutator mutator) {
		
		if (!Loader.isModLoaded("Botania") ||
		    !copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceBotania)) return;
		
		// ====== MANASTEEL TOOLS ======
		Recipes.registerStationMutation(mutator,
				"Botania:manasteelSword", 8,
				"Botania:manasteelPick", 12,
				"Botania:manasteelShovel", 6,
				"Botania:manasteelAxe", 6,
				"Botania:manasteelShears", 2);
		// ====== MANASTEEL ARMOR ======
		ArmorRecipes.registerArmorMutation(mutator,
				"Botania:manasteelHelm", 8,
				"Botania:manasteelChest", 16,
				"Botania:manasteelLegs", 12,
				"Botania:manasteelBoots", 8);
		
		// ====== TERRASTEEL TOOLS ======
		Recipes.registerStationMutation(mutator,
				"Botania:terraSword", 12,
				"Botania:terraPick", 18);
		// ====== TERRASTEEL ARMOR ======
		ArmorRecipes.registerArmorMutation(mutator,
				"Botania:terrasteelHelm", 12,
				"Botania:terrasteelChest", 24,
				"Botania:terrasteelLegs", 18,
				"Botania:terrasteelBoots", 12);
		
		// ====== ELEMENTIUM TOOLS ======
		Recipes.registerStationMutation(mutator,
				"Botania:elementiumSword", 16,
				"Botania:elementiumPick", 24,
				"Botania:elementiumShovel", 12,
				"Botania:elementiumAxe", 12,
				"Botania:elementiumShears", 4);
		// ====== ELEMENTIUM ARMOR ======
		ArmorRecipes.registerArmorMutation(mutator,
				"Botania:elementiumHelm", 16,
				"Botania:elementiumChest", 32,
				"Botania:elementiumLegs", 24,
				"Botania:elementiumBoots", 16);
		
	}
	
}

package net.mcft.copy.tweaks.recipe;

import net.mcft.copy.core.config.setting.Setting;
import net.mcft.copy.tweaks.VanillaTweaksConfig;
import net.mcft.copy.tweaks.copyVanillaTweaks;
import net.mcft.copy.tweaks.util.StationUtils;
import net.mcft.copy.tweaks.util.recipe.RecipeActionRemove;
import net.mcft.copy.tweaks.util.recipe.RecipeIterator;
import net.mcft.copy.tweaks.util.recipe.RecipeMatcherOutputItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

public final class MiscRecipes {
	
	private MiscRecipes() {  }
	
	public static void register(RecipeIterator iterator) {
		
		registerRemove(iterator, VanillaTweaksConfig.replaceFlintAndSteel, Items.flint_and_steel);
		registerRemove(iterator, VanillaTweaksConfig.replaceShears, Items.shears);
		registerRemove(iterator, VanillaTweaksConfig.replaceBucket, Items.bucket);
		registerRemove(iterator, VanillaTweaksConfig.replaceBed, Items.bed);
		registerRemove(iterator, VanillaTweaksConfig.replaceEnchantingTable, Blocks.enchanting_table);
		registerRemove(iterator, VanillaTweaksConfig.replaceAnvil, Blocks.anvil);
		
	}
	
	private static void registerRemove(RecipeIterator iterator, Setting<Boolean> setting, Object... items) {
		if (copyVanillaTweaks.config.<Boolean>get(setting))
			iterator.registerAction(new RecipeMatcherOutputItem(items), RecipeActionRemove.instance);
	}
	
	public static void add() {
		
		if (copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceFlintAndSteel))
			StationUtils.addShapeless(Items.flint_and_steel, 1, Items.flint, "ingotIron");
		
		if (copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceShears))
			StationUtils.addShaped(Items.shears, 1,
					" o",
					"o ", 'o', "ingotIron");
		
		if (copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceBucket))
			StationUtils.addShaped(Items.bucket, 2,
					"o o",
					" o ", 'o', "ingotIron");
		
		if (copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceBed))
			StationUtils.addShaped(Items.bed, 20,
					"WWW",
					"PPP", 'W', Blocks.wool,
					       'P', "plankWood");
		
		if (copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceEnchantingTable))
			StationUtils.addShaped(Blocks.enchanting_table, 30,
					" B ",
					"o#o",
					"###", 'B', Items.book,
					       'o', "gemDiamond",
					       '#', Blocks.obsidian);
		
		if (copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceAnvil))
			StationUtils.addShaped(Blocks.anvil, 15,
					"OOO",
					" o ",
					"ooo", 'O', "blockIron",
					       'o', "ingotIron");
		
	}
	
}

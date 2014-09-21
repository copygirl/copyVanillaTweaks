package net.mcft.copy.tweaks.recipe;

import net.mcft.copy.core.config.setting.Setting;
import net.mcft.copy.tweaks.VanillaTweaksConfig;
import net.mcft.copy.tweaks.copyVanillaTweaks;
import net.mcft.copy.tweaks.util.recipe.RecipeMutator;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

public final class MiscRecipes {
	
	private MiscRecipes() {  }
	
	public static void register(RecipeMutator mutator) {
		
		register(mutator, VanillaTweaksConfig.replaceFlintAndSteel, Items.flint_and_steel, 1);
		register(mutator, VanillaTweaksConfig.replaceShears, Items.shears, 1);
		register(mutator, VanillaTweaksConfig.replaceBucket, Items.bucket, 2);
		register(mutator, VanillaTweaksConfig.replaceBed, Items.bed, 20);
		register(mutator, VanillaTweaksConfig.replaceEnchantingTable, Blocks.enchanting_table, 30);
		register(mutator, VanillaTweaksConfig.replaceAnvil, Blocks.anvil, 15);
		
	}
	
	private static void register(RecipeMutator mutator, Setting<Boolean> setting, Object match, int experience) {
		if (copyVanillaTweaks.config.<Boolean>get(setting)) mutator.registerStationMutation(match, experience);
	}
	
}

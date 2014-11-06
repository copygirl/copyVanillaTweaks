package net.mcft.copy.tweaks.recipe;

import net.mcft.copy.tweaks.VanillaTweaksConfig;
import net.mcft.copy.tweaks.copyVanillaTweaks;
import net.mcft.copy.tweaks.util.StationUtils;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public final class NewRecipes {
	
	private NewRecipes() {  }
	
	public static void add() {
		
		if (copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.addNameTag))
			StationUtils.addShapeless(Items.name_tag, 10,
					Items.paper, Items.string, new ItemStack(Items.dye, 1, 4));
		
	}
	
}

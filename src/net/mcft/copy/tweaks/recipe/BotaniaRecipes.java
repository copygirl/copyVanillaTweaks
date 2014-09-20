package net.mcft.copy.tweaks.recipe;

import net.mcft.copy.tweaks.VanillaTweaksConfig;
import net.mcft.copy.tweaks.copyVanillaTweaks;
import net.mcft.copy.tweaks.util.StationUtils;
import net.mcft.copy.tweaks.util.recipe.RecipeActionRemove;
import net.mcft.copy.tweaks.util.recipe.RecipeIterator;
import net.mcft.copy.tweaks.util.recipe.RecipeMatcherOutputItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public final class BotaniaRecipes {
	
	private static final Item resource = GameRegistry.findItem("Botania", "manaResource");
	private static final ItemStack twig = new ItemStack(resource, 1, 3);
	private static final ItemStack ingotManasteel  = new ItemStack(resource, 1, 0);
	private static final ItemStack ingotTerrasteel = new ItemStack(resource, 1, 4);
	private static final ItemStack ingotElementium = new ItemStack(resource, 1, 7);
	
	private static final ItemStack manaTablet = new ItemStack(GameRegistry.findItem("Botania", "manaTablet"), 1, OreDictionary.WILDCARD_VALUE);
	
	
	private static final Item manasteelSword   = GameRegistry.findItem("Botania", "manasteelSword");
	private static final Item manasteelPickaxe = GameRegistry.findItem("Botania", "manasteelPick");
	private static final Item manasteelShovel  = GameRegistry.findItem("Botania", "manasteelShovel");
	private static final Item manasteelAxe     = GameRegistry.findItem("Botania", "manasteelAxe");
	private static final Item manasteelShears  = GameRegistry.findItem("Botania", "manasteelShears");
	
	private static final Item manasteelHelmet     = GameRegistry.findItem("Botania", "manasteelHelm");
	private static final Item manasteelChestplate = GameRegistry.findItem("Botania", "manasteelChest");
	private static final Item manasteelLeggings   = GameRegistry.findItem("Botania", "manasteelLegs");
	private static final Item manasteelBoots      = GameRegistry.findItem("Botania", "manasteelBoots");
	
	
	private static final Item terraSword   = GameRegistry.findItem("Botania", "terraSword");
	private static final Item terraPickaxe = GameRegistry.findItem("Botania", "terraPick");
	
	private static final Item terrasteelHelmet     = GameRegistry.findItem("Botania", "terrasteelHelm");
	private static final Item terrasteelChestplate = GameRegistry.findItem("Botania", "terrasteelChest");
	private static final Item terrasteelLeggings   = GameRegistry.findItem("Botania", "terrasteelLegs");
	private static final Item terrasteelBoots      = GameRegistry.findItem("Botania", "terrasteelBoots");
	
	
	private static final Item elementiumSword   = GameRegistry.findItem("Botania", "elementiumSword");
	private static final Item elementiumPickaxe = GameRegistry.findItem("Botania", "elementiumPick");
	private static final Item elementiumShovel  = GameRegistry.findItem("Botania", "elementiumShovel");
	private static final Item elementiumAxe     = GameRegistry.findItem("Botania", "elementiumAxe");
	private static final Item elementiumShears  = GameRegistry.findItem("Botania", "elementiumShears");
	
	private static final Item elementiumHelmet     = GameRegistry.findItem("Botania", "elementiumHelm");
	private static final Item elementiumChestplate = GameRegistry.findItem("Botania", "elementiumChest");
	private static final Item elementiumLeggings   = GameRegistry.findItem("Botania", "elementiumLegs");
	private static final Item elementiumBoots      = GameRegistry.findItem("Botania", "elementiumBoots");
	
	
	private BotaniaRecipes() {  }
	
	public static void register(RecipeIterator iterator) {
		
		if (!copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceBotania) ||
		    !Loader.isModLoaded("Botania")) return;
		
		registerRemove(iterator, manasteelSword, manasteelPickaxe, manasteelShovel, manasteelAxe, manasteelShears);
		registerRemove(iterator, manasteelHelmet, manasteelChestplate, manasteelLeggings, manasteelBoots);
		
		registerRemove(iterator, terraSword, terraPickaxe);
		registerRemove(iterator, terrasteelHelmet, terrasteelChestplate, terrasteelLeggings, terrasteelBoots);
		
		registerRemove(iterator, elementiumSword, elementiumPickaxe, elementiumShovel, elementiumAxe, elementiumShears);
		registerRemove(iterator, elementiumHelmet, elementiumChestplate, elementiumLeggings, elementiumBoots);
		
	}
	
	private static void registerRemove(RecipeIterator iterator, Object... items) {
		iterator.registerAction(new RecipeMatcherOutputItem(items), RecipeActionRemove.instance);
	}
	
	public static void add() {
		
		if (!copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceBotania) ||
		    !Loader.isModLoaded("Botania")) return;
		
		// ====== MANASTEEL TOOLS ======
		StationUtils.addShaped(manasteelSword, 8,
				"o",
				"o",
				"/", 'o', ingotManasteel,
				     '/', twig);
		StationUtils.addShaped(manasteelPickaxe, 12,
				"ooo",
				" / ",
				" / ", 'o', ingotManasteel,
				       '/', twig);
		StationUtils.addShaped(manasteelShovel, 6,
				"o",
				"/",
				"/", 'o', ingotManasteel,
				     '/', twig);
		StationUtils.addShaped(manasteelAxe, 6,
				"oo",
				"o/",
				" /", 'o', ingotManasteel,
				      '/', twig);
		StationUtils.addShaped(manasteelShears, 2,
				" o",
				"o ", 'o', ingotManasteel);
		
		// ====== MANASTEEL ARMOR ======
		StationUtils.addShaped(manasteelHelmet, 8,
				"ooo",
				"oHo", 'o', ingotManasteel,
				       'H', Items.leather_helmet);
		StationUtils.addShaped(manasteelChestplate, 16,
				"oCo",
				"ooo",
				"ooo", 'o', ingotManasteel,
				       'C', Items.leather_chestplate);
		StationUtils.addShaped(manasteelLeggings, 12,
				"ooo",
				"oLo",
				"o o", 'o', ingotManasteel,
				       'L', Items.leather_leggings);
		StationUtils.addShaped(manasteelBoots, 8,
				"oBo",
				"o o", 'o', ingotManasteel,
				       'B', Items.leather_boots);
		
		// ====== TERRASTEEL TOOLS ======
		StationUtils.addShaped(terraSword, 12,
				"o",
				"o",
				"/", 'o', ingotTerrasteel,
				     '/', twig);
		StationUtils.addShaped(terraPickaxe, 16,
				"oTo",
				"o/o",
				" / ", 'T', manaTablet,
				       'o', ingotTerrasteel,
				       '/', twig);
		
		// ====== TERRASTEEL ARMOR ======
		StationUtils.addShaped(terrasteelHelmet, 12,
				"ooo",
				"oHo", 'o', ingotTerrasteel,
				       'H', Items.leather_helmet);
		StationUtils.addShaped(terrasteelChestplate, 24,
				"oCo",
				"ooo",
				"ooo", 'o', ingotTerrasteel,
				       'C', Items.leather_chestplate);
		StationUtils.addShaped(terrasteelLeggings, 18,
				"ooo",
				"oLo",
				"o o", 'o', ingotTerrasteel,
				       'L', Items.leather_leggings);
		StationUtils.addShaped(terrasteelBoots, 12,
				"oBo",
				"o o", 'o', ingotTerrasteel,
				       'B', Items.leather_boots);
		
		// ====== ELEMENTIUM TOOLS ======
		StationUtils.addShaped(elementiumSword, 16,
				"o",
				"o",
				"/", 'o', ingotElementium,
				     '/', twig);
		StationUtils.addShaped(elementiumPickaxe, 24,
				"ooo",
				" / ",
				" / ", 'o', ingotElementium,
				       '/', twig);
		StationUtils.addShaped(elementiumShovel, 12,
				"o",
				"/",
				"/", 'o', ingotElementium,
				     '/', twig);
		StationUtils.addShaped(elementiumAxe, 12,
				"oo",
				"o/",
				" /", 'o', ingotElementium,
				      '/', twig);
		StationUtils.addShaped(elementiumShears, 4,
				" o",
				"o ", 'o', ingotElementium);
		
		// ====== ELEMENTIUM ARMOR ======
		StationUtils.addShaped(elementiumHelmet, 16,
				"ooo",
				"oHo", 'o', ingotElementium,
				       'H', Items.leather_helmet);
		StationUtils.addShaped(elementiumChestplate, 32,
				"oCo",
				"ooo",
				"ooo", 'o', ingotElementium,
				       'C', Items.leather_chestplate);
		StationUtils.addShaped(elementiumLeggings, 24,
				"ooo",
				"oLo",
				"o o", 'o', ingotElementium,
				       'L', Items.leather_leggings);
		StationUtils.addShaped(elementiumBoots, 16,
				"oBo",
				"o o", 'o', ingotElementium,
				       'B', Items.leather_boots);
		
	}
	
}

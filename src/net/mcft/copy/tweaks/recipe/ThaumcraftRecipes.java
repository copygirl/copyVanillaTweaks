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
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public final class ThaumcraftRecipes {
	
	private static final Item resource = GameRegistry.findItem("Thaumcraft", "ItemResource");
	private static final ItemStack ingotThaumium = new ItemStack(resource, 1, 2);
	private static final ItemStack ingotVoid     = new ItemStack(resource, 1, 16);
	
	
	private static final Item wand    = GameRegistry.findItem("Thaumcraft", "WandCasting");
	private static final Item wandCap = GameRegistry.findItem("Thaumcraft", "WandCap");
	private static final ItemStack wandCapIron = new ItemStack(wandCap, 1, 0);
	
	
	private static final Item thaumiumSword   = GameRegistry.findItem("Thaumcraft", "ItemSwordThaumium");
	private static final Item thaumiumPickaxe = GameRegistry.findItem("Thaumcraft", "ItemPickThaumium");
	private static final Item thaumiumShovel  = GameRegistry.findItem("Thaumcraft", "ItemShovelThaumium");
	private static final Item thaumiumAxe     = GameRegistry.findItem("Thaumcraft", "ItemAxeThaumium");
	private static final Item thaumiumHoe     = GameRegistry.findItem("Thaumcraft", "ItemHoeThaumium");
	
	private static final Item thaumiumHelmet     = GameRegistry.findItem("Thaumcraft", "ItemHelmetThaumium");
	private static final Item thaumiumChestplate = GameRegistry.findItem("Thaumcraft", "ItemChestplateThaumium");
	private static final Item thaumiumLeggings   = GameRegistry.findItem("Thaumcraft", "ItemLeggingsThaumium");
	private static final Item thaumiumBoots      = GameRegistry.findItem("Thaumcraft", "ItemBootsThaumium");
	
	
	private static final Item voidSword   = GameRegistry.findItem("Thaumcraft", "ItemSwordVoid");
	private static final Item voidPickaxe = GameRegistry.findItem("Thaumcraft", "ItemPickVoid");
	private static final Item voidShovel  = GameRegistry.findItem("Thaumcraft", "ItemShovelVoid");
	private static final Item voidAxe     = GameRegistry.findItem("Thaumcraft", "ItemAxeVoid");
	private static final Item voidHoe     = GameRegistry.findItem("Thaumcraft", "ItemHoeVoid");
	
	private static final Item voidHelmet     = GameRegistry.findItem("Thaumcraft", "ItemHelmetVoid");
	private static final Item voidChestplate = GameRegistry.findItem("Thaumcraft", "ItemChestplateVoid");
	private static final Item voidLeggings   = GameRegistry.findItem("Thaumcraft", "ItemLeggingsVoid");
	private static final Item voidBoots      = GameRegistry.findItem("Thaumcraft", "ItemBootsVoid");
	
	
	private ThaumcraftRecipes() {  }
	
	public static void register(RecipeIterator iterator) {
		
		if (!copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceThaumcraft) ||
		    !Loader.isModLoaded("Thaumcraft")) return;
		
		registerRemove(iterator, wand);
		registerRemove(iterator, thaumiumSword, thaumiumPickaxe, thaumiumShovel, thaumiumAxe, thaumiumHoe);
		registerRemove(iterator, thaumiumHelmet, thaumiumChestplate, thaumiumLeggings, thaumiumBoots);
		registerRemove(iterator, voidSword, voidPickaxe, voidShovel, voidAxe, voidHoe);
		registerRemove(iterator, voidHelmet, voidChestplate, voidLeggings, voidBoots);
		
	}
	
	private static void registerRemove(RecipeIterator iterator, Object... items) {
		iterator.registerAction(new RecipeMatcherOutputItem(items), RecipeActionRemove.instance);
	}
	
	public static void add() {
		
		if (!copyVanillaTweaks.config.<Boolean>get(VanillaTweaksConfig.replaceThaumcraft) ||
		    !Loader.isModLoaded("Thaumcraft")) return;
		
		// ====== WAND ======
		StationUtils.addShaped(wand, 4,
				"  o",
				" / ",
				"o  ", 'o', wandCapIron,
				       '/', "stickWood");
		
		// ====== THAUMIUM TOOLS ======
		StationUtils.addShaped(thaumiumSword, 4,
				"o",
				"o",
				"/", 'o', ingotThaumium,
				     '/', "stickWood");
		StationUtils.addShaped(thaumiumPickaxe, 6,
				"ooo",
				" / ",
				" / ", 'o', ingotThaumium,
				       '/', "stickWood");
		StationUtils.addShaped(thaumiumShovel, 3,
				"o",
				"/",
				"/", 'o', ingotThaumium,
				     '/', "stickWood");
		StationUtils.addShaped(thaumiumAxe, 3,
				"oo",
				"o/",
				" /", 'o', ingotThaumium,
				      '/', "stickWood");
		StationUtils.addShaped(thaumiumHoe, 2,
				"oo",
				" /",
				" /", 'o', ingotThaumium,
				      '/', "stickWood");
		
		// ====== THAUMIUM ARMOR ======
		StationUtils.addShaped(thaumiumHelmet, 4,
				"ooo",
				"oHo", 'o', ingotThaumium,
				       'H', Items.leather_helmet);
		StationUtils.addShaped(thaumiumChestplate, 8,
				"oCo",
				"ooo",
				"ooo", 'o', ingotThaumium,
				       'C', Items.leather_chestplate);
		StationUtils.addShaped(thaumiumLeggings, 6,
				"ooo",
				"oLo",
				"o o", 'o', ingotThaumium,
				       'L', Items.leather_leggings);
		StationUtils.addShaped(thaumiumBoots, 4,
				"oBo",
				"o o", 'o', ingotThaumium,
				       'B', Items.leather_boots);
		
		// ====== VOID TOOLS ======
		StationUtils.addShaped(voidSword, 8,
				"o",
				"o",
				"/", 'o', ingotVoid,
				     '/', "stickWood");
		StationUtils.addShaped(voidPickaxe, 12,
				"ooo",
				" / ",
				" / ", 'o', ingotVoid,
				       '/', "stickWood");
		StationUtils.addShaped(voidShovel, 6,
				"o",
				"/",
				"/", 'o', ingotVoid,
				     '/', "stickWood");
		StationUtils.addShaped(voidAxe, 6,
				"oo",
				"o/",
				" /", 'o', ingotVoid,
				      '/', "stickWood");
		StationUtils.addShaped(voidHoe, 4,
				"oo",
				" /",
				" /", 'o', ingotVoid,
				      '/', "stickWood");
		
		// ====== VOID ARMOR ======
		StationUtils.addShaped(voidHelmet, 8,
				"ooo",
				"oHo", 'o', ingotVoid,
				       'H', Items.leather_helmet);
		StationUtils.addShaped(voidChestplate, 16,
				"oCo",
				"ooo",
				"ooo", 'o', ingotVoid,
				       'C', Items.leather_chestplate);
		StationUtils.addShaped(voidLeggings, 12,
				"ooo",
				"oLo",
				"o o", 'o', ingotVoid,
				       'L', Items.leather_leggings);
		StationUtils.addShaped(voidBoots, 8,
				"oBo",
				"o o", 'o', ingotVoid,
				       'B', Items.leather_boots);
		
	}
	
}

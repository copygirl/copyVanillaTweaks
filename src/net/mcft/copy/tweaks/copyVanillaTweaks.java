package net.mcft.copy.tweaks;

import java.io.File;

import net.mcft.copy.core.config.Config;
import net.mcft.copy.core.config.setting.Setting;
import net.mcft.copy.tweaks.handlers.AnimalBreedingGrowthHandler;
import net.mcft.copy.tweaks.handlers.GravelFlintDropHandler;
import net.mcft.copy.tweaks.handlers.MobDropHandler;
import net.mcft.copy.tweaks.recipe.ArmorRecipes;
import net.mcft.copy.tweaks.recipe.FMPSawRecipes;
import net.mcft.copy.tweaks.recipe.MiscRecipes;
import net.mcft.copy.tweaks.recipe.ToolRecipes;
import net.mcft.copy.tweaks.util.ItemUtils;
import net.mcft.copy.tweaks.util.recipe.RecipeActionReplace;
import net.mcft.copy.tweaks.util.recipe.RecipeIterator;
import net.mcft.copy.tweaks.util.recipe.RecipeMatcherOutputItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = copyVanillaTweaks.MOD_ID, version = "@VERSION@", useMetadata = false,
     dependencies = "required-after:copycore;required-after:betterstorage",
     guiFactory = "net.mcft.copy.tweaks.client.gui.VanillaTweaksGuiFactory")
public class copyVanillaTweaks {
	
	public static final String MOD_ID = "copyVanillaTweaks";
	public static final String MOD_NAME = "copy's Vanilla Tweaks";
	
	public static Config config;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		setupConfig(event.getSuggestedConfigurationFile());
		registerHandlers();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		adjustToolAndArmorDurability();
		iterateRecipes();
		addRecipes();
	}
	
	
	private void setupConfig(File configFile) {
		config = new VanillaTweaksConfig(configFile);
		config.load();
		config.save();
	}
	
	private void registerHandlers() {
		registerHandler(VanillaTweaksConfig.enableGravelFlintDropTweak, new GravelFlintDropHandler());
		registerHandler(VanillaTweaksConfig.enableMobDropTweak, new MobDropHandler());
		registerHandler(VanillaTweaksConfig.enableBreedingAndGrowthTweak, new AnimalBreedingGrowthHandler());
	}
	private void registerHandler(Setting setting, Object handler) {
		if (config.<Boolean>get(setting)) MinecraftForge.EVENT_BUS.register(handler);
	}
	
	private void adjustToolAndArmorDurability() {
		
		ItemUtils.setToolDurability(  80, Items.wooden_sword,  Items.wooden_pickaxe,  Items.wooden_shovel,  Items.wooden_axe,  Items.wooden_hoe);
		ItemUtils.setToolDurability( 160, Items.stone_sword,   Items.stone_pickaxe,   Items.stone_shovel,   Items.stone_axe,   Items.stone_hoe);
		ItemUtils.setToolDurability( 160, Items.golden_sword,  Items.golden_pickaxe,  Items.golden_shovel,  Items.golden_axe,  Items.golden_hoe);
		ItemUtils.setToolDurability( 320, Items.iron_sword,    Items.iron_pickaxe,    Items.iron_shovel,    Items.iron_axe,    Items.iron_hoe);
		ItemUtils.setToolDurability(1720, Items.diamond_sword, Items.diamond_pickaxe, Items.diamond_shovel, Items.diamond_axe, Items.diamond_hoe);
		
		ItemUtils.setArmorDurability(10, Items.leather_helmet,   Items.leather_chestplate,   Items.leather_leggings,   Items.leather_boots);
		ItemUtils.setArmorDurability(12, Items.golden_helmet,    Items.golden_chestplate,    Items.golden_leggings,    Items.golden_boots);
		ItemUtils.setArmorDurability(16, Items.iron_helmet,      Items.iron_chestplate,      Items.iron_leggings,      Items.iron_boots);
		ItemUtils.setArmorDurability(20, Items.chainmail_helmet, Items.chainmail_chestplate, Items.chainmail_leggings, Items.chainmail_boots);
		ItemUtils.setArmorDurability(32, Items.diamond_helmet,   Items.diamond_chestplate,   Items.diamond_leggings,   Items.diamond_boots);
		
	}
	
	private void iterateRecipes() {
		
		RecipeIterator iterator = new RecipeIterator();
		
		ToolRecipes.register(iterator);
		ArmorRecipes.register(iterator);
		MiscRecipes.register(iterator);
		FMPSawRecipes.register(iterator);
		
		if (config.<Boolean>get(VanillaTweaksConfig.replaceCobbleWithSmoothstone))
			iterator.registerAction(new RecipeMatcherOutputItem(
						Blocks.lever, Blocks.dispenser, Blocks.dropper,
						Blocks.piston, Items.brewing_stand
					), new RecipeActionReplace("cobblestone", "stone"));
		
		iterator.go();
		
	}
	
	private void addRecipes() {
		ToolRecipes.add();
		ArmorRecipes.add();
		MiscRecipes.add();
		FMPSawRecipes.add();
	}
	
}

package net.mcft.copy.tweaks;

import java.io.File;

import net.mcft.copy.core.config.Config;
import net.mcft.copy.core.config.ConfigSetting;
import net.mcft.copy.core.config.setting.BooleanSetting;
import net.mcft.copy.core.config.setting.Setting;

public class VanillaTweaksConfig extends Config {
	
	@ConfigSetting(requiresMinecraftRestart = true)
	public static Setting replaceStoneTools = new BooleanSetting("recipes.replaceStoneTools", true)
			.setComment("Replaces stone tool recipes with a crafting table recipe requiring string, flint and smoothstone.");
	@ConfigSetting(requiresMinecraftRestart = true)
	public static Setting replaceGoldTools = new BooleanSetting("recipes.replaceGoldTools", true)
			.setComment("Replaces gold tool recipes with a crafting station recipe requiring experience.");
	@ConfigSetting(requiresMinecraftRestart = true)
	public static Setting replaceIronTools = new BooleanSetting("recipes.replaceIronTools", true)
			.setComment("Replaces iron tool recipes with a crafting station recipe requiring experience.");
	@ConfigSetting(requiresMinecraftRestart = true)
	public static Setting replaceDiamondTools = new BooleanSetting("recipes.replaceDiamondTools", true)
			.setComment("Replaces diamond tool recipes with a crafting station recipe requiring emeralds and experience.");
	
	@ConfigSetting(requiresMinecraftRestart = true)
	public static Setting replaceGoldArmor = new BooleanSetting("recipes.replaceGoldArmor", true)
			.setComment("Replaces gold armor recipes with a crafting station recipe requiring leather armor and experience.");
	@ConfigSetting(requiresMinecraftRestart = true)
	public static Setting replaceIronArmor = new BooleanSetting("recipes.replaceIronArmor", true)
			.setComment("Replaces iron armor recipes with a crafting station recipe requiring leather armor and experience.");
	@ConfigSetting(requiresMinecraftRestart = true)
	public static Setting replaceDiamondArmor = new BooleanSetting("recipes.replaceDiamondArmor", true)
			.setComment("Replaces diamond armor recipes with a crafting station recipe requiring leather armor, emeralds and experience.");
	
	@ConfigSetting(requiresMinecraftRestart = true)
	public static Setting replaceFlintAndSteel = new BooleanSetting("recipes.replaceFlintAndSteel", true)
			.setComment("Replaces flint and steel recipe with a crafting station recipe requiring experience.");
	@ConfigSetting(requiresMinecraftRestart = true)
	public static Setting replaceShears = new BooleanSetting("recipes.replaceShears", true)
			.setComment("Replaces shear recipe with a crafting station recipe requiring experience.");
	@ConfigSetting(requiresMinecraftRestart = true)
	public static Setting replaceBucket = new BooleanSetting("recipes.replaceBucket", true)
			.setComment("Replaces bucket recipe with a crafting station recipe requiring experience.");
	
	@ConfigSetting(requiresMinecraftRestart = true)
	public static Setting replaceSaws = new BooleanSetting("recipes.replaceSaws", true)
			.setComment("Replaces ForgeMultipart saw recipes to require 2 of the material. " +
			            "Iron and diamond saw recipes require crafting station and experience.");
	@ConfigSetting(requiresMinecraftRestart = true)
	public static Setting replaceThaumcraft = new BooleanSetting("recipes.replaceThaumcraft", true)
			.setComment("Replaces Thaumcraft base wand, tool and armor recipes.");
	@ConfigSetting(requiresMinecraftRestart = true)
	public static Setting replaceBotania = new BooleanSetting("recipes.replaceBotania", true)
			.setComment("Replaces Botania tool and armor recipes.");
	
	@ConfigSetting(requiresMinecraftRestart = true)
	public static Setting replaceBed = new BooleanSetting("recipes.replaceBed", true)
			.setComment("Replaces bed recipe with a crafting station recipe requiring experience.");
	@ConfigSetting(requiresMinecraftRestart = true)
	public static Setting replaceEnchantingTable = new BooleanSetting("recipes.replaceEnchantingTable", true)
			.setComment("Replaces enchanting table recipe with a crafting station recipe requiring experience.");
	@ConfigSetting(requiresMinecraftRestart = true)
	public static Setting replaceAnvil = new BooleanSetting("recipes.replaceAnvil", true)
			.setComment("Replaces anvil recipe with a crafting station recipe requiring experience.");
	
	@ConfigSetting(requiresMinecraftRestart = true)
	public static Setting replaceCobbleWithSmoothstone = new BooleanSetting("recipes.replaceCobbleWithSmoothstone", true)
			.setComment("Replaces cobblestone with smoothstone in recipes for: Lever, Dispenser, Dropper, Piston, Brewing Stand.");

	@ConfigSetting(requiresMinecraftRestart = true)
	public static Setting enableGravelFlintDropTweak = new BooleanSetting("tweaks.enableGravelFlintDropTweak", true)
			.setComment("Flint dropping from gravel depends on the shovel tier. Lower tier = higher chance. No flint without shovel.");
	@ConfigSetting(requiresMinecraftRestart = true)
	public static Setting enableMobDropTweak = new BooleanSetting("tweaks.enableMobDropTweak", true)
			.setComment("Mobs drop additional loot. Witches drop potions. Adult chickens occasionally drop feathers.");
	@ConfigSetting(requiresMinecraftRestart = true)
	public static Setting enableBreedingAndGrowthTweak = new BooleanSetting("tweaks.enableBreedingAndGrowthTweak", true)
			.setComment("Animals grow up slower and take longer to be bred again. Breeding gives more XP. Additional pigs from breeding.");
	
	public VanillaTweaksConfig(File file) {
		super(file);
		addAllViaReflection();
	}
	
}

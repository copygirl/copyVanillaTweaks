package net.mcft.copy.tweaks;

import java.io.File;

import net.mcft.copy.core.config.Config;
import net.mcft.copy.core.config.ConfigSetting;
import net.mcft.copy.core.config.setting.BooleanSetting;
import net.mcft.copy.core.config.setting.Setting;

public class VanillaTweaksConfig extends Config {
	
	@ConfigSetting(requiresMinecraftRestart = true)
	public static Setting adjustToolAndArmorDurability = new BooleanSetting("adjustments.toolAndArmorDurability", true)
			.setComment("Adjusts tool and armor durability.");
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

package net.mcft.copy.tweaks.util;

import net.mcft.copy.betterstorage.api.crafting.BetterStorageCrafting;
import net.mcft.copy.betterstorage.api.crafting.ShapedStationRecipe;
import net.mcft.copy.betterstorage.api.crafting.ShapelessStationRecipe;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class StationUtils {
	
	private StationUtils() {  }
	
	public static void addShaped(ItemStack output, int experience, Object... input) {
		BetterStorageCrafting.addStationRecipe(new ShapedStationRecipe(output, input).setRequiredExperience(experience));
	}
	public static void addShaped(Item output, int experience, Object... input) {
		addShaped(new ItemStack(output), experience, input);
	}
	public static void addShaped(Block output, int experience, Object... input) {
		addShaped(Item.getItemFromBlock(output), experience, input);
	}
	
	public static void addShapeless(ItemStack output, int experience, Object... input) {
		BetterStorageCrafting.addStationRecipe(new ShapelessStationRecipe(output, input).setRequiredExperience(experience));
	}
	public static void addShapeless(Item output, int experience, Object... input) {
		addShapeless(new ItemStack(output), experience, input);
	}
	public static void addShapeless(Block output, int experience, Object... input) {
		addShapeless(Item.getItemFromBlock(output), experience, input);
	}
	
}

package net.mcft.copy.tweaks.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

public final class ItemUtils {
	
	private ItemUtils() {  }
	
	public static void setToolDurability(int durability, Item... items) {
		for (Item item : items)
			item.setMaxDamage(durability);
	}
	
	public static void setArmorDurability(int durability, ItemArmor helmet, ItemArmor chestplate,
	                                                      ItemArmor leggins, ItemArmor boots) {
		helmet.setMaxDamage(durability * 11);
		chestplate.setMaxDamage(durability * 16);
		leggins.setMaxDamage(durability * 15);
		boots.setMaxDamage(durability * 13);
	}
	
}

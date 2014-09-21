package net.mcft.copy.tweaks.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import cpw.mods.fml.common.registry.GameData;

public final class ItemUtils {
	
	private ItemUtils() {  }
	
	public static Item getItemFromObject(Object item) {
		if (item instanceof Item)
			return (Item)item;
		else if (item instanceof Block)
			return Item.getItemFromBlock((Block)item);
		else if (item instanceof String)
			return GameData.getItemRegistry().getObject((String)item);
		else throw new IllegalArgumentException("Can't convert " + item.getClass() + " to item.");
	}
	
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

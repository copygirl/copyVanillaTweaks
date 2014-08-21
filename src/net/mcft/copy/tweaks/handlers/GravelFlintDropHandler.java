package net.mcft.copy.tweaks.handlers;

import net.mcft.copy.core.util.RandomUtils;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/** Changes gravel drops so it always drops gravel when not using a shovel.
 *  The chance depends on the tier of the shovel, silk touch and fortune.
 *  
 *  Wood & Gold: 35%, Stone: 20%, Iron: 5%, Diamond: 0%.
 *  Silk touch causes gravel to always be dropped.
 *  Fortune increases the chance by 10% per level. */
public class GravelFlintDropHandler {
	
	@SubscribeEvent
	public void onHarvestDrops(HarvestDropsEvent event) {
		if ((event.block != Blocks.gravel) || event.isSilkTouching)
			return;
		
		double chance = 0;
		ItemStack holding = ((event.harvester != null) ? event.harvester.getCurrentEquippedItem() : null);
		ItemTool tool = (((holding != null) && (holding.getItem() instanceof ItemTool)) ? (ItemTool)holding.getItem() : null);
		
		if ((tool != null) && tool.getToolClasses(holding).contains("shovel")) {
			chance = 0.35 - tool.getHarvestLevel(holding, "shovel") * 0.15;
			if (event.fortuneLevel > 0)
				chance += event.fortuneLevel * 0.15;
		}
		
		event.drops.clear();
		event.drops.add(RandomUtils.getBoolean(chance)
				? new ItemStack(Items.flint) : new ItemStack(Blocks.gravel));
	}
	
}

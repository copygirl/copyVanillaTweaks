package net.mcft.copy.tweaks.handlers;

import net.mcft.copy.core.util.RandomUtils;
import net.mcft.copy.core.util.WorldUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class MobDropHandler {
	
	@SubscribeEvent
	public void onLivingDrops(LivingDropsEvent event) {
		// Don't drop any extras if the entity is a child.
		if (event.entityLiving.isChild()) return;
		
		// Chickens drop 3 extra feathers.
		if (event.entity instanceof EntityChicken)
			event.drops.add(makeItemToDrop(event.entity, Items.feather, 3));
		// Pigs drop 2 extra porkchops.
		else if (event.entity instanceof EntityPig)
			event.drops.add(makeItemToDrop(event.entity, Items.porkchop, 1));
		// Cows drop 1 extra leather and 1 extra beef.
		else if (event.entity instanceof EntityCow) {
			event.drops.add(makeItemToDrop(event.entity, Items.leather, 1));
			event.drops.add(makeItemToDrop(event.entity, Items.beef, 1));
		// Horses drop 1-2 extra leather.
		} else if (event.entity instanceof EntityHorse)
			event.drops.add(makeItemToDrop(event.entity, Items.leather, RandomUtils.getInt(1, 3)));
		// Spiders drop 1 extra string.
		else if (event.entity instanceof EntitySpider)
			event.drops.add(makeItemToDrop(event.entity, Items.string, 1));
		// Skeletons drop 1 extra bones.
		else if (event.entity instanceof EntitySkeleton)
			event.drops.add(makeItemToDrop(event.entity, Items.bone, 1));
		// Creepers drop 1 extra gunpowder.
		else if (event.entity instanceof EntityCreeper)
			event.drops.add(makeItemToDrop(event.entity, Items.gunpowder, 1));
		// Endermen drop 0-1 extra enderpearls.
		else if ((event.entity instanceof EntityEnderman) && RandomUtils.getBoolean(0.5))
			event.drops.add(makeItemToDrop(event.entity, Items.ender_pearl, 1));
		// Blaze drop 1 extra blazerod.
		else if (event.entity instanceof EntityBlaze)
			event.drops.add(makeItemToDrop(event.entity, Items.blaze_rod, 1));
		// Witches have a 25% chance to drop a random potion.
		else if ((event.entity instanceof EntityWitch) && RandomUtils.getBoolean(0.25)) {
			int[] damageValues = {
					8197, 8194, 8205, 8195,    // Healing, Swiftness, Water Breathing, Fire Resistance,
				 	16396, 16388, 16394, 16392 // Harming, Poison, Slowness, Weakness
				};
			int damage = damageValues[RandomUtils.getInt(damageValues.length)];
			event.drops.add(makeItemToDrop(event.entity, new ItemStack(Items.potionitem, 1, damage)));
		}
	}
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		if (event.entity.worldObj.isRemote) return;
		
		// Adult chickens have a 25% chance drop a feather every 8 minutes.
		if ((event.entity instanceof EntityChicken) && !event.entityLiving.isChild() &&
		    ((event.entity.ticksExisted % (8 * 60 * 20)) == 0) && RandomUtils.getBoolean(0.5))
			WorldUtils.dropStackFromEntity(event.entity, new ItemStack(Items.feather), 1.5F);
	}
	
	private static EntityItem makeItemToDrop(Entity entity, ItemStack stack) {
		EntityItem item = new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, stack);
		item.motionX = RandomUtils.getGaussian() * 0.05F;
		item.motionY = RandomUtils.getGaussian() * 0.05F + 0.2F;
		item.motionZ = RandomUtils.getGaussian() * 0.05F;
		return item;
	}
	private static EntityItem makeItemToDrop(Entity entity, Item item, int amount) {
		return makeItemToDrop(entity, new ItemStack(item, amount));
	}
	
}

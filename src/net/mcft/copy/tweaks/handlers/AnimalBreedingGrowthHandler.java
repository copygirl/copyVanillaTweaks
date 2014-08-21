package net.mcft.copy.tweaks.handlers;

import java.util.HashMap;
import java.util.Map;

import net.mcft.copy.core.util.RandomUtils;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/** Slows down the growth of animals and how soon they're ready
 *  to breed again. Drops additional experience when breeding
 *  animals, and spawns an additional piglet when breeding pigs. */
public class AnimalBreedingGrowthHandler {
	
	private int agingSlowdownDefault = 4;
	private Map<Class<? extends EntityAgeable>, Integer> agingSlowdownMap =
			new HashMap<Class<? extends EntityAgeable>, Integer>();
	
	public AnimalBreedingGrowthHandler() {
		agingSlowdownMap.put(EntityChicken.class, 2);
		agingSlowdownMap.put(EntityVillager.class, 3);
	}
	
	private int getAgingSlowdown(EntityAgeable entity) {
		Integer agingSlowdown = agingSlowdownMap.get(entity.getClass());
		return ((agingSlowdown != null) ? agingSlowdown : agingSlowdownDefault);
	}
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		if (event.entity.worldObj.isRemote) return;
		
		if (event.entity instanceof EntityAgeable) {
			EntityAgeable entity = (EntityAgeable)event.entity;
			int age = entity.getGrowingAge();
			int agingSlowdown = getAgingSlowdown(entity);
			if (age == -24000) onChildEntityBred(entity);
			else if ((age != 0) && ((entity.ticksExisted % agingSlowdown) != 0))
				entity.setGrowingAge(age + ((age > 0) ? 1 : -1));
		}
	}
	
	private void onChildEntityBred(EntityAgeable entity) {
		// Drop additional experience.
		int agingSlowdown = getAgingSlowdown(entity);
		int experience = (agingSlowdown - 1) * 2 + RandomUtils.getInt(3);
		while (experience > 0) {
			int xp = EntityXPOrb.getXPSplit(experience);
			entity.worldObj.spawnEntityInWorld(new EntityXPOrb(entity.worldObj, entity.posX, entity.posY, entity.posZ, xp));
			experience -= xp;
		}
		// Spawn additional pigs.
		if (entity instanceof EntityPig) {
			int num = (RandomUtils.getBoolean(1.0 / 200) ? 3 : 1);
			for (int i = 0; i < num; i++) {
				EntityAgeable newEntity = entity.createChild(entity);
				newEntity.setGrowingAge(-23999);
				newEntity.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, RandomUtils.getFloat(360), 0);
				entity.worldObj.spawnEntityInWorld(newEntity);
			}
		}
	}
	
}

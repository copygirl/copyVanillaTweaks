package net.mcft.copy.tweaks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import net.mcft.copy.betterstorage.api.crafting.BetterStorageCrafting;
import net.mcft.copy.betterstorage.api.crafting.ShapedStationRecipe;
import net.mcft.copy.core.util.RandomUtils;
import net.mcft.copy.core.util.WorldUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
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
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = copyVanillaTweaks.MOD_ID, version = "@VERSION@",
     useMetadata = false, dependencies = "required-after:copycore;required-after:betterstorage")
public class copyVanillaTweaks {
	
	public static final String MOD_ID = "copyVanillaTweaks";
	public static final String MOD_NAME = "copy's Vanilla Tweaks";
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
		MinecraftForge.EVENT_BUS.register(this);
		
		// Adjust durability of tools and armor
		
		setToolDurability(  80, Items.wooden_sword,  Items.wooden_pickaxe,  Items.wooden_shovel,  Items.wooden_axe,  Items.wooden_hoe);
		setToolDurability( 160, Items.stone_sword,   Items.stone_pickaxe,   Items.stone_shovel,   Items.stone_axe,   Items.stone_hoe);
		setToolDurability( 160, Items.golden_sword,  Items.golden_pickaxe,  Items.golden_shovel,  Items.golden_axe,  Items.golden_hoe);
		setToolDurability( 320, Items.iron_sword,    Items.iron_pickaxe,    Items.iron_shovel,    Items.iron_axe,    Items.iron_hoe);
		setToolDurability(1720, Items.diamond_sword, Items.diamond_pickaxe, Items.diamond_shovel, Items.diamond_axe, Items.diamond_hoe);
		
		setArmorDurability(10, Items.leather_helmet,   Items.leather_chestplate,   Items.leather_leggings,   Items.leather_boots);
		setArmorDurability(12, Items.golden_helmet,    Items.golden_chestplate,    Items.golden_leggings,    Items.golden_boots);
		setArmorDurability(16, Items.iron_helmet,      Items.iron_chestplate,      Items.iron_leggings,      Items.iron_boots);
		setArmorDurability(20, Items.chainmail_helmet, Items.chainmail_chestplate, Items.chainmail_leggings, Items.chainmail_boots);
		setArmorDurability(32, Items.diamond_helmet,   Items.diamond_chestplate,   Items.diamond_leggings,   Items.diamond_boots);
		
		
		Set removeRecipesOf = new HashSet(Arrays.asList(
				Items.stone_sword,   Items.stone_pickaxe,   Items.stone_shovel,   Items.stone_axe,   Items.stone_hoe,
				Items.golden_sword,  Items.golden_pickaxe,  Items.golden_shovel,  Items.golden_axe,  Items.golden_hoe,
				Items.iron_sword,    Items.iron_pickaxe,    Items.iron_shovel,    Items.iron_axe,    Items.iron_hoe,
				Items.diamond_sword, Items.diamond_pickaxe, Items.diamond_shovel, Items.diamond_axe, Items.diamond_hoe,
				
				Items.golden_helmet,  Items.golden_chestplate,  Items.golden_leggings,  Items.golden_boots,
				Items.iron_helmet,    Items.iron_chestplate,    Items.iron_leggings,    Items.iron_boots,
				Items.diamond_helmet, Items.diamond_chestplate, Items.diamond_leggings, Items.diamond_boots,
				
				Items.bucket, Items.bed, Item.getItemFromBlock(Blocks.enchanting_table), Item.getItemFromBlock(Blocks.anvil)
			));
		// Remove saw recipes if ForgeMultipart is present.
		if (Loader.isModLoaded("ForgeMultipart")) {
			removeRecipesOf.addAll(Arrays.asList(
				Item.itemRegistry.getObject("ForgeMicroblock:sawStone"),
				Item.itemRegistry.getObject("ForgeMicroblock:sawIron"),
				Item.itemRegistry.getObject("ForgeMicroblock:sawDiamond")
					));
		}
		
		Set<Item> replaceCobbleWithStoneIn = new HashSet<Item>(Arrays.asList(
				Item.getItemFromBlock(Blocks.lever),
				Item.getItemFromBlock(Blocks.dispenser),
				Item.getItemFromBlock(Blocks.dropper),
				Item.getItemFromBlock(Blocks.piston),
				Items.brewing_stand
			));
		
		ListIterator<IRecipe> recipeIterator = CraftingManager.getInstance().getRecipeList().listIterator();
		while (recipeIterator.hasNext()) {
			IRecipe recipe = recipeIterator.next();
			ItemStack output = recipe.getRecipeOutput();
			if (output == null) continue;
			if (removeRecipesOf.contains(output.getItem()))
				recipeIterator.remove();
			else if (replaceCobbleWithStoneIn.contains(output.getItem()) &&
			         (recipe instanceof ShapedOreRecipe)) {
				Object[] input = ((ShapedOreRecipe)recipe).getInput();
				for (int i = 0; i < input.length; i++)
					if (input[i] instanceof List) {
					List<ItemStack> stacks = (List<ItemStack>)input[i];
					for (ItemStack stack : stacks)
						if (stack.getItem() == Item.getItemFromBlock(Blocks.cobblestone)) {
							input[i] = OreDictionary.getOres("stone");
							break;
						}
				}
			}
		}
		
		// Stone tools
		
		ToolMaterial.STONE.customCraftingMaterial = Items.flint;
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.stone_sword),
				" x ",
				" o ",
				"s/s", 'o', "stone",
				       'x', Items.flint,
				       '/', "stickWood",
				       's', Items.string));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.stone_pickaxe),
				"xox",
				"s/s",
				" / ", 'o', "stone",
				       'x', Items.flint,
				       '/', "stickWood",
				       's', Items.string));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.stone_shovel),
				" x ",
				"s/s",
				" / ", 'x', Items.flint,
				       '/', "stickWood",
				       's', Items.string));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.stone_axe),
				"xo ",
				"x/ ",
				"s/s", 'o', "stone",
				       'x', Items.flint,
				       '/', "stickWood",
				       's', Items.string));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.stone_hoe),
				"xo ",
				"s/s",
				" / ", 'o', "stone",
				       'x', Items.flint,
				       '/', "stickWood",
				       's', Items.string));
		
		// Gold tools and armor
		
		addStationRecipe(Items.golden_sword, 2,
				"o",
				"o",
				"/", 'o', "ingotGold",
				     '/', "stickWood");
		addStationRecipe(Items.golden_pickaxe, 2,
				"ooo",
				" / ",
				" / ", 'o', "ingotGold",
				       '/', "stickWood");
		addStationRecipe(Items.golden_shovel, 1,
				"o",
				"/",
				"/", 'o', "ingotGold",
				     '/', "stickWood");
		addStationRecipe(Items.golden_axe, 1,
				"oo",
				"o/",
				" /", 'o', "ingotGold",
				      '/', "stickWood");
		addStationRecipe(Items.golden_hoe, 1,
				"oo",
				" /",
				" /", 'o', "ingotGold",
				      '/', "stickWood");
		
		addStationRecipe(Items.golden_helmet, 2,
				"ooo",
				"oHo", 'o', "ingotGold",
				       'H', Items.leather_helmet);
		addStationRecipe(Items.golden_chestplate, 3,
				"oCo",
				"ooo",
				"ooo", 'o', "ingotGold",
				       'C', Items.leather_chestplate);
		addStationRecipe(Items.golden_leggings, 2,
				"ooo",
				"oLo",
				"o o", 'o', "ingotGold",
				       'L', Items.leather_leggings);
		addStationRecipe(Items.golden_boots, 2,
				"oBo",
				"o o", 'o', "ingotGold",
				       'B', Items.leather_boots);
		
		// Iron tools and armor
		
		addStationRecipe(Items.iron_sword, 6,
				"o",
				"o",
				"/", 'o', "ingotIron",
				     '/', "stickWood");
		addStationRecipe(Items.iron_pickaxe, 8,
				"ooo",
				" / ",
				" / ", 'o', "ingotIron",
				       '/', "stickWood");
		addStationRecipe(Items.iron_shovel, 4,
				"o",
				"/",
				"/", 'o', "ingotIron",
				     '/', "stickWood");
		addStationRecipe(Items.iron_axe, 4,
				"oo",
				"o/",
				" /", 'o', "ingotIron",
				      '/', "stickWood");
		addStationRecipe(Items.iron_hoe, 4,
				"oo",
				" /",
				" /", 'o', "ingotIron",
				      '/', "stickWood");
		
		addStationRecipe(Items.iron_helmet, 8,
				"ooo",
				"oHo", 'o', "ingotIron",
				       'H', Items.leather_helmet);
		addStationRecipe(Items.iron_chestplate, 12,
				"oCo",
				"ooo",
				"ooo", 'o', "ingotIron",
				       'C', Items.leather_chestplate);
		addStationRecipe(Items.iron_leggings, 10,
				"ooo",
				"oLo",
				"o o", 'o', "ingotIron",
				       'L', Items.leather_leggings);
		addStationRecipe(Items.iron_boots, 8,
				"oBo",
				"o o", 'o', "ingotIron",
				       'B', Items.leather_boots);
		
		// Diamond tools and armor
		
		addStationRecipe(Items.diamond_sword, 12,
				"o",
				"x",
				"/", 'o', "gemDiamond",
				     'x', "gemEmerald",
				     '/', "stickWood");
		addStationRecipe(Items.diamond_pickaxe, 16,
				"oxo",
				" / ",
				" / ", 'o', "gemDiamond",
				       'x', "gemEmerald",
				       '/', "stickWood");
		addStationRecipe(Items.diamond_shovel, 8,
				"o",
				"/",
				"/", 'o', "gemDiamond",
				     '/', "stickWood");
		addStationRecipe(Items.diamond_axe, 8,
				"ox",
				"o/",
				" /", 'o', "gemDiamond",
				      'x', "gemEmerald",
				      '/', "stickWood");
		addStationRecipe(Items.diamond_hoe, 8,
				"ox",
				" /",
				" /", 'o', "gemDiamond",
				      'x', "gemEmerald",
				      '/', "stickWood");
		
		addStationRecipe(Items.diamond_helmet, 16,
				"oxo",
				"oHo", 'o', "gemDiamond",
				       'x', "gemEmerald",
				       'H', Items.leather_helmet);
		addStationRecipe(Items.diamond_chestplate, 24,
				"oCo",
				"oxo",
				"ooo", 'o', "gemDiamond",
				       'x', "gemEmerald",
				       'C', Items.leather_chestplate);
		addStationRecipe(Items.diamond_leggings, 20,
				"oxo",
				"oLo",
				"o o", 'o', "gemDiamond",
				       'x', "gemEmerald",
				       'L', Items.leather_leggings);
		addStationRecipe(Items.diamond_boots, 16,
				"oBo",
				"o o", 'o', "gemDiamond",
				       'B', Items.leather_boots);
		
		// Other recipes
		
		addStationRecipe(Items.bucket, 2,
				"o o",
				" o ", 'o', "ingotIron");
		
		addStationRecipe(Items.bed, 20,
				"WWW",
				"PPP", 'W', Blocks.wool,
				       'P', "plankWood");
		
		addStationRecipe(Blocks.enchanting_table, 30,
				" B ",
				"o#o",
				"###", 'B', Items.book,
				       'o', "gemDiamond",
				       '#', Blocks.obsidian);
		
		addStationRecipe(Blocks.anvil, 20,
				"OOO",
				" o ",
				"ooo", 'O', "blockIron",
				       'o', "ingotIron");
		
		addStationRecipe(new ItemStack(Items.leather, 4), 1,
				"ooo",
				"ooo",
				"ooo", 'o', Items.rotten_flesh);
		
		// ForgeMultipart saws
		
		if (Loader.isModLoaded("ForgeMultipart")) {
			
			Item stoneRod = (Item)Item.itemRegistry.getObject("ForgeMultipart:stoneRod");
			Item sawStone = (Item)Item.itemRegistry.getObject("ForgeMultipart:sawStone");
			Item sawIron = (Item)Item.itemRegistry.getObject("ForgeMultipart:sawIron");
			Item sawDiamond = (Item)Item.itemRegistry.getObject("ForgeMultipart:sawDiamond");
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(sawStone),
					"/--",
					"/oo", 'o', Items.flint,
					       '/', "stickWood",
					       '-', stoneRod));
			
			addStationRecipe(sawIron, 3,
					"/--",
					"/oo", 'o', "ingotIron",
					       '/', "stickWood",
					       '-', stoneRod);
			
			addStationRecipe(sawDiamond, 6,
					"/--",
					"/oo", 'o', "gemDiamond",
					       '/', "stickWood",
					       '-', stoneRod);
			
		}
		
	}
	private static void setToolDurability(int durability, Item... items) {
		for (Item item : items)
			item.setMaxDamage(durability);
	}
	private static final int[] maxDamageArray = new int[]{ 11, 16, 15, 13 };
	private static void setArmorDurability(int durability, ItemArmor... items) {
		for (ItemArmor item : items)
			item.setMaxDamage(durability * maxDamageArray[item.armorType]);
	}
	private static void addStationRecipe(ItemStack output, int experience, Object... input) {
		BetterStorageCrafting.addStationRecipe(new ShapedStationRecipe(
				new ItemStack[]{ null, null, null, null, output }, input
			).setRequiredExperience(experience));
	}
	private static void addStationRecipe(Item output, int experience, Object... input) {
		addStationRecipe(new ItemStack(output), experience, input);
	}
	private static void addStationRecipe(Block output, int experience, Object... input) {
		addStationRecipe(new ItemStack(output), experience, input);
	}
	
	
	@SubscribeEvent
	public void onHarvestDrops(HarvestDropsEvent event) {
		// Change gravel drops so it always drops gravel not using a shovel.
		// Chance of getting flint is different depending on the shovel tier:
		// Wood & Gold: 35%, Stone: 20%, Iron: 5%, Diamond: 0%.
		if ((event.block == Blocks.gravel) && !event.isSilkTouching) {
			ItemStack holding = ((event.harvester != null) ? event.harvester.getCurrentEquippedItem() : null);
			ItemTool tool = (((holding != null) && (holding.getItem() instanceof ItemTool)) ? (ItemTool)holding.getItem() : null);
			boolean dropsFlint = ((tool != null) && tool.getToolClasses(holding).contains("shovel") &&
			                      RandomUtils.getBoolean(0.35 - tool.getHarvestLevel(holding, "shovel") * 0.15));
			event.drops.clear();
			event.drops.add(dropsFlint ? new ItemStack(Items.flint) : new ItemStack(Blocks.gravel));
		}
	}
	
	
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
	public static EntityItem makeItemToDrop(Entity entity, ItemStack stack) {
		EntityItem item = new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, stack);
		item.motionX = RandomUtils.getGaussian() * 0.05F;
		item.motionY = RandomUtils.getGaussian() * 0.05F + 0.2F;
		item.motionZ = RandomUtils.getGaussian() * 0.05F;
		return item;
	}
	public static EntityItem makeItemToDrop(Entity entity, Item item, int amount) {
		return makeItemToDrop(entity, new ItemStack(item, amount));
	}
	
	private static int agingSlowdownDefault = 4;
	private static Map<Class<? extends EntityAgeable>, Integer> agingSlowdownMap =
			new HashMap<Class<? extends EntityAgeable>, Integer>();
	static {
		agingSlowdownMap.put(EntityChicken.class, 2);
		agingSlowdownMap.put(EntityVillager.class, 3);
	}
	private static int getAgingSlowdown(EntityAgeable entity) {
		Integer agingSlowdown = agingSlowdownMap.get(entity.getClass());
		return ((agingSlowdown != null) ? agingSlowdown : agingSlowdownDefault);
	}
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		if (event.entity.worldObj.isRemote) return;
		
		// Adult chickens have a 25% chance drop a feather every 8 minutes.
		if ((event.entity instanceof EntityChicken) && !event.entityLiving.isChild() &&
		    ((event.entity.ticksExisted % (8 * 60 * 20)) == 0) &&
		    RandomUtils.getBoolean(0.5))
			WorldUtils.dropStackFromEntity(event.entity, new ItemStack(Items.feather), 1.5F);
		
		if (event.entity instanceof EntityAgeable) {
			EntityAgeable entity = (EntityAgeable)event.entity;
			int age = entity.getGrowingAge();
			int agingSlowdown = getAgingSlowdown(entity);
			if (age == -24000) onChildEntityBred(entity);
			else if ((age != 0) && ((entity.ticksExisted % agingSlowdown) != 0))
				entity.setGrowingAge(age + ((age > 0) ? 1 : -1));
		}
	}
	
	public void onChildEntityBred(EntityAgeable entity) {
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

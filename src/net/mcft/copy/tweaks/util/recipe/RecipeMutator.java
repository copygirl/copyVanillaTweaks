package net.mcft.copy.tweaks.util.recipe;

import java.util.LinkedList;
import java.util.List;

import net.mcft.copy.betterstorage.api.BetterStorageUtils;
import net.mcft.copy.betterstorage.api.crafting.BetterStorageCrafting;
import net.mcft.copy.betterstorage.api.crafting.IRecipeInput;
import net.mcft.copy.betterstorage.api.crafting.IStationRecipe;
import net.mcft.copy.betterstorage.api.crafting.RecipeInputBase;
import net.mcft.copy.betterstorage.api.crafting.ShapedStationRecipe;
import net.mcft.copy.betterstorage.api.crafting.ShapelessStationRecipe;
import net.mcft.copy.tweaks.util.RecipeUtils;
import net.mcft.copy.tweaks.util.ShapedRecipeInfo;
import net.mcft.copy.tweaks.util.ShapedRecipeInput;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RecipeMutator {
	
	private final RecipeIterator iterator;
	
	private List<IRecipe> recipesToAdd = new LinkedList<IRecipe>();
	private List<IStationRecipe> stationRecipesToAdd = new LinkedList<IStationRecipe>();
	
	public RecipeMutator(RecipeIterator iterator) {
		this.iterator = iterator;
	}
	
	
	public void registerShapedMutation(IRecipeMatcher matcher, Object... mutation) {
		iterator.registerAction(matcher, new RecipeShapedMutatorAction(mutation));
	}
	public void registerShapedMutation(Object match, Object... mutation) {
		registerShapedMutation(new RecipeMatcherOutputItem(match), mutation);
	}
	
	public void registerStationMutation(IRecipeMatcher matcher, int experience) {
		iterator.registerAction(matcher, new RecipeStationMutatorAction(experience));
	}
	public void registerStationMutation(Object match, int experience) {
		registerStationMutation(new RecipeMatcherOutputItem(match), experience);
	}
	
	public void registerShapedStationMutation(IRecipeMatcher matcher, int experience, Object... mutation) {
		iterator.registerAction(matcher, new RecipeShapedStationMutatorAction(experience, mutation));
	}
	public void registerShapedStationMutation(Object match, int experience, Object... mutation) {
		registerShapedStationMutation(new RecipeMatcherOutputItem(match), experience, mutation);
	}
	
	
	public void add() {
		for (IRecipe recipe : recipesToAdd)
			GameRegistry.addRecipe(recipe);
		for (IStationRecipe recipe : stationRecipesToAdd)
			BetterStorageCrafting.addStationRecipe(recipe);
	}
	
	
	private static IRecipeInput makeInput(Object input) {
		if (input == null)
			return null;
		else if (input instanceof List)
			return new RecipeInputList((List<ItemStack>)input);
		else return BetterStorageCrafting.makeInput(input);
	}
	
	
	private class RecipeShapedMutatorAction implements IRecipeAction {
		
		private final ShapedRecipeInput mutation;
		
		public RecipeShapedMutatorAction(Object... mutation) {
			this.mutation = ShapedRecipeInput.create(mutation);
		}
		
		@Override
		public void doAction(RecipeIterator iterator, IRecipe recipe) {
			iterator.remove();
			ShapedRecipeInput mutatedInput = ShapedRecipeInput.merge(new ShapedRecipeInput(recipe), mutation);
			recipesToAdd.add(new ShapedRecipeInfo(mutatedInput, recipe.getRecipeOutput()).createOreRecipe());
		}
		
	}
	
	private class RecipeStationMutatorAction implements IRecipeAction {
		
		private final int experience;
		
		public RecipeStationMutatorAction(int experience) {
			this.experience = experience;
		}
		
		@Override
		public void doAction(RecipeIterator iterator, IRecipe recipe) {
			iterator.remove();
			
			List recipeInput = RecipeUtils.getRecipeInput(recipe);
			IRecipeInput[] input = new IRecipeInput[recipeInput.size()];
			for (int i = 0; i < input.length; i++)
				input[i] = makeInput(recipeInput.get(i));
			
			ItemStack[] output = new ItemStack[]{ null, null, null, null, recipe.getRecipeOutput() };
			IStationRecipe newRecipe;
			
			if (RecipeUtils.isShaped(recipe)) {
				int width = RecipeUtils.getRecipeWidth(recipe);
				int height = RecipeUtils.getRecipeHeight(recipe);
				newRecipe = new ShapedStationRecipe(input, width, height, output).setRequiredExperience(experience);
			} else newRecipe = new ShapelessStationRecipe(input, output).setRequiredExperience(experience);
			stationRecipesToAdd.add(newRecipe);
		}
		
	}
	
	private class RecipeShapedStationMutatorAction implements IRecipeAction {
		
		private final int experience;
		private final ShapedRecipeInput mutation;
		
		public RecipeShapedStationMutatorAction(int experience, Object... mutation) {
			this.experience = experience;
			this.mutation = ShapedRecipeInput.create(mutation);
		}
		
		@Override
		public void doAction(RecipeIterator iterator, IRecipe recipe) {
			iterator.remove();
			ShapedRecipeInput mutatedInput = ShapedRecipeInput.merge(new ShapedRecipeInput(recipe), mutation);
			IRecipeInput[] input = new IRecipeInput[mutatedInput.input.length];
			for (int i = 0; i < input.length; i++)
				input[i] = makeInput(mutatedInput.input[i]);
			stationRecipesToAdd.add(new ShapedStationRecipe(
					input, mutatedInput.width, mutatedInput.height,
					new ItemStack[]{ null, null, null, null, recipe.getRecipeOutput() })
				.setRequiredExperience(experience));
		}
		
	}
	
	// TODO: Move this class to BetterStorage.
	private static class RecipeInputList extends RecipeInputBase {
		
		private final List<ItemStack> items;
		
		public RecipeInputList(List<ItemStack> items) {
			this.items = items;
		}
		
		@Override
		public int getAmount() { return 1; }
		
		@Override
		public boolean matches(ItemStack stack) {
			for (ItemStack item : items)
				if (BetterStorageUtils.wildcardMatch(item, stack))
					return true;
			return false;
		}
		
		@Override
		@SideOnly(Side.CLIENT)
		public List<ItemStack> getPossibleMatches() { return items; }
		
	}
	
}

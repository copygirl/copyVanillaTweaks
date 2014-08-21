package net.mcft.copy.tweaks.client.gui;

import java.util.Set;

import net.mcft.copy.core.client.gui.GuiConfigBase;
import net.mcft.copy.tweaks.copyVanillaTweaks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class VanillaTweaksGuiFactory implements IModGuiFactory {
	
	public static class VanillaTweaksGuiScreen extends GuiConfigBase {
		
		public VanillaTweaksGuiScreen(GuiScreen parentScreen) {
			super(parentScreen, copyVanillaTweaks.MOD_ID, copyVanillaTweaks.config);
		}
		
	}
	
	@Override
	public void initialize(Minecraft minecraftInstance) {  }
	
	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() { return VanillaTweaksGuiScreen.class; }
	
	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() { return null; }
	
	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) { return null; }
	
}

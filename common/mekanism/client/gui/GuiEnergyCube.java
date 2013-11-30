package mekanism.client.gui;

import mekanism.common.inventory.container.ContainerEnergyCube;
import mekanism.common.tileentity.TileEntityEnergyCube;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import universalelectricity.core.electricity.ElectricityDisplay;
import universalelectricity.core.electricity.ElectricityDisplay.ElectricUnit;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiEnergyCube extends GuiMekanism
{
	public TileEntityEnergyCube tileEntity;
	
	public GuiEnergyCube(InventoryPlayer inventory, TileEntityEnergyCube tentity)
	{
		super(new ContainerEnergyCube(inventory, tentity));
		tileEntity = tentity;
		guiElements.add(new GuiRedstoneControl(this, tileEntity, MekanismUtils.getResource(ResourceType.GUI, "GuiEnergyCube.png")));
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		int xAxis = (mouseX - (width - xSize) / 2);
		int yAxis = (mouseY - (height - ySize) / 2);
		
		String capacityInfo = MekanismUtils.getEnergyDisplay(tileEntity.getEnergy()) + "/" + MekanismUtils.getEnergyDisplay(tileEntity.getMaxEnergy());
		String outputInfo = MekanismUtils.localize("gui.out") + ": " + MekanismUtils.getEnergyDisplay(tileEntity.getMaxOutput()) + "/t";
		
		fontRenderer.drawString(tileEntity.getInvName(), 43, 6, 0x404040);
		fontRenderer.drawString(capacityInfo, 45, 40, 0x00CD00);
		fontRenderer.drawString(outputInfo, 45, 49, 0x00CD00);
		fontRenderer.drawString(MekanismUtils.localize("container.inventory"), 8, ySize - 96 + 2, 0x404040);
		
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	}
	
	@Override
    protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY)
    {
		super.drawGuiContainerBackgroundLayer(partialTick, mouseX, mouseY);
		
		mc.renderEngine.bindTexture(MekanismUtils.getResource(ResourceType.GUI, "GuiEnergyCube.png"));
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int guiWidth = (width - xSize) / 2;
        int guiHeight = (height - ySize) / 2;
        drawTexturedModalRect(guiWidth, guiHeight, 0, 0, xSize, ySize);
        
        int xAxis = mouseX - guiWidth;
 		int yAxis = mouseY - guiHeight;
        
        int displayInt = tileEntity.getScaledEnergyLevel(72);
        drawTexturedModalRect(guiWidth + 65, guiHeight + 17, 176, 0, displayInt, 10);
    }
}

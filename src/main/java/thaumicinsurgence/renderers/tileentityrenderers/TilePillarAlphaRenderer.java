package thaumicinsurgence.renderers.tileentityrenderers;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.tile.TileInfusionPillarRenderer;
import thaumicinsurgence.main.utils.VersionInfo;
import thaumicinsurgence.tileentity.TileEntityInfusionPillarAlpha;

public class TilePillarAlphaRenderer extends TileInfusionPillarRenderer {
    private IModelCustom model;
    private static final ResourceLocation PILLAR = new ResourceLocation("thaumcraft", "textures/models/pillar.obj");

    public TilePillarAlphaRenderer() {
        this.model = AdvancedModelLoader.loadModel(PILLAR);
    }

    public void renderTileEntityAt(
            TileEntityInfusionPillarAlpha tile, double par2, double par4, double par6, float par8) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) par2 + 0.5F, (float) par4, (float) par6 + 0.5F);
        GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        UtilsFX.bindTexture(VersionInfo.ModID, "model/pillar-1.png");
        if (tile.orientation == 3) {
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
        } else if (tile.orientation == 4) {
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        } else if (tile.orientation == 5) {
            GL11.glRotatef(270.0F, 0.0F, 0.0F, 1.0F);
        }

        this.model.renderAll();
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityAt((TileEntityInfusionPillarAlpha) par1TileEntity, par2, par4, par6, par8);
    }
}

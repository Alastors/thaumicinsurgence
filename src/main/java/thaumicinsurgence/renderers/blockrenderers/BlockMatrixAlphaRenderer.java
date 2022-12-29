package thaumicinsurgence.renderers.blockrenderers;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumicinsurgence.main.Config;
import thaumicinsurgence.tileentity.TileEntityInfusionMatrixAlpha;

public class BlockMatrixAlphaRenderer extends BlockRenderer implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        TileEntityRendererDispatcher.instance.renderTileEntityAt(
                new TileEntityInfusionMatrixAlpha(), 0.0D, 0.0D, 0.0D, 0.0F);
    }

    public boolean renderWorldBlock(
            IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return true;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return Config.blockStoneDeviceRI;
    }
}

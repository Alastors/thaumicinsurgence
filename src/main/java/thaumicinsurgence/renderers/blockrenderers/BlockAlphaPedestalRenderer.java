package thaumicinsurgence.renderers.blockrenderers;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import thaumcraft.client.renderers.block.BlockStoneDeviceRenderer;
import thaumcraft.common.tiles.*;
import thaumicinsurgence.block.BlockPedestalAlpha;
import thaumicinsurgence.main.Config;

public class BlockAlphaPedestalRenderer extends BlockStoneDeviceRenderer {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

        block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
        renderer.setRenderBoundsFromBlock(block);
        drawFaces(
                renderer,
                block,
                ((BlockPedestalAlpha) block).iconPedestal[1],
                ((BlockPedestalAlpha) block).iconPedestal[1],
                ((BlockPedestalAlpha) block).iconPedestal[0],
                ((BlockPedestalAlpha) block).iconPedestal[0],
                ((BlockPedestalAlpha) block).iconPedestal[0],
                ((BlockPedestalAlpha) block).iconPedestal[0],
                true);
        block.setBlockBounds(0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
        renderer.setRenderBoundsFromBlock(block);
        drawFaces(
                renderer,
                block,
                ((BlockPedestalAlpha) block).iconPedestal[1],
                ((BlockPedestalAlpha) block).iconPedestal[1],
                ((BlockPedestalAlpha) block).iconPedestal[0],
                ((BlockPedestalAlpha) block).iconPedestal[0],
                ((BlockPedestalAlpha) block).iconPedestal[0],
                ((BlockPedestalAlpha) block).iconPedestal[0],
                true);
        block.setBlockBounds(0.125F, 0.75F, 0.125F, 0.875F, 1.0F, 0.875F);
        renderer.setRenderBoundsFromBlock(block);
        drawFaces(
                renderer,
                block,
                ((BlockPedestalAlpha) block).iconPedestal[1],
                ((BlockPedestalAlpha) block).iconPedestal[1],
                ((BlockPedestalAlpha) block).iconPedestal[0],
                ((BlockPedestalAlpha) block).iconPedestal[0],
                ((BlockPedestalAlpha) block).iconPedestal[0],
                ((BlockPedestalAlpha) block).iconPedestal[0],
                true);
    }

    public boolean renderWorldBlock(
            IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
        renderer.setRenderBoundsFromBlock(block);
        renderer.renderStandardBlock(block, x, y, z);
        block.setBlockBounds(0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
        renderer.setRenderBoundsFromBlock(block);
        renderer.renderStandardBlock(block, x, y, z);
        block.setBlockBounds(0.125F, 0.75F, 0.125F, 0.875F, 1.0F, 0.875F);
        renderer.setRenderBoundsFromBlock(block);
        renderer.renderStandardBlock(block, x, y, z);

        renderer.clearOverrideBlockTexture();
        block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        renderer.setRenderBoundsFromBlock(block);
        return true;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return Config.blockStoneDeviceThreeRI;
    }
}

package thaumicinsurgence.main;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumicinsurgence.main.modules.planar_artifice.client.GuiAlkimiumAlchemyFurnace;
import thaumicinsurgence.main.modules.planar_artifice.core.blocks.tiles.TileAlkimiumAlchemicalFurnace;
import thaumicinsurgence.renderers.blockrenderers.BlockAlphaPedestalRenderer;
import thaumicinsurgence.renderers.blockrenderers.BlockMatrixAlphaRenderer;
import thaumicinsurgence.renderers.blockrenderers.BlockPillarAlphaRenderer;
import thaumicinsurgence.renderers.tileentityrenderers.TileAlphaPedestalRenderer;
import thaumicinsurgence.renderers.tileentityrenderers.TileMatrixAlphaRenderer;
import thaumicinsurgence.renderers.tileentityrenderers.TilePillarAlphaRenderer;
import thaumicinsurgence.tileentity.TileEntityInfusionMatrixAlpha;
import thaumicinsurgence.tileentity.TileEntityInfusionPillarAlpha;
import thaumicinsurgence.tileentity.TileEntityPedestalAlpha;

@SuppressWarnings("unused")
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        specialRenderers();
    }

    public void registerTileEntitySpecialRenderer(Class<? extends TileEntity> tile,
            TileEntitySpecialRenderer renderer) {
        ClientRegistry.bindTileEntitySpecialRenderer(tile, renderer);
    }

    public void registerBlockRenderer(ISimpleBlockRenderingHandler renderer) {
        RenderingRegistry.registerBlockHandler(renderer);
    }

    public void specialRenderers() {

        this.registerTileEntitySpecialRenderer(TileEntityInfusionMatrixAlpha.class, new TileMatrixAlphaRenderer(0));
        Config.blockStoneDeviceRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerBlockRenderer(new BlockMatrixAlphaRenderer());

        this.registerTileEntitySpecialRenderer(TileEntityInfusionPillarAlpha.class, new TilePillarAlphaRenderer());
        Config.blockStoneDeviceTwoRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerBlockRenderer(new BlockPillarAlphaRenderer());

        // TODO: FIX THIS
        Config.blockStoneDeviceThreeRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerTileEntitySpecialRenderer(TileEntityPedestalAlpha.class, new TileAlphaPedestalRenderer());
        this.registerBlockRenderer(new BlockAlphaPedestalRenderer());
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (world instanceof WorldClient) {
            if (ID == 9) {
                return new GuiAlkimiumAlchemyFurnace(
                        player.inventory,
                        (TileAlkimiumAlchemicalFurnace) world.getTileEntity(x, y, z));
            }
            /*
             * switch (ID) { case 0: return new GuiAdvancedWand(player.inventory, world, x, y, z); default: break; }
             */ // bring back when the advanced wand is finished
        }
        return null;
    }
}

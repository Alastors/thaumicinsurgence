package thaumicinsurgence.main;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent.MissingMapping;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import thaumicinsurgence.main.utils.LogHelper;
import thaumicinsurgence.main.utils.VersionInfo;
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
@Mod(
        modid = VersionInfo.ModID,
        name = VersionInfo.ModName,
        version = VersionInfo.Version,
        dependencies = VersionInfo.Depends)
public class ThaumicInsurgence {

    @Mod.Instance(VersionInfo.ModID)
    public static thaumicinsurgence.main.ThaumicInsurgence instance;

    @SidedProxy(serverSide = "thaumicinsurgence.main.CommonProxy", clientSide = "thaumicinsurgence.main.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
        specialRenderers();
    }

    public void registerTileEntitySpecialRenderer(Class tile, TileEntitySpecialRenderer renderer) {
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

    @Mod.EventHandler
    public void handleMissingMapping(FMLMissingMappingsEvent event) {
        for (MissingMapping mapping : event.get()) {
            // TODO: ... maybe not this.
            LogHelper.info(String.format("Missing mapping: %s - ignoring.", mapping.name));
            mapping.ignore();
        }
    }
}

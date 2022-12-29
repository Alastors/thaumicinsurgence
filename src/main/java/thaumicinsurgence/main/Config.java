package thaumicinsurgence.main;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import java.io.File;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import thaumcraft.api.ThaumcraftApi;


import thaumicinsurgence.block.*;
import thaumicinsurgence.item.ItemMiscResources;
import thaumicinsurgence.item.ItemSanitySoapAlpha;
import thaumicinsurgence.item.armor.ItemEightBitRedCrown;
import thaumicinsurgence.item.armor.ItemRedCrown;
import thaumicinsurgence.item.tools.ItemAlastorsWand;
import thaumicinsurgence.item.tools.ItemThaumicInterfacer;
import thaumicinsurgence.block.BlockInfusionFucker;

import thaumicinsurgence.main.utils.VersionInfo;
import thaumicinsurgence.tileentity.TileEntityInfusionFucker;
import thaumicinsurgence.tileentity.TileEntityInfusionMatrixAlpha;
import thaumicinsurgence.tileentity.TileEntityInfusionPillarAlpha;
import thaumicinsurgence.tileentity.TileEntityPedestalAlpha;

/**
 * A class to hold some data related to mod state & functions.
 *
 * @author MysteriousAges
 */
public class Config {
    public static final String CATEGORY_MODULES = "modules";

    public static boolean thaumcraftActive;
    public static int blockStoneDeviceRI;
    public static int blockStoneDeviceTwoRI;
    public static int blockStoneDeviceThreeRI;

    public static ItemMiscResources miscResources;

    public static Item redCrownItem;
    public static Item eightBitRedCrownItem;
    public static Item thaumicInterfacer;
    public static Item alastorsWand;
    public static Item soapAlpha;



    public static BlockInfusionFucker infusionIntercepter;
    public static BlockInfusionMatrixAlpha matrixAlpha;
    public static BlockInfusionPillarAlpha pillarAlpha;
    public static BlockArcaneMarble arcaneMarble;
    public static BlockArcaneMarbleBrick arcaneMarbleBrick;
    public static BlockPedestalAlpha marblePedestal;

    // ----- Config State info ----------------------------------
    public static Configuration configuration;
    private static Config instance = null;

    public static void Init(File configFile) {
        if (instance != null) return;
        instance = new Config();
        FMLCommonHandler.instance().bus().register(instance);
        configuration = new Configuration(configFile);
        configuration.load();
        processConfigFile();

        configuration.save();
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(VersionInfo.ModName)) {
            if (configuration.hasChanged()) {
                configuration.save();
            }
        }
    }

    public static void saveConfigs() {
        configuration.save();
    }

    public static void setupBlocks() {
        arcaneMarble = new BlockArcaneMarble();
        GameRegistry.registerBlock(arcaneMarble, arcaneMarble.getUnlocalizedName());
        arcaneMarbleBrick = new BlockArcaneMarbleBrick();
        GameRegistry.registerBlock(arcaneMarbleBrick, arcaneMarbleBrick.getUnlocalizedName());

        setupInfusionFucker();
    }

    public static void setupItems() {
        redCrownItem = new ItemRedCrown(ThaumcraftApi.armorMatSpecial, 4, 0);

        GameRegistry.registerItem(redCrownItem, redCrownItem.getUnlocalizedName());

        eightBitRedCrownItem = new ItemEightBitRedCrown(ThaumcraftApi.armorMatSpecial, 4, 0);
        GameRegistry.registerItem(eightBitRedCrownItem, eightBitRedCrownItem.getUnlocalizedName());

        thaumicInterfacer = new ItemThaumicInterfacer();
        GameRegistry.registerItem(thaumicInterfacer, thaumicInterfacer.getUnlocalizedName());

        alastorsWand = new ItemAlastorsWand();
        GameRegistry.registerItem(alastorsWand, alastorsWand.getUnlocalizedName());
        
        miscResources = new ItemMiscResources();

        soapAlpha = new ItemSanitySoapAlpha();
        GameRegistry.registerItem(soapAlpha, soapAlpha.getUnlocalizedName());
    }

    private static void processConfigFile() {
        syncConfigs();
    }

    private static void syncConfigs() {
        doModuleConfigs();
    }

    private static void doModuleConfigs() {
        thaumcraftActive =
                configuration.get(CATEGORY_MODULES, "Thaumcraft", true).getBoolean();
    }

    public static void setupInfusionFucker() {
        infusionIntercepter = new BlockInfusionFucker();
        GameRegistry.registerBlock(infusionIntercepter, "infusionIntercepter");
        GameRegistry.registerTileEntity(TileEntityInfusionFucker.class, TileEntityInfusionFucker.tileEntityName);

        matrixAlpha = new BlockInfusionMatrixAlpha();
        GameRegistry.registerBlock(matrixAlpha, "matrixAlpha");
        GameRegistry.registerTileEntity(
                TileEntityInfusionPillarAlpha.class, TileEntityInfusionPillarAlpha.tileEntityName);

        pillarAlpha = new BlockInfusionPillarAlpha();
        GameRegistry.registerBlock(pillarAlpha, "pillarAlpha");
        GameRegistry.registerTileEntity(
                TileEntityInfusionMatrixAlpha.class, TileEntityInfusionMatrixAlpha.tileEntityName);

        marblePedestal = new BlockPedestalAlpha();
        GameRegistry.registerBlock(marblePedestal, "marblePedestal");
        GameRegistry.registerTileEntity(TileEntityPedestalAlpha.class, TileEntityPedestalAlpha.tileEntityName);
    }

    static {
        blockStoneDeviceRI = -1;
        blockStoneDeviceTwoRI = -2;
        blockStoneDeviceThreeRI = -3;
    }
}

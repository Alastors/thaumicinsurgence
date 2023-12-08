package thaumicinsurgence.main.modules.planar_artifice.utils;

import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;
import thaumicinsurgence.main.modules.planar_artifice.PlanarArtifice;
import thaumicinsurgence.main.modules.planar_artifice.core.blocks.BlockAlkimiumAdamantAlchFurnace;
import thaumicinsurgence.main.modules.planar_artifice.core.blocks.BlockAlkimiumAlchFurnace;
import thaumicinsurgence.main.modules.planar_artifice.core.blocks.BlockAlkimiumMithminiteAlchFurnace;
import thaumicinsurgence.main.modules.planar_artifice.core.blocks.BlockAlkimiumMithrilAlchFurnace;
import thaumicinsurgence.main.modules.planar_artifice.core.blocks.BlockAlkimiumThaumiumAlchFurnace;
import thaumicinsurgence.main.modules.planar_artifice.core.blocks.BlockAlkimiumVoidAlchFurnace;
import thaumicinsurgence.main.modules.planar_artifice.core.blocks.BlockPlanarDevice;
import thaumicinsurgence.main.modules.planar_artifice.core.blocks.ItemBlockPlanarDevice;
import thaumicinsurgence.main.modules.planar_artifice.core.blocks.tiles.TileAdamantAlkimiumAlchemicalFurnace;
import thaumicinsurgence.main.modules.planar_artifice.core.blocks.tiles.TileAlkimiumAlchemicalFurnace;
import thaumicinsurgence.main.modules.planar_artifice.core.blocks.tiles.TileMithminiteAlkimiumAlchemicalFurnace;
import thaumicinsurgence.main.modules.planar_artifice.core.blocks.tiles.TileMithrilAlkimiumAlchemicalFurnace;
import thaumicinsurgence.main.modules.planar_artifice.core.blocks.tiles.TileThaumiumAlkimiumAlchemicalFurnace;
import thaumicinsurgence.main.modules.planar_artifice.core.blocks.tiles.TileVoidAlkimiumAlchemicalFurnace;

public class PlanarBlocks {

    public static final Class<PlanarArtifice> core = PlanarArtifice.class;

    public static Block alkimiumAlchFurnace = new BlockAlkimiumAlchFurnace();
    public static Block thaumAlkAlchFurnace = new BlockAlkimiumThaumiumAlchFurnace();
    public static Block voidAlkAlchFurnace = new BlockAlkimiumVoidAlchFurnace();
    public static Block mithrilAlkAlchFurnace = new BlockAlkimiumMithrilAlchFurnace();
    public static Block adamantAlkAlchFurnace = new BlockAlkimiumAdamantAlchFurnace();
    public static Block mithminiteAlkAlchFurnace = new BlockAlkimiumMithminiteAlchFurnace();

    public static Block blockPlanarDevice = new BlockPlanarDevice();

    public static void setup() {
        GameRegistry.registerBlock(alkimiumAlchFurnace, "alkimiumAlchFurnace");
        GameRegistry
                .registerTileEntity(TileAlkimiumAlchemicalFurnace.class, TileAlkimiumAlchemicalFurnace.tileEntityName);

        GameRegistry.registerBlock(thaumAlkAlchFurnace, "thaumAlkAlchFurnace");
        GameRegistry.registerTileEntity(
                TileThaumiumAlkimiumAlchemicalFurnace.class,
                TileThaumiumAlkimiumAlchemicalFurnace.tileEntityName);

        GameRegistry.registerBlock(voidAlkAlchFurnace, "voidAlkAlchFurnace");
        GameRegistry.registerTileEntity(
                TileVoidAlkimiumAlchemicalFurnace.class,
                TileVoidAlkimiumAlchemicalFurnace.tileEntityName);

        GameRegistry.registerBlock(mithrilAlkAlchFurnace, "mithrilAlkAlchFurnace");
        GameRegistry.registerTileEntity(
                TileMithrilAlkimiumAlchemicalFurnace.class,
                TileMithrilAlkimiumAlchemicalFurnace.tileEntityName);

        GameRegistry.registerBlock(adamantAlkAlchFurnace, "adamantAlkAlchFurnace");
        GameRegistry.registerTileEntity(
                TileAdamantAlkimiumAlchemicalFurnace.class,
                TileAdamantAlkimiumAlchemicalFurnace.tileEntityName);

        GameRegistry.registerBlock(mithminiteAlkAlchFurnace, "mithminiteAlkAlchFurnace");
        GameRegistry.registerTileEntity(
                TileMithminiteAlkimiumAlchemicalFurnace.class,
                TileMithminiteAlkimiumAlchemicalFurnace.tileEntityName);

        GameRegistry
                .registerBlock(blockPlanarDevice, ItemBlockPlanarDevice.class, blockPlanarDevice.getUnlocalizedName());
    }

}

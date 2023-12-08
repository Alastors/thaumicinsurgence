package thaumicinsurgence.main;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import thaumicinsurgence.main.modules.planar_artifice.containers.ContainerAlkiumAlchemyFurnace;
import thaumicinsurgence.main.modules.planar_artifice.core.blocks.tiles.TileAlkimiumAlchemicalFurnace;
import thaumicinsurgence.main.utils.CraftingManager;
import thaumicinsurgence.main.utils.compat.ModHelperManager;

@SuppressWarnings("unused")
public class CommonProxy implements IGuiHandler {

    public void preInit(FMLPreInitializationEvent event) {
        Config.Init(event.getSuggestedConfigurationFile());

        Config.setupBlocks();
        Config.setupItems();

        ModHelperManager.preInit();
    }

    public void init(FMLInitializationEvent event) {
        ModHelperManager.init();
    }

    public void postInit(FMLPostInitializationEvent event) {
        ModHelperManager.postInit();

        CraftingManager.setupCrafting();
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        // case 0:
        // return new ContainerAdvancedWand(player.inventory, world, x, y, z);
        // bring back when the Advanced Wand is finished
        if (ID == 9) {
            return new ContainerAlkiumAlchemyFurnace(
                    player.inventory,
                    (TileAlkimiumAlchemicalFurnace) world.getTileEntity(x, y, z));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 0:
                return new ContainerAdvancedWand(player.inventory, world, x, y, z);
            default:
                break;
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }
}

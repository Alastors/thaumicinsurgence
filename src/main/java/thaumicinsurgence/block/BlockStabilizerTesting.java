package thaumicinsurgence.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.crafting.IInfusionStabiliser;
import thaumicinsurgence.main.utils.TabThaumicInsurgence;
import thaumicinsurgence.main.utils.VersionInfo;
import thaumicinsurgence.tileentity.TileEntityStabilizerTesting;

public class BlockStabilizerTesting extends BlockContainer implements IInfusionStabiliser {
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityStabilizerTesting();
    }

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockStabilizerTesting() {
        super(Material.rock);
        this.setCreativeTab(TabThaumicInsurgence.tabThaumicInsurgence);
        this.setBlockName("thaumicStabilizer");
        this.setHardness(1f);
        this.setResistance(1.5f);
        this.setHarvestLevel("axe", 0);
    }

    @Override
    public boolean onBlockActivated(
            World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        boolean activate = false;

        return activate;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {

        if (side == 0) {
            return icons[0];
        } else if (side == 1) {
            return icons[1];
        } else if (side == 2 || side == 3) {
            return icons[2];
        } else {
            return icons[3];
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        icons = new IIcon[4];

        icons[0] = register.registerIcon(VersionInfo.ModID + ":arcane_stone_bricks");
        icons[1] = register.registerIcon(VersionInfo.ModID + ":arcane_stone_alt8");
        icons[2] = register.registerIcon(VersionInfo.ModID + ":arcane_stone_bricks");
        icons[3] = register.registerIcon(VersionInfo.ModID + ":arcane_stone_bricks");
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te != null && te instanceof IInventory) {
            IInventory inventory = (IInventory) te;

            for (int i = 0; i < inventory.getSizeInventory(); i++) {
                ItemStack itemStack = inventory.getStackInSlotOnClosing(i);

                if (itemStack != null) {
                    float spawnX = x + world.rand.nextFloat();
                    float spawnY = y + world.rand.nextFloat();
                    float spawnZ = z + world.rand.nextFloat();

                    EntityItem droppedItem = new EntityItem(world, spawnX, spawnY, spawnZ, itemStack);

                    float mult = 0.05F;

                    droppedItem.motionX = (-0.5F + world.rand.nextFloat()) * mult;
                    droppedItem.motionY = (4 + world.rand.nextFloat()) * mult;
                    droppedItem.motionZ = (-0.5F + world.rand.nextFloat()) * mult;

                    world.spawnEntityInWorld(droppedItem);
                }
            }
        }

        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    public boolean canStabaliseInfusion(World var1, int var2, int var3, int var4) {
        return true;
    }
}

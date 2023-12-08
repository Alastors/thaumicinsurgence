package thaumicinsurgence.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.common.blocks.BlockStoneDevice;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumicinsurgence.main.Config;
import thaumicinsurgence.main.utils.TabThaumicInsurgence;
import thaumicinsurgence.tileentity.TileEntityInfusionMatrixAlpha;

public class BlockInfusionMatrixAlpha extends BlockStoneDevice {

    public BlockInfusionMatrixAlpha() {
        this.setHardness(3.0F);
        this.setResistance(25.0F);
        this.setStepSound(Block.soundTypeStone);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        this.setCreativeTab(TabThaumicInsurgence.tabThaumicInsurgence);
        this.setBlockName("matrixAlpha");
        this.setHarvestLevel("pickaxe", 0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List list) {
        list.add(new ItemStack(par1, 1, 0));
    }

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {}

    public int getRenderType() {
        return Config.blockStoneDeviceRI;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityInfusionMatrixAlpha();
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntityInfusionMatrixAlpha();
    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        InventoryUtils.dropItems(par1World, par2, par3, par4);
        TileEntity tileEntity = par1World.getTileEntity(par2, par3, par4);
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block par5) {
        TileEntity te = world.getTileEntity(x, y, z);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float par7,
            float par8, float par9) {
        if (world.isRemote) {
            return true;
        } else {
            int metadata = world.getBlockMetadata(x, y, z);
            TileEntity tileEntity = world.getTileEntity(x, y, z);
        }
        return super.onBlockActivated(world, x, y, z, player, side, par7, par8, par9);
    }

    @Override
    public void addCollisionBoxesToList(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist,
            Entity par7Entity) {
        int metadata = world.getBlockMetadata(i, j, k);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, arraylist, par7Entity);
    }
}

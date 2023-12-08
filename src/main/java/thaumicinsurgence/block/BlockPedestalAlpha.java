package thaumicinsurgence.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.common.blocks.BlockStoneDevice;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.tiles.TilePedestal;
import thaumicinsurgence.main.Config;
import thaumicinsurgence.main.utils.TabThaumicInsurgence;
import thaumicinsurgence.main.utils.VersionInfo;

public class BlockPedestalAlpha extends BlockStoneDevice {

    public IIcon[] iconPedestal = new IIcon[2];

    public BlockPedestalAlpha() {
        this.setHardness(3.0F);
        this.setResistance(25.0F);
        this.setStepSound(Block.soundTypeStone);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        this.setCreativeTab(TabThaumicInsurgence.tabThaumicInsurgence);
        this.setBlockName("pedestalAlpha");
        this.setHarvestLevel("pickaxe", 0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List list) {
        list.add(new ItemStack(par1, 1, 0));
    }

    public IIcon getIcon(int side, int meta) {
        if (side <= 1) {
            return this.iconPedestal[1];
        } else {
            return this.iconPedestal[0];
        }
        // return this.iconPedestal[1];
    }

    public IIcon getIcon(IBlockAccess iblockaccess, int i, int j, int k, int side) {
        if (side <= 1) {
            return this.iconPedestal[1];
        } else {
            return this.iconPedestal[0];
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        this.iconPedestal[0] = ir.registerIcon(VersionInfo.ModID + ":marble_pedestal_side");
        // this.iconPedestal[0] = ir.registerIcon("thaumcraft:pedestal_side");
        this.iconPedestal[1] = ir.registerIcon(VersionInfo.ModID + ":arcane_marble");
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return Config.blockStoneDeviceThreeRI;
    }

    // TODO: NEEDS TO CHANGE
    public TileEntity createTileEntity(World world, int metadata) {
        return new TilePedestal();
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int i, int j, int k) {
        int metadata = world.getBlockMetadata(i, j, k);
        this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.99F, 0.75F);
        super.setBlockBoundsBasedOnState(world, i, j, k);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float par7,
            float par8, float par9) {
        // player.addChatMessage(new ChatComponentText("this is happening I guess"));
        if (world.isRemote) {
            return true;
        } else {
            TileEntity tileEntity = world.getTileEntity(x, y, z);
            // player.addChatMessage(new ChatComponentText(tileEntity.toString()));
            if (tileEntity instanceof TilePedestal) {
                // player.addChatMessage(new ChatComponentText("yes this is a Pedestal"));
                TilePedestal ped = (TilePedestal) tileEntity;
                if (ped.getStackInSlot(0) != null) {
                    // player.addChatMessage(new ChatComponentText("uh uh uh, uh uh uh, uh uh uh"));
                    InventoryUtils.dropItemsAtEntity(world, x, y, z, player);
                    world.playSoundEffect(
                            (double) x,
                            (double) y,
                            (double) z,
                            "random.pop",
                            0.2F,
                            ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 1.5F);
                    return true;
                }
                if (player.getCurrentEquippedItem() != null) {
                    // player.addChatMessage(new ChatComponentText("nuh nuh nuh, nuh nuh nuh, nuh nuh nuh"));
                    ItemStack i = player.getCurrentEquippedItem().copy();
                    i.stackSize = 1;
                    ped.setInventorySlotContents(0, i);
                    --player.getCurrentEquippedItem().stackSize;
                    if (player.getCurrentEquippedItem().stackSize == 0) {
                        player.setCurrentItemOrArmor(0, (ItemStack) null);
                    }

                    player.inventory.markDirty();
                    world.playSoundEffect(
                            (double) x,
                            (double) y,
                            (double) z,
                            "random.pop",
                            0.2F,
                            ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 1.6F);
                    return true;
                }
            }
        }
        return true;
    }
}

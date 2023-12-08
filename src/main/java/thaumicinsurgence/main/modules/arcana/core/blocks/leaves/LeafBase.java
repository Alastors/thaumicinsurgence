package thaumicinsurgence.main.modules.arcana.core.blocks.leaves;

import java.util.ArrayList;

import net.minecraft.block.BlockOldLeaf;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LeafBase extends BlockOldLeaf {

    public LeafBase() {
        super();
    }

    @Override
    public int damageDropped(int meta) {
        return meta % 8;
    }

    @SideOnly(Side.CLIENT)
    public int getBlockColor() {
        return 0xffffff;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess worldIn, int x, int y, int z, int meta) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(int meta) {
        return 0xffffff;
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, int x, int y, int z) {
        return 0xffffff;
    }

    // getSaplingDropRate
    protected int func_150123_b(int meta) {
        return 50;
    }

    // dropRareItem
    protected void func_150124_c(World w, int x, int y, int z, int meta, int chance) {
        // nothing yet.
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        if (world.getBlockMetadata(x, y, z) % 8 == 1) return new ArrayList<ItemStack>();
        else return super.onSheared(item, world, x, y, z, fortune);
    }
}

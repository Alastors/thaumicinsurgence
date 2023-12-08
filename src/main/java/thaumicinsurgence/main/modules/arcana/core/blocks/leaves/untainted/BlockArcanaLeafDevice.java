package thaumicinsurgence.main.modules.arcana.core.blocks.leaves.untainted;

import java.util.ArrayList;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import thaumicinsurgence.main.modules.arcana.Arcana;
import thaumicinsurgence.main.modules.arcana.core.blocks.leaves.LeafBase;
import thaumicinsurgence.main.modules.arcana.utils.TabArcana;

public class BlockArcanaLeafDevice extends LeafBase {

    public static String blocks[] = { "dair", "eucalyptus", "hawthorn", "willow", "greatwood", "silverwood" };
    IIcon icons[] = new IIcon[blocks.length];

    String blockType = "leaves/untainted/";

    public BlockArcanaLeafDevice() {
        super();
        setCreativeTab(TabArcana.tabArcana);
        setBlockName("AR_LeafDevice");
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        for (int i = 0; i < blocks.length; i++) {
            icons[i] = reg.registerIcon(Arcana.arcanaLabel + blockType + blocks[i] + "_leaves");
        }
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        if (world.getBlockMetadata(x, y, z) % 8 == 1) return new ArrayList<ItemStack>();
        else return super.onSheared(item, world, x, y, z, fortune);
    }

    @Override
    // this is the method that displays it in inventory
    public IIcon getIcon(int side, int meta) {
        return icons[meta % 8];
    }

    @Override
    // this is the method that actually displays it in world
    public IIcon getIcon(IBlockAccess worldIn, int x, int y, int z, int side) {
        int meta = worldIn.getBlockMetadata(x, y, z);
        return icons[meta % 8];
    }
}

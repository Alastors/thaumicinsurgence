package thaumicinsurgence.main.modules.arcana.core.blocks.leaves.untainted;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import thaumicinsurgence.main.modules.arcana.Arcana;
import thaumicinsurgence.main.modules.arcana.core.blocks.leaves.LeafBase;
import thaumicinsurgence.main.modules.arcana.utils.TabArcana;

public class BlockCursedArcanaLeafDevice extends LeafBase {

    public static String blocks[] = { "trypophobius", "oblivion", "old_oblivion", "tarnished", "numnum" }; // 11
    IIcon icons[] = new IIcon[blocks.length];

    String blockType = "leaves/untainted/";

    public BlockCursedArcanaLeafDevice() {
        super();
        setCreativeTab(TabArcana.tabArcana);
        setBlockName("AR_CursedLeafDevice");
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        for (int i = 0; i < blocks.length; i++) {
            icons[i] = reg.registerIcon(Arcana.arcanaLabel + blockType + blocks[i] + "_leaves");
        }
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

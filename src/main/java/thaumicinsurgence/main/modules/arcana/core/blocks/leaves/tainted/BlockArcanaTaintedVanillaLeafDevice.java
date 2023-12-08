package thaumicinsurgence.main.modules.arcana.core.blocks.leaves.tainted;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import thaumicinsurgence.main.modules.arcana.Arcana;
import thaumicinsurgence.main.modules.arcana.core.blocks.leaves.LeafBase;
import thaumicinsurgence.main.modules.arcana.utils.TabArcana;

public class BlockArcanaTaintedVanillaLeafDevice extends LeafBase {

    public static String blocks[] = { "oak", "spruce", "birch", "jungle", "acacia", "darkoak" }; // 6
    IIcon icons[] = new IIcon[blocks.length];
    String blockType = "leaves/tainted/tainted_";

    public BlockArcanaTaintedVanillaLeafDevice() {
        super();
        setCreativeTab(TabArcana.tabArcana);
        setBlockName("AR_TaintedVanillaLeafDevice");
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

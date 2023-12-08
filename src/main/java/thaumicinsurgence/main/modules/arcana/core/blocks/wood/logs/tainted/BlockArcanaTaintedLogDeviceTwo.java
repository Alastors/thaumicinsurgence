package thaumicinsurgence.main.modules.arcana.core.blocks.wood.logs.tainted;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import thaumicinsurgence.main.modules.arcana.Arcana;
import thaumicinsurgence.main.modules.arcana.utils.TabArcana;

public class BlockArcanaTaintedLogDeviceTwo extends BlockRotatedPillar {

    public static String blocks[] = { "tainted_acacia", "tainted_darkoak", "tainted_dair" };
    IIcon top[] = new IIcon[blocks.length];
    IIcon side[] = new IIcon[blocks.length];

    String[] blockNames = { "tainted_acacia_log", "tainted_darkoak_log", "tainted_dair_log" };
    String blockType = "logs/tainted/";

    public BlockArcanaTaintedLogDeviceTwo() {
        super(Material.wood);
        setHardness(1.0F);
        setResistance(1.0F);
        setStepSound(Block.soundTypeWood);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        setCreativeTab(TabArcana.tabArcana);
        setBlockName("AR_TaintedLogDevice2");
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        for (int i = 0; i < blocks.length; i++) {
            top[i] = reg.registerIcon(Arcana.arcanaLabel + blockType + blockNames[i] + "_top");
            side[i] = reg.registerIcon(Arcana.arcanaLabel + blockType + blockNames[i]);
        }
    }

    @Override
    public int damageDropped(int meta) {
        return meta % 4;
    }

    @Override
    protected IIcon getTopIcon(int meta) {
        return top[meta % 4];
    }

    @Override
    protected IIcon getSideIcon(int meta) {
        return side[meta % 4];
    }

    @Override
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @Override
    public boolean isWood(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 5;
    }

    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 5;
    }
}

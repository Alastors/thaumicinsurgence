package thaumicinsurgence.main.modules.arcana.core.blocks.wood;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import thaumicinsurgence.main.modules.arcana.Arcana;
import thaumicinsurgence.main.modules.arcana.utils.TabArcana;

public class BlockArcanaTaintedWoodDevice extends BlockContainer {

    public static String blocks[] = { "tainted_oak", "tainted_spruce", "tainted_birch", "tainted_jungle",
            "tainted_acacia", "tainted_darkoak", "tainted_dair", "tainted_greatwood", "tainted_eucalyptus",
            "tainted_hawthorn", "tainted_willow" };
    IIcon icons[] = new IIcon[blocks.length];

    String[] blockNames = { "tainted_oak_planks", "tainted_spruce_planks", "tainted_birch_planks",
            "tainted_jungle_planks", "tainted_acacia_planks", "tainted_darkoak_planks", "tainted_dair_planks",
            "tainted_greatwood_planks", "tainted_eucalyptus_planks", "tainted_hawthorn_planks",
            "tainted_willow_planks" };
    String blockType = "wood/tainted/";

    public BlockArcanaTaintedWoodDevice() {
        super(Material.wood);
        setHardness(6.0F);
        setResistance(34.0F);
        setStepSound(Block.soundTypeWood);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        setCreativeTab(TabArcana.tabArcana);
        setBlockName("AR_TaintedWoodDevice");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        for (int i = 0; i < blocks.length; i++) {
            icons[i] = reg.registerIcon(Arcana.arcanaLabel + blockType + blockNames[i]);
        }
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    // this is the method that displays it in inventory
    public IIcon getIcon(int side, int meta) {
        return icons[meta];
    }

    @Override
    // this is the method that actually displays it in world
    public IIcon getIcon(IBlockAccess worldIn, int x, int y, int z, int side) {
        int meta = worldIn.getBlockMetadata(x, y, z);
        return icons[meta];
    }
}

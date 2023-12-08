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

public class BlockArcanaWoodDevice extends BlockContainer {

    public static String blocks[] = { "dair", "dead", "greatwood", "silverwood", "silverwood_polished",
            "dead_silverwood", "trypophobius", "eucalyptus", "hawthorn", "willow", "numnum" }; // 11
    IIcon icons[] = new IIcon[blocks.length];

    String[] blockNames = { "dair_planks", "dead_planks", "greatwood_planks", "silverwood_planks",
            "silverwood_polished_planks", "dead_silverwood_planks", "trypophobius_planks", "eucalyptus_planks",
            "hawthorn_planks", "willow_planks", "numnum_planks" }; // 11
    String blockType = "wood/untainted/";

    public BlockArcanaWoodDevice() {
        super(Material.wood);
        setHardness(6.0F);
        setResistance(34.0F);
        setStepSound(Block.soundTypeWood);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        setCreativeTab(TabArcana.tabArcana);
        setBlockName("AR_WoodDevice");
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

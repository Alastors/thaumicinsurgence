package thaumicinsurgence.main.modules.planar_artifice.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import thaumicinsurgence.main.modules.planar_artifice.utils.TabPlanarArtifice;

public class BlockPlanarDevice extends BlockContainer {

    // , "mithrilliumBlock", "adaminiteBlock", "mithminiteBlock"
    public static String blocks[] = { "alkimiumConstruct", "alkimiumBlock" };
    IIcon icons[] = new IIcon[blocks.length];
    // , "bottom_mithrillium", "bottom_adaminite", "bottom_mithminite"
    String iconNames[] = { "alchemical_alkimium_construct", "alkimium_block" };

    public BlockPlanarDevice() {
        super(Material.iron);
        setHardness(6.0F);
        setResistance(34.0F);
        setStepSound(Block.soundTypeMetal);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        setCreativeTab(TabPlanarArtifice.tabPlanarArtifice);
        setBlockName("PA_PlanarDevice");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        for (int i = 0; i < icons.length; i++) {
            icons[i] = reg.registerIcon("planarartifice:" + iconNames[i]);
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

package thaumicinsurgence.main.modules.arcana.core.blocks.stone;

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

public class BlockArcanaTaintedStoneDeviceTwo extends BlockContainer {

    public static String blocks[] = { "tainted_stonebricks", "tainted_bricks", "tainted_obsidian",
            "tainted_sandstone" };

    IIcon icons[] = new IIcon[blocks.length];
    // , "bottom_mithrillium", "bottom_adaminite", "bottom_mithminite"
    String blockType = "stone/tainted/";

    public BlockArcanaTaintedStoneDeviceTwo() {
        super(Material.rock);
        setHardness(6.0F);
        setResistance(34.0F);
        setStepSound(Block.soundTypeStone);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        setCreativeTab(TabArcana.tabArcana);
        setBlockName("AR_TaintedStoneDevice2");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        for (int i = 0; i < blocks.length; i++) {
            icons[i] = reg.registerIcon(Arcana.arcanaLabel + blockType + blocks[i]);
        }
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    // this is the method that displays it in inventory
    public IIcon getIcon(int side, int meta) {
        return getMetaIcon(meta, side);
    }

    @Override
    // this is the method that actually displays it in world
    public IIcon getIcon(IBlockAccess worldIn, int x, int y, int z, int side) {
        int meta = worldIn.getBlockMetadata(x, y, z);
        return getMetaIcon(meta, side);
    }

    public IIcon getMetaIcon(int meta, int side) {
        int tempMeta = meta;
        return (tempMeta == 0 ? icons[0] : (tempMeta == 1 ? icons[1] : (tempMeta == 2 ? icons[2] : icons[3])));
    }
}

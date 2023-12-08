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

public class BlockArcanaTaintedStoneDeviceOne extends BlockContainer {

    public static String blocks[] = { "rock", "gold", "iron", "coal", "lapis", "diamond", "redstone", "emerald",
            "amber", "cinnabar", "ruby", "silver", "arcanium", "destroyed" };

    IIcon icons[] = new IIcon[blocks.length];
    // , "bottom_mithrillium", "bottom_adaminite", "bottom_mithminite"
    String blockType = "stone/tainted/";

    public BlockArcanaTaintedStoneDeviceOne() {
        super(Material.rock);
        setHardness(6.0F);
        setResistance(34.0F);
        setStepSound(Block.soundTypeStone);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        setCreativeTab(TabArcana.tabArcana);
        setBlockName("AR_TaintedStoneDevice");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        icons[0] = reg.registerIcon(Arcana.arcanaLabel + blockType + "tainted_rock");
        for (int i = 1; i < blocks.length; i++) {
            icons[i] = reg.registerIcon(Arcana.arcanaLabel + blockType + "tainted_" + blocks[i] + "_ore");
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
        if (meta == 14) tempMeta += side;
        return (tempMeta == 0 ? icons[0]
                : (tempMeta == 1 ? icons[1]
                        : (tempMeta == 2 ? icons[2]
                                : (tempMeta == 3 ? icons[3]
                                        : (tempMeta == 4 ? icons[4]
                                                : (tempMeta == 5 ? icons[5]
                                                        : (tempMeta == 6 ? icons[6]
                                                                : (tempMeta == 7 ? icons[7]
                                                                        : (tempMeta == 8 ? icons[8]
                                                                                : (tempMeta == 9 ? icons[9]
                                                                                        : (tempMeta == 10 ? icons[10]
                                                                                                : (tempMeta == 11
                                                                                                        ? icons[11]
                                                                                                        : (tempMeta
                                                                                                                == 12 ? icons[12]
                                                                                                                        : icons[13])))))))))))));
    }
}

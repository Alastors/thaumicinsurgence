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

public class BlockArcanaStoneDevice extends BlockContainer {

    public static String blocks[] = { "arcane_stone_bricks", "arcane_stone", "amber_ore", "cinnabar_ore", "ruby_ore",
            "silver_ore", "arcanium_ore", "broken_ore" };
    IIcon icons[] = new IIcon[255];
    // , "bottom_mithrillium", "bottom_adaminite", "bottom_mithminite"
    String blockType = "stone/untainted/";

    public BlockArcanaStoneDevice() {
        super(Material.iron);
        setHardness(6.0F);
        setResistance(34.0F);
        setStepSound(Block.soundTypeMetal);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        setCreativeTab(TabArcana.tabArcana);
        setBlockName("AR_StoneDevice");
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
        if (meta == 7) tempMeta += side;
        return (tempMeta
                == 0 ? icons[2]
                        : (tempMeta == 1 ? icons[3]
                                : (tempMeta == 2 ? icons[4]
                                        : (tempMeta == 3 ? icons[5]
                                                : (tempMeta == 4 ? icons[6]
                                                        : (tempMeta == 5 ? icons[7]
                                                                : (tempMeta == 6 || tempMeta == 7 || tempMeta == 8)
                                                                        ? icons[1]
                                                                        : icons[0])))))); // 1 = arcane stone block
                                                                                          // texture, 0 = arcane stone
                                                                                          // brick texture, brick has
                                                                                          // block on top & bot
    }
}

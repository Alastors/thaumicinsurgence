package thaumicinsurgence.main.modules.arcana.core.blocks.saplings.tainted;

import java.util.Random;

import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import thaumicinsurgence.main.modules.arcana.Arcana;
import thaumicinsurgence.main.modules.arcana.common.event.WorldGenBigOak;
import thaumicinsurgence.main.modules.arcana.utils.ArcanaBlocks;
import thaumicinsurgence.main.modules.arcana.utils.TabArcana;

public class BlockArcanaTaintedSapling extends BlockSapling {

    public static String saplings[] = { "tainted_dair", "tainted_greatwood", "tainted_willow", "tainted_eucalyptus",
            "tainted_hawthorn" };
    IIcon icons[] = new IIcon[saplings.length];

    String blockType = "saplings/tainted/";

    public BlockArcanaTaintedSapling() {
        super();
        setCreativeTab(TabArcana.tabArcana);
        setBlockName("AR_TaintedSapling");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return icons[meta % 8];
    }

    // doGrowTree
    public void func_149878_d(World w, int x, int y, int z, Random rnd) {
        int meta = w.getBlockMetadata(x, y, z) % 8;
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(w, rnd, x, y, z)) return;
        w.setBlockToAir(x, y, z);
        switch (meta) {
            case 0: // dair
                new WorldGenBigOak(
                        true,
                        6,
                        meta + 2,
                        meta,
                        5,
                        ArcanaBlocks.blockArcanaTaintedLogDeviceTwo,
                        ArcanaBlocks.blockTaintedArcanaLeafDevice).generate(w, rnd, x, y, z);
                return;
            case 1: // greatwood
                new WorldGenBigOak(
                        true,
                        6,
                        0,
                        1,
                        5,
                        ArcanaBlocks.blockArcanaTaintedLogDeviceThree,
                        ArcanaBlocks.blockTaintedArcanaLeafDevice).generate(w, rnd, x, y, z);
                return;
            case 2: // willow
                new WorldGenBigOak(
                        true,
                        6,
                        3,
                        2,
                        5,
                        ArcanaBlocks.blockArcanaTaintedLogDeviceThree,
                        ArcanaBlocks.blockTaintedArcanaLeafDevice).generate(w, rnd, x, y, z);
                return;
            case 3: // eucalyptus
                new WorldGenBigOak(
                        true,
                        6,
                        1,
                        3,
                        5,
                        ArcanaBlocks.blockArcanaTaintedLogDeviceThree,
                        ArcanaBlocks.blockTaintedArcanaLeafDevice).generate(w, rnd, x, y, z);
                return;
            case 4: // hawthorn
                new WorldGenBigOak(
                        true,
                        6,
                        2,
                        4,
                        5,
                        ArcanaBlocks.blockArcanaTaintedLogDeviceThree,
                        ArcanaBlocks.blockTaintedArcanaLeafDevice).generate(w, rnd, x, y, z);

        }
    }

    public int damageDropped(int meta) {
        return meta % 8;
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        for (int i = 0; i < saplings.length; i++) {
            icons[i] = reg.registerIcon(Arcana.arcanaLabel + blockType + saplings[i] + "_sapling");
        }
    }
}

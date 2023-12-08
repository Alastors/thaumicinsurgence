package thaumicinsurgence.main.modules.arcana.core.blocks.saplings.untainted;

import java.util.Random;

import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import thaumicinsurgence.main.modules.arcana.Arcana;
import thaumicinsurgence.main.modules.arcana.common.event.WorldGenBigOak;
import thaumicinsurgence.main.modules.arcana.utils.ArcanaBlocks;
import thaumicinsurgence.main.modules.arcana.utils.TabArcana;

public class BlockArcanaSapling extends BlockSapling {

    public static String saplings[] = { "dair", "eucalyptus", "hawthorn", "willow", "greatwood", "silverwood" };
    IIcon icons[] = new IIcon[saplings.length];

    String blockType = "saplings/untainted/";

    public BlockArcanaSapling() {
        super();
        setCreativeTab(TabArcana.tabArcana);
        setBlockName("AR_Sapling");
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
            case 0:
                new WorldGenBigOak(
                        true,
                        6,
                        meta,
                        meta,
                        5,
                        ArcanaBlocks.blockArcanaLogDeviceTwo,
                        ArcanaBlocks.blockArcanaLeafDevice).generate(w, rnd, x, y, z);
                return;
            case 1:
            case 2:
            case 3:
                new WorldGenBigOak(
                        true,
                        6,
                        meta,
                        meta,
                        5,
                        ArcanaBlocks.blockArcanaLogDeviceOne,
                        ArcanaBlocks.blockArcanaLeafDevice).generate(w, rnd, x, y, z);
                return;
            case 4:
            case 5:
                new WorldGenBigOak(
                        true,
                        6,
                        meta - 2,
                        meta,
                        5,
                        ArcanaBlocks.blockArcanaLogDeviceTwo,
                        ArcanaBlocks.blockArcanaLeafDevice).generate(w, rnd, x, y, z);
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

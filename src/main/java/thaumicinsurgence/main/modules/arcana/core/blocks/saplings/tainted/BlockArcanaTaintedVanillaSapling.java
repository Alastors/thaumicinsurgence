package thaumicinsurgence.main.modules.arcana.core.blocks.saplings.tainted;

import java.util.Random;

import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import thaumicinsurgence.main.modules.arcana.Arcana;
import thaumicinsurgence.main.modules.arcana.common.event.WorldGenBigOak;
import thaumicinsurgence.main.modules.arcana.common.event.WorldGenOak;
import thaumicinsurgence.main.modules.arcana.utils.ArcanaBlocks;
import thaumicinsurgence.main.modules.arcana.utils.TabArcana;

public class BlockArcanaTaintedVanillaSapling extends BlockSapling {

    public static String saplings[] = { "tainted_oak", "tainted_spruce", "tainted_birch", "tainted_jungle",
            "tainted_acacia", "tainted_darkoak" };
    IIcon icons[] = new IIcon[saplings.length];

    String blockType = "saplings/tainted/";

    public BlockArcanaTaintedVanillaSapling() {
        super();
        setCreativeTab(TabArcana.tabArcana);
        setBlockName("AR_TaintedVanillaSapling");
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
            case 0: // Oak
            case 2: // Birch
                new WorldGenOak(
                        true,
                        5,
                        meta,
                        meta,
                        false,
                        ArcanaBlocks.blockArcanaTaintedLogDeviceOne,
                        ArcanaBlocks.blockArcanaTaintedVanillaLeafDevice).generate(w, rnd, x, y, z);
                return;
            case 1: // Spruce
            case 3: // Jungle
                new WorldGenBigOak(
                        true,
                        6,
                        meta,
                        meta,
                        5,
                        ArcanaBlocks.blockArcanaTaintedLogDeviceOne,
                        ArcanaBlocks.blockArcanaTaintedVanillaLeafDevice).generate(w, rnd, x, y, z);
                return;
            case 4: // Acacia
            case 5: // Dark Oak
                new WorldGenBigOak(
                        true,
                        6,
                        meta - 4,
                        meta,
                        5,
                        ArcanaBlocks.blockArcanaTaintedLogDeviceTwo,
                        ArcanaBlocks.blockArcanaTaintedVanillaLeafDevice).generate(w, rnd, x, y, z);
                return;
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

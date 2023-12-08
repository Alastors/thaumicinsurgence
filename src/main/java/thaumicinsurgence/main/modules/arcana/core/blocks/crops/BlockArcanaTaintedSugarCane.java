package thaumicinsurgence.main.modules.arcana.core.blocks.crops;

import java.util.Random;

import net.minecraft.block.BlockReed;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import thaumicinsurgence.main.modules.arcana.Arcana;
import thaumicinsurgence.main.modules.arcana.utils.ArcanaBlocks;
import thaumicinsurgence.main.modules.arcana.utils.TabArcana;

public class BlockArcanaTaintedSugarCane extends BlockReed {

    IIcon icons[] = new IIcon[2];

    public BlockArcanaTaintedSugarCane() {
        float f = 0.375F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
        this.setTickRandomly(true);
        setCreativeTab(TabArcana.tabArcana);
        setBlockName("AR_TaintedReeds");
        disableStats();
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        icons[0] = reg.registerIcon(Arcana.arcanaLabel + "tainted_sugar_cane");
        icons[1] = reg.registerIcon(Arcana.arcanaLabel + "plants/tainted_sugar_cane_block");
    }

    @Override
    // this is the method that displays it in general
    public IIcon getIcon(int side, int meta) {
        return icons[1];
    }

    @Override
    public Item getItem(World worldIn, int x, int y, int z) {
        return super.getItem(worldIn, x, y, z);
    }

    public void updateTick(World worldIn, int x, int y, int z, Random random) {
        if (worldIn.getBlock(x, y - 1, z) == ArcanaBlocks.blockTaintedReeds || this.func_150170_e(worldIn, x, y, z)) {
            if (worldIn.isAirBlock(x, y + 1, z)) {
                int l;

                for (l = 1; worldIn.getBlock(x, y - l, z) == this; ++l) {
                    ;
                }

                if (l < 3) {
                    int i1 = worldIn.getBlockMetadata(x, y, z);

                    if (i1 == 15) {
                        worldIn.setBlock(x, y + 1, z, this);
                        worldIn.setBlockMetadataWithNotify(x, y, z, 0, 4);
                    } else {
                        worldIn.setBlockMetadataWithNotify(x, y, z, i1 + 1, 4);
                    }
                }
            }
        }
    }

    public boolean canBlockStay(World worldIn, int x, int y, int z) {
        return this.canPlaceBlockAt(worldIn, x, y, z);
    }

    // 16777215 = default texture colour
    public int colorMultiplier(IBlockAccess worldIn, int x, int y, int z) {
        return 16777215;
    }

    public int getRenderType() {
        return 1;
    }

}

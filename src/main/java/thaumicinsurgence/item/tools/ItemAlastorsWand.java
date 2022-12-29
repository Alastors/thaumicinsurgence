package thaumicinsurgence.item.tools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileInfusionMatrix;
import thaumcraft.common.tiles.TilePedestal;
import thaumicinsurgence.block.BlockArcaneMarble;
import thaumicinsurgence.block.BlockArcaneMarbleBrick;
import thaumicinsurgence.main.Config;
import thaumicinsurgence.main.utils.TabThaumicInsurgence;
import thaumicinsurgence.tileentity.TileEntityInfusionMatrixAlpha;
import thaumicinsurgence.tileentity.TileEntityInfusionPillarAlpha;

public class ItemAlastorsWand extends Item {
    public TileEntity matrix;

    public ItemAlastorsWand() {
        setCreativeTab(TabThaumicInsurgence.tabThaumicInsurgence);
        setUnlocalizedName("ItemAlastorsWand");
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.itemIcon = ir.registerIcon("thaumicinsurgence:corrupted_arcanium_wand_core");
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.itemIcon;
    }

    @Override
    public boolean onItemUseFirst(
            ItemStack stack,
            EntityPlayer player,
            World world,
            int targetX,
            int targetY,
            int targetZ,
            int side,
            float hitX,
            float hitY,
            float hitZ) {
        if (!world.isRemote) {
            matrix = world.getTileEntity(targetX, targetY, targetZ);
            Block bottom[] = new Block[4], middle[] = new Block[4];
            TileEntity pedestal;
            boolean bottomLayer = true, middleLayer = true, topLayer = true;
            if (matrix instanceof TileInfusionMatrix) {

                String[] users =
                        MinecraftServer.getServer().getConfigurationManager().getAllUsernames();
                player.addChatMessage(new ChatComponentText("That's a big ass mudspaw right der"));

                // gets the bottom four bricks
                bottom[0] = world.getBlock(targetX + 1, targetY - 2, targetZ + 1);
                bottom[1] = world.getBlock(targetX + 1, targetY - 2, targetZ - 1);
                bottom[2] = world.getBlock(targetX - 1, targetY - 2, targetZ - 1);
                bottom[3] = world.getBlock(targetX - 1, targetY - 2, targetZ + 1);
                pedestal = world.getTileEntity(targetX, targetY - 2, targetZ);
                if (!(pedestal instanceof TilePedestal)) {
                    bottomLayer = false;
                }
                for (int i = 0; i < 4; i++) {
                    // player.addChatMessage(new ChatComponentText(bottom[i].getUnlocalizedName()));
                    if (!(bottom[i] instanceof BlockArcaneMarbleBrick)) {
                        bottomLayer = false;
                    }
                }

                player.addChatMessage(new ChatComponentText("bottom layer: " + (((Boolean) bottomLayer)).toString()));

                // gets the middle four blocks
                middle[0] = world.getBlock(targetX + 1, targetY - 1, targetZ + 1);
                middle[1] = world.getBlock(targetX + 1, targetY - 1, targetZ - 1);
                middle[2] = world.getBlock(targetX - 1, targetY - 1, targetZ - 1);
                middle[3] = world.getBlock(targetX - 1, targetY - 1, targetZ + 1);
                for (int i = 0; i < 4; i++) {
                    // player.addChatMessage(new ChatComponentText(middle[i].getUnlocalizedName()));
                    if (!(middle[i] instanceof BlockArcaneMarble)) {
                        middleLayer = false;
                    }
                }
                player.addChatMessage(new ChatComponentText("middle layer: " + (((Boolean) middleLayer)).toString()));
                if (middleLayer && bottomLayer) {
                    TileEntityInfusionPillarAlpha justTheTip;
                    world.setBlock(targetX + 1, targetY - 1, targetZ + 1, ConfigBlocks.blockStoneDevice, 4, 3);
                    world.setBlock(targetX + 1, targetY - 1, targetZ - 1, ConfigBlocks.blockStoneDevice, 4, 3);
                    world.setBlock(targetX - 1, targetY - 1, targetZ - 1, ConfigBlocks.blockStoneDevice, 4, 3);
                    world.setBlock(targetX - 1, targetY - 1, targetZ + 1, ConfigBlocks.blockStoneDevice, 4, 3);

                    world.setBlock(targetX + 1, targetY - 2, targetZ + 1, Config.pillarAlpha, 3, 3);
                    justTheTip =
                            (TileEntityInfusionPillarAlpha) world.getTileEntity(targetX + 1, targetY - 2, targetZ + 1);
                    // 4 is the right orientation for this one.
                    justTheTip.orientation = 4;
                    world.setBlock(targetX + 1, targetY - 2, targetZ - 1, Config.pillarAlpha, 3, 3);
                    justTheTip =
                            (TileEntityInfusionPillarAlpha) world.getTileEntity(targetX + 1, targetY - 2, targetZ - 1);
                    // 5 is the right orientation for this one.
                    justTheTip.orientation = 5;
                    world.setBlock(targetX - 1, targetY - 2, targetZ - 1, Config.pillarAlpha, 3, 3);
                    justTheTip =
                            (TileEntityInfusionPillarAlpha) world.getTileEntity(targetX - 1, targetY - 2, targetZ - 1);
                    // 0 is the right orientation for this one.
                    justTheTip.orientation = 0;
                    world.setBlock(targetX - 1, targetY - 2, targetZ + 1, Config.pillarAlpha, 3, 3);
                    justTheTip =
                            (TileEntityInfusionPillarAlpha) world.getTileEntity(targetX - 1, targetY - 2, targetZ + 1);
                    // 3 is the right orientation for this one.
                    justTheTip.orientation = 3;
                }
            }
        }

        return false;
    }

    public static boolean fitInfusionAltar(World world, int x, int y, int z) {
        ItemStack br1 = new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6);
        ItemStack br2 = new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 7);
        ItemStack bs = new ItemStack(Config.matrixAlpha, 1, 2);
        new ItemStack(ConfigBlocks.blockStoneDevice, 1, 1);
        ItemStack[][][] blueprint = new ItemStack[][][] {
            {{null, null, null}, {null, bs, null}, {null, null, null}},
            {{br1, null, br1}, {null, null, null}, {br1, null, br1}},
            {{br2, null, br2}, {null, null, null}, {br2, null, br2}}
        };

        for (int yy = 0; yy < 3; ++yy) {
            for (int xx = 0; xx < 3; ++xx) {
                for (int zz = 0; zz < 3; ++zz) {
                    if (blueprint[yy][xx][zz] == null) {
                        if (xx == 1 && zz == 1 && yy == 2) {
                            TileEntity t = world.getTileEntity(x + xx, y - yy + 2, z + zz);
                            if (!(t instanceof TilePedestal)) {
                                return false;
                            }
                        } else if (!world.isAirBlock(x + xx, y - yy + 2, z + zz)) {
                            return false;
                        }
                    } else {
                        Block block = world.getBlock(x + xx, y - yy + 2, z + zz);
                        int md = world.getBlockMetadata(x + xx, y - yy + 2, z + zz);
                        if (!(new ItemStack(block, 1, md)).isItemEqual(blueprint[yy][xx][zz])) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public static void replaceInfusionAltar(World world, int x, int y, int z) {
        int[][][] blueprint = new int[][][] {
            {{0, 0, 0}, {0, 9, 0}, {0, 0, 0}},
            {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}},
            {{2, 0, 3}, {0, 0, 0}, {4, 0, 5}}
        };

        for (int yy = 0; yy < 3; ++yy) {
            for (int xx = 0; xx < 3; ++xx) {
                for (int zz = 0; zz < 3; ++zz) {
                    if (blueprint[yy][xx][zz] != 0) {
                        if (blueprint[yy][xx][zz] == 1) {
                            world.setBlock(x + xx, y - yy + 2, z + zz, ConfigBlocks.blockStoneDevice, 4, 3);
                            world.addBlockEvent(x + xx, y - yy + 2, z + zz, ConfigBlocks.blockStoneDevice, 1, 0);
                        }

                        if (blueprint[yy][xx][zz] > 1 && blueprint[yy][xx][zz] < 9) {
                            world.setBlock(x + xx, y - yy + 2, z + zz, ConfigBlocks.blockStoneDevice, 3, 3);
                            TileEntityInfusionPillarAlpha tip =
                                    (TileEntityInfusionPillarAlpha) world.getTileEntity(x + xx, y - yy + 2, z + zz);
                            tip.orientation = (byte) blueprint[yy][xx][zz];
                            world.markBlockForUpdate(x + xx, y - yy + 2, z + zz);
                            world.addBlockEvent(x + xx, y - yy + 2, z + zz, ConfigBlocks.blockStoneDevice, 1, 0);
                        }

                        if (blueprint[yy][xx][zz] == 9) {
                            TileEntityInfusionMatrixAlpha tis =
                                    (TileEntityInfusionMatrixAlpha) world.getTileEntity(x + xx, y - yy + 2, z + zz);
                            tis.active = true;
                            world.markBlockForUpdate(x + xx, y - yy + 2, z + zz);
                        }
                    }
                }
            }
        }

        world.playSoundEffect((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "thaumcraft:wand", 1.0F, 1.0F);
    }
}

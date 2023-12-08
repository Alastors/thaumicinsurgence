package thaumicinsurgence.main.modules.arcana.utils;

import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;
import thaumicinsurgence.main.modules.arcana.Arcana;
import thaumicinsurgence.main.modules.arcana.core.blocks.crops.BlockArcanaTaintedSugarCane;
import thaumicinsurgence.main.modules.arcana.core.blocks.leaves.tainted.BlockArcanaTaintedLeafDevice;
import thaumicinsurgence.main.modules.arcana.core.blocks.leaves.tainted.BlockArcanaTaintedVanillaLeafDevice;
import thaumicinsurgence.main.modules.arcana.core.blocks.leaves.tainted.ItemBlockArcanaTaintedLeafDevice;
import thaumicinsurgence.main.modules.arcana.core.blocks.leaves.tainted.ItemBlockArcanaTaintedVanillaLeafDevice;
import thaumicinsurgence.main.modules.arcana.core.blocks.leaves.untainted.BlockArcanaLeafDevice;
import thaumicinsurgence.main.modules.arcana.core.blocks.leaves.untainted.BlockCursedArcanaLeafDevice;
import thaumicinsurgence.main.modules.arcana.core.blocks.leaves.untainted.ItemBlockArcanaLeafDevice;
import thaumicinsurgence.main.modules.arcana.core.blocks.leaves.untainted.ItemBlockCursedArcanaLeafDevice;
import thaumicinsurgence.main.modules.arcana.core.blocks.saplings.tainted.BlockArcanaTaintedSapling;
import thaumicinsurgence.main.modules.arcana.core.blocks.saplings.tainted.BlockArcanaTaintedVanillaSapling;
import thaumicinsurgence.main.modules.arcana.core.blocks.saplings.tainted.ItemBlockArcanaTaintedSapling;
import thaumicinsurgence.main.modules.arcana.core.blocks.saplings.tainted.ItemBlockArcanaTaintedVanillaSapling;
import thaumicinsurgence.main.modules.arcana.core.blocks.saplings.untainted.BlockArcanaSapling;
import thaumicinsurgence.main.modules.arcana.core.blocks.saplings.untainted.BlockCursedArcanaSapling;
import thaumicinsurgence.main.modules.arcana.core.blocks.saplings.untainted.ItemBlockArcanaSapling;
import thaumicinsurgence.main.modules.arcana.core.blocks.saplings.untainted.ItemBlockCursedArcanaSapling;
import thaumicinsurgence.main.modules.arcana.core.blocks.stone.BlockArcanaStoneDevice;
import thaumicinsurgence.main.modules.arcana.core.blocks.stone.BlockArcanaTaintedStoneDeviceOne;
import thaumicinsurgence.main.modules.arcana.core.blocks.stone.BlockArcanaTaintedStoneDeviceTwo;
import thaumicinsurgence.main.modules.arcana.core.blocks.stone.ItemBlockArcanaStoneDevice;
import thaumicinsurgence.main.modules.arcana.core.blocks.stone.ItemBlockArcanaTaintedStoneDeviceOne;
import thaumicinsurgence.main.modules.arcana.core.blocks.stone.ItemBlockArcanaTaintedStoneDeviceTwo;
import thaumicinsurgence.main.modules.arcana.core.blocks.wood.BlockArcanaTaintedWoodDevice;
import thaumicinsurgence.main.modules.arcana.core.blocks.wood.BlockArcanaWoodDevice;
import thaumicinsurgence.main.modules.arcana.core.blocks.wood.ItemBlockArcanaTaintedWoodDevice;
import thaumicinsurgence.main.modules.arcana.core.blocks.wood.ItemBlockArcanaWoodDevice;
import thaumicinsurgence.main.modules.arcana.core.blocks.wood.logs.tainted.BlockArcanaTaintedLogDeviceOne;
import thaumicinsurgence.main.modules.arcana.core.blocks.wood.logs.tainted.BlockArcanaTaintedLogDeviceThree;
import thaumicinsurgence.main.modules.arcana.core.blocks.wood.logs.tainted.BlockArcanaTaintedLogDeviceTwo;
import thaumicinsurgence.main.modules.arcana.core.blocks.wood.logs.tainted.ItemBlockArcanaTaintedLogDeviceOne;
import thaumicinsurgence.main.modules.arcana.core.blocks.wood.logs.tainted.ItemBlockArcanaTaintedLogDeviceThree;
import thaumicinsurgence.main.modules.arcana.core.blocks.wood.logs.tainted.ItemBlockArcanaTaintedLogDeviceTwo;
import thaumicinsurgence.main.modules.arcana.core.blocks.wood.logs.untainted.BlockArcanaLogDeviceOne;
import thaumicinsurgence.main.modules.arcana.core.blocks.wood.logs.untainted.BlockArcanaLogDeviceThree;
import thaumicinsurgence.main.modules.arcana.core.blocks.wood.logs.untainted.BlockArcanaLogDeviceTwo;
import thaumicinsurgence.main.modules.arcana.core.blocks.wood.logs.untainted.ItemBlockArcanaLogDeviceOne;
import thaumicinsurgence.main.modules.arcana.core.blocks.wood.logs.untainted.ItemBlockArcanaLogDeviceThree;
import thaumicinsurgence.main.modules.arcana.core.blocks.wood.logs.untainted.ItemBlockArcanaLogDeviceTwo;

public class ArcanaBlocks {

    public static final Class<Arcana> core = Arcana.class;

    public static Block blockArcanaStoneDevice = new BlockArcanaStoneDevice();
    public static Block blockArcanaTaintedStoneDeviceOne = new BlockArcanaTaintedStoneDeviceOne();
    public static Block blockArcanaTaintedStoneDeviceTwo = new BlockArcanaTaintedStoneDeviceTwo();
    public static Block blockArcanaWoodDevice = new BlockArcanaWoodDevice();
    public static Block blockArcanaTaintedWoodDevice = new BlockArcanaTaintedWoodDevice();
    public static Block blockArcanaLogDeviceOne = new BlockArcanaLogDeviceOne();
    public static Block blockArcanaLogDeviceTwo = new BlockArcanaLogDeviceTwo();
    public static Block blockArcanaLogDeviceThree = new BlockArcanaLogDeviceThree();
    public static Block blockArcanaTaintedLogDeviceOne = new BlockArcanaTaintedLogDeviceOne();
    public static Block blockArcanaTaintedLogDeviceTwo = new BlockArcanaTaintedLogDeviceTwo();
    public static Block blockArcanaTaintedLogDeviceThree = new BlockArcanaTaintedLogDeviceThree();
    public static Block blockArcanaLeafDevice = new BlockArcanaLeafDevice();
    public static Block blockTaintedReeds = new BlockArcanaTaintedSugarCane();
    public static Block blockSaplings = new BlockArcanaSapling();
    public static Block blockTaintedVanillaSaplings = new BlockArcanaTaintedVanillaSapling();
    public static Block blockArcanaTaintedVanillaLeafDevice = new BlockArcanaTaintedVanillaLeafDevice();
    public static Block blockTaintedArcanaSaplings = new BlockArcanaTaintedSapling();
    public static Block blockTaintedArcanaLeafDevice = new BlockArcanaTaintedLeafDevice();
    public static Block blockCursedArcanaSaplings = new BlockCursedArcanaSapling();
    public static Block blockCursedArcanaLeafDevice = new BlockCursedArcanaLeafDevice();

    public static void setup() {

        GameRegistry.registerBlock(
                blockArcanaStoneDevice,
                ItemBlockArcanaStoneDevice.class,
                blockArcanaStoneDevice.getUnlocalizedName());

        GameRegistry.registerBlock(
                blockArcanaTaintedStoneDeviceOne,
                ItemBlockArcanaTaintedStoneDeviceOne.class,
                blockArcanaTaintedStoneDeviceOne.getUnlocalizedName());

        GameRegistry.registerBlock(
                blockArcanaTaintedStoneDeviceTwo,
                ItemBlockArcanaTaintedStoneDeviceTwo.class,
                blockArcanaTaintedStoneDeviceTwo.getUnlocalizedName());

        GameRegistry.registerBlock(
                blockArcanaWoodDevice,
                ItemBlockArcanaWoodDevice.class,
                blockArcanaWoodDevice.getUnlocalizedName());

        GameRegistry.registerBlock(
                blockArcanaTaintedWoodDevice,
                ItemBlockArcanaTaintedWoodDevice.class,
                blockArcanaTaintedWoodDevice.getUnlocalizedName());

        GameRegistry.registerBlock(
                blockArcanaLogDeviceOne,
                ItemBlockArcanaLogDeviceOne.class,
                blockArcanaLogDeviceOne.getUnlocalizedName());

        GameRegistry.registerBlock(
                blockArcanaLogDeviceTwo,
                ItemBlockArcanaLogDeviceTwo.class,
                blockArcanaLogDeviceTwo.getUnlocalizedName());

        GameRegistry.registerBlock(
                blockArcanaLogDeviceThree,
                ItemBlockArcanaLogDeviceThree.class,
                blockArcanaLogDeviceThree.getUnlocalizedName());

        GameRegistry.registerBlock(
                blockArcanaTaintedLogDeviceOne,
                ItemBlockArcanaTaintedLogDeviceOne.class,
                blockArcanaTaintedLogDeviceOne.getUnlocalizedName());

        GameRegistry.registerBlock(
                blockArcanaTaintedLogDeviceTwo,
                ItemBlockArcanaTaintedLogDeviceTwo.class,
                blockArcanaTaintedLogDeviceTwo.getUnlocalizedName());

        GameRegistry.registerBlock(
                blockArcanaTaintedLogDeviceThree,
                ItemBlockArcanaTaintedLogDeviceThree.class,
                blockArcanaTaintedLogDeviceThree.getUnlocalizedName());

        GameRegistry.registerBlock(
                blockArcanaLeafDevice,
                ItemBlockArcanaLeafDevice.class,
                blockArcanaLeafDevice.getUnlocalizedName());

        GameRegistry.registerBlock(blockTaintedReeds, blockTaintedReeds.getUnlocalizedName());

        GameRegistry.registerBlock(blockSaplings, ItemBlockArcanaSapling.class, blockSaplings.getUnlocalizedName());

        GameRegistry.registerBlock(
                blockTaintedVanillaSaplings,
                ItemBlockArcanaTaintedVanillaSapling.class,
                blockTaintedVanillaSaplings.getUnlocalizedName());

        GameRegistry.registerBlock(
                blockArcanaTaintedVanillaLeafDevice,
                ItemBlockArcanaTaintedVanillaLeafDevice.class,
                blockArcanaTaintedVanillaLeafDevice.getUnlocalizedName());

        GameRegistry.registerBlock(
                blockTaintedArcanaSaplings,
                ItemBlockArcanaTaintedSapling.class,
                blockTaintedArcanaSaplings.getUnlocalizedName());

        GameRegistry.registerBlock(
                blockTaintedArcanaLeafDevice,
                ItemBlockArcanaTaintedLeafDevice.class,
                blockTaintedArcanaLeafDevice.getUnlocalizedName());

        GameRegistry.registerBlock(
                blockCursedArcanaSaplings,
                ItemBlockCursedArcanaSapling.class,
                blockCursedArcanaSaplings.getUnlocalizedName());

        GameRegistry.registerBlock(
                blockCursedArcanaLeafDevice,
                ItemBlockCursedArcanaLeafDevice.class,
                blockCursedArcanaLeafDevice.getUnlocalizedName());
    }

}

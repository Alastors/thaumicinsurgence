package thaumicinsurgence.main.modules.arcana.utils;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;

public class ArcanaRecipes {

    public static CrucibleRecipe arcaniumGoggles;
    public static CrucibleRecipe tDair, tGW, tWillow, tEuc, tHawthorn;
    public static CrucibleRecipe tOak, tSpruce, tBirch, tJungle, tAcacia, tDOak;
    public static CrucibleRecipe dair, euc, hawthorn, willow, arcanus, shimmer;
    public static CrucibleRecipe trypo, oblivion, old_oblivion, tarnished, numnum;
    public static ShapelessArcaneRecipe tainted_oak, tainted_spruce, tainted_birch, tainted_jungle, tainted_acacia,
            tainted_darkoak, tainted_dair, tainted_greatwood, tainted_eucalyptus, tainted_hawthorn, tainted_willow;
    public static ShapelessArcaneRecipe dairPlank, dead, greatwood, silverwood, dead_silverwood, trypophobius,
            eucalyptus, hawthornPlank, willowPlank, numnumPlank, polished;

    public static void arcanaAlchemy() {
        arcaniumGoggles = ThaumcraftApi.addCrucibleRecipe(
                "Arcana",
                new ItemStack(ArcanaItems.goggles),
                new ItemStack(ConfigItems.itemGoggles),
                new AspectList().add(Aspect.TAINT, 8));

        taintedSaplings();
        taintedVanilla();
        arcanaSaplings();
        cursedSaplings();
        planks();
        taintedPlanks();
    }

    public static void taintedSaplings() {
        tDair = ThaumcraftApi.addCrucibleRecipe(
                "AR_TaintedSapling",
                new ItemStack(ArcanaBlocks.blockTaintedArcanaSaplings, 1, 0),
                new ItemStack(ArcanaBlocks.blockSaplings, 1, 0),
                new AspectList().add(Aspect.TAINT, 1));
        tGW = ThaumcraftApi.addCrucibleRecipe(
                "AR_TaintedSapling",
                new ItemStack(ArcanaBlocks.blockTaintedArcanaSaplings, 1, 1),
                new ItemStack(ArcanaBlocks.blockSaplings, 1, 5),
                new AspectList().add(Aspect.TAINT, 1));
        tWillow = ThaumcraftApi.addCrucibleRecipe(
                "AR_TaintedSapling",
                new ItemStack(ArcanaBlocks.blockTaintedArcanaSaplings, 1, 2),
                new ItemStack(ArcanaBlocks.blockSaplings, 1, 3),
                new AspectList().add(Aspect.TAINT, 1));
        tEuc = ThaumcraftApi.addCrucibleRecipe(
                "AR_TaintedSapling",
                new ItemStack(ArcanaBlocks.blockTaintedArcanaSaplings, 1, 3),
                new ItemStack(ArcanaBlocks.blockSaplings, 1, 1),
                new AspectList().add(Aspect.TAINT, 1));
        tHawthorn = ThaumcraftApi.addCrucibleRecipe(
                "AR_TaintedSapling",
                new ItemStack(ArcanaBlocks.blockTaintedArcanaSaplings, 1, 4),
                new ItemStack(ArcanaBlocks.blockSaplings, 1, 2),
                new AspectList().add(Aspect.TAINT, 1));
    }

    public static void taintedVanilla() {
        tOak = ThaumcraftApi.addCrucibleRecipe(
                "AR_TaintedVanilla",
                new ItemStack(ArcanaBlocks.blockTaintedVanillaSaplings, 1, 0),
                new ItemStack(Blocks.sapling, 1, 0),
                new AspectList().add(Aspect.TAINT, 1));
        tSpruce = ThaumcraftApi.addCrucibleRecipe(
                "AR_TaintedVanilla",
                new ItemStack(ArcanaBlocks.blockTaintedVanillaSaplings, 1, 1),
                new ItemStack(Blocks.sapling, 1, 5),
                new AspectList().add(Aspect.TAINT, 1));
        tBirch = ThaumcraftApi.addCrucibleRecipe(
                "AR_TaintedVanilla",
                new ItemStack(ArcanaBlocks.blockTaintedVanillaSaplings, 1, 2),
                new ItemStack(Blocks.sapling, 1, 3),
                new AspectList().add(Aspect.TAINT, 1));
        tJungle = ThaumcraftApi.addCrucibleRecipe(
                "AR_TaintedVanilla",
                new ItemStack(ArcanaBlocks.blockTaintedVanillaSaplings, 1, 3),
                new ItemStack(Blocks.sapling, 1, 1),
                new AspectList().add(Aspect.TAINT, 1));
        tAcacia = ThaumcraftApi.addCrucibleRecipe(
                "AR_TaintedVanilla",
                new ItemStack(ArcanaBlocks.blockTaintedVanillaSaplings, 1, 4),
                new ItemStack(Blocks.sapling, 1, 2),
                new AspectList().add(Aspect.TAINT, 1));
        tDOak = ThaumcraftApi.addCrucibleRecipe(
                "AR_TaintedVanilla",
                new ItemStack(ArcanaBlocks.blockTaintedVanillaSaplings, 1, 5),
                new ItemStack(Blocks.sapling, 1, 2),
                new AspectList().add(Aspect.TAINT, 1));
    }

    public static void arcanaSaplings() {
        // 32767: Wildcard Value
        dair = ThaumcraftApi.addCrucibleRecipe(
                "AR_ArcanaSapling",
                new ItemStack(ArcanaBlocks.blockSaplings, 1, 0),
                new ItemStack(Blocks.sapling, 1, 32767),
                new AspectList().add(Aspect.AIR, 1));
        euc = ThaumcraftApi.addCrucibleRecipe(
                "AR_ArcanaSapling",
                new ItemStack(ArcanaBlocks.blockSaplings, 1, 1),
                new ItemStack(Blocks.sapling, 1, 32767),
                new AspectList().add(Aspect.HEAL, 1));
        hawthorn = ThaumcraftApi.addCrucibleRecipe(
                "AR_ArcanaSapling",
                new ItemStack(ArcanaBlocks.blockSaplings, 1, 2),
                new ItemStack(Blocks.sapling, 1, 32767),
                new AspectList().add(Aspect.ORDER, 1));
        willow = ThaumcraftApi.addCrucibleRecipe(
                "AR_ArcanaSapling",
                new ItemStack(ArcanaBlocks.blockSaplings, 1, 3),
                new ItemStack(Blocks.sapling, 1, 32767),
                new AspectList().add(Aspect.WATER, 1));
        arcanus = ThaumcraftApi.addCrucibleRecipe(
                "AR_ArcanaSapling",
                new ItemStack(ArcanaBlocks.blockSaplings, 1, 4),
                new ItemStack(ConfigBlocks.blockCustomPlant, 1, 0),
                new AspectList().add(Aspect.EXCHANGE, 1));
        shimmer = ThaumcraftApi.addCrucibleRecipe(
                "AR_ArcanaSapling",
                new ItemStack(ArcanaBlocks.blockSaplings, 1, 5),
                new ItemStack(ConfigBlocks.blockCustomPlant, 1, 1),
                new AspectList().add(Aspect.EXCHANGE, 1));
    }

    public static void cursedSaplings() {
        trypo = ThaumcraftApi.addCrucibleRecipe(
                "AR_CursedSapling",
                new ItemStack(ArcanaBlocks.blockCursedArcanaSaplings, 1, 0),
                new ItemStack(Blocks.sapling, 1, 32767),
                new AspectList().add(Aspect.VOID, 1));
        oblivion = ThaumcraftApi.addCrucibleRecipe(
                "AR_CursedSapling",
                new ItemStack(ArcanaBlocks.blockCursedArcanaSaplings, 1, 1),
                new ItemStack(Blocks.sapling, 1, 32767),
                new AspectList().add(Aspect.ELDRITCH, 1));
        old_oblivion = ThaumcraftApi.addCrucibleRecipe(
                "AR_CursedSapling",
                new ItemStack(ArcanaBlocks.blockCursedArcanaSaplings, 1, 2),
                new ItemStack(ArcanaBlocks.blockCursedArcanaSaplings, 1, 1),
                new AspectList().add(Aspect.HEAL, 1));
        tarnished = ThaumcraftApi.addCrucibleRecipe(
                "AR_CursedSapling",
                new ItemStack(ArcanaBlocks.blockCursedArcanaSaplings, 1, 3),
                new ItemStack(ArcanaBlocks.blockSaplings, 1, 5),
                new AspectList().add(Aspect.ENTROPY, 1));
        numnum = ThaumcraftApi.addCrucibleRecipe(
                "AR_CursedSapling",
                new ItemStack(ArcanaBlocks.blockCursedArcanaSaplings, 1, 4),
                new ItemStack(Blocks.sapling, 1, 32767),
                new AspectList().add(Aspect.HUNGER, 1));
    }

    public static void planks() {
        dairPlank = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "AR_Base",
                new ItemStack(ArcanaBlocks.blockArcanaWoodDevice, 4, 0),
                new AspectList().add(Aspect.ENTROPY, 2),
                new ItemStack(ArcanaBlocks.blockArcanaLogDeviceTwo, 1, 0));
        dead = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "AR_Base",
                new ItemStack(ArcanaBlocks.blockArcanaWoodDevice, 4, 1),
                new AspectList().add(Aspect.ENTROPY, 2),
                new ItemStack(ArcanaBlocks.blockArcanaLogDeviceTwo, 1, 1));
        greatwood = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "AR_Base",
                new ItemStack(ArcanaBlocks.blockArcanaWoodDevice, 4, 2),
                new AspectList().add(Aspect.ENTROPY, 2),
                new ItemStack(ArcanaBlocks.blockArcanaLogDeviceTwo, 1, 2));
        silverwood = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "AR_Base",
                new ItemStack(ArcanaBlocks.blockArcanaWoodDevice, 4, 3),
                new AspectList().add(Aspect.ENTROPY, 2),
                new ItemStack(ArcanaBlocks.blockArcanaLogDeviceTwo, 1, 3));
        polished = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "AR_Base",
                new ItemStack(ArcanaBlocks.blockArcanaWoodDevice, 1, 4),
                new AspectList().add(Aspect.ORDER, 1),
                new ItemStack(ArcanaBlocks.blockArcanaWoodDevice, 1, 3));
        dead_silverwood = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "AR_Base",
                new ItemStack(ArcanaBlocks.blockArcanaWoodDevice, 4, 5),
                new AspectList().add(Aspect.ENTROPY, 2),
                new ItemStack(ArcanaBlocks.blockArcanaLogDeviceThree, 1, 0));
        trypophobius = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "AR_Base",
                new ItemStack(ArcanaBlocks.blockArcanaWoodDevice, 4, 6),
                new AspectList().add(Aspect.ENTROPY, 2),
                new ItemStack(ArcanaBlocks.blockArcanaLogDeviceOne, 1, 0));
        eucalyptus = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "AR_Base",
                new ItemStack(ArcanaBlocks.blockArcanaWoodDevice, 4, 7),
                new AspectList().add(Aspect.ENTROPY, 2),
                new ItemStack(ArcanaBlocks.blockArcanaLogDeviceOne, 1, 1));
        hawthornPlank = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "AR_Base",
                new ItemStack(ArcanaBlocks.blockArcanaWoodDevice, 4, 8),
                new AspectList().add(Aspect.ENTROPY, 2),
                new ItemStack(ArcanaBlocks.blockArcanaLogDeviceOne, 1, 2));
        willowPlank = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "AR_Base",
                new ItemStack(ArcanaBlocks.blockArcanaWoodDevice, 4, 9),
                new AspectList().add(Aspect.ENTROPY, 2),
                new ItemStack(ArcanaBlocks.blockArcanaLogDeviceOne, 1, 3));
        numnumPlank = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "AR_Base",
                new ItemStack(ArcanaBlocks.blockArcanaWoodDevice, 4, 10),
                new AspectList().add(Aspect.ENTROPY, 2),
                new ItemStack(ArcanaBlocks.blockArcanaLogDeviceThree, 1, 1));
    }

    public static void taintedPlanks() {
        tainted_oak = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "AR_Base",
                new ItemStack(ArcanaBlocks.blockArcanaTaintedWoodDevice, 4, 0),
                new AspectList().add(Aspect.ENTROPY, 2),
                new ItemStack(ArcanaBlocks.blockArcanaTaintedLogDeviceOne, 1, 0));
        tainted_spruce = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "AR_Base",
                new ItemStack(ArcanaBlocks.blockArcanaTaintedWoodDevice, 4, 1),
                new AspectList().add(Aspect.ENTROPY, 2),
                new ItemStack(ArcanaBlocks.blockArcanaTaintedLogDeviceOne, 1, 1));
        tainted_birch = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "AR_Base",
                new ItemStack(ArcanaBlocks.blockArcanaTaintedWoodDevice, 4, 2),
                new AspectList().add(Aspect.ENTROPY, 2),
                new ItemStack(ArcanaBlocks.blockArcanaTaintedLogDeviceOne, 1, 2));
        tainted_jungle = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "AR_Base",
                new ItemStack(ArcanaBlocks.blockArcanaTaintedWoodDevice, 4, 3),
                new AspectList().add(Aspect.ENTROPY, 2),
                new ItemStack(ArcanaBlocks.blockArcanaTaintedLogDeviceOne, 1, 3));
        tainted_acacia = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "AR_Base",
                new ItemStack(ArcanaBlocks.blockArcanaTaintedWoodDevice, 4, 5),
                new AspectList().add(Aspect.ENTROPY, 2),
                new ItemStack(ArcanaBlocks.blockArcanaTaintedLogDeviceTwo, 1, 0));
        tainted_darkoak = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "AR_Base",
                new ItemStack(ArcanaBlocks.blockArcanaTaintedWoodDevice, 4, 6),
                new AspectList().add(Aspect.ENTROPY, 2),
                new ItemStack(ArcanaBlocks.blockArcanaTaintedLogDeviceTwo, 1, 1));
        tainted_dair = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "AR_Base",
                new ItemStack(ArcanaBlocks.blockArcanaTaintedWoodDevice, 4, 7),
                new AspectList().add(Aspect.ENTROPY, 2),
                new ItemStack(ArcanaBlocks.blockArcanaTaintedLogDeviceTwo, 1, 2));
        tainted_greatwood = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "AR_Base",
                new ItemStack(ArcanaBlocks.blockArcanaTaintedWoodDevice, 4, 8),
                new AspectList().add(Aspect.ENTROPY, 2),
                new ItemStack(ArcanaBlocks.blockArcanaTaintedLogDeviceThree, 1, 0));
        tainted_eucalyptus = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "AR_Base",
                new ItemStack(ArcanaBlocks.blockArcanaTaintedWoodDevice, 4, 9),
                new AspectList().add(Aspect.ENTROPY, 2),
                new ItemStack(ArcanaBlocks.blockArcanaTaintedLogDeviceThree, 1, 2));
        tainted_hawthorn = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "AR_Base",
                new ItemStack(ArcanaBlocks.blockArcanaTaintedWoodDevice, 4, 10),
                new AspectList().add(Aspect.ENTROPY, 2),
                new ItemStack(ArcanaBlocks.blockArcanaTaintedLogDeviceThree, 1, 3));
        tainted_willow = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "AR_Base",
                new ItemStack(ArcanaBlocks.blockArcanaTaintedWoodDevice, 4, 11),
                new AspectList().add(Aspect.ENTROPY, 2),
                new ItemStack(ArcanaBlocks.blockArcanaTaintedLogDeviceThree, 1, 1));

    }
}

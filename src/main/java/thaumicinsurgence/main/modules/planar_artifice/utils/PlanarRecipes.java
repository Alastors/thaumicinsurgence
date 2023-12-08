package thaumicinsurgence.main.modules.planar_artifice.utils;

import net.minecraft.item.ItemStack;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraft.common.config.ConfigItems;
import thaumicinsurgence.main.modules.planar_artifice.core.PlanarAspects;
import thaumicinsurgence.main.utils.compat.ThaumcraftHelper;

public class PlanarRecipes {

    public static ShapedArcaneRecipe alkimiumGoggles;
    public static ShapedArcaneRecipe alkimiumFurnace;
    public static ShapedArcaneRecipe alkimiumBlock;
    public static ShapedArcaneRecipe alkimiumConstruct;
    public static ShapedArcaneRecipe alkimiumPlates;
    public static ShapelessArcaneRecipe alkimiumNuggets;
    public static ShapedArcaneRecipe nuggetsToIngots;
    public static ShapelessArcaneRecipe blocksToIngots;
    public static CrucibleRecipe alkimium;
    public static CrucibleRecipe alkimiumScribe;

    public static void planarCrafting() {
        alkimiumGoggles = ThaumcraftApi.addArcaneCraftingRecipe(
                "PA_Alkimium",
                new ItemStack(PlanarItems.goggles),
                new AspectList().add(Aspect.ORDER, 35),
                "A A",
                " G ",
                "A A",
                Character.valueOf('A'),
                new ItemStack(PlanarItems.alkimium, 1, 2),
                Character.valueOf('G'),
                new ItemStack(ConfigItems.itemGoggles));

        alkimiumPlates = ThaumcraftApi.addArcaneCraftingRecipe(
                "PA_Alkimium",
                new ItemStack(PlanarItems.alkimium, 1, 2),
                new AspectList().add(Aspect.ORDER, 4),
                " I ",
                " I ",
                "   ",
                Character.valueOf('I'),
                new ItemStack(PlanarItems.alkimium, 1, 0));

        alkimiumNuggets = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "PA_Alkimium",
                new ItemStack(PlanarItems.alkimium, 9, 1),
                new AspectList().add(Aspect.ENTROPY, 2),
                new ItemStack(PlanarItems.alkimium, 1, 0));

        alkimiumBlock = ThaumcraftApi.addArcaneCraftingRecipe(
                "PA_Alkimium",
                new ItemStack(PlanarBlocks.blockPlanarDevice, 1, 1),
                new AspectList(),
                "III",
                "III",
                "III",
                Character.valueOf('I'),
                new ItemStack(PlanarItems.alkimium, 1, 0));

        nuggetsToIngots = ThaumcraftApi.addArcaneCraftingRecipe(
                "PA_Alkimium",
                new ItemStack(PlanarItems.alkimium, 1, 0),
                new AspectList(),
                "III",
                "III",
                "III",
                Character.valueOf('I'),
                new ItemStack(PlanarItems.alkimium, 1, 1));

        blocksToIngots = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                "PA_Alkimium",
                new ItemStack(PlanarItems.alkimium, 9, 0),
                new AspectList().add(Aspect.ENTROPY, 10),
                new ItemStack(PlanarBlocks.blockPlanarDevice, 1, 1));

        alkimiumConstruct = ThaumcraftApi.addArcaneCraftingRecipe(
                "PA_Alkimium",
                new ItemStack(PlanarBlocks.blockPlanarDevice, 1, 0),
                new AspectList(),
                "PPP",
                "BCB",
                "PPP",
                Character.valueOf('P'),
                new ItemStack(PlanarItems.alkimium, 1, 2),
                Character.valueOf('B'),
                new ItemStack(PlanarBlocks.blockPlanarDevice, 1, 1),
                Character.valueOf('C'),
                new ItemStack(
                        ThaumcraftHelper.metal,
                        1,
                        ThaumcraftHelper.MetalDeviceType.ALCHEMICAL_CONSTRUCT.ordinal()));

        alkimiumFurnace = ThaumcraftApi.addArcaneCraftingRecipe(
                "PA_Alkimium",
                new ItemStack(PlanarBlocks.alkimiumAlchFurnace),
                new AspectList().add(Aspect.ORDER, 75).add(Aspect.ENTROPY, 75).add(Aspect.WATER, 75)
                        .add(Aspect.FIRE, 75).add(Aspect.EARTH, 75).add(Aspect.AIR, 75),
                "PAP",
                "PCP",
                "IBI",
                Character.valueOf('C'),
                new ItemStack(PlanarBlocks.blockPlanarDevice, 1, 0),
                Character.valueOf('B'),
                new ItemStack(PlanarBlocks.blockPlanarDevice, 1, 1),
                Character.valueOf('P'),
                new ItemStack(PlanarItems.alkimium, 1, 2),
                Character.valueOf('I'),
                new ItemStack(PlanarItems.alkimium, 1, 0),
                Character.valueOf('A'),
                new ItemStack(ThaumcraftHelper.metal, 1, ThaumcraftHelper.MetalDeviceType.ALEMBIC.ordinal()));
    }

    public static void planarAlchemy() {
        alkimium = ThaumcraftApi.addCrucibleRecipe(
                "PA_Alkimium",
                new ItemStack(PlanarItems.alkimium, 1, 0),
                new ItemStack(ThaumcraftHelper.miscResource, 1, ThaumcraftHelper.MiscResource.THAUMIUM.ordinal()),
                new AspectList().add(PlanarAspects.ALCHEMY, 16));

        alkimiumScribe = ThaumcraftApi.addCrucibleRecipe(
                "Pa_Alkimium",
                new ItemStack(PlanarItems.scribe),
                new ItemStack(ConfigItems.itemInkwell),
                new AspectList().add(PlanarAspects.ALCHEMY, 100));
    }

}

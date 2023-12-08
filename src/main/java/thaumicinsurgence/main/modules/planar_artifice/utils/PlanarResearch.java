package thaumicinsurgence.main.modules.planar_artifice.utils;

import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumicinsurgence.main.modules.planar_artifice.core.PlanarAspects;

public class PlanarResearch {

    public static void planarResearch() {
        String category = "THAUMICINSURGENCE";
        ResearchItem alkimiumPage, gogglesPage, furnacePage, scribePage;
        ResearchPage alkimium1, alkimium2, alkimium3, alkimium4, alkimium5, alkimium6, alkimium7, alkimium8;
        ResearchPage goggles1, goggles2;
        ResearchPage furnace1, furnace2;
        ResearchPage scribe1, scribe2;

        alkimiumPage = new ResearchItem(
                "PA_Alkimium",
                category,
                new AspectList().add(Aspect.MAGIC, 25).add(PlanarAspects.ALCHEMY, 25),
                2,
                -2,
                0,
                new ItemStack(PlanarItems.logo));

        alkimium1 = new ResearchPage("Alkimium.1");
        alkimium2 = new ResearchPage(PlanarRecipes.alkimium);
        alkimium3 = new ResearchPage(PlanarRecipes.alkimiumNuggets);
        alkimium4 = new ResearchPage(PlanarRecipes.alkimiumPlates);
        alkimium5 = new ResearchPage(PlanarRecipes.alkimiumBlock);
        alkimium6 = new ResearchPage(PlanarRecipes.alkimiumConstruct);
        alkimium7 = new ResearchPage(PlanarRecipes.nuggetsToIngots);
        alkimium8 = new ResearchPage(PlanarRecipes.blocksToIngots);

        alkimiumPage.setPages(alkimium1, alkimium2, alkimium3, alkimium4, alkimium5, alkimium6, alkimium7, alkimium8);
        alkimiumPage.setParents("DISTILESSENTIA");
        ResearchCategories.addResearch(alkimiumPage);

        gogglesPage = new ResearchItem(
                "PA_Goggles",
                category,
                new AspectList().add(Aspect.MAGIC, 25).add(PlanarAspects.ALCHEMY, 25).add(Aspect.TOOL, 25)
                        .add(Aspect.ARMOR, 25),
                4,
                -2,
                0,
                new ItemStack(PlanarItems.goggles));

        goggles1 = new ResearchPage("AlkimiumGoggles.1");
        goggles2 = new ResearchPage(PlanarRecipes.alkimiumGoggles);

        gogglesPage.setPages(goggles1, goggles2);
        gogglesPage.setParents("PA_Alkimium");
        ResearchCategories.addResearch(gogglesPage);

        furnacePage = new ResearchItem(
                "PA_Furnace",
                category,
                new AspectList().add(Aspect.MAGIC, 25).add(PlanarAspects.ALCHEMY, 25).add(Aspect.MOTION, 10),
                4,
                -4,
                0,
                new ItemStack(PlanarBlocks.alkimiumAlchFurnace));
        furnace1 = new ResearchPage("AlkimiumFurnace.1");
        furnace2 = new ResearchPage(PlanarRecipes.alkimiumFurnace);

        furnacePage.setPages(furnace1, furnace2);
        furnacePage.setParents("PA_Alkimium");
        ResearchCategories.addResearch(furnacePage);

        scribePage = new ResearchItem(
                "PA_Scribe",
                category,
                new AspectList().add(Aspect.MAGIC, 25).add(PlanarAspects.ALCHEMY, 25).add(Aspect.TOOL, 25)
                        .add(Aspect.ENERGY, 10),
                2,
                -4,
                0,
                new ItemStack(PlanarItems.scribe));
        scribe1 = new ResearchPage("AlkimiumScribe.1");
        scribe2 = new ResearchPage(PlanarRecipes.alkimiumScribe);

        scribePage.setPages(scribe1, scribe2);
        scribePage.setParents("PA_Alkimium", "WANDPED");
        ResearchCategories.addResearch(scribePage);
    }
}

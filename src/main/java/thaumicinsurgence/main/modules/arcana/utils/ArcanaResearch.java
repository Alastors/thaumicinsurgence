package thaumicinsurgence.main.modules.arcana.utils;

import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class ArcanaResearch {

    public static void arcanaResearch() {
        String category = "THAUMICINSURGENCE";
        ResearchItem arcanaPage, taintedSapling, taintedVanilla, arcanaSapling, cursedSapling;
        ResearchPage arcana1, arcana2;
        ResearchPage tainted1, tainted2, tainted3, tainted4, tainted5, tainted6, vanilla1, vanilla2, vanilla3, vanilla4,
                vanilla5, vanilla6, vanilla7, aSapling1, aSapling2, aSapling3, aSapling4, aSapling5, aSapling6,
                aSapling7, cursed1, cursed2, cursed3, cursed4, cursed5, cursed6;

        arcanaPage = new ResearchItem(
                "AR_Base",
                category,
                new AspectList().add(Aspect.MAGIC, 25).add(Aspect.TAINT, 25),
                -2,
                -2,
                0,
                new ItemStack(ArcanaItems.arcanaLogo));

        arcana1 = new ResearchPage("Arcana.1");
        arcana2 = new ResearchPage(ArcanaRecipes.arcaniumGoggles);

        arcanaPage.setPages(arcana1, arcana2);
        arcanaPage.setParents("CRUCIBLE");
        ResearchCategories.addResearch(arcanaPage);

        taintedSapling = new ResearchItem(
                "AR_TaintedSapling",
                category,
                new AspectList().add(Aspect.MAGIC, 25).add(Aspect.TAINT, 25).add(Aspect.TREE, 25),
                -2,
                -4,
                0,
                new ItemStack(ArcanaBlocks.blockTaintedArcanaSaplings, 1, 0));

        tainted1 = new ResearchPage("Tainted.1");
        tainted2 = new ResearchPage(ArcanaRecipes.tDair);
        tainted3 = new ResearchPage(ArcanaRecipes.tGW);
        tainted4 = new ResearchPage(ArcanaRecipes.tWillow);
        tainted5 = new ResearchPage(ArcanaRecipes.tEuc);
        tainted6 = new ResearchPage(ArcanaRecipes.tHawthorn);
        taintedSapling.setPages(tainted1, tainted2, tainted3, tainted4, tainted5, tainted6);
        taintedSapling.setParents("AR_Base");
        ResearchCategories.addResearch(taintedSapling);

        taintedVanilla = new ResearchItem(
                "AR_TaintedVanilla",
                category,
                new AspectList().add(Aspect.MAGIC, 25).add(Aspect.TAINT, 25).add(Aspect.TREE, 25),
                -3,
                -4,
                0,
                new ItemStack(ArcanaBlocks.blockTaintedVanillaSaplings, 1, 4));

        vanilla1 = new ResearchPage("Vanilla.1");
        vanilla2 = new ResearchPage(ArcanaRecipes.tOak);
        vanilla3 = new ResearchPage(ArcanaRecipes.tSpruce);
        vanilla4 = new ResearchPage(ArcanaRecipes.tBirch);
        vanilla5 = new ResearchPage(ArcanaRecipes.tJungle);
        vanilla6 = new ResearchPage(ArcanaRecipes.tAcacia);
        vanilla7 = new ResearchPage(ArcanaRecipes.tDOak);
        taintedVanilla.setPages(vanilla1, vanilla2, vanilla3, vanilla4, vanilla5, vanilla6, vanilla7);
        taintedVanilla.setParents("AR_Base");
        ResearchCategories.addResearch(taintedVanilla);

        arcanaSapling = new ResearchItem(
                "AR_ArcanaSapling",
                category,
                new AspectList().add(Aspect.MAGIC, 25).add(Aspect.TAINT, 25).add(Aspect.TREE, 25),
                -4,
                -2,
                0,
                new ItemStack(ArcanaBlocks.blockSaplings, 1, 0));

        aSapling1 = new ResearchPage("ASapling.1");
        aSapling2 = new ResearchPage(ArcanaRecipes.dair);
        aSapling3 = new ResearchPage(ArcanaRecipes.euc);
        aSapling4 = new ResearchPage(ArcanaRecipes.hawthorn);
        aSapling5 = new ResearchPage(ArcanaRecipes.willow);
        aSapling6 = new ResearchPage(ArcanaRecipes.arcanus);
        aSapling7 = new ResearchPage(ArcanaRecipes.shimmer);

        arcanaSapling.setPages(aSapling1, aSapling2, aSapling3, aSapling4, aSapling5, aSapling6, aSapling7);
        arcanaSapling.setParents("AR_Base");
        ResearchCategories.addResearch(arcanaSapling);

        cursedSapling = new ResearchItem(
                "AR_CursedSapling",
                category,
                new AspectList().add(Aspect.MAGIC, 25).add(Aspect.TAINT, 25).add(Aspect.TREE, 25),
                -4,
                -3,
                0,
                new ItemStack(ArcanaBlocks.blockCursedArcanaSaplings, 1, 0));

        cursed1 = new ResearchPage("Cursed.1");
        cursed2 = new ResearchPage(ArcanaRecipes.trypo);
        cursed3 = new ResearchPage(ArcanaRecipes.oblivion);
        cursed4 = new ResearchPage(ArcanaRecipes.old_oblivion);
        cursed5 = new ResearchPage(ArcanaRecipes.tarnished);
        cursed6 = new ResearchPage(ArcanaRecipes.numnum);

        cursedSapling.setPages(cursed1, cursed2, cursed3, cursed4, cursed5, cursed6);
        cursedSapling.setParents("AR_Base");
        ResearchCategories.addResearch(cursedSapling);
    }
}

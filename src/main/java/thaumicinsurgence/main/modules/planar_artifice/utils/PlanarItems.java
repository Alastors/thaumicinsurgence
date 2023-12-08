package thaumicinsurgence.main.modules.planar_artifice.utils;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.registry.GameRegistry;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumicinsurgence.main.modules.planar_artifice.PlanarArtifice;
import thaumicinsurgence.main.modules.planar_artifice.core.PlanarAspects;
import thaumicinsurgence.main.modules.planar_artifice.core.items.ItemAlkimium;
import thaumicinsurgence.main.modules.planar_artifice.core.items.ItemAlkimiumScribe;
import thaumicinsurgence.main.modules.planar_artifice.core.items.ItemLogo;
import thaumicinsurgence.main.modules.planar_artifice.core.items.armour.ItemAlkimiumGoggles;

// ConfigResearch.recipes.put("BalancedShard_" + a, ThaumcraftApi.addCrucibleRecipe("CRUCIBLE", new
// ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(ConfigItems.itemShard, 1, a), al));
public class PlanarItems {

    public static final Class<PlanarArtifice> core = PlanarArtifice.class;
    public static Item alkimium = new ItemAlkimium();
    public static Item logo = new ItemLogo();
    public static Item goggles = new ItemAlkimiumGoggles(ThaumcraftApi.armorMatSpecial, 4, 0);
    public static Item scribe = new ItemAlkimiumScribe();

    public static void setup() {
        GameRegistry.registerItem(alkimium, "Alkimium");

        GameRegistry.registerItem(logo, "logo");

        GameRegistry.registerItem(goggles, goggles.getUnlocalizedName());

        GameRegistry.registerItem(scribe, scribe.getUnlocalizedName());
    }

    public static void setupItemAspects() {
        ItemStack item;
        AspectList list;

        list = new AspectList(new ItemStack(Items.leather_boots)).add(PlanarAspects.ALCHEMY, 2);
        item = new ItemStack(Items.nether_wart);
        ThaumcraftApi.registerObjectTag(item, list);

        list = new AspectList(new ItemStack(PlanarItems.alkimium, 1, 0)).add(PlanarAspects.ALCHEMY, 4)
                .add(Aspect.METAL, 2);
        item = new ItemStack(PlanarItems.alkimium);
        ThaumcraftApi.registerObjectTag(item, list);

        list = new AspectList(new ItemStack(PlanarItems.alkimium, 1, 2)).add(PlanarAspects.ALCHEMY, 4)
                .add(Aspect.METAL, 2);
        item = new ItemStack(PlanarItems.alkimium);
        ThaumcraftApi.registerObjectTag(item, list);

        list = new AspectList(new ItemStack(PlanarItems.alkimium, 1, 1)).add(Aspect.METAL, 1);
        item = new ItemStack(PlanarItems.alkimium);
        ThaumcraftApi.registerObjectTag(item, list);

        list = new AspectList(new ItemStack(PlanarItems.goggles)).add(PlanarAspects.ALCHEMY, 8).add(Aspect.METAL, 4);
        item = new ItemStack(PlanarItems.goggles);
        ThaumcraftApi.registerObjectTag(item, list);

        list = new AspectList(new ItemStack(PlanarItems.scribe)).add(PlanarAspects.ALCHEMY, 4);
        item = new ItemStack(PlanarItems.scribe);
        ThaumcraftApi.registerObjectTag(item, list);
    }

}

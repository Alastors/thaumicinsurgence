package thaumicinsurgence.main.modules.planar_artifice.utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabPlanarArtifice extends CreativeTabs {

    public static TabPlanarArtifice tabPlanarArtifice = new TabPlanarArtifice();

    public TabPlanarArtifice() {
        super(getNextID(), "Planar Artifice");
    }

    public Item getTabIconItem() {
        return PlanarItems.logo;
    }
}

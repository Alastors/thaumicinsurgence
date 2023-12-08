package thaumicinsurgence.main.modules.arcana.utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabArcana extends CreativeTabs {

    public static TabArcana tabArcana = new TabArcana();

    public TabArcana() {
        super(getNextID(), "Arcana");
    }

    public Item getTabIconItem() {
        return ArcanaItems.arcanaLogo;
    }
}

package thaumicinsurgence.main.modules.arcana.core.blocks.leaves.tainted;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import thaumicinsurgence.main.modules.arcana.core.blocks.leaves.ItemLeafBase;

public class ItemBlockArcanaTaintedLeafDevice extends ItemLeafBase {

    public ItemBlockArcanaTaintedLeafDevice(Block block) {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        // love the creative naming schemes mojang, thx XOXO
        for (int i = 0; i < BlockArcanaTaintedLeafDevice.blocks.length; i++) {
            list.add(new ItemStack(this, 1, i));
        }
    }
}

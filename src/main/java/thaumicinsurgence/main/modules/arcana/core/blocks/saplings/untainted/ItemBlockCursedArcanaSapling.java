package thaumicinsurgence.main.modules.arcana.core.blocks.saplings.untainted;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import thaumicinsurgence.main.modules.arcana.core.blocks.saplings.ItemSaplingBase;

public class ItemBlockCursedArcanaSapling extends ItemSaplingBase {

    public ItemBlockCursedArcanaSapling(Block block) {
        super(block);
        setHasSubtypes(true);

    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        // love the creative naming schemes mojang, thx XOXO
        for (int i = 0; i < BlockCursedArcanaSapling.saplings.length; i++) {
            list.add(new ItemStack(this, 1, i));
        }
    }
}

package thaumicinsurgence.main.modules.arcana.core.blocks.crops;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockArcanaTaintedSugarCane extends ItemBlock {

    public ItemBlockArcanaTaintedSugarCane(Block block) {
        super(block);
    }

    @Override
    public int getMetadata(int damageVal) {
        return damageVal;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        // love the creative naming schemes mojang, thx XOXO
        list.add(new ItemStack(this, 1, 0));
    }

    @Override
    public String getUnlocalizedName(ItemStack item) {
        return getUnlocalizedName() + "." + item.getItemDamage();
    }
}

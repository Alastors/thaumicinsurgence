package thaumicinsurgence.main.modules.arcana.core.blocks.wood.logs.tainted;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockArcanaTaintedLogDeviceThree extends ItemBlock {

    public ItemBlockArcanaTaintedLogDeviceThree(Block block) {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damageVal) {
        return damageVal;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        // love the creative naming schemes mojang, thx XOXO
        for (int i = 0; i < BlockArcanaTaintedLogDeviceThree.blocks.length; i++) {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack item) {
        return getUnlocalizedName() + "." + item.getItemDamage();
    }
}

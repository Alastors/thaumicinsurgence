package thaumicinsurgence.main.modules.arcana.core.blocks.leaves;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemLeafBase extends ItemBlock {

    public ItemLeafBase(Block block) {
        super(block);
    }

    @Override
    public int getMetadata(int damageVal) {
        return damageVal;
    }

    @Override
    public String getUnlocalizedName(ItemStack item) {
        return getUnlocalizedName() + "." + item.getItemDamage();
    }
}

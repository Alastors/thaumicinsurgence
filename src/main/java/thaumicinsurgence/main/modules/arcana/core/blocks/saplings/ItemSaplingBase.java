package thaumicinsurgence.main.modules.arcana.core.blocks.saplings;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSaplingBase extends ItemBlock {

    public ItemSaplingBase(Block block) {
        super(block);
    }

    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack item) {
        return getUnlocalizedName() + "." + item.getItemDamage();
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return this.field_150939_a.getIcon(2, meta);
    }
}

package thaumicinsurgence.main.modules.arcana.core.blocks.saplings.untainted;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockArcanaSapling extends ItemBlock {

    public ItemBlockArcanaSapling(Block block) {
        super(block);
        setHasSubtypes(true);

    }

    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        // love the creative naming schemes mojang, thx XOXO
        for (int i = 0; i < BlockArcanaSapling.saplings.length; i++) {
            list.add(new ItemStack(this, 1, i));
        }
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

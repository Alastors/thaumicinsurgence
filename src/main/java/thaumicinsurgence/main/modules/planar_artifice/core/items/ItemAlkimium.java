package thaumicinsurgence.main.modules.planar_artifice.core.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumicinsurgence.main.modules.planar_artifice.utils.TabPlanarArtifice;

public class ItemAlkimium extends Item {

    public IIcon ingot;
    public IIcon nugget;
    public IIcon plate;

    public ItemAlkimium() {
        this.setMaxStackSize(64);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(TabPlanarArtifice.tabPlanarArtifice);
        this.setUnlocalizedName("alkimium");
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.ingot = ir.registerIcon("planarartifice:alkimium/alkimium_ingot");
        this.nugget = ir.registerIcon("planarartifice:alkimium/alkimium_nugget");
        this.plate = ir.registerIcon("planarartifice:alkimium/alkimium_plate");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        switch (damage) {
            case 0:
                return ingot;
            case 1:
                return nugget;
            case 2:
                return plate;
            default:
                return null;
        }
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int a = 0; a <= 2; ++a) {
            par3List.add(new ItemStack(this, 1, a));
        }
    }

    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return getUnlocalizedName() + "." + par1ItemStack.getItemDamage();
    }
}

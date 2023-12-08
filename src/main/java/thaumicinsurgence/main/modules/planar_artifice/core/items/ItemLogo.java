package thaumicinsurgence.main.modules.planar_artifice.core.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemLogo extends Item {

    public IIcon logo;

    public ItemLogo() {
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
        this.setUnlocalizedName("logo");
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.logo = ir.registerIcon("planarartifice:alkimium/logo_icon");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        return logo;
    }
}

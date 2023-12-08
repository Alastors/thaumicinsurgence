package thaumicinsurgence.main.modules.arcana.core.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemArcanaLogo extends Item {

    public IIcon logo;

    public ItemArcanaLogo() {
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
        this.setUnlocalizedName("arcanaLogo");
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.logo = ir.registerIcon("arcana:tainted_codex");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        return logo;
    }
}

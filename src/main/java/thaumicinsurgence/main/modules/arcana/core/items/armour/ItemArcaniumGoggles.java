package thaumicinsurgence.main.modules.arcana.core.items.armour;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.IGoggles;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.nodes.IRevealer;
import thaumcraft.common.items.armor.ItemGoggles;
import thaumicinsurgence.main.modules.arcana.utils.TabArcana;

public class ItemArcaniumGoggles extends ItemGoggles
        implements IRepairable, IVisDiscountGear, IRevealer, IGoggles, IRunicArmor {

    public IIcon icon;

    // At some point in the future, I might actually make these have unique effects.
    public ItemArcaniumGoggles(final ArmorMaterial material, final int j, final int k) {
        super(material, j, k);
        setCreativeTab(TabArcana.tabArcana);
        setUnlocalizedName("arcanium_goggles");
        setMaxDamage(3575);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister ir) {
        icon = ir.registerIcon("arcana:arcanium_goggles");
    }

    @Override
    public EnumRarity getRarity(ItemStack itemstack) {
        return EnumRarity.epic;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        return this.icon;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "arcana:models/research_goggles.png";
    }

    @Override
    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
        return 5;
    }
}

package thaumicinsurgence.item.armor;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.IGoggles;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.nodes.IRevealer;
import thaumcraft.common.items.armor.ItemGoggles;
import thaumicinsurgence.api.IPurifyingGear;
import thaumicinsurgence.main.utils.TabThaumicInsurgence;

public class ItemBIGSHOT2 extends ItemGoggles
        implements IRepairable, IVisDiscountGear, IRevealer, IGoggles, IRunicArmor, IPurifyingGear {

    public IIcon icon;

    public ItemBIGSHOT2(final ArmorMaterial material, final int j, final int k) {
        super(material, j, k);
        setCreativeTab(TabThaumicInsurgence.tabThaumicInsurgence);
        setUnlocalizedName("ItemBIGSHOT2");
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister ir) {
        icon = ir.registerIcon("thaumicinsurgence:[[big_shot]]");
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "thaumicinsurgence:model/[[JACKPOT]].png";
    }

    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
        return 15;
    }

    @Override
    public int getPurity(ItemStack var1, EntityPlayer var2) {
        return 10;
    }

    public void onUpdate(final ItemStack stack, final World world, final Entity entity, final int i, final boolean b) {
        super.onUpdate(stack, world, entity, i, b);
        if (!world.isRemote && stack.isItemDamaged()
                && entity.ticksExisted % 20 == 0
                && entity instanceof EntityLivingBase) {
            stack.damageItem(-1, (EntityLivingBase) entity);
        }
    }

    @Override
    public void onArmorTick(final World world, final EntityPlayer player, final ItemStack stack) {
        super.onArmorTick(world, player, stack);
        if (!world.isRemote && stack.isItemDamaged() && player.ticksExisted % 20 == 0) {
            stack.damageItem(-1, player);
        }
    }
}

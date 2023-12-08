package thaumicinsurgence.main.modules.planar_artifice.core.items;

import java.text.DecimalFormat;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import baubles.api.BaubleType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.IScribeTools;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.items.baubles.ItemAmuletVis;
import thaumicinsurgence.main.modules.planar_artifice.PlanarArtifice;
import thaumicinsurgence.main.modules.planar_artifice.utils.TabPlanarArtifice;

public class ItemAlkimiumScribe extends ItemAmuletVis implements IScribeTools {

    public IIcon scribe;
    public static int charge = 250;
    DecimalFormat decimalFormatter = new DecimalFormat("#######.##");

    public ItemAlkimiumScribe() {
        setHasSubtypes(false);
        setMaxDamage(250);
        setUnlocalizedName("alkimium_scribe");
        setCreativeTab(TabPlanarArtifice.tabPlanarArtifice);
        setNoRepair();
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return getUnlocalizedName();
    }

    // Could've used Math.min, but it would've just been a bunch of gross nested methods.
    public int returnLowestVis(ItemStack stack) {
        int lowest = 25000;
        if (lowest > getVis(stack, Aspect.FIRE)) lowest = getVis(stack, Aspect.FIRE);
        if (lowest > getVis(stack, Aspect.AIR)) lowest = getVis(stack, Aspect.AIR);
        if (lowest > getVis(stack, Aspect.WATER)) lowest = getVis(stack, Aspect.WATER);
        if (lowest > getVis(stack, Aspect.EARTH)) lowest = getVis(stack, Aspect.EARTH);
        if (lowest > getVis(stack, Aspect.ORDER)) lowest = getVis(stack, Aspect.ORDER);
        if (lowest > getVis(stack, Aspect.ENTROPY)) lowest = getVis(stack, Aspect.ENTROPY);
        return lowest / 100;
    }

    @Override
    public int getDamage(ItemStack stack) {
        return charge - returnLowestVis(stack);
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        int currentDamage = stack.getItemDamage();
        if (damage > currentDamage) {
            if (returnLowestVis(stack) >= 1) {
                // While you might be worried that this wouldn't work,
                // I assure you, this ensures you can't use the scribe
                // if even one aspect is less than 1.
                this.addVis(stack, Aspect.FIRE, -1, true);
                this.addVis(stack, Aspect.AIR, -1, true);
                this.addVis(stack, Aspect.WATER, -1, true);
                this.addVis(stack, Aspect.EARTH, -1, true);
                this.addVis(stack, Aspect.ORDER, -1, true);
                this.addVis(stack, Aspect.ENTROPY, -1, true);
            }
            return;
        }
        super.setDamage(stack, damage);
    }

    @Override
    @SideOnly(Side.CLIENT)
    // Looks redundant, I still have to include it, or the parent supersedes it.
    public void getSubItems(Item item, CreativeTabs creativeTab, List list) {
        list.add(new ItemStack(item, 1, charge));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        scribe = ir.registerIcon("planarartifice:alchemical_scribing_tools");
    }

    @Override
    public IIcon getIconFromDamage(int par1) {
        return scribe;
    }

    @Override
    public EnumRarity getRarity(ItemStack itemstack) {
        return PlanarArtifice.planarGreen;
    }

    @Override
    // Returning null ensures the game doesn't think you can wear a pen as an amulet.
    public BaubleType getBaubleType(ItemStack itemstack) {
        return null;
    }

    @Override
    // Has to be overriden otherwise the game thinks this is an amulet.
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {}

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(
                EnumChatFormatting.GOLD + StatCollector.translateToLocal("item.capacity.text")
                        + " "
                        + getMaxVis(stack) / 100);
        if (stack.hasTagCompound()) {
            for (Aspect aspect : Aspect.getPrimalAspects()) {
                if (stack.stackTagCompound.hasKey(aspect.getTag())) {
                    String amount = decimalFormatter
                            .format((double) ((float) stack.stackTagCompound.getInteger(aspect.getTag()) / 100.0F));
                    list.add(" §" + aspect.getChatcolor() + aspect.getName() + "§r x " + amount);
                    // Not quite sure why § is used here, it was used in the original
                    // vis amulets, so I kept it in for consistency's sake.
                }
            }
        }
    }

    @Override
    public int getMaxVis(ItemStack stack) {
        return charge * 100;
    } // currently 250 vis, I might increase it, vis works to 2 sig figs after the decimal

    @Override
    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return false;
    } // So you can't use it like an amulet.

    @Override
    // I don't understand why Azanor has a boolean value that would prevent this method
    // from working, FFS just don't let the method be used the first place then dude.
    public int addVis(ItemStack stack, Aspect aspect, int amount, boolean useless) {
        if (!aspect.isPrimal()) {
            return 0;
        }
        // Azanor did something weird here that I corrected in my rewrite.
        // That being, he for some reason didn't just have an if statement
        // similar to the one found above, but further included an else
        // statement to the effect of the code below, I feel that anxiety
        // riddled paranoid thought that causes redundancy such as that.
        int toStore = getVis(stack, aspect) + amount * 100;
        int remaining = Math.max(toStore - getMaxVis(stack), 0);
        if (useless) {
            this.storeVis(stack, aspect, Math.min(toStore, getMaxVis(stack)));
        }
        return remaining / 100;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false;
    }
}

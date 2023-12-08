package thaumicinsurgence.item.tools;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.tiles.TileInfusionMatrix;
import thaumcraft.common.tiles.TilePedestal;
import thaumicinsurgence.main.utils.TabThaumicInsurgence;

public class ItemThaumicInterfacer extends Item {

    public TileEntity matrix;

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.itemIcon = ir.registerIcon("thaumicinsurgence:silverwoodfilter");
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.itemIcon;
    }

    public ItemThaumicInterfacer() {
        setCreativeTab(TabThaumicInsurgence.tabThaumicInsurgence);
        setUnlocalizedName("ItemThaumicInterfacer");
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int targetX, int targetY,
            int targetZ, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            matrix = world.getTileEntity(targetX, targetY, targetZ);
            if (matrix instanceof TileInfusionMatrix) {

                String[] users = MinecraftServer.getServer().getConfigurationManager().getAllUsernames();
                player.addChatMessage(new ChatComponentText("That's a big ass mudspaw right der"));
                player.addChatMessage(new ChatComponentText(users[0]));
                player.addChatMessage(
                        new ChatComponentText(
                                "Infusion Matrix Instability: "
                                        + String.valueOf(((TileInfusionMatrix) matrix).instability)));
            } else if (world.getTileEntity(targetX, targetY, targetZ) instanceof INode) {
                AspectList al = ResearchManager
                        .reduceToPrimals(((INode) world.getTileEntity(targetX, targetY, targetZ)).getAspects());
                player.addChatMessage(
                        new ChatComponentTranslation(
                                "Energized Primal Aspects Equivalents within this node:" + " Aer: "
                                        + (int) Math.sqrt(al.getAmount(Aspect.AIR))
                                        + " Terra: "
                                        + (int) Math.sqrt(al.getAmount(Aspect.EARTH))
                                        + " Ignis: "
                                        + (int) Math.sqrt(al.getAmount(Aspect.FIRE))
                                        + " Aqua: "
                                        + (int) Math.sqrt(al.getAmount(Aspect.WATER))
                                        + " Ordo: "
                                        + (int) Math.sqrt(al.getAmount(Aspect.ORDER))
                                        + " Perditio: "
                                        + (int) Math.sqrt(al.getAmount(Aspect.ENTROPY))));
                return true;
            } else if (matrix instanceof TilePedestal) {
                player.addChatMessage(new ChatComponentText("Pedestal"));
            } else {
                player.addChatMessage(new ChatComponentText("Error: Mudspaw"));
            }
        }

        return false;
    }
}

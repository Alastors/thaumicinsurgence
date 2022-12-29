package thaumicinsurgence.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.common.tiles.TileInfusionPillar;
import thaumicinsurgence.main.utils.VersionInfo;

public class TileEntityInfusionPillarAlpha extends TileInfusionPillar {
    public byte orientation = 0;
    public static final String tileEntityName = VersionInfo.ModID + ".pillarAlpha";

    public TileEntityInfusionPillarAlpha() {}

    @Override
    public boolean canUpdate() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.getBoundingBox(
                (double) (this.xCoord - 1),
                (double) (this.yCoord - 1),
                (double) (this.zCoord - 1),
                (double) (this.xCoord + 1),
                (double) (this.yCoord + 2),
                (double) (this.zCoord + 1));
    }

    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.orientation = nbttagcompound.getByte("orientation");
    }

    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.setByte("orientation", this.orientation);
    }
}

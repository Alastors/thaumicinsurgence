package thaumicinsurgence.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.tiles.TilePedestal;
import thaumicinsurgence.main.utils.VersionInfo;

public class TileEntityPedestalAlpha extends TilePedestal {
    private static final int[] slots = new int[] {0};
    private ItemStack[] inventory = new ItemStack[1];
    private String customName;
    public static final String tileEntityName = VersionInfo.ModID + ".marblePedestal";

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.getBoundingBox(
                (double) this.xCoord,
                (double) this.yCoord,
                (double) this.zCoord,
                (double) (this.xCoord + 1),
                (double) (this.yCoord + 2),
                (double) (this.zCoord + 1));
    }

    public int getSizeInventory() {
        return 1;
    }

    public ItemStack getStackInSlot(int par1) {
        return this.inventory[par1];
    }

    public ItemStack decrStackSize(int par1, int par2) {
        if (this.inventory[par1] != null) {
            if (!this.worldObj.isRemote) {
                this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            }

            if (this.inventory[par1].stackSize <= par2) {
                ItemStack itemstack = this.inventory[par1];
                this.inventory[par1] = null;
                this.markDirty();
                return itemstack;
            } else {
                ItemStack itemstack = this.inventory[par1].splitStack(par2);
                if (this.inventory[par1].stackSize == 0) {
                    this.inventory[par1] = null;
                }

                this.markDirty();
                return itemstack;
            }
        } else {
            return null;
        }
    }

    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.inventory[par1] != null) {
            ItemStack itemstack = this.inventory[par1];
            this.inventory[par1] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
        this.inventory[par1] = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
        if (!this.worldObj.isRemote) {
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }
    }

    public void setInventorySlotContentsFromInfusion(int par1, ItemStack par2ItemStack) {
        this.inventory[par1] = par2ItemStack;
        this.markDirty();
        if (!this.worldObj.isRemote) {
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }
    }

    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.customName : "container.pedestal";
    }

    public boolean hasCustomInventoryName() {
        return this.customName != null && this.customName.length() > 0;
    }

    public void setGuiDisplayName(String par1Str) {
        this.customName = par1Str;
    }

    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        NBTTagList nbttaglist = nbttagcompound.getTagList("Items", 10);
        this.inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");
            if (b0 >= 0 && b0 < this.inventory.length) {
                this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                this.inventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbttagcompound.setTag("Items", nbttaglist);
    }

    public void readFromNBT(NBTTagCompound nbtCompound) {
        super.readFromNBT(nbtCompound);
        if (nbtCompound.hasKey("CustomName")) {
            this.customName = nbtCompound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound nbtCompound) {
        super.writeToNBT(nbtCompound);
        if (this.hasCustomInventoryName()) {
            nbtCompound.setString("CustomName", this.customName);
        }
    }

    public int getInventoryStackLimit() {
        return 1;
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
    }

    public boolean canUpdate() {
        return false;
    }

    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this
                && par1EntityPlayer.getDistanceSq(
                                (double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D)
                        <= 64.0D;
    }

    public void openInventory() {}

    public void closeInventory() {}

    public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
        return true;
    }

    public int[] getAccessibleSlotsFromSide(int par1) {
        return slots;
    }

    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
        return this.getStackInSlot(par1) == null;
    }

    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) {
        return true;
    }

    public boolean receiveClientEvent(int i, int j) {
        if (i == 11) {
            if (this.worldObj.isRemote) {
                for (int a = 0; a < Thaumcraft.proxy.particleCount(5); ++a) {
                    Thaumcraft.proxy.blockSparkle(
                            this.worldObj, this.xCoord, this.yCoord + 1, this.zCoord, 12583104, 2);
                }
            }

            return true;
        } else if (i != 12) {
            return super.receiveClientEvent(i, j);
        } else {
            if (this.worldObj.isRemote) {
                for (int a = 0; a < Thaumcraft.proxy.particleCount(10); ++a) {
                    Thaumcraft.proxy.blockSparkle(this.worldObj, this.xCoord, this.yCoord + 1, this.zCoord, -9999, 2);
                }
            }

            return true;
        }
    }
}

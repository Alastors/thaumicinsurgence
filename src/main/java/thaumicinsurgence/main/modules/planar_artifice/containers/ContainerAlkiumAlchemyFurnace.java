package thaumicinsurgence.main.modules.planar_artifice.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.container.SlotLimitedHasAspects;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumicinsurgence.main.modules.planar_artifice.core.blocks.tiles.TileAlkimiumAlchemicalFurnace;

public class ContainerAlkiumAlchemyFurnace extends Container {

    private TileAlkimiumAlchemicalFurnace furnace;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;
    private int lastVis;
    private int lastSmelt;

    public ContainerAlkiumAlchemyFurnace(InventoryPlayer par1InventoryPlayer,
            TileAlkimiumAlchemicalFurnace tileEntity) {
        this.furnace = tileEntity;
        this.addSlotToContainer(new SlotLimitedHasAspects(tileEntity, 0, 80, 8));
        this.addSlotToContainer(new Slot(tileEntity, 1, 80, 48));

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int var5 = 0; var5 < 9; ++var5) {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var5, 8 + var5 * 18, 142));
        }

    }

    public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.furnace.furnaceCookTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.furnace.burnRemaining);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.furnace.currentItemBurnTime);
        par1ICrafting.sendProgressBarUpdate(this, 3, this.furnace.vis);
        par1ICrafting.sendProgressBarUpdate(this, 4, this.furnace.smeltTime);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);
            if (this.lastCookTime != this.furnace.furnaceCookTime) {
                icrafting.sendProgressBarUpdate(this, 0, this.furnace.furnaceCookTime);
            }

            if (this.lastBurnTime != this.furnace.burnRemaining) {
                icrafting.sendProgressBarUpdate(this, 1, this.furnace.burnRemaining);
            }

            if (this.lastItemBurnTime != this.furnace.currentItemBurnTime) {
                icrafting.sendProgressBarUpdate(this, 2, this.furnace.currentItemBurnTime);
            }

            if (this.lastVis != this.furnace.vis) {
                icrafting.sendProgressBarUpdate(this, 3, this.furnace.vis);
            }

            if (this.lastSmelt != this.furnace.smeltTime) {
                icrafting.sendProgressBarUpdate(this, 4, this.furnace.smeltTime);
            }
        }

        this.lastCookTime = this.furnace.furnaceCookTime;
        this.lastBurnTime = this.furnace.burnRemaining;
        this.lastItemBurnTime = this.furnace.currentItemBurnTime;
        this.lastVis = this.furnace.vis;
        this.lastSmelt = this.furnace.smeltTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            this.furnace.furnaceCookTime = par2;
        }

        if (par1 == 1) {
            this.furnace.burnRemaining = par2;
        }

        if (par1 == 2) {
            this.furnace.currentItemBurnTime = par2;
        }

        if (par1 == 3) {
            this.furnace.vis = par2;
        }

        if (par1 == 4) {
            this.furnace.smeltTime = par2;
        }

    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.furnace.isUseableByPlayer(par1EntityPlayer);
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(par2);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (par2 != 1 && par2 != 0) {
                AspectList al = ThaumcraftCraftingManager.getObjectTags(itemstack1);
                al = ThaumcraftCraftingManager.getBonusTags(itemstack1, al);
                if (TileAlkimiumAlchemicalFurnace.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)
                            && !this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                } else if (al != null && al.size() > 0) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                } else if (par2 >= 2 && par2 < 29) {
                    if (!this.mergeItemStack(itemstack1, 29, 38, false)) {
                        return null;
                    }
                } else if (par2 >= 29 && par2 < 38 && !this.mergeItemStack(itemstack1, 2, 29, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 2, 38, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }
}

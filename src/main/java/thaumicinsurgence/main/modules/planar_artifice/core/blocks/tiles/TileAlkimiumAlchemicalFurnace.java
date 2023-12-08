package thaumicinsurgence.main.modules.planar_artifice.core.blocks.tiles;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.tiles.TileAlchemyFurnace;
import thaumcraft.common.tiles.TileAlembic;
import thaumcraft.common.tiles.TileBellows;

public class TileAlkimiumAlchemicalFurnace extends TileAlchemyFurnace {

    private static final int[] slots_bottom = new int[] { 1 };
    private static final int[] slots_top = new int[0];
    private static final int[] slots_sides = new int[] { 0 };

    public int[] getAccessibleSlotsFromSide(int slot) {
        return slot == 0 ? slots_bottom : (slot == 1 ? slots_top : slots_sides);
    }

    public static final String tileEntityName = "planarartifice.alkimium_smeltery";
    public int burnRemaining;
    public int ticks;
    public int bellowCount;

    @Override
    public void getBellows() {
        bellowCount = TileBellows
                .getBellows(this.worldObj, this.xCoord, this.yCoord, this.zCoord, ForgeDirection.VALID_DIRECTIONS);
    }

    // Configurable values
    public int speedValue;
    public int maxVis;

    public TileAlkimiumAlchemicalFurnace() {
        setValues();
    }

    public void setValues() {
        speedValue = 20;
        maxVis = 100;
    }

    public int returnSpeed() {
        return speedValue;
    }

    public int returnMaxVis() {
        return maxVis;
    }

    // Handles the
    @SideOnly(Side.CLIENT)
    @Override
    public int getContentsScaled(int item) {
        return vis * item / returnMaxVis();
    }

    private String customName;
    private ItemStack[] furnaceItemStacks = new ItemStack[2];
    public boolean alumentumUsed = false;

    public boolean burnRemaining() {
        return burnRemaining > 0;
    }

    @Override
    public void updateEntity() {
        if (burnRemaining()) {
            burnRemaining--;
        }
        boolean isBurning = burnRemaining();
        boolean stillBurning = false;
        ticks++;

        // Ask @Alastors if you want the diagrams related to 1 -> 6
        // they go into intense detail on how each of these methods work.
        if (!worldObj.isRemote) {
            // 1
            // Manages bellows
            if (bellowCount == -1) {
                getBellows();
            }

            // 2
            // Handles moving essentia to the alembics
            if (ticks % returnSpeed() == 0 && aspects.size() > 0) {
                AspectList furnaceList = new AspectList();

                for (int deep = 1; deep < 6; deep++) {
                    TileEntity tile = this.worldObj.getTileEntity(this.xCoord, this.yCoord + deep, this.zCoord);
                    if (!(tile instanceof TileAlembic)) {
                        break;
                    }

                    TileAlembic alembic = (TileAlembic) tile;
                    Aspect as = alembic.aspect;

                    // checking the aspect (if applicable) and taking out of the furnace
                    if (as != null && alembic.amount < alembic.maxAmount && aspects.getAmount(as) > 0) {
                        takeFromContainer(as, 1);
                        furnaceList.merge(as, 1);
                        alembic.addToContainer(as, 1);
                        worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                        worldObj.markBlockForUpdate(this.xCoord, this.yCoord + deep, this.zCoord);
                    } else if (as == null || alembic.amount == 0) {
                        if (alembic.aspectFilter == null) {
                            as = takeRandomAspect(furnaceList);
                        } else if (takeFromContainer(alembic.aspectFilter, 1)) {
                            as = alembic.aspectFilter;
                        }
                        if (as != null) {
                            alembic.addToContainer(as, 1);
                            worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                            worldObj.markBlockForUpdate(this.xCoord, this.yCoord + deep, this.zCoord);
                        }
                    }
                }
            }

            // 3
            // Handles smelting when there ISN'T fuel yet
            if (!burnRemaining() && smeltable() && furnaceItemStacks[1] != null) {
                currentItemBurnTime = burnRemaining = TileEntityFurnace.getItemBurnTime(furnaceItemStacks[1]);
                if (burnRemaining()) {
                    stillBurning = true;
                    alumentumUsed = this.furnaceItemStacks[1]
                            .isItemEqual(new ItemStack(ConfigItems.itemResource, 1, 0));
                }
                furnaceItemStacks[1].stackSize--;
                if (this.furnaceItemStacks[1].stackSize == 0) {
                    this.furnaceItemStacks[1] = null;
                }
            }

            // 4 & 5
            // 4: Handles smelting when the furnace is ALREADY FUELED
            // 5: If there isn't an item, reset.
            if (burnRemaining() && smeltable()) {
                furnaceCookTime++;
                if (furnaceCookTime >= smeltTime) { // this is the part I'm trying to change, smeltTime = 100
                    furnaceCookTime = 0;
                    smeltItem(); // TODO: rewrite this as well
                    stillBurning = true;
                }
            } else {
                furnaceCookTime = 0; // if no item is present, reset the progress bar.
            }

            // 6
            // If the furnace is still burning, trigger for mark dirty and update.
            // this is mostly kept for consistency with the original code.
            if (burnRemaining()) {
                stillBurning = true;
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            }
        }

        if (stillBurning) {
            markDirty();
        }
    }

    public boolean smeltable() {
        if (furnaceItemStacks[0] == null) {
            return false;
        }
        AspectList smeltList = ThaumcraftCraftingManager.getObjectTags(furnaceItemStacks[0]);
        smeltList = ThaumcraftCraftingManager.getBonusTags(furnaceItemStacks[0], smeltList);
        if (smeltList.size() == 0) {
            return false;
        }
        if (smeltList.visSize() > returnMaxVis() - vis) {
            return false;
        }
        smeltTime = (int) ((smeltList.visSize() * 10) * (1.0F - 0.125F * bellowCount));
        // This formula breaks down to: (1) * (2)
        // (1) each point of essentia in an item adds (base) 10 ticks (half a second) to the total time (2 aspects = 1
        // second)
        // (2) each bellow reduces the total time by an eighth, starting from x1.125, all the way to x0.75 (three total)
        return true;
    }

    @Override
    public void smeltItem() {
        if (smeltable()) {
            AspectList smeltList = ThaumcraftCraftingManager.getObjectTags(furnaceItemStacks[0]);
            smeltList = ThaumcraftCraftingManager.getBonusTags(furnaceItemStacks[0], smeltList);
            for (Aspect aspect : smeltList.getAspects()) {
                aspects.add(aspect, smeltList.getAmount(aspect));
            }
            vis = this.aspects.visSize();
            furnaceItemStacks[0].stackSize--;
            if (furnaceItemStacks[0].stackSize <= 0) {
                furnaceItemStacks[0] = null;
            }
        }
    }

    // Basic stuff for NBT data
    // has to be overridden due to private vars (UGH)
    @Override
    public void readFromNBT(NBTTagCompound nbtCompound) {
        super.readFromNBT(nbtCompound);
        NBTTagList nbttaglist = nbtCompound.getTagList("Items", 10);
        furnaceItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");
            if (b0 >= 0 && b0 < furnaceItemStacks.length) {
                furnaceItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        alumentumUsed = nbtCompound.getBoolean("speedBoost");
        furnaceCookTime = nbtCompound.getShort("CookTime");
        currentItemBurnTime = TileEntityFurnace.getItemBurnTime(furnaceItemStacks[1]);
        if (nbtCompound.hasKey("CustomName")) {
            customName = nbtCompound.getString("CustomName");
        }

        this.aspects.readFromNBT(nbtCompound);
        this.vis = this.aspects.visSize();
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtCompound) {
        super.writeToNBT(nbtCompound);
        nbtCompound.setBoolean("speedBoost", alumentumUsed);
        nbtCompound.setShort("CookTime", (short) furnaceCookTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.furnaceItemStacks.length; ++i) {
            if (this.furnaceItemStacks[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                furnaceItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbtCompound.setTag("Items", nbttaglist);
        if (hasCustomInventoryName()) {
            nbtCompound.setString("CustomName", this.customName);
        }

        this.aspects.writeToNBT(nbtCompound);
    }

    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        burnRemaining = nbttagcompound.getShort("BurnTime");
        vis = nbttagcompound.getShort("Vis");
    }

    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.setShort("BurnTime", (short) burnRemaining);
        nbttagcompound.setShort("Vis", (short) vis);
    }

    // Basic stuff for inventory management,
    // I wouldn't've overridden but the parent's methods use private vars annoyingly.
    @Override
    public int getSizeInventory() {
        return furnaceItemStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int x) {
        return furnaceItemStacks[x];
    }

    @Override
    public ItemStack decrStackSize(int x, int y) {
        if (this.furnaceItemStacks[x] != null) {
            ItemStack itemstack;
            if (furnaceItemStacks[x].stackSize <= y) {
                itemstack = furnaceItemStacks[x];
                furnaceItemStacks[x] = null;
            } else {
                itemstack = this.furnaceItemStacks[x].splitStack(y);
                if (furnaceItemStacks[x].stackSize == 0) {
                    furnaceItemStacks[x] = null;
                }
            }
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int x) {
        if (furnaceItemStacks[x] != null) {
            ItemStack itemstack = furnaceItemStacks[x];
            furnaceItemStacks[x] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int x, ItemStack item) {
        furnaceItemStacks[x] = item;
        if (item != null && item.stackSize > getInventoryStackLimit()) {
            item.stackSize = getInventoryStackLimit();
        }

    }

}

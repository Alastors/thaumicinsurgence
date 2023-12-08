package thaumicinsurgence.tileentity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.crafting.IInfusionStabiliser;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.container.InventoryFake;
import thaumcraft.common.lib.crafting.InfusionRunicAugmentRecipe;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.lib.events.EssentiaHandler;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockZap;
import thaumcraft.common.lib.network.fx.PacketFXInfusionSource;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.tiles.TileInfusionMatrix;
import thaumcraft.common.tiles.TileInfusionPillar;
import thaumcraft.common.tiles.TilePedestal;
import thaumicinsurgence.main.utils.VersionInfo;

public class TileEntityInfusionMatrixAlpha extends TileInfusionMatrix implements IWandable, IAspectContainer {

    private ArrayList<ChunkCoordinates> pedestals = new ArrayList();
    private int dangerCount = 0;
    public boolean active = false;
    public boolean crafting = false;
    public boolean checkSurroundings = true;
    public int symmetry = 0;
    public int instability = 0;
    private AspectList recipeEssentia = new AspectList();
    private ArrayList<ItemStack> recipeIngredients = null;
    private Object recipeOutput = null;
    private String recipePlayer = null;
    private String recipeOutputLabel = null;
    private ItemStack recipeInput = null;
    private int recipeInstability = 0;
    private int recipeXP = 0;
    private int recipeType = 0;
    public HashMap<String, TileEntityInfusionMatrixAlpha.SourceFX> sourceFX = new HashMap();
    public int count = 0;
    public int craftCount = 0;
    public float startUp;
    private int countDelay = 10;
    ArrayList<ItemStack> ingredients = new ArrayList();
    int itemCount = 0;

    TilePedestal[] pedestalList = new TilePedestal[this.pedestals.size()];
    ItemStack[] pedestalItems = new ItemStack[this.pedestals.size()];
    int numPedestals = 0;
    int pedsGoneThru = 0;
    boolean startOfCycle = false;

    public static final String tileEntityName = VersionInfo.ModID + ".matrixAlpha";

    // public TileEntityInfusionMatrixAlpha() {}

    @Override
    public void readCustomNBT(NBTTagCompound nbtCompound) {
        this.active = nbtCompound.getBoolean("active");
        this.crafting = nbtCompound.getBoolean("crafting");
        this.instability = nbtCompound.getShort("instability");
        this.recipeEssentia.readFromNBT(nbtCompound);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbtCompound) {
        nbtCompound.setBoolean("active", this.active);
        nbtCompound.setBoolean("crafting", this.crafting);
        nbtCompound.setShort("instability", (short) this.instability);
        this.recipeEssentia.writeToNBT(nbtCompound);
    }

    // TODO: change as needed
    @Override
    public void readFromNBT(NBTTagCompound nbtCompound) {
        super.readFromNBT(nbtCompound);
        NBTTagList nbttaglist = nbtCompound.getTagList("recipein", 10);
        this.recipeIngredients = new ArrayList();

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("item");
            this.recipeIngredients.add(ItemStack.loadItemStackFromNBT(nbttagcompound1));
        }

        String rot = nbtCompound.getString("rotype");
        if (rot != null && rot.equals("@")) {
            this.recipeOutput = ItemStack.loadItemStackFromNBT(nbtCompound.getCompoundTag("recipeout"));
        } else if (rot != null) {
            this.recipeOutputLabel = rot;
            this.recipeOutput = nbtCompound.getTag("recipeout");
        }

        this.recipeInput = ItemStack.loadItemStackFromNBT(nbtCompound.getCompoundTag("recipeinput"));
        this.recipeInstability = nbtCompound.getInteger("recipeinst");
        this.recipeType = nbtCompound.getInteger("recipetype");
        this.recipeXP = nbtCompound.getInteger("recipexp");
        this.recipePlayer = nbtCompound.getString("recipeplayer");
        if (this.recipePlayer.isEmpty()) {
            this.recipePlayer = null;
        }
    }

    // TODO: change as needed
    @Override
    public void writeToNBT(NBTTagCompound nbtCompound) {
        super.writeToNBT(nbtCompound);
        if (this.recipeIngredients != null && this.recipeIngredients.size() > 0) {
            NBTTagList nbttaglist = new NBTTagList();
            int count = 0;

            for (ItemStack stack : this.recipeIngredients) {
                if (stack != null) {
                    NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                    nbttagcompound1.setByte("item", (byte) count);
                    stack.writeToNBT(nbttagcompound1);
                    nbttaglist.appendTag(nbttagcompound1);
                    ++count;
                }
            }

            nbtCompound.setTag("recipein", nbttaglist);
        }

        if (this.recipeOutput != null && this.recipeOutput instanceof ItemStack) {
            nbtCompound.setString("rotype", "@");
        }

        if (this.recipeOutput != null && this.recipeOutput instanceof NBTBase) {
            nbtCompound.setString("rotype", this.recipeOutputLabel);
        }

        if (this.recipeOutput != null && this.recipeOutput instanceof ItemStack) {
            nbtCompound.setTag("recipeout", ((ItemStack) this.recipeOutput).writeToNBT(new NBTTagCompound()));
        }

        if (this.recipeOutput != null && this.recipeOutput instanceof NBTBase) {
            nbtCompound.setTag("recipeout", (NBTBase) this.recipeOutput);
        }

        if (this.recipeInput != null) {
            nbtCompound.setTag("recipeinput", this.recipeInput.writeToNBT(new NBTTagCompound()));
        }

        nbtCompound.setInteger("recipeinst", this.recipeInstability);
        nbtCompound.setInteger("recipetype", this.recipeType);
        nbtCompound.setInteger("recipexp", this.recipeXP);
        if (this.recipePlayer == null) {
            nbtCompound.setString("recipeplayer", "");
        } else {
            nbtCompound.setString("recipeplayer", this.recipePlayer);
        }
    }

    // TODO: change as needed
    @Override
    public void updateEntity() {
        super.updateEntity();
        ++this.count;
        if (this.checkSurroundings) {
            this.checkSurroundings = false;
            this.getSurroundings();
        }

        if (this.worldObj.isRemote) {
            this.doEffects();
        } else if (this.count % (this.crafting ? 20 : 100) == 0 && !this.validLocation()) {
            this.active = false;
            this.markDirty();
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        } else {
            if (this.active && this.crafting && this.count % this.countDelay == 0) {
                // try {
                if (!startOfCycle) {
                    pedestalList = new TilePedestal[this.pedestals.size()];
                    pedestalItems = new ItemStack[this.pedestals.size()];
                    numPedestals = 0;
                    pedsGoneThru = 0;
                    startOfCycle = true;
                }
                // I am going to have to rewrite this entire portion from scratch.
                this.craftCycle();
                // } catch (Exception ignored) {}

                this.markDirty();
            }
        }
    }

    // TODO: change as needed
    private void inEvZap(boolean all) {
        List<Entity> targets = this.worldObj.getEntitiesWithinAABB(
                EntityLivingBase.class,
                AxisAlignedBB.getBoundingBox(
                        (double) this.xCoord,
                        (double) this.yCoord,
                        (double) this.zCoord,
                        (double) (this.xCoord + 1),
                        (double) (this.yCoord + 1),
                        (double) (this.zCoord + 1)).expand(10.0D, 10.0D, 10.0D));
        if (targets != null && targets.size() > 0) {
            for (Entity target : targets) {
                PacketHandler.INSTANCE.sendToAllAround(
                        new PacketFXBlockZap(
                                (float) this.xCoord + 0.5F,
                                (float) this.yCoord + 0.5F,
                                (float) this.zCoord + 0.5F,
                                (float) target.posX,
                                (float) target.posY + target.height / 2.0F,
                                (float) target.posZ),
                        new NetworkRegistry.TargetPoint(
                                this.worldObj.provider.dimensionId,
                                (double) this.xCoord,
                                (double) this.yCoord,
                                (double) this.zCoord,
                                32.0D));
                target.attackEntityFrom(DamageSource.magic, (float) (4 + this.worldObj.rand.nextInt(4)));
                if (!all) {
                    break;
                }
            }
        }
    }

    // TODO: change as needed
    private void inEvHarm(boolean all) {
        List<EntityLivingBase> targets = this.worldObj.getEntitiesWithinAABB(
                EntityLivingBase.class,
                AxisAlignedBB.getBoundingBox(
                        (double) this.xCoord,
                        (double) this.yCoord,
                        (double) this.zCoord,
                        (double) (this.xCoord + 1),
                        (double) (this.yCoord + 1),
                        (double) (this.zCoord + 1)).expand(10.0D, 10.0D, 10.0D));
        if (targets != null && targets.size() > 0) {
            for (EntityLivingBase target : targets) {
                if (this.worldObj.rand.nextBoolean()) {
                    target.addPotionEffect(new PotionEffect(Config.potionTaintPoisonID, 120, 0, false));
                } else {
                    PotionEffect pe = new PotionEffect(Config.potionVisExhaustID, 2400, 0, true);
                    pe.getCurativeItems().clear();
                    target.addPotionEffect(pe);
                }

                if (!all) {
                    break;
                }
            }
        }
    }

    // TODO: change as needed
    private void inEvWarp() {
        List<EntityPlayer> targets = this.worldObj.getEntitiesWithinAABB(
                EntityPlayer.class,
                AxisAlignedBB.getBoundingBox(
                        (double) this.xCoord,
                        (double) this.yCoord,
                        (double) this.zCoord,
                        (double) (this.xCoord + 1),
                        (double) (this.yCoord + 1),
                        (double) (this.zCoord + 1)).expand(10.0D, 10.0D, 10.0D));
        if (targets != null && targets.size() > 0) {
            EntityPlayer target = (EntityPlayer) targets.get(this.worldObj.rand.nextInt(targets.size()));
            if (this.worldObj.rand.nextFloat() < 0.25F) {
                Thaumcraft.addStickyWarpToPlayer(target, 1);
            } else {
                Thaumcraft.addWarpToPlayer(target, 1 + this.worldObj.rand.nextInt(5), true);
            }
        }
    }

    // TODO: change as needed
    private void inEvEjectItem(int type) {
        for (int q = 0; q < 50 && this.pedestals.size() > 0; ++q) {
            ChunkCoordinates cc = (ChunkCoordinates) this.pedestals
                    .get(this.worldObj.rand.nextInt(this.pedestals.size()));
            TileEntity te = this.worldObj.getTileEntity(cc.posX, cc.posY, cc.posZ);
            if (te != null && te instanceof TilePedestal && ((TilePedestal) te).getStackInSlot(0) != null) {
                if (type >= 3 && type != 5) {
                    ((TilePedestal) te).setInventorySlotContents(0, (ItemStack) null);
                } else {
                    InventoryUtils.dropItems(this.worldObj, cc.posX, cc.posY, cc.posZ);
                }

                if (type != 1 && type != 3) {
                    if (type != 2 && type != 4) {
                        if (type == 5) {
                            this.worldObj.createExplosion(
                                    (Entity) null,
                                    (double) ((float) cc.posX + 0.5F),
                                    (double) ((float) cc.posY + 0.5F),
                                    (double) ((float) cc.posZ + 0.5F),
                                    1.0F,
                                    false);
                        }
                    } else {
                        this.worldObj.setBlock(cc.posX, cc.posY + 1, cc.posZ, ConfigBlocks.blockFluxGas, 7, 3);
                        this.worldObj.playSoundEffect(
                                (double) cc.posX,
                                (double) cc.posY,
                                (double) cc.posZ,
                                "random.fizz",
                                0.3F,
                                1.0F);
                    }
                } else {
                    this.worldObj.setBlock(cc.posX, cc.posY + 1, cc.posZ, ConfigBlocks.blockFluxGoo, 7, 3);
                    this.worldObj.playSoundEffect(
                            (double) cc.posX,
                            (double) cc.posY,
                            (double) cc.posZ,
                            "game.neutral.swim",
                            0.3F,
                            1.0F);
                }

                this.worldObj.addBlockEvent(cc.posX, cc.posY, cc.posZ, ConfigBlocks.blockStoneDevice, 11, 0);
                PacketHandler.INSTANCE.sendToAllAround(
                        new PacketFXBlockZap(
                                (float) this.xCoord + 0.5F,
                                (float) this.yCoord + 0.5F,
                                (float) this.zCoord + 0.5F,
                                (float) cc.posX + 0.5F,
                                (float) cc.posY + 1.5F,
                                (float) cc.posZ + 0.5F),
                        new NetworkRegistry.TargetPoint(
                                this.worldObj.provider.dimensionId,
                                (double) this.xCoord,
                                (double) this.yCoord,
                                (double) this.zCoord,
                                32.0D));
                return;
            }
        }
    }

    // TODO: change as needed
    @Override
    public boolean validLocation() {
        TileEntity te = null;
        te = this.worldObj.getTileEntity(this.xCoord, this.yCoord - 2, this.zCoord);
        if (te instanceof TilePedestal) {
            te = this.worldObj.getTileEntity(this.xCoord + 1, this.yCoord - 2, this.zCoord + 1);
            if (te instanceof TileInfusionPillar) {
                te = this.worldObj.getTileEntity(this.xCoord + 1, this.yCoord - 2, this.zCoord - 1);
                if (te instanceof TileInfusionPillar) {
                    te = this.worldObj.getTileEntity(this.xCoord - 1, this.yCoord - 2, this.zCoord - 1);
                    if (te instanceof TileInfusionPillar) {
                        te = this.worldObj.getTileEntity(this.xCoord - 1, this.yCoord - 2, this.zCoord + 1);
                        return te instanceof TileInfusionPillar;
                    }
                }
            }
        }
        return false;
    }

    private void getSurroundings() {
        ArrayList<ChunkCoordinates> stuff = new ArrayList();
        this.pedestals.clear();

        try {
            for (int xx = -12; xx <= 12; ++xx) {
                for (int zz = -12; zz <= 12; ++zz) {
                    boolean skip = false;

                    for (int yy = -5; yy <= 10; ++yy) {
                        if (xx != 0 || zz != 0) {
                            int x = this.xCoord + xx;
                            int y = this.yCoord - yy;
                            int z = this.zCoord + zz;
                            TileEntity te = this.worldObj.getTileEntity(x, y, z);
                            if (!skip && yy > 0
                                    && Math.abs(xx) <= 8
                                    && Math.abs(zz) <= 8
                                    && te != null
                                    && te instanceof TilePedestal) {
                                this.pedestals.add(new ChunkCoordinates(x, y, z));
                                skip = true;
                            } else {
                                Block bi = this.worldObj.getBlock(x, y, z);
                                if (bi == Blocks.skull
                                        || bi instanceof IInfusionStabiliser && ((IInfusionStabiliser) bi)
                                                .canStabaliseInfusion(this.getWorldObj(), x, y, z)) {
                                    stuff.add(new ChunkCoordinates(x, y, z));
                                }
                            }
                        }
                    }
                }
            }

            this.symmetry = 0;

            for (ChunkCoordinates cc : this.pedestals) {
                boolean items = false;
                int x = this.xCoord - cc.posX;
                int z = this.zCoord - cc.posZ;
                TileEntity te = this.worldObj.getTileEntity(cc.posX, cc.posY, cc.posZ);
                if (te != null && te instanceof TilePedestal) {
                    this.symmetry += 2;
                    if (((TilePedestal) te).getStackInSlot(0) != null) {
                        ++this.symmetry;
                        items = true;
                    }
                }

                int xx = this.xCoord + x;
                int zz = this.zCoord + z;
                te = this.worldObj.getTileEntity(xx, cc.posY, zz);
                if (te != null && te instanceof TilePedestal) {
                    this.symmetry -= 2;
                    if (((TilePedestal) te).getStackInSlot(0) != null && items) {
                        --this.symmetry;
                    }
                }
            }

            float sym = 0.0F;

            for (ChunkCoordinates cc : stuff) {
                boolean items = false;
                int x = this.xCoord - cc.posX;
                int z = this.zCoord - cc.posZ;
                Block bi = this.worldObj.getBlock(cc.posX, cc.posY, cc.posZ);
                if (bi == Blocks.skull || bi instanceof IInfusionStabiliser && ((IInfusionStabiliser) bi)
                        .canStabaliseInfusion(this.getWorldObj(), cc.posX, cc.posY, cc.posZ)) {
                    sym += 0.1F;
                }

                int xx = this.xCoord + x;
                int zz = this.zCoord + z;
                bi = this.worldObj.getBlock(xx, cc.posY, zz);
                if (bi == Blocks.skull || bi instanceof IInfusionStabiliser && ((IInfusionStabiliser) bi)
                        .canStabaliseInfusion(this.getWorldObj(), cc.posX, cc.posY, cc.posZ)) {
                    sym -= 0.2F;
                }
            }

            this.symmetry = (int) ((float) this.symmetry + sym);
        } catch (Exception ignored) {}
    }

    // Directly from the infusion matrix
    // TODO: change as needed
    @Override
    public void craftingStart(EntityPlayer player) {

        if (!this.validLocation()) {
            this.active = false;
            this.markDirty();
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        } else {
            this.getSurroundings();
            TileEntity te = null;
            this.recipeInput = null;
            te = this.worldObj.getTileEntity(this.xCoord, this.yCoord - 2, this.zCoord);
            if (te != null && te instanceof TilePedestal) {
                TilePedestal ped = (TilePedestal) te;
                if (ped.getStackInSlot(0) != null) {
                    this.recipeInput = ped.getStackInSlot(0).copy();
                }
            }

            if (this.recipeInput != null) {
                ArrayList<ItemStack> components = new ArrayList();

                for (ChunkCoordinates cc : this.pedestals) {
                    te = this.worldObj.getTileEntity(cc.posX, cc.posY, cc.posZ);
                    if (te != null && te instanceof TilePedestal) {
                        TilePedestal ped = (TilePedestal) te;
                        if (ped.getStackInSlot(0) != null) {
                            components.add(ped.getStackInSlot(0).copy());
                        }
                    }
                }

                if (components.size() != 0) {
                    InfusionRecipe recipe = ThaumcraftCraftingManager
                            .findMatchingInfusionRecipe(components, this.recipeInput, player);
                    if (recipe != null) {
                        this.recipeType = 0;
                        this.recipeIngredients = new ArrayList();
                        if (recipe instanceof InfusionRunicAugmentRecipe) {
                            for (ItemStack ing : ((InfusionRunicAugmentRecipe) recipe)
                                    .getComponents(this.recipeInput)) {
                                this.recipeIngredients.add(ing.copy());
                            }
                        } else {
                            for (ItemStack ing : recipe.getComponents()) {
                                this.recipeIngredients.add(ing.copy());
                            }
                        }

                        if (recipe.getRecipeOutput(this.recipeInput) instanceof Object[]) {
                            Object[] obj = (Object[]) ((Object[]) recipe.getRecipeOutput(this.recipeInput));
                            this.recipeOutputLabel = (String) obj[0];
                            this.recipeOutput = (NBTBase) obj[1];
                        } else {
                            this.recipeOutput = recipe.getRecipeOutput(this.recipeInput);
                        }

                        this.recipeInstability = recipe.getInstability(this.recipeInput);
                        this.recipeEssentia = recipe.getAspects(this.recipeInput).copy();
                        this.recipePlayer = player.getCommandSenderName();
                        this.instability = this.symmetry + this.recipeInstability;
                        this.crafting = true;
                        this.worldObj.playSoundEffect(
                                (double) this.xCoord,
                                (double) this.yCoord,
                                (double) this.zCoord,
                                "thaumcraft:craftstart",
                                0.5F,
                                1.0F);
                        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                        this.markDirty();
                    } else {
                        InfusionEnchantmentRecipe recipe2 = ThaumcraftCraftingManager
                                .findMatchingInfusionEnchantmentRecipe(components, this.recipeInput, player);
                        if (recipe2 != null) {
                            this.recipeType = 1;
                            this.recipeIngredients = new ArrayList();

                            for (ItemStack ing : recipe2.components) {
                                this.recipeIngredients.add(ing.copy());
                            }

                            this.recipeOutput = recipe2.getEnchantment();
                            this.recipeInstability = recipe2.calcInstability(this.recipeInput);
                            AspectList esscost = recipe2.aspects.copy();
                            float essmod = recipe2.getEssentiaMod(this.recipeInput);

                            for (Aspect as : esscost.getAspects()) {
                                esscost.add(as, (int) ((float) esscost.getAmount(as) * essmod));
                            }

                            this.recipeEssentia = esscost;
                            this.recipeXP = recipe2.calcXP(this.recipeInput);
                            this.instability = this.symmetry + this.recipeInstability;
                            this.crafting = true;
                            this.worldObj.playSoundEffect(
                                    (double) this.xCoord,
                                    (double) this.yCoord,
                                    (double) this.zCoord,
                                    "thaumcraft:craftstart",
                                    0.5F,
                                    1.0F);
                            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                            this.markDirty();
                        }
                    }
                }
            }
        }
    }

    // START OF craft Cycle ///////// Marking cause this code is long af //////////////////////////////////////

    // Directly from the infusion matrix
    // TODO: change as needed
    @Override
    public void craftCycle() {

        boolean valid = false;
        TileEntity te = this.worldObj.getTileEntity(this.xCoord, this.yCoord - 2, this.zCoord);

        // if te is an instance of TilePedestal, ped is set as it's pedestal version.
        // if ped has an item in it's slot, this is detected.
        // if all of the items combined are equal to a recipe, it sets valid as true.
        if (te instanceof TilePedestal) {
            valid = isValid(valid, te);
        }

        // this handles the instability effects.
        if (!valid || this.instability > 0 && this.worldObj.rand.nextInt(500) <= this.instability) {
            switch (this.worldObj.rand.nextInt(21)) {
                case 0:
                case 2:
                case 10:
                case 13:
                    this.inEvEjectItem(0);
                    break;
                case 1:
                case 11:
                    this.inEvEjectItem(2);
                    break;
                case 3:
                case 8:
                case 14:
                    this.inEvZap(false);
                    break;
                case 4:
                case 15:
                    this.inEvEjectItem(5);
                    break;
                case 5:
                case 16:
                    this.inEvHarm(false);
                    break;
                case 6:
                case 17:
                    this.inEvEjectItem(1);
                    break;
                case 7:
                    this.inEvEjectItem(4);
                    break;
                case 9:
                    this.worldObj.createExplosion(
                            (Entity) null,
                            (double) ((float) this.xCoord + 0.5F),
                            (double) ((float) this.yCoord + 0.5F),
                            (double) ((float) this.zCoord + 0.5F),
                            1.5F + this.worldObj.rand.nextFloat(),
                            false);
                    break;
                case 12:
                    this.inEvZap(true);
                    break;
                case 18:
                    this.inEvHarm(true);
                    break;
                case 19:
                    this.inEvEjectItem(3);
                    break;
                case 20:
                    this.inEvWarp();
            }

            if (valid) {
                return;
            }
        }

        // if you remove the center item, this happens.
        if (!valid) {
            removeCenter();
        }

        // this is how he handles the enchantment bullshit, the real question is, why did he handle it in this one.
        else if (this.recipeType == 1 && this.recipeXP > 0) {
            List<EntityPlayer> targets = this.worldObj.getEntitiesWithinAABB(
                    EntityPlayer.class,
                    AxisAlignedBB.getBoundingBox(
                            (double) this.xCoord,
                            (double) this.yCoord,
                            (double) this.zCoord,
                            (double) (this.xCoord + 1),
                            (double) (this.yCoord + 1),
                            (double) (this.zCoord + 1)).expand(10.0D, 10.0D, 10.0D));
            if (targets != null && targets.size() > 0) {
                for (EntityPlayer target : targets) {
                    if (target.experienceLevel > 0) {
                        target.addExperienceLevel(-1);
                        --this.recipeXP;
                        target.attackEntityFrom(DamageSource.magic, (float) this.worldObj.rand.nextInt(2));
                        SimpleNetworkWrapper var22 = PacketHandler.INSTANCE;
                        PacketFXInfusionSource var23 = new PacketFXInfusionSource(
                                this.xCoord,
                                this.yCoord,
                                this.zCoord,
                                (byte) 0,
                                (byte) 0,
                                (byte) 0,
                                target.getEntityId());
                        double var24 = (double) this.xCoord;
                        double var25 = (double) this.yCoord;
                        double var26 = (double) this.zCoord;
                        var22.sendToAllAround(
                                var23,
                                new NetworkRegistry.TargetPoint(
                                        this.getWorldObj().provider.dimensionId,
                                        var24,
                                        var25,
                                        var26,
                                        32.0D));
                        this.worldObj.playSoundAtEntity(
                                target,
                                "random.fizz",
                                1.0F,
                                2.0F + this.worldObj.rand.nextFloat() * 0.4F);
                        this.countDelay = 20;
                        return;
                    }
                }

                Aspect[] ingEss = this.recipeEssentia.getAspects();
                if (ingEss != null && ingEss.length > 0 && this.worldObj.rand.nextInt(3) == 0) {
                    Aspect as = ingEss[this.worldObj.rand.nextInt(ingEss.length)];
                    this.recipeEssentia.add(as, 1);

                    // this is a reference to instability
                    if (this.worldObj.rand.nextInt(Math.max(50 - this.recipeInstability * 2, 0)) == 0) {
                        ++this.instability;
                    }

                    // this is a reference to instability, was originally 25
                    if (this.instability > 500) {
                        this.instability = 500;
                    }

                    this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                    this.markDirty();
                }
            }

        } else {
            // I'm not entirely sure what the point of this is
            if (this.recipeType == 1 && this.recipeXP == 0) {
                this.countDelay = 0;
            }

            // this section is all related to sucking in vis as it comes, I can easily improve this.
            // or alternatively, I could straight remove this and use my intercepter as the absolute essentia method.
            // TODO: no Alastor, you can't remove this fully, or the matrix will just do the recipe for free!
            if (this.recipeEssentia.visSize() > 0) {
                for (Aspect aspect : this.recipeEssentia.getAspects()) {
                    if (this.recipeEssentia.getAmount(aspect) > 0) {
                        if (EssentiaHandler.drainEssentia(this, aspect, ForgeDirection.UNKNOWN, 12)) {
                            this.recipeEssentia.reduce(aspect, 1);
                            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                            this.markDirty();
                            return;
                        }
                        // the Math.max statement ensures that it doesn't go below 0, // if the instability would put it
                        // below 10,it hard floors // the chance to add instability at 1/10 per tick or approx // a 10%
                        // chance per tick. oraround +2 instability per sec // originally this caused a massive crash.
                        // if
                        if (this.worldObj.rand.nextInt(Math.max(100 - this.recipeInstability * 3, 10)) == 0) {
                            ++this.instability;
                        }
                        // this is a reference to instability, was originally 25 if (this.instability > 500) {
                        // this.instability =500; }
                        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                        this.markDirty();
                    }
                }
                this.checkSurroundings = true;
            }

            // this section is related to resetting the matrix if there are no items left in the recipe
            // also turns the central item into the desired item.
            if (this.recipeIngredients.size() <= 0) {
                sendToEnd();
            }

            // this portion handles actually taking the pedestals, and detecting if they have items (why is he doing
            // this again?)
            else {

                for (int a = 0; a < this.recipeIngredients.size(); ++a) {

                    ///////////////////////////////////// I think this matters!

                    for (ChunkCoordinates cc : this.pedestals) {
                        te = this.worldObj.getTileEntity(cc.posX, cc.posY, cc.posZ);

                        // if te is a Tile Pedestal, wacky hijinks ensues.
                        if (te instanceof TilePedestal && ((TilePedestal) te).getStackInSlot(0) != null
                                && InfusionRecipe.areItemStacksEqual(
                                        ((TilePedestal) te).getStackInSlot(0),
                                        (ItemStack) this.recipeIngredients.get(a),
                                        true)) {
                            if (numPedestals > 1) {
                                numPedestals = 0;
                                break;
                            }
                            // I'm not 100% sure what this contributes.
                            if (this.itemCount == 0) {
                                this.itemCount = 5;
                                SimpleNetworkWrapper var10000 = PacketHandler.INSTANCE;
                                // causes the infusion sucky particle animation from the matrix to the pedestal
                                PacketFXInfusionSource var10001 = new PacketFXInfusionSource(
                                        this.xCoord,
                                        this.yCoord,
                                        this.zCoord,
                                        (byte) (this.xCoord - cc.posX),
                                        (byte) (this.yCoord - cc.posY),
                                        (byte) (this.zCoord - cc.posZ),
                                        0);

                                double var10005 = (double) this.xCoord;
                                double var10006 = (double) this.yCoord;
                                double var10007 = (double) this.zCoord;

                                var10000.sendToAllAround(
                                        var10001,
                                        new NetworkRegistry.TargetPoint(
                                                this.getWorldObj().provider.dimensionId,
                                                var10005,
                                                var10006,
                                                var10007,
                                                32.0D));
                            }
                            // Item count is always 0. At least when it reaches this, what is the point?
                            else if (this.itemCount-- <= 1) {

                                pedestalList[numPedestals] = (TilePedestal) te;
                                ItemStack is = ((TilePedestal) te).getStackInSlot(0).getItem()
                                        .getContainerItem(((TilePedestal) te).getStackInSlot(0));
                                pedestalItems[numPedestals] = is;
                                numPedestals++;
                                String[] users = MinecraftServer.getServer().getConfigurationManager()
                                        .getAllUsernames();
                                EntityPlayer tempPlayer = MinecraftServer.getServer().getEntityWorld()
                                        .getPlayerEntityByName(users[0]);

                                tempPlayer.addChatMessage(
                                        new ChatComponentText(
                                                "we are on item num: " + numPedestals
                                                        + " we have gone through "
                                                        + pedsGoneThru
                                                        + " total."));
                            }

                            return;
                        }
                        // End of detection of pedestals that have something in them.

                    }

                    for (int b = 0; b < recipeIngredients.size(); ++b) {
                        te = pedestalList[b];
                        ItemStack is = pedestalItems[b];
                        ((TilePedestal) te).setInventorySlotContents(0, is == null ? null : is.copy());
                        this.recipeIngredients.remove(b);
                    }
                    // END OF WHAT MATTERS? ////////////////////////////////////////////////////

                    Aspect[] ingEss = this.recipeEssentia.getAspects();
                    if (ingEss != null && ingEss.length > 0 && this.worldObj.rand.nextInt(1 + a) == 0) {
                        Aspect as = ingEss[this.worldObj.rand.nextInt(ingEss.length)];
                        this.recipeEssentia.add(as, 1);

                        // this is a reference to instability
                        if (this.worldObj.rand.nextInt(Math.max(50 - this.recipeInstability * 2, 0)) == 0) {
                            ++this.instability;
                        }

                        // this is a reference to instability, was originally 25
                        if (this.instability > 500) {
                            this.instability = 500;
                        }

                        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                        this.markDirty();
                    }
                }
            }
        }
    }
    // END OF craft Cycle ///////// Marking cause this code is long af //////////////////////////////////////

    public boolean isValid(boolean valid, TileEntity te) {
        TilePedestal ped = (TilePedestal) te;
        pedsGoneThru++;
        if (ped.getStackInSlot(0) != null) {
            ItemStack i2 = ped.getStackInSlot(0).copy();
            if (this.recipeInput.getItemDamage() == 32767) {
                i2.setItemDamage(32767);
            }

            if (InventoryUtils.areItemStacksEqualForCrafting(i2, this.recipeInput, true, true, false)) {
                valid = true;
            }
        }
        return valid;
    }

    // resets the matrix if you remove the center
    public void removeCenter() {
        this.instability = 0;
        this.crafting = false;
        this.recipeEssentia = new AspectList();
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        this.worldObj.playSoundEffect(
                (double) this.xCoord,
                (double) this.yCoord,
                (double) this.zCoord,
                "thaumcraft:craftfail",
                1.0F,
                0.6F);
        this.markDirty();
    }

    public void sendToEnd() {
        this.instability = 0;
        this.crafting = false;
        this.craftingFinish(this.recipeOutput, this.recipeOutputLabel);
        this.recipeOutput = null;
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        this.markDirty();
    }

    // Directly from the infusion matrix
    // TODO: change as needed
    @Override
    public void craftingFinish(Object out, String label) {
        TileEntity te = this.worldObj.getTileEntity(this.xCoord, this.yCoord - 2, this.zCoord);

        if (te instanceof TilePedestal) {
            if (out instanceof ItemStack) {
                ((TilePedestal) te).setInventorySlotContentsFromInfusion(0, ((ItemStack) out).copy());
            } else if (out instanceof NBTBase) {
                ItemStack temp = ((TilePedestal) te).getStackInSlot(0);
                NBTBase tag = (NBTBase) out;
                temp.setTagInfo(label, tag);
                this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord - 2, this.zCoord);
                te.markDirty();
            } else if (out instanceof Enchantment) {
                ItemStack temp = ((TilePedestal) te).getStackInSlot(0);
                Map enchantments = EnchantmentHelper.getEnchantments(temp);
                enchantments.put(
                        Integer.valueOf(((Enchantment) out).effectId),
                        Integer.valueOf(EnchantmentHelper.getEnchantmentLevel(((Enchantment) out).effectId, temp) + 1));
                EnchantmentHelper.setEnchantments(enchantments, temp);
                this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord - 2, this.zCoord);
                te.markDirty();
            }

            if (this.recipePlayer != null) {
                EntityPlayer p = this.worldObj.getPlayerEntityByName(this.recipePlayer);
                if (p != null) {
                    FMLCommonHandler.instance().firePlayerCraftingEvent(
                            p,
                            ((TilePedestal) te).getStackInSlot(0),
                            new InventoryFake(this.recipeIngredients));
                }
            }

            this.startOfCycle = false;
            this.recipeEssentia = new AspectList();
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            this.markDirty();
            this.worldObj
                    .addBlockEvent(this.xCoord, this.yCoord - 2, this.zCoord, ConfigBlocks.blockStoneDevice, 12, 0);
        }
    }

    // Directly from the infusion matrix
    // TODO: change as needed
    @Override
    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side,
            int md) {
        if (!world.isRemote && this.active && !this.crafting) {
            this.craftingStart(player);
            return 0;
        } else if (!world.isRemote && !this.active && this.validLocation()) {
            this.active = true;
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            this.markDirty();
            return 0;
        } else {
            return -1;
        }
    }

    // Directly from the infusion matrix
    // TODO: change as needed
    @Override
    public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {}

    // Directly from the infusion matrix
    // TODO: change as needed
    @Override
    public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {}

    // THIS IS WHERE IT GRABS ITEMS FROM!!! /////////////////////////////////////////////////////////////////////

    // Directly from the infusion matrix
    // TODO: change as needed
    private void doEffects() {
        if (this.crafting) {
            if (this.craftCount == 0) {
                this.worldObj.playSound(
                        (double) this.xCoord,
                        (double) this.yCoord,
                        (double) this.zCoord,
                        "thaumcraft:infuserstart",
                        0.5F,
                        1.0F,
                        false);
            } else if (this.craftCount % 65 == 0) {
                this.worldObj.playSound(
                        (double) this.xCoord,
                        (double) this.yCoord,
                        (double) this.zCoord,
                        "thaumcraft:infuser",
                        0.5F,
                        1.0F,
                        false);
            }

            ++this.craftCount;
            Thaumcraft.proxy.blockRunes(
                    this.worldObj,
                    (double) this.xCoord,
                    (double) (this.yCoord - 2),
                    (double) this.zCoord,
                    0.5F + this.worldObj.rand.nextFloat() * 0.2F,
                    0.1F,
                    0.7F + this.worldObj.rand.nextFloat() * 0.3F,
                    25,
                    -0.03F);
        } else if (this.craftCount > 0) {
            this.craftCount -= 2;
            if (this.craftCount < 0) {
                this.craftCount = 0;
            }

            if (this.craftCount > 50) {
                this.craftCount = 50;
            }
        }

        if (this.active && this.startUp != 1.0F) {
            if (this.startUp < 1.0F) {
                this.startUp += Math.max(this.startUp / 10.0F, 0.001F);
            }

            if ((double) this.startUp > 0.999D) {
                this.startUp = 1.0F;
            }
        }

        if (!this.active && this.startUp > 0.0F) {
            if (this.startUp > 0.0F) {
                this.startUp -= this.startUp / 10.0F;
            }

            if ((double) this.startUp < 0.001D) {
                this.startUp = 0.0F;
            }
        }

        for (String fxk : (String[]) this.sourceFX.keySet().toArray(new String[0])) {

            TileEntityInfusionMatrixAlpha.SourceFX fx = (TileEntityInfusionMatrixAlpha.SourceFX) this.sourceFX.get(fxk);
            if (fx.ticks <= 0) {

                this.sourceFX.remove(fxk);

            } else {
                if (fx.loc.posX == this.xCoord && fx.loc.posY == this.yCoord && fx.loc.posZ == this.zCoord) {
                    Entity player = this.worldObj.getEntityByID(fx.color);
                    if (player != null) {
                        for (int a = 0; a < Thaumcraft.proxy.particleCount(2); ++a) {
                            Thaumcraft.proxy.drawInfusionParticles4(
                                    this.worldObj,
                                    player.posX + (double) ((this.worldObj.rand.nextFloat()
                                            - this.worldObj.rand.nextFloat()) * player.width),
                                    player.boundingBox.minY + (double) (this.worldObj.rand.nextFloat() * player.height),
                                    player.posZ + (double) ((this.worldObj.rand.nextFloat()
                                            - this.worldObj.rand.nextFloat()) * player.width),
                                    this.xCoord,
                                    this.yCoord,
                                    this.zCoord);
                        }
                    }
                } else {
                    TileEntity tile = this.worldObj.getTileEntity(fx.loc.posX, fx.loc.posY, fx.loc.posZ);

                    if (tile instanceof TilePedestal) {

                        ItemStack is = ((TilePedestal) tile).getStackInSlot(0);

                        if (is != null) {
                            if (this.worldObj.rand.nextInt(3) == 0) {

                                Thaumcraft.proxy.drawInfusionParticles3(
                                        this.worldObj,
                                        (double) ((float) fx.loc.posX + this.worldObj.rand.nextFloat()),
                                        (double) ((float) fx.loc.posY + this.worldObj.rand.nextFloat() + 1.0F),
                                        (double) ((float) fx.loc.posZ + this.worldObj.rand.nextFloat()),
                                        this.xCoord,
                                        this.yCoord,
                                        this.zCoord);

                            } else {
                                Item bi = is.getItem();
                                int md = is.getItemDamage();

                                if (is.getItemSpriteNumber() == 0 && bi instanceof ItemBlock) {
                                    for (int a = 0; a < Thaumcraft.proxy.particleCount(2); ++a) {
                                        Thaumcraft.proxy.drawInfusionParticles2(
                                                this.worldObj,
                                                (double) ((float) fx.loc.posX + this.worldObj.rand.nextFloat()),
                                                (double) ((float) fx.loc.posY + this.worldObj.rand.nextFloat() + 1.0F),
                                                (double) ((float) fx.loc.posZ + this.worldObj.rand.nextFloat()),
                                                this.xCoord,
                                                this.yCoord,
                                                this.zCoord,
                                                Block.getBlockFromItem(bi),
                                                md);
                                    }
                                } else {
                                    for (int a = 0; a < Thaumcraft.proxy.particleCount(2); ++a) {
                                        Thaumcraft.proxy.drawInfusionParticles1(
                                                this.worldObj,
                                                (double) ((float) fx.loc.posX + 0.4F
                                                        + this.worldObj.rand.nextFloat() * 0.2F),
                                                (double) ((float) fx.loc.posY + 1.23F
                                                        + this.worldObj.rand.nextFloat() * 0.2F),
                                                (double) ((float) fx.loc.posZ + 0.4F
                                                        + this.worldObj.rand.nextFloat() * 0.2F),
                                                this.xCoord,
                                                this.yCoord,
                                                this.zCoord,
                                                bi,
                                                md);
                                    }
                                }
                            }
                        }

                    } else {
                        fx.ticks = 0;
                    }
                }

                --fx.ticks;
                this.sourceFX.put(fxk, fx);
            }
        }

        // this is a reference to instability
        if (this.crafting && this.instability > 0 && this.worldObj.rand.nextInt(200) <= this.instability) {
            Thaumcraft.proxy.nodeBolt(
                    this.worldObj,
                    (float) this.xCoord + 0.5F,
                    (float) this.yCoord + 0.5F,
                    (float) this.zCoord + 0.5F,
                    (float) this.xCoord + 0.5F
                            + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 2.0F,
                    (float) this.yCoord + 0.5F
                            + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 2.0F,
                    (float) this.zCoord + 0.5F
                            + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 2.0F);
        }
    }

    // Directly from the infusion matrix
    // TODO: change as needed
    @Override
    public AspectList getAspects() {
        return this.recipeEssentia;
    }

    // Directly from the infusion matrix
    // TODO: change as needed
    @Override
    public void setAspects(AspectList aspects) {}

    // Directly from the infusion matrix
    // TODO: change as needed
    @Override
    public int addToContainer(Aspect tag, int amount) {
        return 0;
    }

    // Directly from the infusion matrix
    // TODO: change as needed
    @Override
    public boolean takeFromContainer(Aspect tag, int amount) {
        return false;
    }

    // Directly from the infusion matrix
    // TODO: change as needed
    @Override
    public boolean takeFromContainer(AspectList ot) {
        return false;
    }

    // Directly from the infusion matrix
    // TODO: change as needed
    @Override
    public boolean doesContainerContainAmount(Aspect tag, int amount) {
        return false;
    }

    // Directly from the infusion matrix
    // TODO: change as needed
    @Override
    public boolean doesContainerContain(AspectList ot) {
        return false;
    }

    // Directly from the infusion matrix
    // TODO: change as needed
    @Override
    public int containerContains(Aspect tag) {
        return 0;
    }

    // Directly from the infusion matrix
    // TODO: change as needed
    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return true;
    }

    // Directly from the infusion matrix
    // TODO: change as needed
    public class SourceFX {

        public ChunkCoordinates loc;
        public int ticks;
        public int color;
        public int entity;

        public SourceFX(ChunkCoordinates loc, int ticks, int color) {
            this.loc = loc;
            this.ticks = ticks;
            this.color = color;
        }
    }
}

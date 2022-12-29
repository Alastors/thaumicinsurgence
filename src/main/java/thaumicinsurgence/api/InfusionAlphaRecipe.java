package thaumicinsurgence.api;

import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;

public class InfusionAlphaRecipe extends InfusionRecipe {
    protected AspectList aspects;
    protected String research;
    private ItemStack[] components;
    private ItemStack recipeInput;
    protected Object recipeOutput;
    protected int instability;

    public InfusionAlphaRecipe(
            String research, Object output, int inst, AspectList aspects2, ItemStack input, ItemStack[] recipe) {
        super(research, output, inst, aspects2, input, recipe);
        this.research = research;
        this.recipeOutput = output;
        this.recipeInput = input;
        this.aspects = aspects2;
        this.components = recipe;
        this.instability = inst;
    }

    public boolean matches(ArrayList<ItemStack> input, ItemStack central, World world, EntityPlayer player) {
        if (this.getRecipeInput() == null) {
            return false;
        } else if (this.research.length() > 0
                && !ThaumcraftApiHelper.isResearchComplete(player.getCommandSenderName(), this.research)) {
            return false;
        } else {
            ItemStack i2 = central.copy();
            if (this.getRecipeInput().getItemDamage() == 32767) {
                i2.setItemDamage(32767);
            }

            if (!areItemStacksEqual(i2, this.getRecipeInput(), true)) {
                return false;
            } else {
                ArrayList<ItemStack> ii = new ArrayList();

                for (ItemStack is : input) {
                    ii.add(is.copy());
                }

                for (ItemStack comp : this.getComponents()) {
                    boolean b = false;

                    for (int a = 0; a < ii.size(); ++a) {
                        i2 = ((ItemStack) ii.get(a)).copy();
                        if (comp.getItemDamage() == 32767) {
                            i2.setItemDamage(32767);
                        }

                        if (areItemStacksEqual(i2, comp, true)) {
                            ii.remove(a);
                            b = true;
                            break;
                        }
                    }

                    if (!b) {
                        return false;
                    }
                }

                return ii.size() == 0;
            }
        }
    }

    public static boolean areItemStacksEqual(ItemStack stack0, ItemStack stack1, boolean fuzzy) {
        if (stack0 == null && stack1 != null) {
            return false;
        } else if (stack0 != null && stack1 == null) {
            return false;
        } else if (stack0 == null && stack1 == null) {
            return true;
        } else {
            boolean t1 = ThaumcraftApiHelper.areItemStackTagsEqualForCrafting(stack0, stack1);
            if (!t1) {
                return false;
            } else {
                if (fuzzy) {
                    Integer od = Integer.valueOf(OreDictionary.getOreID(stack0));
                    if (od.intValue() != -1) {
                        ItemStack[] ores =
                                (ItemStack[]) OreDictionary.getOres(od).toArray(new ItemStack[0]);
                        if (ThaumcraftApiHelper.containsMatch(false, new ItemStack[] {stack1}, ores)) {
                            return true;
                        }
                    }
                }

                boolean damage = stack0.getItemDamage() == stack1.getItemDamage() || stack1.getItemDamage() == 32767;
                return stack0.getItem() != stack1.getItem()
                        ? false
                        : (!damage ? false : stack0.stackSize <= stack0.getMaxStackSize());
            }
        }
    }

    public Object getRecipeOutput() {
        return this.getRecipeOutput(this.getRecipeInput());
    }

    public AspectList getAspects() {
        return this.getAspects(this.getRecipeInput());
    }

    public int getInstability() {
        return this.getInstability(this.getRecipeInput());
    }

    public String getResearch() {
        return this.research;
    }

    public ItemStack getRecipeInput() {
        return this.recipeInput;
    }

    public ItemStack[] getComponents() {
        return this.components;
    }

    public Object getRecipeOutput(ItemStack input) {
        return this.recipeOutput;
    }

    public AspectList getAspects(ItemStack input) {
        return this.aspects;
    }

    public int getInstability(ItemStack input) {
        return this.instability;
    }
}

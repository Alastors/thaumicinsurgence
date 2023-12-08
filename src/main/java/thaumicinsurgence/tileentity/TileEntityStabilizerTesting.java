package thaumicinsurgence.tileentity;

import net.minecraft.world.World;

import thaumcraft.api.crafting.IInfusionStabiliser;
import thaumcraft.common.tiles.TileInfusionMatrix;
import thaumcraft.common.tiles.TileNodeStabilizer;

public class TileEntityStabilizerTesting extends TileNodeStabilizer implements IInfusionStabiliser {

    public TileInfusionMatrix matrix = null;
    public Boolean redstoneState;

    @Override
    public void updateEntity() {

        // these two if statements handle setting the TE variables the intercepter deals with.
        if (matrix == null) setMatrix();

        /*
         * this method handles literally everything this does, I moved it to it's own method so that I could make it not
         * do anything if the matrix didn't exist to save idle performance
         */
        if (matrix != null) {
            matrixIntercepterStuff();
        }
    }

    public void setMatrix() {
        try {
            if (worldObj.getTileEntity(this.xCoord + 1, this.yCoord + 3, this.zCoord) instanceof TileInfusionMatrix) {
                matrix = (TileInfusionMatrix) worldObj.getTileEntity(this.xCoord + 1, this.yCoord + 3, this.zCoord);
            } else if (worldObj
                    .getTileEntity(this.xCoord - 1, this.yCoord + 3, this.zCoord) instanceof TileInfusionMatrix) {
                        matrix = (TileInfusionMatrix) worldObj
                                .getTileEntity(this.xCoord - 1, this.yCoord + 3, this.zCoord);
                    } else
                if (worldObj
                        .getTileEntity(this.xCoord, this.yCoord + 3, this.zCoord + 1) instanceof TileInfusionMatrix) {
                            matrix = (TileInfusionMatrix) worldObj
                                    .getTileEntity(this.xCoord, this.yCoord + 3, this.zCoord + 1);
                        } else
                    if (worldObj.getTileEntity(
                            this.xCoord,
                            this.yCoord + 3,
                            this.zCoord - 1) instanceof TileInfusionMatrix) {
                                matrix = (TileInfusionMatrix) worldObj
                                        .getTileEntity(this.xCoord, this.yCoord + 3, this.zCoord - 1);
                            }
        } catch (Exception ignored) {}
    }

    public void matrixIntercepterStuff() {}

    public void updateRedstone(boolean state) {
        if (this.redstoneState != null && state && !this.redstoneState) {
            this.tryToStabilize();
        }
        this.redstoneState = state;
    }

    private void tryToStabilize() {
        if (this.matrix != null) {}
    }

    @Override
    public boolean canStabaliseInfusion(World var1, int var2, int var3, int var4) {
        return true;
    }
}

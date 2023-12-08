package thaumicinsurgence.main.modules.planar_artifice.core.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumicinsurgence.main.modules.planar_artifice.utils.TabPlanarArtifice;

public class BlockAlkimiumAdamantAlchFurnace extends BlockAlkimiumAlchFurnace {

    public BlockAlkimiumAdamantAlchFurnace() {
        this.setCreativeTab(TabPlanarArtifice.tabPlanarArtifice);
        this.setHardness(2);
        this.setResistance(3);
        this.setBlockName("alkimium_smeltery_adaminite");
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        icons[0] = ir.registerIcon("planarartifice:furnaces/alkimium_smeltery_adaminite_side");
        icons[1] = ir.registerIcon("planarartifice:furnaces/alkimium_smeltery_top");
        icons[2] = ir.registerIcon("planarartifice:furnaces/alkimium_smeltery_top");
        icons[3] = ir.registerIcon("planarartifice:furnaces/alkimium_smeltery_adaminite_front");
        icons[4] = ir.registerIcon("planarartifice:furnaces/alkimium_smeltery_adaminite_front_on");
        icons[5] = ir.registerIcon("planarartifice:adaminite_block");
    }
}

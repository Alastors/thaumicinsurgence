package thaumicinsurgence.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import thaumicinsurgence.main.utils.TabThaumicInsurgence;
import thaumicinsurgence.main.utils.VersionInfo;

public class BlockArcaneMarble extends Block {

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockArcaneMarble() {
        super(Material.rock);
        this.setCreativeTab(TabThaumicInsurgence.tabThaumicInsurgence);
        this.setBlockName("arcaneMarble");
        this.setHardness(10f);
        this.setResistance(1.5f);
        this.setHarvestLevel("pickaxe", 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {

        return icons[0];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        icons = new IIcon[1];
        icons[0] = register.registerIcon(VersionInfo.ModID + ":arcane_marble");
    }
}

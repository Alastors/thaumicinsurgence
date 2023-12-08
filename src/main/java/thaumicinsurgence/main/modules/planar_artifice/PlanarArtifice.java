package thaumicinsurgence.main.modules.planar_artifice;

import net.minecraft.item.EnumRarity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.EnumHelper;

import thaumicinsurgence.main.modules.ModuleConfig;
import thaumicinsurgence.main.modules.planar_artifice.utils.PlanarBlocks;
import thaumicinsurgence.main.modules.planar_artifice.utils.PlanarItems;
import thaumicinsurgence.main.utils.VersionInfo;
import thaumicinsurgence.main.utils.compat.IModHelper;

public class PlanarArtifice implements IModHelper {

    public static EnumRarity planarGreen = EnumHelper
            .addRarity(VersionInfo.ModID, EnumChatFormatting.GREEN, VersionInfo.ModName);

    public void preInit() {
        if (ModuleConfig.planarArtificeActive) {
            PlanarBlocks.setup();
            PlanarItems.setup();
        }
    }

    public void init() {}

    public void postInit() {
        // TBRecipes.setup();
        // TBThaumonomicon.setup();
    }

}

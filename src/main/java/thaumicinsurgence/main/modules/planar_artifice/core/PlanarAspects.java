package thaumicinsurgence.main.modules.planar_artifice.core;

import net.minecraft.util.ResourceLocation;

import thaumcraft.api.aspects.Aspect;
import thaumicinsurgence.main.modules.planar_artifice.utils.PlanarArtificeAPI;

public class PlanarAspects {

    public static Aspect ALCHEMY = null;

    public static void addPlanar_Aspects() {

        // 3c04b5 - original blue
        ALCHEMY = new Aspect(
                "alkimia",
                0x00af9d,
                new Aspect[] { Aspect.MAGIC, Aspect.WATER },
                new ResourceLocation("planarartifice", "textures/aspects/bubbling-flask.png"),
                1);
        PlanarArtificeAPI.planarAspectAlchemy = ALCHEMY;
    }
}

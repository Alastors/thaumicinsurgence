package thaumicinsurgence.main.utils.compat;

import java.util.ArrayList;
import java.util.List;

import thaumicinsurgence.main.modules.arcana.Arcana;
import thaumicinsurgence.main.modules.planar_artifice.PlanarArtifice;

public class ModHelperManager {

    private static List<IModHelper> helpers;

    public static void preInit() {
        setupHelpers();

        for (IModHelper helper : helpers) {
            helper.preInit();
        }
    }

    public static void init() {
        for (IModHelper helper : helpers) {
            helper.init();
        }
    }

    public static void postInit() {
        for (IModHelper helper : helpers) {
            helper.postInit();
        }
    }

    private static void setupHelpers() {
        helpers = new ArrayList<>();
        helpers.add(new ThaumcraftHelper());
        helpers.add(new PlanarArtifice());
        helpers.add(new Arcana());
    }
}

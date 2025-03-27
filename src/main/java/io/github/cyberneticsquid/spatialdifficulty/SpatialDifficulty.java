package io.github.cyberneticsquid.spatialdifficulty;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(SpatialDifficulty.MODID)
public class SpatialDifficulty {
    // Define mod ID in a common place.
    public static final String MODID = "spatialdifficulty";

    public SpatialDifficulty() {
        // Register ForgeConfigSpec so that Forge can create and load the config file for us.
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}

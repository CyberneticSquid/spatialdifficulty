package io.github.cyberneticsquid.spatialdifficulty;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;


@Mod.EventBusSubscriber(modid = SpatialDifficulty.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.DoubleValue NUMERATOR = BUILDER.comment("Used in the local difficulty formula. Moderate impact.")
            .defineInRange("numerator", 5.0, 1.0, 10.0);

    private static final ForgeConfigSpec.DoubleValue EXPONENT = BUILDER.comment("Used in the local difficulty formula. Major impact.")
            .defineInRange("exponent", 3.0, 1.0, 10.0);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static double numerator;
    public static double exponent;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        numerator = NUMERATOR.get();
        exponent = EXPONENT.get();
    }
}

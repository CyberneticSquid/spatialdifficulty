package io.github.cyberneticsquid.spatialdifficulty.mixin;

import io.github.cyberneticsquid.spatialdifficulty.Config;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DifficultyInstance.class)
public abstract class DifficultyInstanceMixin {

    // Minimum and maximum local difficulty values for each global difficulty setting.
    @Unique
    private final static double[] spatialdifficulty$min = {0.0, 0.75, 1.5, 2.25};
    @Unique
    private final static double[] spatialdifficulty$max = {0.0, 1.5, 4.0, 6.75};

    @Inject(method = "calculateDifficulty", at = @At("HEAD"), cancellable = true)
    private void onCalculateDifficulty(Difficulty d, long x, long z, float y, CallbackInfoReturnable<Float> cir) {
        if (d == Difficulty.PEACEFUL) { cir.setReturnValue(0F); return; }
        double dMin = spatialdifficulty$min[d.getId()];
        double dMax = spatialdifficulty$max[d.getId()];
        // See README for neat formula.
        cir.setReturnValue((float)
                Math.max(dMin, dMax - ((dMax - dMin) * (Config.numerator / Math.pow(Mth.length(x, z), 1.0 / Config.exponent))))
        );
    }
}

package io.github.cyberneticsquid.spatialdifficulty.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.storage.WritableLevelData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Level.class)
public abstract class LevelMixin implements LevelAccessor {
    @Shadow @Final protected WritableLevelData levelData;

    @Inject(method = "getCurrentDifficultyAt", at = @At("HEAD"), cancellable = true)
    public void onGetCurrentDifficultyAt(BlockPos pos, CallbackInfoReturnable<DifficultyInstance> cir) {
        int distX = Mth.abs(pos.getX() - this.levelData.getXSpawn());
        int distZ = Mth.abs(pos.getZ() - this.levelData.getZSpawn());
        cir.setReturnValue(new DifficultyInstance(this.getDifficulty(), distX, distZ, 0F));
    }
}

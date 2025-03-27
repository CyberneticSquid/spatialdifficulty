package io.github.cyberneticsquid.spatialdifficulty.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.storage.LevelData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldGenRegion.class)
public class WorldGenRegionMixin {
    @Shadow @Final private ServerLevel level;

    @Shadow @Final private LevelData levelData;

    @Inject(method = "getCurrentDifficultyAt", at = @At("HEAD"), cancellable = true)
    public void OnGetCurrentDifficultyAt(BlockPos pos, CallbackInfoReturnable<DifficultyInstance> cir) {
        int distX = Mth.abs(pos.getX() - this.levelData.getXSpawn());
        int distZ = Mth.abs(pos.getZ() - this.levelData.getZSpawn());
        cir.setReturnValue(new DifficultyInstance(this.level.getDifficulty(), distX, distZ, 0F));
    }
}

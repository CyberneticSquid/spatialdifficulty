package io.github.cyberneticsquid.spatialdifficulty.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(DebugScreenOverlay.class)
public abstract class DebugScreenOverlayMixin {
    @Shadow @Final private Minecraft minecraft;

    @Shadow protected abstract Level getLevel();

    // IDEA complains about the next two lines, but I'm pretty sure they work.
    @ModifyVariable(method = "getGameInformation", at = @At("STORE"))
    protected DifficultyInstance onGetGameInformation(DifficultyInstance d) {
        BlockPos pos = this.minecraft.getCameraEntity().blockPosition();
        Level level = this.getLevel();
        int distX = Mth.abs(pos.getX() - level.getLevelData().getXSpawn());
        int distZ = Mth.abs(pos.getZ() - level.getLevelData().getZSpawn());
        return new DifficultyInstance(level.getDifficulty(), distX, distZ, 0F);
    }
}

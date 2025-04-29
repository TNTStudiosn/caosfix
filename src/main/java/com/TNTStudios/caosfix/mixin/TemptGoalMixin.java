package com.TNTStudios.caosfix.mixin;

import net.minecraft.world.entity.ai.goal.TemptGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TemptGoal.class)
public class TemptGoalMixin {

    @Shadow private int calmDown;

    @Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
    public void patchCanUse(CallbackInfoReturnable<Boolean> cir) {
        if (this.calmDown > 0) {
            this.calmDown--;
            cir.setReturnValue(false);
        } else {
            this.calmDown = 10; // Evaluar solo una vez cada 10 ticks
        }
    }
}

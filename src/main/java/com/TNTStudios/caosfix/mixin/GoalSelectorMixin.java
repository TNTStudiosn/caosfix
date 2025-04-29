package com.TNTStudios.caosfix.mixin;

import net.minecraft.world.entity.ai.goal.GoalSelector;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GoalSelector.class)
public class GoalSelectorMixin {
    private int tickCounter = 0;

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void patchGoalTick(CallbackInfo ci) {
        tickCounter++;
        if (tickCounter % 5 != 0) { // Solo procesar AI cada 5 ticks
            ci.cancel();
        }
    }
}

package com.asatsuki256.betterdot.mixin;

import com.asatsuki256.betterdot.common.IDamageSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEffect.class)
public class MobEffectMixin {

    private static final DamageSource POISON = (DamageSource) ((IDamageSource) (new DamageSource("magic")).bypassArmor().setMagic()).betterdot_setDoT(true);

    @Inject(method = "applyEffectTick(Lnet/minecraft/world/entity/LivingEntity;I)V", at = @At("HEAD"), cancellable = true)
    public void betterdot_applyEffectTickInjectHead(LivingEntity living, int strength, CallbackInfo ci) {
        MobEffect mobEffect = (MobEffect) (Object) this;
        if (mobEffect == MobEffects.POISON) {
            if (living.getHealth() > 1.0F) {
                living.hurt(POISON, 1.0F);
            }
            ci.cancel();
        }
    }

}

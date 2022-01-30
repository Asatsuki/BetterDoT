package com.asatsuki256.betterdot.mixin;

import com.asatsuki256.betterdot.common.BetterDotHelper;
import com.asatsuki256.betterdot.common.DotHandler;
import com.asatsuki256.betterdot.common.DotHandlerCapability;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Unique
    private int betterdot_prevInvulnerableTime;

    @Inject(method = "hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", at = @At("HEAD"), cancellable = true)
    private void betterdot_hurtHeadInject(DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = ((LivingEntity) (Object) this);
        betterdot_prevInvulnerableTime = living.invulnerableTime;

        if (BetterDotHelper.isDotDamage(damageSource)) {
            DotHandler dotHandler = (DotHandler) ((LivingEntity) (Object) this).getCapability(DotHandlerCapability.DOT_HANDLER_CAPABILITY).orElse(null);
            int specificITime = dotHandler != null && dotHandler.iTicks.containsKey(damageSource.getMsgId()) ? dotHandler.iTicks.get(damageSource.getMsgId()) : 0;
            if (specificITime > 10) {
                cir.cancel(); // DamageSource別無敵時間が10より大きいか、このフレームにダメージを受けていれば、攻撃キャンセル
                return;
            } else {
                // DamageSource別無敵時間が10未満ならば、他のダメージの無敵時間に関わらず攻撃を通す
                // 他のダメージの無敵時間を一旦0にする(あとでbetterdot_prevInvulnerableTimeから復帰)
                living.invulnerableTime = 0;
            }
        }

    }

    @Inject(method = "hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", at = @At("TAIL"))
    private void betterdot_hurtTailInject(DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (BetterDotHelper.isDotDamage(damageSource)) {
            ((LivingEntity) (Object) this).invulnerableTime = betterdot_prevInvulnerableTime; // 無敵時間をもとに戻す
            DotHandler dotHandler = (DotHandler) ((LivingEntity) (Object) this).getCapability(DotHandlerCapability.DOT_HANDLER_CAPABILITY).orElse(null);
            if (dotHandler != null) {
                dotHandler.iTicks.put(damageSource.getMsgId(), ((LivingEntity) (Object) this).invulnerableDuration); // DamageSource固有の無敵時間を記録
            }
        }
    }

}

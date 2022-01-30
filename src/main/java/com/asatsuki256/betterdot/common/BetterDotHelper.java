package com.asatsuki256.betterdot.common;

import net.minecraft.world.damagesource.DamageSource;

public class BetterDotHelper {

    public static boolean isDotDamage(DamageSource damageSource) {
        return ((IDamageSource) damageSource).betterdot_isDoT() || BetterDotOptions.COMMON.dotDamageSource.get().contains(damageSource.getMsgId());
    }

}

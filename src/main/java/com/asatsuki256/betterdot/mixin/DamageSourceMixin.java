package com.asatsuki256.betterdot.mixin;

import com.asatsuki256.betterdot.common.IDamageSource;
import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(DamageSource.class)
public class DamageSourceMixin implements IDamageSource {

    @Unique
    private boolean betterdot_dot;

    @Override
    public boolean betterdot_isDoT() {
        return betterdot_dot;
    }

    @Override
    public IDamageSource betterdot_setDoT(boolean isDoT) {
        betterdot_dot = isDoT;
        return this;
    }
}

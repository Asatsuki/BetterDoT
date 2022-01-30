package com.asatsuki256.betterdot.common;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.List;

public class BetterDotOptions {

    public static class Common {

        //private static final String dotDamageSourceDefault = "inFire,onFire,lava,hotFloor,magic,cramming,drown,starve,cactus,magic,wither,dragonBreath,dryout,sweetBerryBush,freeze";
        private static final List<String> dotDamageSourceDefault = Arrays.asList(
                "inFire", "onFire", "lava", "hotFloor",
                "cramming", "drown", "starve", "cactus", "wither",
                "dragonBreath", "dryout", "sweetBerryBush", "freeze"
        );
        public final ForgeConfigSpec.ConfigValue<List<String>> dotDamageSource;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("general");
            this.dotDamageSource = builder.define("dotDamageSource", dotDamageSourceDefault);
            builder.pop();
        }

    }

    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {
        Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON = commonSpecPair.getLeft();
        COMMON_SPEC = commonSpecPair.getRight();
    }

}

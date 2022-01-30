package com.asatsuki256.betterdot.common;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("betterdot")
public class BetterDotMod {
    public static final String MOD_ID = "betterdot";
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger("BetterDoT");

    public BetterDotMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BetterDotOptions.COMMON_SPEC);
    }
}

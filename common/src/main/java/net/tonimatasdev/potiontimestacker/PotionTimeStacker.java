package net.tonimatasdev.potiontimestacker;

import com.mojang.logging.LogUtils;

import java.util.logging.Logger;

public class PotionTimeStacker {
    public static final String MOD_ID = "potiontimestacker";

    public static void init() {
        LogUtils.getLogger().info("PotionTimeStacker has been enabled.");
    }
}

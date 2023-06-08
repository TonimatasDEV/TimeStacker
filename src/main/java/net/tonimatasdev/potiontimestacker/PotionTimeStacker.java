package net.tonimatasdev.potiontimestacker;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(PotionTimeStacker.MOD_ID)
public class PotionTimeStacker {
    public static final String MOD_ID = "potiontimestacker";

    public PotionTimeStacker() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}

package net.tonimatasdev.potiontimestacker;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(PotionTimeStacker.MODID)
public class PotionTimeStacker {
    public static final String MODID = "potiontimestacker";

    public PotionTimeStacker() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}

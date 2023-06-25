package net.tonimatasdev.potiontimestacker.forge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(PotionTimeStacker.MOD_ID)
public class PotionTimeStacker {
    public static final String MOD_ID = "net/tonimatasdev/potiontimestacker";

    public PotionTimeStacker() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}

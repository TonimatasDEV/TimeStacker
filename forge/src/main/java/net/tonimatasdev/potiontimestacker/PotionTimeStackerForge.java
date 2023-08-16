package net.tonimatasdev.potiontimestacker;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("potiontimestacker")
public class PotionTimeStackerForge {

    public PotionTimeStackerForge() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}

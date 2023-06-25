package net.tonimatasdev.potiontimestacker.forge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.tonimatasdev.potiontimestacker.PotionTimeStacker;

@Mod(PotionTimeStacker.MOD_ID)
public class PotionTimeStackerForge {

    public PotionTimeStackerForge() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}

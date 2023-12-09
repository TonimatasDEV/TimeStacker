package dev.tonimatas.timestacker.forge;

import dev.tonimatas.timestacker.TimeStacker;
import net.minecraftforge.fml.common.Mod;

@Mod(TimeStacker.MOD_ID)
public class TimeStackerForge {
    public TimeStackerForge() {
        TimeStacker.init();
    }
}
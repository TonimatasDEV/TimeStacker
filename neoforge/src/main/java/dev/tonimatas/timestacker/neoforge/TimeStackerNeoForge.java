package dev.tonimatas.timestacker.neoforge;

import dev.tonimatas.timestacker.TimeStacker;
import net.neoforged.fml.common.Mod;

@Mod(TimeStacker.MOD_ID)
public class TimeStackerNeoForge {
    public TimeStackerNeoForge() {
        TimeStacker.init();
    }
}
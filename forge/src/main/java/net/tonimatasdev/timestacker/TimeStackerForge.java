package net.tonimatasdev.timestacker;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("timestacker")
public class TimeStackerForge {

    public TimeStackerForge() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}

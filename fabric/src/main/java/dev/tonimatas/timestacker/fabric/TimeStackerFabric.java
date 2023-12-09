package dev.tonimatas.timestacker.fabric;

import dev.tonimatas.timestacker.TimeStacker;
import net.fabricmc.api.ModInitializer;

public class TimeStackerFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        TimeStacker.init();
    }
}
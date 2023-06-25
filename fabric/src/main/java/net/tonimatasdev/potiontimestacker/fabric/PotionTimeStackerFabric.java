package net.tonimatasdev.potiontimestacker.fabric;

import net.fabricmc.api.ModInitializer;
import net.tonimatasdev.potiontimestacker.PotionTimeStacker;

public class PotionTimeStackerFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        PotionTimeStacker.init();
    }
}
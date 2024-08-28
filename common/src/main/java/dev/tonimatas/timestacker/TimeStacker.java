package dev.tonimatas.timestacker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TimeStacker {
	public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	public static final String MOD_ID = "timestacker";
	public static Config config;

	public static void init() {
		File configFolder = new File("config");

		if (!configFolder.exists()) {
			configFolder.mkdirs();
		}
		
		File configFile = new File(configFolder, "time-stacker.json");
		
		if (!configFile.exists()) {
			writeConfig(configFile);
		} else {
			loadConfig(configFile);
		}
		
		LogUtils.getLogger().info("Time Stacker has been initialized successfully");
	}

	public static boolean applyEffect(LivingEntity livingEntity, MobEffectInstance mobEffectInstance, Entity entity) {
		MobEffectInstance mobEffectInstance1 = livingEntity.getEffect(mobEffectInstance.getEffect());
		
		boolean isBlacklisted = !config.getBlacklist().contains(mobEffectInstance.getEffect().getRegisteredName());
		
		if (livingEntity.hasEffect(mobEffectInstance.getEffect()) && mobEffectInstance1 != null && isBlacklisted) {
			
			int time = mobEffectInstance.getDuration() + mobEffectInstance1.getDuration();
			if (time > getMaxTimeInTicks()) {
				time = getMaxTimeInTicks();
			}

			return livingEntity.addEffect(new MobEffectInstance(mobEffectInstance.getEffect(), time, mobEffectInstance.getAmplifier(), mobEffectInstance.isAmbient(), mobEffectInstance.isVisible(), mobEffectInstance.showIcon()), entity);
		} else {
			return livingEntity.addEffect(mobEffectInstance);
		}
	}
	
	public static void writeConfig(File file) {
		config = new Config();
		config.setComment("The time is in minutes. Change timeUnit to s for seconds and h for hours. You can use /effect ... to see the modid/effectname and blacklist it.");
		config.setTimeUnit("m");
		config.setMaxTime(16);
		config.setBlacklist(List.of("modid:effectname"));

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		try (FileWriter writer = new FileWriter(file)) {
			gson.toJson(config, writer);
		} catch (IOException e) {
			LogUtils.getLogger().error("Failed writing the config of Time Stacker", e);
		}
	}
	
	public static void loadConfig(File file) {
		try (FileReader reader = new FileReader(file)) {
			config = gson.fromJson(reader, Config.class);
		} catch (JsonSyntaxException | IOException e) {
			LogUtils.getLogger().error("Failed to load config of Time Stacker", e);
		}
	}
	
	public static int getMaxTimeInTicks() {
        return switch (config.getTimeUnit()) {
            case "h" -> config.getMaxTime() * 60 * 60 * 20;
            case "m" -> config.getMaxTime() * 60 * 20;
            case "s" -> config.getMaxTime() * 20;
            default -> {
                LogUtils.getLogger().error("[Time Stacker] Unsupported time unit {}", config.getTimeUnit());
                yield 0;
            }
        };
	}
}

package dev.tonimatas.timestacker;

import com.mojang.logging.LogUtils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class TimeStacker {
	public static final String MOD_ID = "timestacker";

	public static void init() {
		LogUtils.getLogger().info("Time Stacker has been initialized successfully");
	}

	public static boolean applyEffect(LivingEntity livingEntity, MobEffectInstance mobEffectInstance, Entity entity) {
		MobEffectInstance mobEffectInstance1 = livingEntity.getEffect(mobEffectInstance.getEffect());

		if (livingEntity.hasEffect(mobEffectInstance.getEffect()) && mobEffectInstance1 != null) {
			return livingEntity.addEffect(new MobEffectInstance(mobEffectInstance.getEffect(), mobEffectInstance.getDuration() + mobEffectInstance1.getDuration(), mobEffectInstance.getAmplifier(), mobEffectInstance.isAmbient(), mobEffectInstance.isVisible(), mobEffectInstance.showIcon()), entity);
		} else {
			return livingEntity.addEffect(mobEffectInstance);
		}
	}
}

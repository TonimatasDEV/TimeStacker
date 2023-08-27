package net.tonimatasdev.timestacker;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;

public class TimeStackerFabric implements ModInitializer {
    @Override
    public void onInitialize() {
    }

    public static boolean applyEffect(LivingEntity livingEntity, StatusEffectInstance statusEffectInstance, Entity entity) {
        StatusEffectInstance statusEffectInstance1 = livingEntity.getStatusEffect(statusEffectInstance.getEffectType());

        if (livingEntity.hasStatusEffect(statusEffectInstance.getEffectType()) && statusEffectInstance1 != null) {
            return livingEntity.addStatusEffect(new StatusEffectInstance(statusEffectInstance.getEffectType(), statusEffectInstance.getDuration() + statusEffectInstance1.getDuration(), statusEffectInstance.getAmplifier(), statusEffectInstance.isAmbient(), statusEffectInstance.shouldShowParticles(), statusEffectInstance.shouldShowIcon()), entity);
        } else {
            return livingEntity.addStatusEffect(statusEffectInstance);
        }
    }
}
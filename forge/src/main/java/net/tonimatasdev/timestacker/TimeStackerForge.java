package net.tonimatasdev.timestacker;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("timestacker")
public class TimeStackerForge {

    public TimeStackerForge() {
        MinecraftForge.EVENT_BUS.register(this);
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

package dev.tonimatas.timestacker.mixins;

import dev.tonimatas.timestacker.TimeStacker;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AreaEffectCloud.class)
public class AreaEffectCloudMixin {
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z"))
    public boolean finishUsing(LivingEntity livingEntity, MobEffectInstance mobEffectInstance, Entity entity) {
        return TimeStacker.applyEffect(livingEntity, mobEffectInstance, entity);
    }
}

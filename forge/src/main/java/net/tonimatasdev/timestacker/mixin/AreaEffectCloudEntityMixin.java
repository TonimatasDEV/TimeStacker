package net.tonimatasdev.timestacker.mixin;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.tonimatasdev.timestacker.TimeStackerForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AreaEffectCloud.class)
public class AreaEffectCloudEntityMixin {
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z"))
    public boolean finishUsing(LivingEntity livingEntity, MobEffectInstance mobEffectInstance, Entity entity) {
        return TimeStackerForge.applyEffect(livingEntity, mobEffectInstance, entity);
    }
}
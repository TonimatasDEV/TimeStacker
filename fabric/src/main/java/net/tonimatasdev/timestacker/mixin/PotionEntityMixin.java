package net.tonimatasdev.timestacker.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.tonimatasdev.timestacker.TimeStackerFabric;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PotionEntity.class)
public class PotionEntityMixin {
    @Redirect(method = "applySplashPotion", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;Lnet/minecraft/entity/Entity;)Z"))
    public boolean finishUsing(LivingEntity livingEntity, StatusEffectInstance statusEffectInstance, Entity entity) {
        return TimeStackerFabric.applyEffect(livingEntity, statusEffectInstance, entity);
    }
}

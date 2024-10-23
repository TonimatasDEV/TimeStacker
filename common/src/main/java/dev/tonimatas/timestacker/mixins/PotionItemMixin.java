package dev.tonimatas.timestacker.mixins;

import dev.tonimatas.timestacker.TimeStacker;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.alchemy.PotionContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PotionContents.class)
public class PotionItemMixin {
    @Redirect(method = "method_62840", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z"))
    private static boolean finishUsing(LivingEntity livingEntity, MobEffectInstance mobEffectInstance) {
        return TimeStacker.applyEffect(livingEntity, mobEffectInstance, null);
    }
}

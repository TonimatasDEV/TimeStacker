package net.tonimatasdev.timestacker.mixin;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.PotionItem;
import net.tonimatasdev.timestacker.TimeStackerForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PotionItem.class)
public class PotionItemMixin {
    @Redirect(method = "finishUsingItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z"))
    public boolean finishUsing(LivingEntity livingEntity, MobEffectInstance mobEffectInstance) {
        return TimeStackerForge.applyEffect(livingEntity, mobEffectInstance, null);
    }
}

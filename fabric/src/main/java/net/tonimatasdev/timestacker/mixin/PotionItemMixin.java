package net.tonimatasdev.timestacker.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.PotionItem;
import net.tonimatasdev.timestacker.TimeStackerFabric;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PotionItem.class)
public class PotionItemMixin {
    @Redirect(method = "finishUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;)Z"))
    public boolean finishUsing(LivingEntity livingEntity, StatusEffectInstance statusEffectInstance) {
        return TimeStackerFabric.applyEffect(livingEntity, statusEffectInstance, null);
    }
}

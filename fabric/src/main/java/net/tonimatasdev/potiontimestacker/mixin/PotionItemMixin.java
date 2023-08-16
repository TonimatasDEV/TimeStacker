package net.tonimatasdev.potiontimestacker.mixin;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.PotionUtil;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PotionItem.class)
public class PotionItemMixin {

    /**
     * @author TonimatasDEV
     * @reason Stack the time of the same potions.
     */
    @Overwrite
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity)user : null;
        if (playerEntity instanceof ServerPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger((ServerPlayerEntity)playerEntity, stack);
        }

        if (!world.isClient) {
            for (StatusEffectInstance statusEffectInstance : PotionUtil.getPotionEffects(stack)) {
                if (statusEffectInstance.getEffectType().isInstant()) {
                    statusEffectInstance.getEffectType().applyInstantEffect(playerEntity, playerEntity, user, statusEffectInstance.getAmplifier(), 1.0D);
                } else {
                    if (user.hasStatusEffect(statusEffectInstance.getEffectType())) {
                        StatusEffectInstance statusEffectInstance1 = user.getStatusEffect(statusEffectInstance.getEffectType());
                        assert statusEffectInstance1 != null;

                        int duration = statusEffectInstance.getDuration() + statusEffectInstance1.getDuration();
                        user.addStatusEffect(new StatusEffectInstance(statusEffectInstance1.getEffectType(), duration, statusEffectInstance1.getAmplifier(), statusEffectInstance1.isAmbient(), statusEffectInstance1.isAmbient(), statusEffectInstance1.shouldShowIcon()));
                    } else {
                        user.addStatusEffect(new StatusEffectInstance(statusEffectInstance));
                    }
                }
            }
        }

        if (playerEntity != null) {
            playerEntity.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
            if (!playerEntity.getAbilities().creativeMode) {
                stack.decrement(1);
            }
        }

        if (playerEntity == null || !playerEntity.getAbilities().creativeMode) {
            if (stack.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if (playerEntity != null) {
                playerEntity.getInventory().insertStack(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        user.emitGameEvent(GameEvent.DRINK);
        return stack;
    }
}

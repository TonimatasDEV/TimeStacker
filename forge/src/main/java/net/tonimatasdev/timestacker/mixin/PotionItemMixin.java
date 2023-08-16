package net.tonimatasdev.timestacker.mixin;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PotionItem.class)
public class PotionItemMixin {

    /**
     * @author TonimatasDEV
     * @reason Stack the time of the same potions.
     */
    @Overwrite
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        Player player = livingEntity instanceof Player ? (Player) livingEntity : null;
        if (player instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, itemStack);
        }

        if (!level.isClientSide) {
            for (MobEffectInstance mobEffectInstance : PotionUtils.getMobEffects(itemStack)) {
                if (mobEffectInstance.getEffect().isInstantenous()) {
                    mobEffectInstance.getEffect().applyInstantenousEffect(player, player, livingEntity, mobEffectInstance.getAmplifier(), 1.0D);
                } else {
                    if (livingEntity.hasEffect(mobEffectInstance.getEffect())) {
                        MobEffectInstance mobEffectInstance1 = livingEntity.getEffect(mobEffectInstance.getEffect());
                        assert mobEffectInstance1 != null;

                        int duration = mobEffectInstance.getDuration() + mobEffectInstance1.getDuration();
                        livingEntity.addEffect(new MobEffectInstance(mobEffectInstance1.getEffect(), duration, mobEffectInstance1.getAmplifier(), mobEffectInstance1.isAmbient(), mobEffectInstance1.isVisible(), mobEffectInstance1.showIcon()));
                    } else {
                        livingEntity.addEffect(new MobEffectInstance(mobEffectInstance));
                    }
                }
            }
        }

        if (player != null) {
            player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
            if (!player.getAbilities().instabuild) {
                itemStack.shrink(1);
            }
        }

        if (player == null || !player.getAbilities().instabuild) {
            if (itemStack.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if (player != null) {
                player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        livingEntity.gameEvent(GameEvent.DRINK);
        return itemStack;
    }
}

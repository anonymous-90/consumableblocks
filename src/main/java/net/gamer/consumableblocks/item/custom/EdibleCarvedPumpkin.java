package net.gamer.consumableblocks.item.custom;

import net.gamer.consumableblocks.DataAttachments.Abilities;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class EdibleCarvedPumpkin extends Item {
    public EdibleCarvedPumpkin(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(@NonNull ItemStack itemStack) {
        return true;
    }

    @Override
    public void appendHoverText(@NonNull ItemStack itemStack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, Consumer<Component> builder, @NonNull TooltipFlag tooltipFlag) {
       builder.accept(Component.translatable("consumableblocks.ediblecarved_pumpkin.tooltip"));
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }

    @Override
    public ItemStack finishUsingItem(@NonNull ItemStack itemStack, @NonNull Level level, @NonNull LivingEntity entity) {
        if(entity instanceof  ServerPlayer player){
            Abilities.AbilityData ability = Abilities.get(player);
            if(!ability.has(Abilities.HasCarvedPumpkin) && ability.getCurrentCount() < 3){
                ability.Add(Abilities.HasCarvedPumpkin,true);
                ability.setCurrentCount(ability.getCurrentCount() +1);
                player.sendOverlayMessage(Component.literal("§6Hide Ability Acquired"));

            }else if (ability.has(Abilities.HasFurnace)){
                player.sendOverlayMessage(Component.literal("§cAlready haas ability "));

            }
        }
        return super.finishUsingItem(itemStack, level, entity);
    }

    @Override
    public void onCraftedBy(@NonNull ItemStack itemStack, @NonNull Player player) {
        Abilities.AbilityData ability = Abilities.get(player);
        if(ability.has(Abilities.CarvedPumpkinUnlocked)){
            return;
        }else {
            ability.Add(Abilities.CarvedPumpkinUnlocked,true);
            ability.Add(Abilities.CarvedPumpkinEnabled,true);
        }
        super.onCraftedBy(itemStack, player);
    }
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        Abilities.AbilityData ability = Abilities.get(player);
        if (ability.getCurrentCount() == 3){
            player.sendSystemMessage(Component.literal("slots full"));
            return InteractionResult.FAIL;
        }
        return super.use(level, player, hand);
    }
}

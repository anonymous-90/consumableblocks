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

public class EdibleObsidian extends Item {
    public EdibleObsidian(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(@NonNull ItemStack itemStack) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
       builder.accept(Component.translatable("consumableblocks.edibleobsidian.tooltip"));
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        if(entity instanceof ServerPlayer player){
            Abilities.AbilityData ability = Abilities.get(player);
            if(!ability.has(Abilities.HasObsidian) && ability.getCurrentCount() < 3){
                ability.Add(Abilities.HasObsidian,true);
                ability.setCurrentCount(ability.getCurrentCount() +1 );
                // add modifier/ability when you have it
                player.sendOverlayMessage(Component.literal("§6 Ability Acquired"));

            }else if(ability.has(Abilities.HasObsidian)) {
                player.sendOverlayMessage(Component.literal("§calready have modifier"));

            }
        }
        return super.finishUsingItem(itemStack, level, entity);
    }

    @Override
    public void onCraftedBy(@NonNull ItemStack itemStack, @NonNull Player player) {
        Abilities.AbilityData ability = Abilities.get(player);
        if(ability.has(Abilities.ObsidianUnlocked)){
            return;
        }else {
            ability.Add(Abilities.ObsidianUnlocked,true);
            ability.Add(Abilities.ObsidianEnabled,true);
        }
        super.onCraftedBy(itemStack, player);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        Abilities.AbilityData ability = Abilities.get(player);
        if (ability.getCurrentCount() == 3){
            player.sendSystemMessage(Component.literal("slots full"));
            return InteractionResult.FAIL;
        }
        return super.use(level, player, hand);
    }
}

package net.gamer.consumableblocks.item.custom;

import net.gamer.consumableblocks.DataAttachments.Abilities;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
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
            if(!ability.has(Abilities.HasObsidian)){
                ability.Add(Abilities.HasObsidian,true);
                // add modifier/ability when you have it
                player.sendOverlayMessage(Component.literal("§6 Ability Acquired"));

            }else {
                player.sendOverlayMessage(Component.literal("§calready have modifier"));

            }
        }
        return super.finishUsingItem(itemStack, level, entity);
    }

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
}

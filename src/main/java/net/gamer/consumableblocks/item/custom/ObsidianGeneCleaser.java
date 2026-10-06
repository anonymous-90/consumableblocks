package net.gamer.consumableblocks.item.custom;
import net.gamer.consumableblocks.DataAttachments.Abilities;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class ObsidianGeneCleaser extends Item {
    public ObsidianGeneCleaser(Properties properties) {
        super(properties);
    }


    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        builder.accept(Component.translatable("consumableblocks.obsidiangenecleaser.tooltip"));
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        if(entity instanceof  ServerPlayer player ){
        Abilities.AbilityData ability = Abilities.get(entity);
        if(ability.has(Abilities.HasObsidian)){
            ability.Remove(Abilities.HasObsidian);
        }else {
            player.sendSystemMessage(Component.literal("you dont have the ability"));
        }

        }
        return super.finishUsingItem(itemStack, level, entity);
    }
}

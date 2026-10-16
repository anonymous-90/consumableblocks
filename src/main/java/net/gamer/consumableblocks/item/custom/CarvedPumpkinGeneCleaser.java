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

public class CarvedPumpkinGeneCleaser extends Item {
    public CarvedPumpkinGeneCleaser(Properties properties) {
        super(properties);
    }


    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        builder.accept(Component.translatable("consumableblocks.carved_pumpkin_genecleaser.tooltip"));
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        if(entity instanceof  ServerPlayer player ){
        Abilities.AbilityData ability = Abilities.get(entity);
        if(ability.has(Abilities.HasCarvedPumpkin)){
            ability.Remove(Abilities.HasCarvedPumpkin);
        }else {
            player.sendSystemMessage(Component.literal("you dont have the ability"));
        }
        if (ability.getCurrentCount() == 0){
                player.sendSystemMessage(Component.literal("no abilities to remove "));

            }
            if (ability.getCurrentCount() > 0){
                ability.decrementCurrentCount(1);

            }
        }
        return super.finishUsingItem(itemStack, level, entity);
    }
}

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
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class EdibleFurnace extends Item {
    public EdibleFurnace(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(@NonNull ItemStack itemStack) {
        return true;
    }

    @Override
    public void appendHoverText(@NonNull ItemStack itemStack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, Consumer<Component> builder, @NonNull TooltipFlag tooltipFlag) {
       builder.accept(Component.translatable("consumableblocks.ediblefurnace.tooltip"));
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }

    @Override
    public ItemStack finishUsingItem(@NonNull ItemStack itemStack, @NonNull Level level, @NonNull LivingEntity entity) {
        if(entity instanceof  ServerPlayer player){
            if(!Abilities.get(player).has(Abilities.HasFurnace)){
                Abilities.get(player).Add(Abilities.HasFurnace,true);
                player.sendOverlayMessage(Component.literal("§6Smelting Ability Acquired"));

            }else if (Abilities.get(player).has(Abilities.HasFurnace)){
                player.sendOverlayMessage(Component.literal("§cAlready have modifier"));

            }
        }
        return super.finishUsingItem(itemStack, level, entity);
    }

}

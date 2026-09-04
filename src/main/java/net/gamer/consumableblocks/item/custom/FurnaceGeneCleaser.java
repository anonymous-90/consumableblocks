package net.gamer.consumableblocks.item.custom;

import net.gamer.consumableblocks.ConsumableBlocks;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class FurnaceGeneCleaser extends Item {
    public FurnaceGeneCleaser(Properties properties) {
        super(properties);
    }


    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        builder.accept(Component.translatable("consumableblocks.furnacegenecleaser.tooltip"));
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        if(entity instanceof  ServerPlayer player){
            AdvancementHolder furnace = level.getServer().getAdvancements().get(Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"has_furnace"));
            if (furnace != null){
                AdvancementProgress progress = player.getAdvancements().getOrStartProgress(furnace);
                if(progress.isDone()){
                    for(String criteria: progress.getCompletedCriteria()) {
                        player.getAdvancements().revoke(furnace,criteria);
                    }
            }

            }
        }
        return super.finishUsingItem(itemStack, level, entity);
    }
}

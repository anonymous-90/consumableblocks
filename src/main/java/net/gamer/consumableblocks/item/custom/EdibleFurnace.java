package net.gamer.consumableblocks.item.custom;

import net.gamer.consumableblocks.ConsumableBlocks;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.numbers.StyledFormat;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import org.jspecify.annotations.NonNull;

import java.util.Objects;
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
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        if(entity instanceof  ServerPlayer player){
            AdvancementHolder furnace = level.getServer().getAdvancements().get(Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"has_furnace"));
            if(furnace != null){
                AdvancementProgress progress = player.getAdvancements().getOrStartProgress(furnace);
                if(!progress.isDone()){
                    for(String criteria: progress.getRemainingCriteria()){
                        player.getAdvancements().award(furnace,criteria);
                        player.sendOverlayMessage(Component.literal("§6Smelting Ability Acquired"));
                    }
                }else if(progress.isDone()){
                    player.sendOverlayMessage(Component.literal("§cAlready have modifier"));
                }
            }
        }
        return super.finishUsingItem(itemStack, level, entity);
    }
}

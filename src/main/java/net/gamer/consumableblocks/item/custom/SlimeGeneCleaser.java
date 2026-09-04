package net.gamer.consumableblocks.item.custom;

import net.gamer.consumableblocks.ConsumableBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class SlimeGeneCleaser extends Item {
    public SlimeGeneCleaser(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        if(entity instanceof ServerPlayer player){
            player.getAttributes().getInstance(Attributes.BOUNCINESS).removeModifier(Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"slime_eaten"));
        }
        return super.finishUsingItem(itemStack, level, entity);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        builder.accept(Component.translatable("consumableblocks.slimegenecleaser.tooltip"));
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}

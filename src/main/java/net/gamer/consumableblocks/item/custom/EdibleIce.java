package net.gamer.consumableblocks.item.custom;

import net.gamer.consumableblocks.ConsumableBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.Objects;
import java.util.function.Consumer;

public class EdibleIce extends Item {
    public EdibleIce(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(@NonNull ItemStack itemStack) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
       builder.accept(Component.translatable("consumableblocks.edibleice.tooltip"));
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        if(entity instanceof ServerPlayer player && player.level() instanceof ServerLevel serverLevel && player.getAdvancements().getOrStartProgress(Objects.requireNonNull(serverLevel.getServer().getAdvancements().get(Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID, "ice_enabled")))).isDone()){
            if(player.getAttributes().hasModifier(Attributes.MOVEMENT_SPEED,Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"ice_eaten"))){
                player.sendOverlayMessage(Component.literal("§calready have modifier"));
            }else {
                player.getAttribute(Attributes.MOVEMENT_SPEED).addPermanentModifier(new AttributeModifier(Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"ice_eaten"),0.2000000030, AttributeModifier.Operation.ADD_VALUE));
                player.sendOverlayMessage(Component.literal("§6Speed Ability Acquired"));

            }
        }
        return super.finishUsingItem(itemStack, level, entity);
    }
}

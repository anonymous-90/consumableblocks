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

public class EdibleSlime extends Item {
    public EdibleSlime(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(@NonNull ItemStack itemStack) {
        return true;
    }

    @Override
    public void appendHoverText(@NonNull ItemStack itemStack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
       builder.accept(Component.translatable("consumableblocks.edibleslime.tooltip"));
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }

    @Override
    public @NonNull ItemStack finishUsingItem(@NonNull ItemStack itemStack, @NonNull Level level, @NonNull LivingEntity entity) {
//        entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS,200,2,false,true));
        if (entity instanceof ServerPlayer player && player.level() instanceof ServerLevel serverLevel && player.getAdvancements().getOrStartProgress(Objects.requireNonNull(serverLevel.getServer().getAdvancements().get(Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID, "slime_enabled")))).isDone()){
//            player.sendOverlayMessage(Component.literal("hello world"));
            if (player.getAttributes().hasModifier(Attributes.BOUNCINESS,Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"slime_eaten"))){
                player.sendOverlayMessage(Component.literal("§calready have modifier"));

            }else{
                Objects.requireNonNull(player.getAttribute(Attributes.BOUNCINESS)).addPermanentModifier(new AttributeModifier(Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"slime_eaten"),10.0, AttributeModifier.Operation.ADD_VALUE));
                player.sendOverlayMessage(Component.literal("§6Bounciness ability Acquired"));
            }

        }
        return super.finishUsingItem(itemStack, level, entity);
    }
}

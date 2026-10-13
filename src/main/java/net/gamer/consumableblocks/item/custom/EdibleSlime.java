package net.gamer.consumableblocks.item.custom;
import net.gamer.consumableblocks.ConsumableBlocks;
import net.gamer.consumableblocks.DataAttachments.Abilities;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
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
        if (entity instanceof ServerPlayer player){
            Abilities.AbilityData ability = Abilities.get(player);
            if (ability.has(Abilities.HasSlime)){
                player.sendOverlayMessage(Component.literal("§calready have modifier"));

            }else if(!ability.has(Abilities.HasSlime) && ability.getCurrentCount() < 3){
                ability.Add(Abilities.HasSlime,true);
                Objects.requireNonNull(player.getAttribute(Attributes.BOUNCINESS)).addPermanentModifier(new AttributeModifier(Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"slime_eaten"),10.0, AttributeModifier.Operation.ADD_VALUE));
                ability.setCurrentCount(ability.getCurrentCount() +1);
                player.sendOverlayMessage(Component.literal("§6Bounciness ability Acquired"));
            }

        }
        return super.finishUsingItem(itemStack, level, entity);
    }
    public void onCraftedBy(@NonNull ItemStack itemStack, @NonNull Player player) {
        Abilities.AbilityData ability = Abilities.get(player);
        if(ability.has(Abilities.SlimeUnlocked)){
            return;
        }else {
            ability.Add(Abilities.SlimeUnlocked,true);
            ability.Add(Abilities.SlimeEnabled,true);
        }
        super.onCraftedBy(itemStack, player);
    }
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        Abilities.AbilityData ability = Abilities.get(player);
        if (ability.getCurrentCount() == 3){
            player.sendSystemMessage(Component.literal("slots full"));
            return InteractionResult.FAIL;
        }
        return super.use(level, player, hand);
    }
}

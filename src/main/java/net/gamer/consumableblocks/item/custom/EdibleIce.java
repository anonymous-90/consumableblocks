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
        if(entity instanceof ServerPlayer player){
            Abilities.AbilityData ability = Abilities.get(player);
            if(ability.has(Abilities.HasIce) ){
                player.sendOverlayMessage(Component.literal("§calready have modifier"));
            }else if(!ability.has(Abilities.HasIce) && ability.getCurrentCount() < 3){
                ability.Add(Abilities.HasIce,true);
                Objects.requireNonNull(player.getAttribute(Attributes.MOVEMENT_SPEED)).addPermanentModifier(new AttributeModifier(Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"ice_eaten"),0.2000000030, AttributeModifier.Operation.ADD_VALUE));
                ability.setCurrentCount(ability.getCurrentCount() +1);
                player.sendOverlayMessage(Component.literal("§6Speed Ability Acquired"));

            }
        }
        return super.finishUsingItem(itemStack, level, entity);
    }
    public void onCraftedBy(@NonNull ItemStack itemStack, @NonNull Player player) {
        Abilities.AbilityData ability = Abilities.get(player);
        if(ability.has(Abilities.IceUnlocked)){
            return;
        }else {
            ability.Add(Abilities.IceUnlocked,true);
            ability.Add(Abilities.IceEnabled,true);
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

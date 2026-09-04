package net.gamer.consumableblocks.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.gamer.consumableblocks.ConsumableBlocks;
import net.gamer.consumableblocks.fuelData.Fuel;
import net.gamer.consumableblocks.networking.packet.SmeltPayloadC2S;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;

import javax.swing.*;
import java.util.Objects;

// on the server
public class ServerBoundPackets {
    public static void handleSmeltPayload(SmeltPayloadC2S smeltPayloadC2S, ServerPlayNetworking.Context context) {
        if (context.player().level().recipeAccess().getRecipeFor(RecipeType.SMELTING,new SingleRecipeInput(context.player().getMainHandItem()),context.player().level()).isPresent() && Fuel.get(context.player()).hasFuelAttachment()){;
            var Item = context.player().level().recipeAccess().getRecipeFor(RecipeType.SMELTING,new SingleRecipeInput(context.player().getMainHandItem()),context.player().level()).get().value().assemble(new SingleRecipeInput(context.player().getMainHandItem())).copy();
            boolean HasFurnace = context.player().getAdvancements().getOrStartProgress(Objects.requireNonNull(Objects.requireNonNull(context.server()).getAdvancements().get(Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID, "has_furnace")))).isDone();
            boolean FurnaceEnabled = context.player().getAdvancements().getOrStartProgress(Objects.requireNonNull(Objects.requireNonNull(context.server()).getAdvancements().get(Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID, "furnace_enabled")))).isDone();
            boolean FurnaceUnlocked = context.player().getAdvancements().getOrStartProgress(Objects.requireNonNull(Objects.requireNonNull(context.server()).getAdvancements().get(Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID, "furnace_unlock")))).isDone();
                if(Fuel.get(context.player()).getCurrentFuel() > 0 && !context.player().isCrouching() && HasFurnace && FurnaceEnabled && FurnaceUnlocked){
                    context.player().addItem(Item);
                    context.player().getMainHandItem().shrink(1);
                    Fuel.get(context.player()).DecrementCurrentFuel(1);
                    context.player().sendOverlayMessage(Component.literal("§6Fuel:"+Fuel.get(context.player()).getCurrentFuel()));

                }
                if(Fuel.get(context.player()).getCurrentFuel() >0 && context.player().isCrouching() && HasFurnace && FurnaceEnabled && FurnaceUnlocked){
                    var ItemwholeStack = context.player().level().recipeAccess().getRecipeFor(RecipeType.SMELTING,new SingleRecipeInput(context.player().getMainHandItem()),context.player().level()).get().value().assemble(new SingleRecipeInput(context.player().getMainHandItem())).copyWithCount(context.player().getMainHandItem().getCount());
                    if(Fuel.get(context.player()).getCurrentFuel() >= context.player().getMainHandItem().count()){
                        Fuel.get(context.player()).DecrementCurrentFuel(context.player().getMainHandItem().count());
                        context.player().getMainHandItem().shrink(context.player().getMainHandItem().count());
                        context.player().addItem(ItemwholeStack);
                        context.player().sendOverlayMessage(Component.literal("§6Fuel:"+Fuel.get(context.player()).getCurrentFuel()));


                    }else if(Fuel.get(context.player()).getCurrentFuel() < context.player().getMainHandItem().count() && Fuel.get(context.player()).getCurrentFuel() > 0){
                        var ItemPartialStack = context.player().level().recipeAccess().getRecipeFor(RecipeType.SMELTING,new SingleRecipeInput(context.player().getMainHandItem()),context.player().level()).get().value().assemble(new SingleRecipeInput(context.player().getMainHandItem())).copyWithCount(Fuel.get(context.player()).getCurrentFuel());
                        context.player().getMainHandItem().shrink(Fuel.get(context.player()).getCurrentFuel());
                        context.player().addItem(ItemPartialStack);
                        Fuel.get(context.player()).DecrementCurrentFuel(Fuel.get(context.player()).getCurrentFuel());
                        context.player().sendOverlayMessage(Component.literal("§6Fuel:"+Fuel.get(context.player()).getCurrentFuel()));
                    }

                    }


                }
                if(Fuel.get(context.player()).getCurrentFuel() == 0){
                    context.player().sendOverlayMessage(Component.literal("§4§lNoFuel Eat Some Coal" + context.player().hasAttached(Fuel.Current_Fuel)));





        }
    }
}

package net.gamer.consumableblocks.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.gamer.consumableblocks.ConsumableBlocks;
import net.gamer.consumableblocks.DataAttachments.Abilities;
import net.gamer.consumableblocks.DataAttachments.Fuel;
import net.gamer.consumableblocks.networking.packet.UiPayloadC2S;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;

// on the server
public class ServerBoundPackets {
    public static void handleSmeltPayload(ServerPlayNetworking.Context context) {
        if (context.player().level().recipeAccess().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(context.player().getMainHandItem()), context.player().level()).isPresent() && Fuel.get(context.player()).hasFuelAttachment()) {
            var Item = context.player().level().recipeAccess().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(context.player().getMainHandItem()), context.player().level()).get().value().assemble(new SingleRecipeInput(context.player().getMainHandItem())).copy();
            Abilities.AbilityData ability = Abilities.get(context.player());
            boolean HasFurnace =  ability.has(Abilities.HasFurnace);
            boolean FurnaceEnabled = ability.has(Abilities.FurnaceEnabled);
            boolean FurnaceUnlocked = ability.has(Abilities.FurnaceUnlocked);
            if (Fuel.get(context.player()).getCurrentFuel() > 0 && !context.player().isCrouching() && HasFurnace && FurnaceEnabled && FurnaceUnlocked) {
                context.player().addItem(Item);
                context.player().getMainHandItem().shrink(1);
                Fuel.get(context.player()).DecrementCurrentFuel(1);
                context.player().connection.send(new ClientboundSoundPacket(Holder.direct(SoundEvents.BLAZE_SHOOT), SoundSource.MASTER, context.player().getX(), context.player().getY(), context.player().getZ(), 1.0f, 1.0f, context.player().getRandom().nextLong()));

            }
            if (Fuel.get(context.player()).getCurrentFuel() > 0 && context.player().isCrouching() && HasFurnace && FurnaceEnabled && FurnaceUnlocked) {
                var ItemwholeStack = context.player().level().recipeAccess().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(context.player().getMainHandItem()), context.player().level()).get().value().assemble(new SingleRecipeInput(context.player().getMainHandItem())).copyWithCount(context.player().getMainHandItem().getCount());
                if (Fuel.get(context.player()).getCurrentFuel() >= context.player().getMainHandItem().count()) {
                    Fuel.get(context.player()).DecrementCurrentFuel(context.player().getMainHandItem().count());
                    context.player().getMainHandItem().shrink(context.player().getMainHandItem().count());
                    context.player().addItem(ItemwholeStack);
                    context.player().connection.send(new ClientboundSoundPacket(Holder.direct(SoundEvents.BLAZE_SHOOT), SoundSource.MASTER, context.player().getX(), context.player().getY(), context.player().getZ(), 1.0f, 1.0f, context.player().getRandom().nextLong()));


                } else if (Fuel.get(context.player()).getCurrentFuel() < context.player().getMainHandItem().count() && Fuel.get(context.player()).getCurrentFuel() > 0) {
                    var ItemPartialStack = context.player().level().recipeAccess().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(context.player().getMainHandItem()), context.player().level()).get().value().assemble(new SingleRecipeInput(context.player().getMainHandItem())).copyWithCount(Fuel.get(context.player()).getCurrentFuel());
                    context.player().getMainHandItem().shrink(Fuel.get(context.player()).getCurrentFuel());
                    context.player().addItem(ItemPartialStack);
                    Fuel.get(context.player()).DecrementCurrentFuel(Fuel.get(context.player()).getCurrentFuel());
                    context.player().connection.send(new ClientboundSoundPacket(Holder.direct(SoundEvents.BLAZE_SHOOT), SoundSource.MASTER, context.player().getX(), context.player().getY(), context.player().getZ(), 1.0f, 1.0f, context.player().getRandom().nextLong()));
                }

            }


        }
        if (Fuel.get(context.player()).getCurrentFuel() == 0) {
            Holder<SoundEvent> SoundHolder = Holder.direct(SoundEvents.ANVIL_LAND);
            context.player().connection.send(new ClientboundSoundPacket(SoundHolder, SoundSource.BLOCKS, context.player().getX(), context.player().getY(), context.player().getZ(), 1.0f, 1.0f, context.player().getRandom().nextLong()));
            context.player().sendOverlayMessage(Component.literal("§4§lNoFuel Eat Some Coal"));


        }
    }

    public static void handleUiPayload(UiPayloadC2S uiPayloadC2S, ServerPlayNetworking.Context context) {
        Minecraft player = Minecraft.getInstance();
        AdvancementHolder furnace = context.player().level().getServer().getAdvancements().get(Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID, "has_furnace"));
        assert furnace != null;
        Abilities.AbilityData ability = Abilities.get(context.player());
        assert player.gui.screen() != null;
        if (uiPayloadC2S.value() == 1  && ability.has(Abilities.IceEnabled)){
            ability.Remove(Abilities.IceEnabled);
            }else if(uiPayloadC2S.value() == 1 &&!ability.has(Abilities.IceEnabled)){
                ability.Add(Abilities.IceEnabled,true);
        }
        if (uiPayloadC2S.value() == 2 && ability.has(Abilities.SlimeEnabled)){
            ability.Remove(Abilities.SlimeEnabled);
        }else if(uiPayloadC2S.value() == 2&&!ability.has(Abilities.SlimeEnabled)){
            ability.Add(Abilities.SlimeEnabled,true);
        }
        if (uiPayloadC2S.value() == 3  && ability.has(Abilities.ObsidianEnabled)){
            ability.Remove(Abilities.ObsidianEnabled);
        }else if(uiPayloadC2S.value() ==  3&& !ability.has(Abilities.ObsidianEnabled)){
            ability.Add(Abilities.ObsidianEnabled,true);
        }
        if (uiPayloadC2S.value() == 4  && ability.has(Abilities.FurnaceEnabled)){
            ability.Remove(Abilities.FurnaceEnabled);
        }else if(uiPayloadC2S.value() == 4 &&!ability.has(Abilities.FurnaceEnabled)){
            ability.Add(Abilities.FurnaceEnabled,true);
        }
        if (uiPayloadC2S.value() == 5  && ability.has(Abilities.CarvedPumpkinEnabled)){
            ability.Remove(Abilities.CarvedPumpkinEnabled);
        }else if(uiPayloadC2S.value() == 5 &&!ability.has(Abilities.CarvedPumpkinEnabled)){
            ability.Add(Abilities.CarvedPumpkinEnabled,true);
        }
        }
    }
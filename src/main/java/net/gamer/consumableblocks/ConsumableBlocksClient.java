package net.gamer.consumableblocks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.gamer.consumableblocks.fuelData.Fuel;
import net.gamer.consumableblocks.keymapping.ModKeymappings;
import net.gamer.consumableblocks.networking.packet.SmeltPayloadC2S;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Items;


public class ConsumableBlocksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModKeymappings.Register();
        ClientTickEvents.END_CLIENT_TICK.register(ConsumableBlocksClient::onEndTick);
    }
    public static void onEndTick(Minecraft client){
        while (ModKeymappings.SmeltItem.consumeClick()){
            assert client.player != null;
            if(client.player.isCrouching() && client.player.getAttached(Fuel.Current_Fuel) != null){
//                client.player.playSound(SoundEvents.ANVIL_LAND,1f,1f);
            client.player.sendOverlayMessage(Component.literal("attachedFuel " + client.player.hasAttached(Fuel.Current_Fuel)));
            }
            ClientPlayNetworking.send(new SmeltPayloadC2S("test",1));



        }
    }

}

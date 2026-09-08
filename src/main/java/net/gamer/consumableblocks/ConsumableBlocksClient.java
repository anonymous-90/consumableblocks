package net.gamer.consumableblocks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.gamer.consumableblocks.hud.Fuelbar;
import net.gamer.consumableblocks.keymapping.ModKeymappings;
import net.gamer.consumableblocks.networking.packet.SmeltPayloadC2S;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;


public class ConsumableBlocksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModKeymappings.Register();
        ClientTickEvents.END_CLIENT_TICK.register(ConsumableBlocksClient::onEndTick);
        Fuelbar.RegisterElement();
        HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT, Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"test"),Fuelbar::extract);
    }
    public static void onEndTick(Minecraft client){
        while (ModKeymappings.SmeltItem.consumeClick()){
            assert client.player != null;
            ClientPlayNetworking.send(new SmeltPayloadC2S("test",1));




        }

    }

}

package net.gamer.consumableblocks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.gamer.consumableblocks.hud.Fuelbar;
import net.gamer.consumableblocks.keymapping.ModKeymappings;
import net.gamer.consumableblocks.menu.ConfigScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;


public class ConsumableBlocksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModKeymappings.Register();
        ClientTickEvents.END_CLIENT_TICK.register(ConsumableBlocksClient::onEndTick);
        Fuelbar.RegisterElement();
        HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT, Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"test"), (graphics, deltaTracker) -> Fuelbar.extract(graphics));
    }
    public static void onEndTick(Minecraft client){
        while (ModKeymappings.SmeltItem.consumeClick()){
            assert client.player != null;
//            ClientPlayNetworking.send(new SmeltPayloadC2S("test",1));



        }
        if(ModKeymappings.MenuKey.consumeClick()){
            client.gui.setScreen(new ConfigScreen(Component.literal("TestScreen")));
        }
//                client.player.connection.send(new ClientboundSoundPacket(Holder.direct(SoundEvents.ANVIL_FALL), SoundSource.BLOCKS,client.player.getX(),client.player.getY(),client.player.getZ(),1,1,client.player.getRandom().nextLong()));


        }

    }

package net.gamer.consumableblocks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.gamer.consumableblocks.keymapping.ModKeymappings;
import net.gamer.consumableblocks.menu.MainMenu;
import net.gamer.consumableblocks.hud.ModHud;
import net.gamer.consumableblocks.networking.packet.SmeltPayloadC2S;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;


public class ConsumableBlocksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModKeymappings.Register();
        ClientTickEvents.END_CLIENT_TICK.register(ConsumableBlocksClient::onEndTick);
        ModHud.registerAbiliyUI();
        HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT, Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"fuel_bar"), (graphics, deltaTracker) -> ModHud.Fuelbar(graphics));
        HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT, Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"abilities"), (graphics, deltaTracker) -> ModHud.AbilityElements(graphics));
//        HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT, Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"ability_obsidian"), (graphics, deltaTracker) -> ModAbilitiesUI.ObsidanElement(graphics));
    }
    public static void onEndTick(Minecraft client){
        while (ModKeymappings.SmeltItem.consumeClick()){
            assert client.player != null;
            ClientPlayNetworking.send(new SmeltPayloadC2S("test",1));



        }
        if(ModKeymappings.MenuKey.consumeClick()){
            client.gui.setScreen(new MainMenu(Component.literal("test")));
        }
//                client.player.connection.send(new ClientboundSoundPacket(Holder.direct(SoundEvents.ANVIL_FALL), SoundSource.BLOCKS,client.player.getX(),client.player.getY(),client.player.getZ(),1,1,client.player.getRandom().nextLong()));


        }

    }

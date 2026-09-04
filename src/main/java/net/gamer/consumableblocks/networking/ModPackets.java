package net.gamer.consumableblocks.networking;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.gamer.consumableblocks.networking.packet.SmeltPayloadC2S;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public class ModPackets {
    private static void registerClientBound(PayloadTypeRegistry<RegistryFriendlyByteBuf> registry){
       registry.register(SmeltPayloadC2S.TYPE,SmeltPayloadC2S.STREAM_CODEC);
    }
    private static void registryServerBound(PayloadTypeRegistry<RegistryFriendlyByteBuf> registry){
        registry.register(SmeltPayloadC2S.TYPE,SmeltPayloadC2S.STREAM_CODEC);

        ServerPlayNetworking.registerGlobalReceiver(SmeltPayloadC2S.TYPE,ServerBoundPackets::handleSmeltPayload);


    }
    public static void registerPackets(){
        registerClientBound(PayloadTypeRegistry.clientboundPlay());
        registryServerBound(PayloadTypeRegistry.serverboundPlay());
    }
}

package net.gamer.consumableblocks.networking.packet;

import net.gamer.consumableblocks.ConsumableBlocks;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record UiPayloadC2S(String name, int value) implements CustomPacketPayload {
    public static final Type<UiPayloadC2S> TYPE = new Type<>(Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"ui_payload"));

    public static final StreamCodec<RegistryFriendlyByteBuf, UiPayloadC2S> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            UiPayloadC2S::name,
            ByteBufCodecs.VAR_INT,
            UiPayloadC2S::value,
            UiPayloadC2S::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}

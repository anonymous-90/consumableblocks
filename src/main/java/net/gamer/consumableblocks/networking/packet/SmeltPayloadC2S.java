package net.gamer.consumableblocks.networking.packet;

import net.gamer.consumableblocks.ConsumableBlocks;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public record SmeltPayloadC2S(String name, int value) implements CustomPacketPayload {
    public static final Type<SmeltPayloadC2S> TYPE = new Type<>(Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"smelt_payload"));

    public static final StreamCodec<RegistryFriendlyByteBuf,SmeltPayloadC2S> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            SmeltPayloadC2S::name,
            ByteBufCodecs.VAR_INT,
            SmeltPayloadC2S::value,
            SmeltPayloadC2S::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}

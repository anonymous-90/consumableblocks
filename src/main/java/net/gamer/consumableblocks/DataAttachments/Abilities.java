package net.gamer.consumableblocks.DataAttachments;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.gamer.consumableblocks.ConsumableBlocks;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;

public class Abilities {
    public static final AttachmentType<Boolean> FurnaceUnlocked = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"furnace_unlocked"),
            booleanBuilder -> booleanBuilder
                    .persistent(Codec.BOOL)
                    .syncWith(ByteBufCodecs.BOOL,AttachmentSyncPredicate.targetOnly())
                    .buildAndRegister(Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"furnace_unlocked")));

    public static final AttachmentType<Boolean> FurnaceEnabled = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"furnace_enabled"),
            booleanBuilder -> booleanBuilder
                    .persistent(Codec.BOOL)
                    .syncWith(ByteBufCodecs.BOOL,AttachmentSyncPredicate.targetOnly())
                    .buildAndRegister(Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"furnace_enabled")));

    public static final AttachmentType<Boolean> HasFurnace = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"has_furnace"),
            booleanBuilder -> booleanBuilder
                    .persistent(Codec.BOOL)
                    .syncWith(ByteBufCodecs.BOOL,AttachmentSyncPredicate.targetOnly())
                    .buildAndRegister(Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"has_furnace")));


        public static void registerAbilitiesData(){
            ConsumableBlocks.LOGGER.info("registered Abilities Data for" + ConsumableBlocks.MOD_ID );
        }
    public static AbilityData get(AttachmentTarget target){
        return new AbilityData(target);
    }


    public record AbilityData(AttachmentTarget target){

        public boolean has (AttachmentType<Boolean> T){
            return this.target.hasAttached(T);
        }
        public void Add (AttachmentType<Boolean> T,boolean B){
            this.target.setAttached(T,B);
        }
        public void Remove(AttachmentType<Boolean> T){
            this.target.removeAttached(T);
        }
    }

}

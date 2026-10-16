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
    public static  final  AttachmentType<Integer> CurrentAbilityCount = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"current_ability_count"),
            integerBuilder -> integerBuilder
                    .persistent(Codec.INT)
                    .syncWith(ByteBufCodecs.INT,AttachmentSyncPredicate.targetOnly())
                    .copyOnDeath()
                    );

    public static final AttachmentType<Boolean> FurnaceUnlocked = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"furnace_unlocked"),
            booleanBuilder -> booleanBuilder
                    .persistent(Codec.BOOL)
                    .syncWith(ByteBufCodecs.BOOL,AttachmentSyncPredicate.targetOnly())
                    .copyOnDeath()
                    );

    public static final AttachmentType<Boolean> FurnaceEnabled = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"furnace_enabled"),
            booleanBuilder -> booleanBuilder
                    .persistent(Codec.BOOL)
                    .syncWith(ByteBufCodecs.BOOL,AttachmentSyncPredicate.targetOnly())
                    .copyOnDeath()
               );

    public static final AttachmentType<Boolean> HasFurnace = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"has_furnace"),
            booleanBuilder -> booleanBuilder
                    .persistent(Codec.BOOL)
                    .syncWith(ByteBufCodecs.BOOL,AttachmentSyncPredicate.targetOnly())
                    .copyOnDeath()
                  );

// ice

    public static final AttachmentType<Boolean> IceUnlocked = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"ice_unlocked"),
            booleanBuilder -> booleanBuilder
                    .persistent(Codec.BOOL)
                    .syncWith(ByteBufCodecs.BOOL,AttachmentSyncPredicate.targetOnly())
                    .copyOnDeath()
                   );

    public static final AttachmentType<Boolean> IceEnabled = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"ice_enabled"),
            booleanBuilder -> booleanBuilder
                    .persistent(Codec.BOOL)
                    .syncWith(ByteBufCodecs.BOOL,AttachmentSyncPredicate.targetOnly())
                    .copyOnDeath()
                    );

    public static final AttachmentType<Boolean> HasIce = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"has_ice"),
            booleanBuilder -> booleanBuilder
                    .persistent(Codec.BOOL)
                    .syncWith(ByteBufCodecs.BOOL,AttachmentSyncPredicate.targetOnly())
                    .copyOnDeath()
          );

    // obsidian

    public static final AttachmentType<Boolean> ObsidianUnlocked = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"obsidian_unlocked"),
            booleanBuilder -> booleanBuilder
                    .persistent(Codec.BOOL)
                    .syncWith(ByteBufCodecs.BOOL,AttachmentSyncPredicate.targetOnly())
                    .copyOnDeath()
                    );

    public static final AttachmentType<Boolean> ObsidianEnabled = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"obsidian_enabled"),
            booleanBuilder -> booleanBuilder
                    .persistent(Codec.BOOL)
                    .syncWith(ByteBufCodecs.BOOL,AttachmentSyncPredicate.targetOnly())
                    .copyOnDeath()
                    );

    public static final AttachmentType<Boolean> HasObsidian = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"has_obsidian"),
            booleanBuilder -> booleanBuilder
                    .persistent(Codec.BOOL)
                    .syncWith(ByteBufCodecs.BOOL,AttachmentSyncPredicate.targetOnly())
                    .copyOnDeath()
                    );

    // slime

    public static final AttachmentType<Boolean> SlimeUnlocked = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"slime_unlocked"),
            booleanBuilder -> booleanBuilder
                    .persistent(Codec.BOOL)
                    .syncWith(ByteBufCodecs.BOOL,AttachmentSyncPredicate.targetOnly())
                    .copyOnDeath()
                    );

    public static final AttachmentType<Boolean> SlimeEnabled = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"slime_enabled"),
            booleanBuilder -> booleanBuilder
                    .persistent(Codec.BOOL)
                    .syncWith(ByteBufCodecs.BOOL,AttachmentSyncPredicate.targetOnly())
                    .copyOnDeath()
                    );

    public static final AttachmentType<Boolean> HasSlime = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"has_slime"),
            booleanBuilder -> booleanBuilder
                    .persistent(Codec.BOOL)
                    .syncWith(ByteBufCodecs.BOOL,AttachmentSyncPredicate.targetOnly())
                    .copyOnDeath()
                    );
    // Carved Pumpkin

    public static final AttachmentType<Boolean> CarvedPumpkinUnlocked = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"carved_pumpkin_unlocked"),
            booleanBuilder -> booleanBuilder
                    .persistent(Codec.BOOL)
                    .syncWith(ByteBufCodecs.BOOL,AttachmentSyncPredicate.targetOnly())
                    .copyOnDeath()
                    );

    public static final AttachmentType<Boolean> CarvedPumpkinEnabled = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"carved_pumpkin_enabled"),
            booleanBuilder -> booleanBuilder
                    .persistent(Codec.BOOL)
                    .syncWith(ByteBufCodecs.BOOL,AttachmentSyncPredicate.targetOnly())
                    .copyOnDeath()
                    );

    public static final AttachmentType<Boolean> HasCarvedPumpkin = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"has_carved_pumpkin"),
            booleanBuilder -> booleanBuilder
                    .persistent(Codec.BOOL)
                    .syncWith(ByteBufCodecs.BOOL,AttachmentSyncPredicate.targetOnly())
                    .copyOnDeath()
                    );

        public static void registerAbilitiesData(){
            ConsumableBlocks.LOGGER.info("registered Abilities Data for" + ConsumableBlocks.MOD_ID );
        }
    public static AbilityData get(AttachmentTarget target){
        return new AbilityData(target);
    }


    public record AbilityData(AttachmentTarget target){

        public boolean has ( AttachmentType<Boolean> T){
            return this.target.hasAttached(T);
        }
        public void Add (AttachmentType<Boolean> T,boolean B){
            this.target.setAttached(T,B);
        }
        public void Remove(AttachmentType<?> T){
            this.target.removeAttached(T);
        }
        public void setCurrentCount(int value){
            this.target.setAttached(CurrentAbilityCount,value);
        }
        public int getCurrentCount() {
            return this.target.getAttachedOrElse(CurrentAbilityCount,0);
        }
        public void decrementCurrentCount(int value){
            this.target.modifyAttached(CurrentAbilityCount, currentabilityCount -> currentabilityCount - value);
        }

     }

}

package net.gamer.consumableblocks.fuelData;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.gamer.consumableblocks.ConsumableBlocks;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;

public class Fuel {
    public static final AttachmentType<Integer> Current_Fuel = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"current_fuel"),
            integerBuilder -> integerBuilder
                    .persistent(Codec.INT)
                    .syncWith(ByteBufCodecs.INT, AttachmentSyncPredicate.targetOnly())
                    .initializer(()->0)
                    .copyOnDeath()
    );
    private static final AttachmentType<Integer> Max_Fuel = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"max_fuel"),
            integerBuilder -> integerBuilder
                    .persistent(Codec.INT)
                    .syncWith(ByteBufCodecs.INT, AttachmentSyncPredicate.targetOnly())
    );
    public static FuelData get(AttachmentTarget target){
        return new FuelData(target);
    }
    public record FuelData(AttachmentTarget target){
        public int getCurrentFuel(){
            return this.target.getAttachedOrElse(Current_Fuel,0);
        }
        public int DecrementCurrentFuel(int value){
            return this.target.modifyAttached(Current_Fuel,currentFuel -> currentFuel-value);
        }
        public void setCurrentFuel(int value){
            this.target.setAttached(Current_Fuel,value);
        }
        public int getMaxFuel(){
            return this.target.getAttachedOrElse(Max_Fuel,0);
        }
        public void setMaxFuel(int value){
            this.target.setAttached(Max_Fuel,value);
        }
        public void RemoveFuelAttachment(){
            this.target.removeAttached(Current_Fuel);
        }
        public boolean hasFuelAttachment(){
            return this.target.hasAttached(Current_Fuel);
        }
    }

}

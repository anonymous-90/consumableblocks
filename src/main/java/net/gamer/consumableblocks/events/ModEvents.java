package net.gamer.consumableblocks.events;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.gamer.consumableblocks.ConsumableBlocks;
import net.gamer.consumableblocks.DataAttachments.Abilities;
import net.gamer.consumableblocks.DataAttachments.Fuel;
import net.gamer.consumableblocks.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundContainerSlotStateChangedPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Items;

import java.util.Objects;

public class ModEvents {

    public static void EventRegister(){
        UseItemCallback.EVENT.register((player, level, _) -> {
            if(player instanceof  ServerPlayer serverPlayer){
                boolean HasFurnace = serverPlayer.getAdvancements().getOrStartProgress(Objects.requireNonNull(Objects.requireNonNull(level.getServer()).getAdvancements().get(Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID, "has_furnace")))).isDone();
                boolean FurnaceEnabled = serverPlayer.getAdvancements().getOrStartProgress(Objects.requireNonNull(Objects.requireNonNull(level.getServer()).getAdvancements().get(Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID, "furnace_enabled")))).isDone();
                boolean FurnaceUnlocked = serverPlayer.getAdvancements().getOrStartProgress(Objects.requireNonNull(Objects.requireNonNull(level.getServer()).getAdvancements().get(Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID, "furnace_unlock")))).isDone();
                Fuel.get(player).setMaxFuel(100);
                if(player.getMainHandItem().getItem() == Items.COAL && Fuel.get(player).getCurrentFuel() != 100 && !player.isCrouching() && HasFurnace && FurnaceEnabled && FurnaceUnlocked){
                    Fuel.get(player).setCurrentFuel(Fuel.get(player).getCurrentFuel()+1);
                    player.getMainHandItem().shrink(1);
                }else if (player.getMainHandItem().getItem() == Items.COAL && Fuel.get(player).getCurrentFuel() != 100 && player.isCrouching() && HasFurnace && FurnaceEnabled && FurnaceUnlocked){
                    int SpaceLeft = Fuel.get(player).getMaxFuel() - Fuel.get(player).getCurrentFuel();
                    int LeftToMove = Math.min(player.getMainHandItem().count(),SpaceLeft);
                    int newFuel = Fuel.get(player).getCurrentFuel() + LeftToMove;
                    Fuel.get(player).setCurrentFuel(newFuel);
                    player.getMainHandItem().shrink(LeftToMove);


                }
                if(player.getMainHandItem().getItem() == Items.STICK){
//                    player.sendOverlayMessage(Component.literal("CurrentFuel " +Fuel.get(player).getCurrentFuel()));
                    Abilities.get(player).Remove(Abilities.FurnaceUnlocked);
                }
                if(player.getMainHandItem().getItem() == Items.DIAMOND){
                    Abilities.get(player).Remove(Abilities.FurnaceEnabled);

                }
            }

            return InteractionResult.PASS;
        });


//        ServerTickEvents.END_SERVER_TICK.register(server -> {
//            for(ServerPlayer Serverplayer: server.getPlayerList().getPlayers()){
//                boolean HasSlimeAbility = Serverplayer.getAttributes().hasModifier(Attributes.BOUNCINESS,Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"slime_eaten"));
//                LocalPlayer localPlayer = Minecraft.getInstance().player;
//
//                    if (Serverplayer.fallDistance == 0 && Serverplayer.getLastClientInput().jump() && !Serverplayer.isJumping()){
//                    Serverplayer.level().sendParticles(Serverplayer, ParticleTypes.ITEM_SLIME,false,true,Serverplayer.getX(),Serverplayer.getY()+0.5,Serverplayer.getZ(),59,1,0,1,1);
//                    Serverplayer.sendOverlayMessage(Component.literal("tets"));
//
//                    }
//
//
//            }
//        });
// temporary crafting check
        ServerTickEvents.END_SERVER_TICK.register(server -> {

            for(ServerPlayer player: server.getPlayerList().getPlayers()){
                Abilities.AbilityData abilities = Abilities.get(player);
                if(player.getInventory().contains(ModItems.EdibleFurnace.getDefaultInstance()) && !Abilities.get(player).has(Abilities.FurnaceUnlocked) && !abilities.has(Abilities.FurnaceEnabled)){
                    Abilities.get(player).Add(Abilities.FurnaceUnlocked,true);
                    player.sendSystemMessage(Component.literal("furnace unlocked"));
                    abilities.Add(Abilities.FurnaceEnabled,true);
                    player.sendSystemMessage(Component.literal("furnace enabled"));
                } else {
                    return;
                }
            }
        });


    }



}

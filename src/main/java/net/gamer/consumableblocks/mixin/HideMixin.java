package net.gamer.consumableblocks.mixin;

import net.gamer.consumableblocks.DataAttachments.Abilities;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderMan.class)

public abstract class HideMixin {
    @Inject(at = @At("HEAD"),method = "isBeingStaredBy",cancellable = true)
    private void canBeSeen(final Player player, CallbackInfoReturnable<Boolean> cir){
        Abilities.AbilityData ability = Abilities.get(player);
        if(ability.has(Abilities.HasCarvedPumpkin)){
            cir.setReturnValue(false);
        }
    }
}

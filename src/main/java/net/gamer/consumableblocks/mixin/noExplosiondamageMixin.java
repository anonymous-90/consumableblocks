package net.gamer.consumableblocks.mixin;

import net.gamer.consumableblocks.DataAttachments.Abilities;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ExplosionDamageCalculator.class)
public abstract class noExplosiondamageMixin {
    @Inject(at = @At("HEAD"),method = "shouldDamageEntity",cancellable = true)
    private void canDamageEntity(Explosion explosion, Entity entity, CallbackInfoReturnable<Boolean> cir){
        if (entity instanceof ServerPlayer player && Abilities.get(player).has(Abilities.HasObsidian)){
            player.sendSystemMessage(Component.literal("test"));
            cir.setReturnValue(false);

        }
    }
}

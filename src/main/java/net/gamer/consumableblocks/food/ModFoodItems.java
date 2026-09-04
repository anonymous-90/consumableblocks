package net.gamer.consumableblocks.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModFoodItems {
    public static final FoodProperties superAwesomeStickFood = new FoodProperties.Builder()
            .nutrition(0)
            .alwaysEdible()
            .saturationModifier(0.0f)
            .build();
    public static final Consumable superAwesomeStickConsumable = Consumables.defaultFood()
            .animation(ItemUseAnimation.EAT)
            .consumeSeconds(1)
            .hasConsumeParticles(true)
            .build();
    public static final FoodProperties EdibleFood = new FoodProperties.Builder()
            .nutrition(0)
            .alwaysEdible()
            .saturationModifier(0.0f)
            .build();
    public static final Consumable EdibleConsumable = Consumables.defaultFood()
            .animation(ItemUseAnimation.EAT)
            .consumeSeconds(3)
            .hasConsumeParticles(true)
            .build();
    public static final FoodProperties GeneCleaserFood = new FoodProperties.Builder()
            .nutrition(0)
            .alwaysEdible()
            .saturationModifier(0.0f)
            .build();
    public static final Consumable GeneCleaserConsumable = Consumables.defaultDrink()
            .animation(ItemUseAnimation.DRINK)
            .consumeSeconds(3)
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NAUSEA,40,1,false,false)))
            .build();
}




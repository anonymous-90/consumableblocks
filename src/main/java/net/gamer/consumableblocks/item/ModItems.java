package net.gamer.consumableblocks.item;

import net.gamer.consumableblocks.ConsumableBlocks;
import net.gamer.consumableblocks.food.ModFoodItems;
import net.gamer.consumableblocks.item.custom.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.function.Function;


public class ModItems {

    public static final Item EdibleIce = registerItems("edible_ice",properties -> new EdibleIce(properties.food(ModFoodItems.superAwesomeStickFood,ModFoodItems.superAwesomeStickConsumable)));

    public static final Item EdibleSlime = registerItems("edible_slime",properties -> new EdibleSlime(properties
            .food(ModFoodItems.EdibleFood,ModFoodItems.EdibleConsumable)));

public static final Item EdibleFurnace = registerItems("edible_furnace",properties -> new EdibleFurnace(properties
        .food(ModFoodItems.EdibleFood,ModFoodItems.EdibleConsumable)));

public static final Item EdibleObsidian = registerItems("edible_obsidian",properties -> new EdibleObsidian(properties
        .food(ModFoodItems.EdibleFood,ModFoodItems.EdibleConsumable)));

    public static  final Item IceGeneCleanser = registerItems("ice_gene_cleanser",properties -> new IceGeneCleaser(properties
            .food(ModFoodItems.GeneCleaserFood,ModFoodItems.GeneCleaserConsumable)));

    public static  final Item SlimeGeneCleanser = registerItems("slime_gene_cleanser",properties -> new SlimeGeneCleaser(properties
            .food(ModFoodItems.GeneCleaserFood,ModFoodItems.GeneCleaserConsumable)));

    public static  final Item ObsidianGeneCleanser = registerItems("obsidian_gene_cleanser",properties -> new ObsidianGeneCleaser(properties
            .food(ModFoodItems.GeneCleaserFood,ModFoodItems.GeneCleaserConsumable)));

    public static  final Item FurnaceGeneCleanser = registerItems("furnace_gene_cleanser",properties -> new FurnaceGeneCleaser(properties
            .food(ModFoodItems.GeneCleaserFood,ModFoodItems.GeneCleaserConsumable)));

    private static Item registerItems(String name, Function<Item.Properties,Item> function){
       return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,name),
               function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,name)))));
    }

    public static void registerModItems(){
        ConsumableBlocks.LOGGER.info("registered Item for" + ConsumableBlocks.MOD_ID );
    }
}

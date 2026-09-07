package net.gamer.consumableblocks.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.gamer.consumableblocks.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull RecipeOutput output) {
        return new RecipeProvider(registries,output) {
            @Override
            public void buildRecipes() {


                shaped(RecipeCategory.MISC,ModItems.EdibleFurnace,1)
                        .pattern(" x ")
                        .pattern(" o ")
                        .define('x', Items.EXPERIENCE_BOTTLE)
                        .define('o',Items.FURNACE)
                        .unlockedBy(getHasName(Items.FURNACE),has(Items.FURNACE))
                        .group("ConsumableBlocks")
                        .save(output);


               shaped(RecipeCategory.MISC,ModItems.EdibleObsidian,1)
                        .pattern(" x ")
                        .pattern(" o ")
                        .define('x', Items.EXPERIENCE_BOTTLE)
                        .define('o',Items.OBSIDIAN)
                        .unlockedBy(getHasName(Items.OBSIDIAN),has(Items.OBSIDIAN))
                        .group("ConsumableBlocks")
                        .save(output);


              shaped(RecipeCategory.MISC,ModItems.EdibleSlime,1)
                        .pattern(" x ")
                        .pattern(" o ")
                        .define('x', Items.EXPERIENCE_BOTTLE)
                        .define('o',Items.SLIME_BLOCK)
                        .unlockedBy(getHasName(Items.SLIME_BLOCK),has(Items.SLIME_BLOCK))
                        .group("ConsumableBlocks")
                        .save(output);


              shaped(RecipeCategory.MISC,ModItems.EdibleIce,1)
                        .pattern(" x ")
                        .pattern(" o ")
                        .define('x', Items.EXPERIENCE_BOTTLE)
                        .define('o',Items.ICE)
                        .unlockedBy(getHasName(Items.ICE),has(Items.ICE))
                        .group("ConsumableBlocks")
                        .save(output);

              // gene cleasers
              shaped(RecipeCategory.MISC,ModItems.FurnaceGeneCleanser,1)
                        .pattern(" b ")
                        .pattern(" e ")
                        .pattern(" w ")
                        .define('b', Items.FURNACE)
                        .define('e',Items.GLASS_BOTTLE)
                        .define('w',Items.WATER_BUCKET)
                        .unlockedBy(getHasName(Items.FURNACE),has(Items.FURNACE))
                        .group("ConsumableBlocks")
                        .save(output);



              shaped(RecipeCategory.MISC,ModItems.IceGeneCleanser,1)
                        .pattern(" b ")
                        .pattern(" e ")
                        .pattern(" w ")
                        .define('b', Items.ICE)
                        .define('e',Items.GLASS_BOTTLE)
                        .define('w',Items.WATER_BUCKET)
                        .unlockedBy(getHasName(Items.ICE),has(Items.ICE))
                        .group("ConsumableBlocks")
                        .save(output);


              shaped(RecipeCategory.MISC,ModItems.SlimeGeneCleanser,1)
                        .pattern(" b ")
                        .pattern(" e ")
                        .pattern(" w ")
                        .define('b', Items.SLIME_BLOCK)
                        .define('e',Items.GLASS_BOTTLE)
                        .define('w',Items.WATER_BUCKET)
                        .unlockedBy(getHasName(Items.SLIME_BLOCK),has(Items.SLIME_BLOCK))
                        .group("ConsumableBlocks")
                        .save(output);



              shaped(RecipeCategory.MISC,ModItems.ObsidianGeneCleanser,1)
                        .pattern(" b ")
                        .pattern(" e ")
                        .pattern(" w ")
                        .define('b', Items.OBSIDIAN)
                        .define('e',Items.GLASS_BOTTLE)
                        .define('w',Items.WATER_BUCKET)
                        .unlockedBy(getHasName(Items.OBSIDIAN),has(Items.OBSIDIAN))
                        .group("ConsumableBlocks")
                        .save(output);
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "ConsumableBlocks Recipes";
    }
}

package net.gamer.consumableblocks.datagen;

import net.gamer.consumableblocks.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
itemModelGenerators.generateFlatItem(ModItems.EdibleIce,ModelTemplates.FLAT_ITEM);
itemModelGenerators.generateFlatItem(ModItems.EdibleSlime,ModelTemplates.FLAT_ITEM);
itemModelGenerators.generateFlatItem(ModItems.EdibleObsidian,ModelTemplates.FLAT_ITEM);
itemModelGenerators.generateFlatItem(ModItems.EdibleFurnace,ModelTemplates.FLAT_ITEM);
itemModelGenerators.generateFlatItem(ModItems.IceGeneCleanser,ModelTemplates.FLAT_ITEM);
itemModelGenerators.generateFlatItem(ModItems.SlimeGeneCleanser,ModelTemplates.FLAT_ITEM);
itemModelGenerators.generateFlatItem(ModItems.ObsidianGeneCleanser,ModelTemplates.FLAT_ITEM);
itemModelGenerators.generateFlatItem(ModItems.FurnaceGeneCleanser,ModelTemplates.FLAT_ITEM);
    }
}

package net.gamer.consumableblocks.creativeTabs;

import net.gamer.consumableblocks.ConsumableBlocks;
import net.gamer.consumableblocks.item.ModItems;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
public static final CreativeModeTab ItemsTab = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"test_mod_items"), FabricCreativeModeTab.builder()
                .icon(() -> new ItemStack(ModItems.EdibleSlime))
                .title(Component.translatable("creativemodetab.testmod.items"))
                .displayItems((parameters, output) -> {
                    output.accept(ModItems.EdibleIce);
                    output.accept(ModItems.EdibleSlime);
                    output.accept(ModItems.EdibleFurnace);
                    output.accept(ModItems.EdibleObsidian);
                    output.accept(ModItems.IceGeneCleanser);
                    output.accept(ModItems.ObsidianGeneCleanser);
                    output.accept(ModItems.SlimeGeneCleanser);
                    output.accept(ModItems.FurnaceGeneCleanser);
                })
        .build());

    public static void registerModCreativeModeTabs() {
        ConsumableBlocks.LOGGER.info("registering tabs for" + ConsumableBlocks.MOD_ID);

    }
}

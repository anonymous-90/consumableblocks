package net.gamer.consumableblocks.hud;

import net.gamer.consumableblocks.ConsumableBlocks;
import net.gamer.consumableblocks.fuelData.Fuel;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.font.FontSet;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.data.DataSource;
import net.minecraft.network.chat.contents.data.DataSources;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.util.Util;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class Fuelbar {
    private static final Identifier FuelEmpty = Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"hud/fuel_empty");
    private static final Identifier Fuel_BAR_PROGRESS_SPRITE = Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"hud/fuel_bar_background");
    private static final Minecraft minecraft = Minecraft.getInstance();

    public static void extract(@NonNull GuiGraphicsExtractor graphics, @NonNull DeltaTracker deltaTracker) {
        LocalPlayer player = Minecraft.getInstance().player;
        int guiHeight = graphics.guiHeight();
        boolean FuelData = Fuel.get(player).hasFuelAttachment();
        if(FuelData){
            int color = 0xFFFF0000; // Red
            graphics.text(minecraft.font,Component.literal(Integer.toString(Fuel.get(player).getCurrentFuel())),175,215,color);
        }

    }
    public static void RegisterElement(){
        ConsumableBlocks.LOGGER.info("Registering new Hud Element For " + ConsumableBlocks.MOD_ID);
    }
}



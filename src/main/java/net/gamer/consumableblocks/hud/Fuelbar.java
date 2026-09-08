package net.gamer.consumableblocks.hud;

import net.gamer.consumableblocks.ConsumableBlocks;
import net.gamer.consumableblocks.fuelData.Fuel;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.util.Util;
import org.jspecify.annotations.NonNull;

public class Fuelbar {
    private static final Identifier FuelEmpty = Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"hud/fuel_empty");
    private static final Identifier Fuel_BAR_PROGRESS_SPRITE = Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"hud/fuel_bar_background");
    private static final Minecraft minecraft = Minecraft.getInstance();

    public static void extract(@NonNull GuiGraphicsExtractor graphics, @NonNull DeltaTracker deltaTracker) {
        LocalPlayer player = Minecraft.getInstance().player;
        int guiHeight = graphics.guiHeight();
        int FuelData = Fuel.get(player).getCurrentFuel();
        if(FuelData == 0){
            int color = 0xFFFF0000; // Red
            int targetColor = 0xFF00FF00; // Green

            // You can use the Util.getMillis() function to get the current time in milliseconds.
            // Divide by 1000 to get seconds.
            double currentTime = Util.getMillis() / 1000.0;

            // "ler
            // p" simply means "linear interpolation", which is a fancy way of saying "blend".
            float lerpedAmount = Mth.abs(Mth.sin((float) currentTime));
            int lerpedColor = ARGB.linearLerp(lerpedAmount, color, targetColor);


            // Draw a square with the lerped color.
            // x1, x2, y1, y2, color
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED,Fuel_BAR_PROGRESS_SPRITE,182, 5, 182, 5);
//            graphics.fill(0, 0, 10, 10, lerpedColor);
        }

    }
    public static void RegisterElement(){
        ConsumableBlocks.LOGGER.info("Registering new Hud Element For " + ConsumableBlocks.MOD_ID);
    }
}



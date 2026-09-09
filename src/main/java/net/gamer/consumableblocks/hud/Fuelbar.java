package net.gamer.consumableblocks.hud;
import net.gamer.consumableblocks.ConsumableBlocks;
import net.gamer.consumableblocks.fuelData.Fuel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;

import java.awt.*;

public class Fuelbar {
    private static final Minecraft minecraft = Minecraft.getInstance();
    private static final Identifier FuelTexture = Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"hud/fuel_empty");

    public static void extract(@NonNull GuiGraphicsExtractor graphics) {
        LocalPlayer player = Minecraft.getInstance().player;
        boolean FuelData = Fuel.get(player).hasFuelAttachment();
        int height = minecraft.getWindow().getGuiScaledHeight();
        int width = minecraft.getWindow().getGuiScaledWidth();
        int x= width/2;
        int y= height;
        if(FuelData){
            int redcolor = 0xFFFF0000; // Red
            int whitecolor= 0xFFFFFFFF;
           graphics.blitSprite(RenderPipelines.GUI_TEXTURED,FuelTexture,x - -90,y-17,16,16);
           if(Fuel.get(player).getCurrentFuel() == 0){
           graphics.text(minecraft.font,Component.literal(Integer.toString(Fuel.get(player).getCurrentFuel())),x- -107,y- 13,redcolor);

           }else {
               graphics.text(minecraft.font,Component.literal(Integer.toString(Fuel.get(player).getCurrentFuel())),x- -107,y- 13,whitecolor);

           }
        }

    }
    public static void RegisterElement(){
        ConsumableBlocks.LOGGER.info("Registering new Hud Element For " + ConsumableBlocks.MOD_ID);
    }
}



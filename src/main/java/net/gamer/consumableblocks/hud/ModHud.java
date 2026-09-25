package net.gamer.consumableblocks.hud;

import net.gamer.consumableblocks.ConsumableBlocks;
import net.gamer.consumableblocks.DataAttachments.Abilities;
import net.gamer.consumableblocks.DataAttachments.Fuel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class ModHud {
    private static final Minecraft minecraft = Minecraft.getInstance();
    private static final Identifier AbilityFurnace = Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"hud/furnace_ability");;
    private static final Identifier AbilityIce = Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"hud/ice_ability");;
    private static final Identifier AbilitySlime = Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"hud/slime_ability");;
    private static final Identifier AbilityObsidian = Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"hud/obsidian_ability");;
    private static final Identifier FuelTexture = Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"hud/fuel_empty");

    public static void AbilityElements(GuiGraphicsExtractor graphics){
        LocalPlayer player = Minecraft.getInstance().player;
        Abilities.AbilityData ability = Abilities.get(player);
        int width = minecraft.getWindow().getGuiScaledWidth();
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED,AbilityFurnace, width -18,0,18,18);
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED,AbilityIce, width - 18, 20,18,18);
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED,AbilitySlime,width-18,40,18,18);
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED,AbilityObsidian,width-18,60,18,18);

}
public static void Fuelbar(GuiGraphicsExtractor graphics){
    LocalPlayer player = Minecraft.getInstance().player;
    Abilities.AbilityData ability = Abilities.get(player);
    boolean FuelData = Fuel.get(player).hasFuelAttachment();
    int height = minecraft.getWindow().getGuiScaledHeight();
    int width = minecraft.getWindow().getGuiScaledWidth();
    int x= width/2;
    if(FuelData && ability.has(Abilities.HasFurnace)){
        int redcolor = 0xFFFF0000; // Red
        int whitecolor= 0xFFFFFFFF;
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED,FuelTexture, x + 90,height -17,16,16);
        if(Fuel.get(player).getCurrentFuel() == 0){
            graphics.text(minecraft.font, Component.literal(Integer.toString(Fuel.get(player).getCurrentFuel())), x + 107,height-  13,redcolor);

        }else {
            graphics.text(minecraft.font,Component.literal(Integer.toString(Fuel.get(player).getCurrentFuel())), x + 107,height- 13,whitecolor);

        }
    }
}
public static void registerAbiliyUI(){
    ConsumableBlocks.LOGGER.info("registering ability ui for " + ConsumableBlocks.MOD_ID);
}
}

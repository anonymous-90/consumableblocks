package net.gamer.consumableblocks.menu;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.CommonColors;
import net.minecraft.world.level.block.ColorCollection;
import org.jspecify.annotations.NonNull;

import java.awt.*;
import java.net.URI;

public class credits extends Screen {
    protected credits(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        Button click = Button.builder(Component.empty(),button -> {
            clickUrlAction(minecraft,new credits(Component.empty()), URI.create("https://www.youtube.com/@anonymousgamer4713"));
        }).bounds(minecraft.getWindow().getGuiScaledWidth()/2 -60,  minecraft.getWindow().getGuiScaledHeight()/2 -62, 117,12).build();
        click.setAlpha(0);
        click.setTooltip(Tooltip.create(Component.literal("Click me :)")));

        Button back = Button.builder(Component.literal("Back"),button -> {
            minecraft.gui.setScreen(new MainMenu(Component.empty()));
        }).bounds(minecraft.getWindow().getGuiScaledWidth()/2 -60,minecraft.getWindow().getGuiScaledHeight()/2+107,100,20).build();
        this.addRenderableWidget(click);
        this.addRenderableWidget(back);
    }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);
        graphics.text(this.font,Component.literal("Credits"),minecraft.getWindow().getGuiScaledWidth()/2 -22,  minecraft.getWindow().getGuiScaledHeight()/2 -120, CommonColors.WHITE);
        graphics.text(this.font,Component.literal("§6§lCreated by Gamer99"),minecraft.getWindow().getGuiScaledWidth()/2 -60,  minecraft.getWindow().getGuiScaledHeight()/2 -90,CommonColors.WHITE);
        graphics.text(this.font,Component.literal("§5§lMy Youtube Channel"),minecraft.getWindow().getGuiScaledWidth()/2 -60,  minecraft.getWindow().getGuiScaledHeight()/2 -60,CommonColors.WHITE);
    }
}

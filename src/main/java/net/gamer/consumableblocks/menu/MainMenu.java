package net.gamer.consumableblocks.menu;

import net.gamer.consumableblocks.menu.help.helpMainMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.CommonColors;
import org.jspecify.annotations.NonNull;

public class MainMenu extends Screen {
    public MainMenu(Component title) {
        super(title);
    }

    @Override
    protected void init() {

        Button Config = Button.builder(Component.literal("Config"), (btn) -> {
        minecraft.gui.setScreen(new ConfigScreen(Component.literal("config")));

        }).bounds(minecraft.getWindow().getGuiScaledWidth()/2 -120,  minecraft.getWindow().getGuiScaledHeight()/2 -60, 120, 20).build();

        Button Credits = Button.builder(Component.literal("Credits"), (btn) -> {


        }).bounds(minecraft.getWindow().getGuiScaledWidth()/2 +1,  minecraft.getWindow().getGuiScaledHeight()/2 -60, 120, 20).build();


        Button Help = Button.builder(Component.literal("Help"), (btn) -> {
        minecraft.gui.setScreen(new helpMainMenu(Component.literal("help")));

        }).bounds(minecraft.getWindow().getGuiScaledWidth()/2 -120,  minecraft.getWindow().getGuiScaledHeight()/2 -38, 120, 20).build();

        Button Blocksuggestions = Button.builder(Component.literal("Block Suggestions"), (btn) -> {


        }).bounds(minecraft.getWindow().getGuiScaledWidth()/2 +1,  minecraft.getWindow().getGuiScaledHeight()/2 -38, 120, 20).build();
        this.addRenderableWidget(Config);
        this.addRenderableWidget(Credits);
        this.addRenderableWidget(Help);
        this.addRenderableWidget(Blocksuggestions);
    }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);
        graphics.textWithWordWrap(this.font, FormattedText.of("Consumable Blocks"), minecraft.getWindow().getGuiScaledWidth()/2 -45,  minecraft.getWindow().getGuiScaledHeight()/2 -120, 220, CommonColors.WHITE);
    }
}
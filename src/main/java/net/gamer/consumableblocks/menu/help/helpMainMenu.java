package net.gamer.consumableblocks.menu.help;

import net.gamer.consumableblocks.menu.ConfigScreen;
import net.gamer.consumableblocks.menu.MainMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.CommonColors;
import org.jspecify.annotations.NonNull;

public class helpMainMenu extends Screen {
    public helpMainMenu(Component title) {
        super(title);
    }

    @Override
    protected void init() {


//
        Button Config = Button.builder(Component.literal("Blocks"), (btn) -> {

        }).bounds( minecraft.getWindow().getGuiScaledWidth()/2 -120,  minecraft.getWindow().getGuiScaledHeight()/2 -70, 120, 20).build();

        Button Credits = Button.builder(Component.literal("Abilities"), (btn) -> {


        }).bounds(minecraft.getWindow().getGuiScaledWidth()/2 +1,  minecraft.getWindow().getGuiScaledHeight()/2 -70, 120, 20).build();


        Button Back = Button.builder(Component.literal("Back"), (btn) -> {
        minecraft.gui.setScreen(new MainMenu(Component.empty()));

        }).bounds(minecraft.getWindow().getGuiScaledWidth()/2 -60,  minecraft.getWindow().getGuiScaledHeight()/2 -47, 120, 20).build();
        this.addRenderableWidget(Config);
        this.addRenderableWidget(Credits);
        this.addRenderableWidget(Back);
    }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);
        graphics.textWithWordWrap(this.font, FormattedText.of("Help Menu"), minecraft.getWindow().getGuiScaledWidth()/2 -22,  minecraft.getWindow().getGuiScaledHeight()/2 -120, 220, CommonColors.WHITE);

    }
}
package net.gamer.consumableblocks.menu;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.CommonColors;
import org.jspecify.annotations.NonNull;

public class BlockSuggetionMenu extends Screen {
    protected BlockSuggetionMenu(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        Button suggestBlcok = Button.builder(Component.literal("Suggest Block"),button -> {

        }).bounds(minecraft.getWindow().getGuiScaledWidth()/2 -60, minecraft.getWindow().getGuiScaledHeight()/2 -40,150,20).build();


        Button back = Button.builder(Component.literal("Back"),button -> {
            minecraft.gui.setScreen(new MainMenu(Component.empty()));
        }).bounds(minecraft.getWindow().getGuiScaledWidth()/2 -60, minecraft.getWindow().getGuiScaledHeight()/2 -20,150,20).build();
        suggestBlcok.setTooltip(Tooltip.create(Component.literal("leave a comment on the demo video(§4to be added§r)")));
        this.addRenderableWidget(suggestBlcok);
        this.addRenderableWidget(back);
    }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);
        graphics.text(this.font,Component.literal("Block Suggestions"),minecraft.getWindow().getGuiScaledWidth()/2 -42, minecraft.getWindow().getGuiScaledHeight()/2 -120, CommonColors.WHITE);
        graphics.textWithWordWrap(this.font, FormattedText.of("§4§lWarning§r:Not all blocks requested blocks will be Added due To Knowladge limits or balancing issues"),minecraft.getWindow().getGuiScaledWidth()/2 -90, minecraft.getWindow().getGuiScaledHeight()/2 -100, 200, CommonColors.WHITE);
    }
}

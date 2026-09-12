package net.gamer.consumableblocks.menu;
import net.gamer.consumableblocks.item.ModItems;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.PlainTextButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import org.jspecify.annotations.NonNull;

public class ConfigScreen extends Screen {
        public ConfigScreen(Component title) {
            super(title);
        }

        @Override
        protected void init() {

            Button Ice = Button.builder(Component.literal("ice"), (btn) -> {
                // When the button is clicked, we can display a toast to the screen.
                assert minecraft.player != null;
                minecraft.player.sendSystemMessage(Component.literal("clicked Ice"));
//                ClientPlayNetworking.send(new UiPayloadC2S("config",1));
                ;
            }).bounds(this.minecraft.getWindow().getGuiScaledWidth()-this.width+150, 80 - this.font.lineHeight, 16, 16).build();

            Button Slime = Button.builder(Component.literal("slime"), (btn) -> {
                // When the button is clicked, we can display a toast to the screen.

                assert minecraft.player != null;
                minecraft.player.sendSystemMessage(Component.literal("clicked Slime"));
//                ClientPlayNetworking.send(new UiPayloadC2S("config",2));
                ;
            }).bounds(this.minecraft.getWindow().getGuiScaledWidth()-this.width+150, 120 - this.font.lineHeight, 16, 16).build();

            Button Obsidian = Button.builder(Component.literal("obsidian"), (btn) -> {
                // When the button is clicked, we can display a toast to the screen.
                assert minecraft.player != null;
                minecraft.player.sendSystemMessage(Component.literal("clicked Obsidian"));
//                ClientPlayNetworking.send(new UiPayloadC2S("config",1));
                ;
            }).bounds(this.minecraft.getWindow().getGuiScaledWidth()-this.width+150, 160 - this.font.lineHeight, 16, 16).build();

            Button Furnace = Button.builder(Component.literal("furnace"), (btn) -> {
                // When the button is clicked, we can display a toast to the screen.
                assert minecraft.player != null;
                minecraft.player.sendSystemMessage(Component.literal("clicked furnace"));

//                ClientPlayNetworking.send(new UiPayloadC2S("config",2));
                ;
            }).bounds(this.minecraft.getWindow().getGuiScaledWidth()-this.width+150, 200 - this.font.lineHeight, 16, 16).build();
            PlainTextButton Iceon =  new PlainTextButton(this.minecraft.getWindow().getGuiScaledWidth()-this.width+180, 83 - this.font.lineHeight,20,20,Component.literal("§a§lON"),(textbtn)-> {
                assert minecraft.player != null;
                minecraft.player.sendSystemMessage(Component.literal("clicked Ice On"));
            },this.font);

            PlainTextButton IceStatus =  new PlainTextButton(this.minecraft.getWindow().getGuiScaledWidth()-this.width+210, 83 - this.font.lineHeight,20,20,Component.literal("§6§l?"),(textbtn)-> {
                assert minecraft.player != null;
                minecraft.player.sendSystemMessage(Component.literal("clicked Ice Status"));
            },this.font);

            PlainTextButton Iceoff =  new PlainTextButton(this.minecraft.getWindow().getGuiScaledWidth()-this.width+180, 83 - this.font.lineHeight,20,20,Component.literal("on"),(textbtn)-> {
                assert minecraft.player != null;
                minecraft.player.sendSystemMessage(Component.literal("clicked Ice off"));
            },this.font);
            Component IceTooltip = Component.literal(ModItems.EdibleIce.getDefaultInstance().getItemName().getString().indent(1).stripLeading()).append(Component.translatable("consumableblocks.edibleice.tooltip"));
            Ice.setAlpha(0f);
            Ice.setTooltip(Tooltip.create(IceTooltip));
            Slime.setAlpha(0f);
            Obsidian.setAlpha(0f);
            Furnace.setAlpha(0f);
            this.addRenderableWidget(Iceon);
            this.addRenderableWidget(IceStatus);
            this.addRenderableWidget(Ice);
            this.addRenderableWidget(Slime);
            this.addRenderableWidget(Obsidian);
            this.addRenderableWidget(Furnace);

        }

        @Override
        public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
            super.extractRenderState(graphics, mouseX, mouseY, delta);

            // Minecraft doesn't have a "label" widget, so we'll have to draw our own text.
            // We'll subtract the font height from the Y position to make the text appear above the button.
            // Subtracting an extra 10 pixels will give the text some padding.
            // font, text, x, y, color, hasShadow
            graphics.textWithWordWrap(this.font, FormattedText.of("Turn on or off the ability to consume certain blocks.Also Check If the block is enabled §lthis will only work if you have crafted the block To unlock it"),this.minecraft.getWindow().getGuiScaledWidth()-this.width+150, 40 - this.font.lineHeight - 20, 200,0xFFFFFFFF);
            graphics.fakeItem(ModItems.EdibleIce.getDefaultInstance(),this.minecraft.getWindow().getGuiScaledWidth()-this.width+150, 80 - this.font.lineHeight);
            graphics.fakeItem(ModItems.EdibleSlime.getDefaultInstance(),this.minecraft.getWindow().getGuiScaledWidth()-this.width+150, 120 - this.font.lineHeight);
            graphics.fakeItem(ModItems.EdibleObsidian.getDefaultInstance(),this.minecraft.getWindow().getGuiScaledWidth()-this.width+150, 160 - this.font.lineHeight);
            graphics.fakeItem(ModItems.EdibleFurnace.getDefaultInstance(),this.minecraft.getWindow().getGuiScaledWidth()-this.width+150, 200 - this.font.lineHeight);
        }

}

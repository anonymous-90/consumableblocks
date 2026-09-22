package net.gamer.consumableblocks.menu;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.gamer.consumableblocks.ConsumableBlocks;
import net.gamer.consumableblocks.DataAttachments.Abilities;
import net.gamer.consumableblocks.item.ModItems;
import net.gamer.consumableblocks.networking.packet.UiPayloadC2S;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.util.CommonColors;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Optional;
import org.jspecify.annotations.NonNull;

public class ConfigScreen extends Screen {

    private BlockList list;
    private static final Identifier test = Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"hud/menu_background");

    public ConfigScreen(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        int listWidth = 220;
        int listHeight = this.height - 90;
        this.list = new BlockList(this.minecraft, listWidth, listHeight, 70, 24);
        this.list.setX((this.width - listWidth) / 2);
        this.list.addBlock(ModItems.EdibleIce, "Ice", "consumableblocks.edibleice.tooltip", Abilities.get(minecraft.player).has(Abilities.IceEnabled));
        this.list.addBlock(ModItems.EdibleSlime, "Slime", "consumableblocks.edibleslime.tooltip", Abilities.get(minecraft.player).has(Abilities.SlimeEnabled));
        this.list.addBlock(ModItems.EdibleObsidian, "Obsidian", "consumableblocks.edibleobsidian.tooltip", Abilities.get(minecraft.player).has(Abilities.ObsidianEnabled));
        this.list.addBlock(ModItems.EdibleFurnace, "Furnace","consumableblocks.ediblefurnace.tooltip", Abilities.get(minecraft.player).has(Abilities.FurnaceEnabled));;
        PlainTextButton back = new PlainTextButton(minecraft.getWindow().getGuiScaledWidth()/2 -10,list.getBottom()+7,20,20,Component.literal("back"),button -> {
            minecraft.gui.setScreen(new MainMenu(Component.empty()));
        },font);
        this.addRenderableWidget(this.list);
        this.addRenderableWidget(back);
    }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);
        graphics.textWithWordWrap(this.font, FormattedText.of("Turn on or off the ability to consume certain blocks. This will only work if you have crafted the block to unlock it."), (this.width - 220) / 2, 20, 220, CommonColors.WHITE);

    }


    class BlockList extends ObjectSelectionList<BlockEntry> {

        BlockList(Minecraft minecraft, int width, int height, int y, int itemHeight) {
            super(minecraft, width, height, y, itemHeight);
        }

        void addBlock(Item item, String name, String tooltipKey, boolean enabled) {
            this.addEntry(new BlockEntry(item, name, tooltipKey,enabled));
        }

        @Override
        public int getRowWidth() {
            return this.getWidth() - 20;
        }

        @Override
        protected void extractListBackground(@NonNull GuiGraphicsExtractor graphics) {
            super.extractListBackground(graphics);
            int listWidth = 220;
            int listHeight = this.height - 90;
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED,test,list.getX(),list.getY(),listWidth,listHeight+90);
        }
    }

    class BlockEntry extends ObjectSelectionList.Entry<BlockEntry> {

        private final ItemStack stack;
        private final String name;
        private final String tooltipKey;
        private boolean enabled;

        BlockEntry(Item item, String name, String tooltipKey, boolean enabled) {
            this.stack = new ItemStack(item);
            this.name = name;
            this.tooltipKey = tooltipKey;
            this.enabled = enabled;
        }

        @Override
        public void extractContent(GuiGraphicsExtractor graphics, int mouseX, int mouseY, boolean hovered, float delta) {
            int x = this.getContentX();
            int y = this.getContentY();
            graphics.fakeItem(this.stack, x, y);
//            graphics.text(ConfigScreen.this.font, this.name, x + 24, y + 4, CommonColors.WHITE);
            String status = this.enabled ? "ON" : "OFF";
            int color = this.enabled ? CommonColors.GREEN : CommonColors.SOFT_RED;
            graphics.text(ConfigScreen.this.font, status, this.getContentRight() - ConfigScreen.this.font.width(status) - 4, y + 4, color);
            if(mouseX > 142 && mouseX < 157){
            if (hovered) {
                List<Component> tooltip = List.of(this.stack.getHoverName(), Component.translatable(this.tooltipKey));
                graphics.setTooltipForNextFrame(ConfigScreen.this.font, tooltip, Optional.empty(),mouseX,mouseY);
//                minecraft.player.sendSystemMessage(Component.literal("mouseY: " + mouseY+"mouseX: " + mouseX));
            }

            }
        }


        @Override
        public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
            this.enabled = !this.enabled;
            if (ConfigScreen.this.minecraft.player != null && this.name.equals("Ice")) {
                ClientPlayNetworking.send(new UiPayloadC2S("config",1));

            }  if (ConfigScreen.this.minecraft.player != null && this.name.equals("Slime")) {
                ClientPlayNetworking.send(new UiPayloadC2S("config",2));

            }  if (ConfigScreen.this.minecraft.player != null && this.name.equals("Obsidian")) {
                ClientPlayNetworking.send(new UiPayloadC2S("config",3));

            }  if (ConfigScreen.this.minecraft.player != null && this.name.equals("Furnace")) {
                ClientPlayNetworking.send(new UiPayloadC2S("config",4));

            }
            return true;
        }


        @Override
        public Component getNarration() {
            return Component.literal(this.name);
        }
    }

}

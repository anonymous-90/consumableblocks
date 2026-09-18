package net.gamer.consumableblocks.menu;

import net.gamer.consumableblocks.DataAttachments.Abilities;
import net.gamer.consumableblocks.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.item.Item;
import net.minecraft.util.CommonColors;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Optional;
import org.jspecify.annotations.NonNull;

public class ConfigScreen extends Screen {

    private BlockList list;

    public ConfigScreen(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        int listWidth = 220;
        int listHeight = this.height - 90;
        this.list = new BlockList(this.minecraft, listWidth, listHeight, 70, 24);
        this.list.setX((this.width - listWidth) / 2);
        this.list.addBlock(ModItems.EdibleIce, "Ice", "consumableblocks.edibleice.tooltip", false);
        this.list.addBlock(ModItems.EdibleSlime, "Slime", "consumableblocks.edibleslime.tooltip", false);
        this.list.addBlock(ModItems.EdibleObsidian, "Obsidian", "consumableblocks.edibleobsidian.tooltip", false);
        this.list.addBlock(ModItems.EdibleFurnace, "Furnace", "consumableblocks.ediblefurnace.tooltip", Abilities.get(minecraft.player).has(Abilities.FurnaceEnabled));

//        Item[] extras = {Items.DIRT, Items.STONE, Items.OAK_LOG, Items.APPLE, Items.DIAMOND, Items.IRON_INGOT, Items.GOLD_INGOT, Items.BREAD, Items.CARROT, Items.POTATO, Items.BEEF, Items.PORKCHOP, Items.CHICKEN, Items.COAL, Items.EMERALD, Items.REDSTONE, Items.SUGAR, Items.WHEAT, Items.EGG, Items.FEATHER};
//        for (Item extra : extras) {
//            this.list.addBlock(extra, new ItemStack(extra).getHoverName().getString(), "consumableblocks.edibleice.tooltip");
//        }

        this.addRenderableWidget(this.list);
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
            graphics.text(ConfigScreen.this.font, this.name, x + 24, y + 4, CommonColors.WHITE);
            String status = this.enabled ? "ON" : "OFF";
            int color = this.enabled ? CommonColors.GREEN : CommonColors.SOFT_RED;
            graphics.text(ConfigScreen.this.font, status, this.getContentRight() - ConfigScreen.this.font.width(status) - 4, y + 4, color);
            if (hovered) {
                List<Component> tooltip = List.of(this.stack.getHoverName(), Component.translatable(this.tooltipKey));
                graphics.setTooltipForNextFrame(ConfigScreen.this.font, tooltip, Optional.empty(), mouseX, mouseY);
            }
        }

        @Override
        public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
            this.enabled = !this.enabled;
            if (ConfigScreen.this.minecraft.player != null) {
                ConfigScreen.this.minecraft.player.sendSystemMessage(Component.literal(this.name + " is now " + (this.enabled ? "ON" : "OFF")));
            }
            return true;
        }

        @Override
        public Component getNarration() {
            return Component.literal(this.name);
        }
    }
}

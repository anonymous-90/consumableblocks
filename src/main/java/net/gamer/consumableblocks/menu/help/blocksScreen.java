package net.gamer.consumableblocks.menu.help;

import net.gamer.consumableblocks.ConsumableBlocks;
import net.gamer.consumableblocks.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.Identifier;
import net.minecraft.util.CommonColors;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

public class blocksScreen extends Screen {

    private BlockList list;
    public blocksScreen(Component title) {
        super(title);
    }
    private static final Identifier MENU_LIST_BACKGROUND = Identifier.withDefaultNamespace("textures/gui/menu_list_background.png");

    private static final Identifier InworldListBackhround = Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"inworld_menu_list_background");

    @Override
    protected void init() {
        int listWidth = 420;
        int listHeight = this.height - 90;
        this.list = new BlockList(this.minecraft, listWidth, listHeight, 70, 24);
        this.list.setX((this.width - listWidth) / 2);
        this.list.addBlock(ModItems.EdibleIce, "Ice", "gives you speed 2x Until you drink the gene cleanser.To gain the ability back you must eat the block again");
        this.list.addBlock(ModItems.EdibleSlime, "Slime", "makes you bouncy Until you drink the gene cleanser To gain the ability back you must eat the block again");
        this.list.addBlock(ModItems.EdibleObsidian, "Obsidian", "gives you explosion knockback resistance Until you drink the gene cleanser To gain the ability back you must eat the block again");
        this.list.addBlock(ModItems.EdibleFurnace, "Furnace", "smelt items on right Click as long as you have fuel.Until you drink the gene cleanser.To gain the ability back you must eat the block again");
        this.list.addBlock(ModItems.EdibleCarvedPumpkin, "Cavrved Pumpkin", "Hides you from the endermen's gaze");
        Button back = Button.builder(Component.empty(),button -> {
            minecraft.gui.setScreen(new helpMainMenu(Component.empty()));
        }).bounds(minecraft.getWindow().getGuiScaledWidth()/2 -10,list.getBottom()+7,20,20).build();
        back.setAlpha(0);


        this.addRenderableWidget(this.list);
        this.addRenderableWidget(back);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED,InworldListBackhround,0,0,this.width,this.height);
    }


    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);
        graphics.textWithWordWrap(this.font, FormattedText.of("Blocks Information"), minecraft.getWindow().getGuiScaledWidth()/2 -22,  minecraft.getWindow().getGuiScaledHeight()/2 -120, 220, CommonColors.WHITE);
        graphics.text(font,Component.literal("Back"),minecraft.getWindow().getGuiScaledWidth()/2 -10,list.getBottom()+7,CommonColors.WHITE);

    }
    class BlockList extends ObjectSelectionList<BlockEntry> {

        BlockList(Minecraft minecraft, int width, int height, int y, int itemHeight) {
            super(minecraft, width, height, y, itemHeight);

        }
        void addBlock(Item item, String name, String descrition) {
            this.addEntry(new BlockEntry(item, name,descrition));
        }

        @Override
        public int getRowWidth() {
            return this.getWidth() - 20;
        }

        @Override
        protected void extractListBackground(GuiGraphicsExtractor graphics) {

        }

        @Override
        protected void extractListSeparators(GuiGraphicsExtractor graphics) {
        }

        @Override
        protected void extractSelection(GuiGraphicsExtractor graphics, BlockEntry entry, int outlineColor) {
            super.extractSelection(graphics,entry,outlineColor);
        }

    }



    class BlockEntry extends ObjectSelectionList.Entry<BlockEntry> {

        private final ItemStack stack;
        private final String name;
        private final String description;
        private boolean enabled;

        BlockEntry(Item item, String name, String description) {
            this.stack = new ItemStack(item);
            this.name = name;
            this.description = description;
        }
        @Override
        public void extractContent(GuiGraphicsExtractor graphics, int mouseX, int mouseY, boolean hovered, float delta) {
            int x = this.getContentX();
            int y = this.getContentY();
            graphics.fakeItem(this.stack, x, y);
            graphics.textWithWordWrap(blocksScreen.this.font, FormattedText.of(description), x+20, y + 5, 400, CommonColors.WHITE);

        }

        @Override
        public Component getNarration() {
            return Component.literal(this.name);
        }


    }

}

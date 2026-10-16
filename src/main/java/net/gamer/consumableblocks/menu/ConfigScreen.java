package net.gamer.consumableblocks.menu;

import com.mojang.blaze3d.pipeline.RenderPipeline;
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
    public ConfigScreen(Component title) {
        super(title);
    }
    private static final Identifier MENU_LIST_BACKGROUND = Identifier.withDefaultNamespace("textures/gui/menu_list_background.png");

    private static final Identifier InworldListBackhround = Identifier.fromNamespaceAndPath(ConsumableBlocks.MOD_ID,"inworld_menu_list_background");

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
        this.list.addBlock(ModItems.EdibleCarvedPumpkin, "Carved pumpkin","consumableblocks.ediblefurnace.tooltip", Abilities.get(minecraft.player).has(Abilities.CarvedPumpkinEnabled));;
        Button back = Button.builder(Component.empty(),button -> {
            minecraft.gui.setScreen(new MainMenu(Component.empty()));
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
        graphics.textWithWordWrap(this.font, FormattedText.of("Turn on or off the ability to consume certain blocks. This will only work if you have crafted the block to unlock it.\n\n§6§lNotice:§rDisabling a block will make the ability no longer obtainable. However if you already have the ability it will remain"), (this.width - 220) / 2, 3, 220, CommonColors.WHITE);
        graphics.text(font,Component.literal("Back"),minecraft.getWindow().getGuiScaledWidth()/2 -10,list.getBottom()+7,CommonColors.WHITE);

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
        protected void extractListBackground(GuiGraphicsExtractor graphics) {

        }

        @Override
        protected void extractListSeparators(GuiGraphicsExtractor graphics) {
            super.extractListSeparators(graphics);
        }

        @Override
        protected void extractSelection(GuiGraphicsExtractor graphics, BlockEntry entry, int outlineColor) {
            super.extractSelection(graphics,entry,outlineColor);
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
                graphics.setTooltipForNextFrame(ConfigScreen.this.font,Component.literal("§6§lNotice:§rDisabling A Block Will Make The Ability No Longer Obtainable However If You Already Have The Ability It Will Remain"),mouseX,mouseY);
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

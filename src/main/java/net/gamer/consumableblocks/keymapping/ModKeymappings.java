package net.gamer.consumableblocks.keymapping;

import com.mojang.blaze3d.platform.InputConstants;
import net.gamer.consumableblocks.ConsumableBlocks;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class ModKeymappings {
    public static final KeyMapping SmeltItem = new KeyMapping("key.consumable_blocks_key", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_B,KeyMapping.Category.MISC);

    public  static void Register(){
        ConsumableBlocks.LOGGER.info("registering keybinds for" + ConsumableBlocks.MOD_ID);
    }
}

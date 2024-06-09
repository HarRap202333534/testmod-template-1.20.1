package net.flabu.testmod.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.flabu.testmod.gui.TestGui;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.security.Key;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_TEST = "key.category.testmod.test";
    public static final String KEY_OPEN_GUI = "key.testmod.open_gui";

    public static KeyBinding openGuiKey;

    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (openGuiKey.wasPressed()) {
                MinecraftClient.getInstance().setScreen(new TestGui());
            }
        });
    }

    public static void register(){
        openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_OPEN_GUI,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_F4,
                KEY_CATEGORY_TEST
        ));

        registerKeyInputs();
    }
}

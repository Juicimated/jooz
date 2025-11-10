package dev.juici.jooz;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class JoozLibClient implements ClientModInitializer {
    private static KeyBinding[] activeKeys = new KeyBinding[5];
    private static KeyBinding switchGroupKey;

    @Override
    public void onInitializeClient() {
        activeKeys[0] = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.jooz.active1",
                GLFW.GLFW_KEY_V, "category.jooz"));
        activeKeys[1] = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.jooz.active2",
                GLFW.GLFW_KEY_B, "category.jooz"));
        activeKeys[2] = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.jooz.active3",
                GLFW.GLFW_KEY_N, "category.jooz"));
        activeKeys[3] = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.jooz.active4",
                GLFW.GLFW_KEY_M, "category.jooz"));
        activeKeys[4] = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.jooz.active5",
                GLFW.GLFW_KEY_COMMA, "category.jooz"));

        switchGroupKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.jooz.switch_group",
                GLFW.GLFW_KEY_PERIOD, "category.jooz"));
    }
}
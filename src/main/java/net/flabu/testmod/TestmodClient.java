package net.flabu.testmod;

import net.fabricmc.api.ClientModInitializer;
import net.flabu.testmod.event.KeyInputHandler;
import net.minecraft.client.gui.screen.Screen;

public class TestmodClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
    }
}

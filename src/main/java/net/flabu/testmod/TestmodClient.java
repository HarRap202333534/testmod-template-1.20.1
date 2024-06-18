package net.flabu.testmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.flabu.testmod.entity.ModEntities;
import net.flabu.testmod.entity.custom.CocatrixEntityRenderer;
import net.flabu.testmod.event.KeyInputHandler;
import net.minecraft.client.gui.screen.Screen;

public class TestmodClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
        EntityRendererRegistry.register(ModEntities.COCATRIX, CocatrixEntityRenderer::new);
    }
}

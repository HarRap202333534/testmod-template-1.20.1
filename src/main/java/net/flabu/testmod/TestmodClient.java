package net.flabu.testmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.flabu.testmod.block.ModBlocks;
import net.flabu.testmod.block.entity.ModBlockEntities;
import net.flabu.testmod.block.entity.client.AnimatedBlockRenderer;
import net.flabu.testmod.entity.ModEntities;
import net.flabu.testmod.entity.custom.CocatrixEntityRenderer;
import net.flabu.testmod.event.KeyInputHandler;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class TestmodClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
        EntityRendererRegistry.register(ModEntities.COCATRIX, CocatrixEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.ANIMATED_BLOCK_ENTITY, AnimatedBlockRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GRINDSTONE, RenderLayer.getTranslucent());
    }
}

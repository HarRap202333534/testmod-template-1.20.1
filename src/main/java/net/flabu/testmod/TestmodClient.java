package net.flabu.testmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.mixin.client.rendering.LivingEntityRendererAccessor;
import net.flabu.testmod.block.ModBlocks;
import net.flabu.testmod.block.entity.ModBlockEntities;
import net.flabu.testmod.block.entity.client.AnimatedBlockRenderer;
import net.flabu.testmod.entity.ModEntities;
import net.flabu.testmod.entity.custom.CocatrixEntityRenderer;
import net.flabu.testmod.event.KeyInputHandler;
import net.flabu.testmod.mixin.EntityRenderersAccessor;
import net.flabu.testmod.util.ExampleDummyPlayerRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.sound.SoundEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import org.intellij.lang.annotations.Identifier;

import javax.naming.Context;
import java.util.HashMap;
import java.util.Map;

public class TestmodClient implements ClientModInitializer {
    public static final Map<String, net.minecraft.util.Identifier> textureMap = new HashMap<>();

    public static Map<String, net.minecraft.util.Identifier> getMap(){
        return textureMap;
    }


    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
        EntityRendererRegistry.register(ModEntities.COCATRIX, CocatrixEntityRenderer::new);
        EntityRendererRegistry.register(EntityType.PLAYER, ExampleDummyPlayerRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.ANIMATED_BLOCK_ENTITY, AnimatedBlockRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GRINDSTONE, RenderLayer.getTranslucent());

        NbtCompound nbt = new NbtCompound();
        nbt.get("url");
    }

}

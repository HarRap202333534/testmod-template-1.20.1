package net.flabu.testmod.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.flabu.testmod.Testmod;
import net.flabu.testmod.TestmodClient;
import net.flabu.testmod.mixin.ExampleMixin;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class ExampleDummyPlayerRenderer extends LivingEntityRenderer {


    public ExampleDummyPlayerRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new PlayerEntityModel(ctx.getPart(EntityModelLayers.PLAYER), false), 0.5F);
    }

    @Override
    public Identifier getTexture(Entity entity) {
        int i = 0;
        NbtCompound cmpd = new NbtCompound();
        if (entity instanceof PlayerEntity) {
            PlayerEntity dummyEntity = (PlayerEntity) entity;
            String url = cmpd.getString("url");
            Testmod.LOGGER.info(url);
            String entityId = url.replaceAll("[^a-zA-Z0-9]", "").toLowerCase(Locale.ROOT);
            // Check if the texture has already been registered
            if (TestmodClient.textureMap.containsKey(entityId)) {
                return TestmodClient.textureMap.get(entityId);
            }

            // Create NativeImage from URL
            NativeImage image = null;
            try {
                // Fetch the image from the URL
                URL imageURL = new URL(url);
                InputStream imageStream = imageURL.openStream();

                // Convert the image to a NativeImage
                image = NativeImage.read(imageStream);
            } catch (Exception e) {
                Testmod.LOGGER.info("Error loading image from URL: " + url + " - " + e.toString());
                return new Identifier("minecraft", "textures/entity/player/wide/steve.png");
            }

            // Apply the texture to the player
            TextureManager textureManager = this.dispatcher.textureManager;
            NativeImageBackedTexture texture = new NativeImageBackedTexture(image);
            Identifier textureId = new Identifier("modid", "dummyplayertexture" + entityId);
            textureManager.registerTexture(textureId, texture);

            // Store the texture UUID in the texture map to prevent lag
            TestmodClient.textureMap.put(entityId, textureId);

            return textureId;
        }
        return new Identifier("minecraft", "textures/entity/player/wide/steve.png");
    }
}
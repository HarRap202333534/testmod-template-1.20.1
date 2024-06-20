package net.flabu.testmod.block.entity.client;

import mod.azure.azurelib.model.GeoModel;
import net.flabu.testmod.Testmod;
import net.flabu.testmod.block.entity.AnimatedBlockEntity;
import net.minecraft.util.Identifier;

public class AnimatedBlockModel extends GeoModel<AnimatedBlockEntity> {
    @Override
    public Identifier getModelResource(AnimatedBlockEntity animatedBlockEntity) {
        return new Identifier(Testmod.MOD_ID, "geo/grindstone.geo.json");
    }

    @Override
    public Identifier getTextureResource(AnimatedBlockEntity animatedBlockEntity) {
        return new Identifier(Testmod.MOD_ID, "textures/block/grindstone_azure_texture.png");
    }

    @Override
    public Identifier getAnimationResource(AnimatedBlockEntity animatedBlockEntity) {
        return new Identifier(Testmod.MOD_ID, "aniamtions/grindstone.animation.json");
    }
}

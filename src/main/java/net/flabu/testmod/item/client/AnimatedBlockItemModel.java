package net.flabu.testmod.item.client;

import mod.azure.azurelib.model.GeoModel;
import net.flabu.testmod.Testmod;
import net.flabu.testmod.item.AnimatedBlockItem;
import net.minecraft.util.Identifier;

public class AnimatedBlockItemModel extends GeoModel<AnimatedBlockItem> {
    @Override
    public Identifier getModelResource(AnimatedBlockItem animatedBlockItem) {
        return new Identifier(Testmod.MOD_ID, "geo/grindstone.geo.json");
    }

    @Override
    public Identifier getTextureResource(AnimatedBlockItem animatedBlockItem) {
        return new Identifier(Testmod.MOD_ID, "textures/block/grindstone_azure_texture.png");
    }

    @Override
    public Identifier getAnimationResource(AnimatedBlockItem animatedBlockItem) {
        return new Identifier(Testmod.MOD_ID, "animations/grindstone.animation.json");
    }
}

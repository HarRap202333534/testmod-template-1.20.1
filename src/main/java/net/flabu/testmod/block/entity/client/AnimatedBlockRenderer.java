package net.flabu.testmod.block.entity.client;

import mod.azure.azurelib.renderer.GeoBlockRenderer;
import net.flabu.testmod.block.entity.AnimatedBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;

public class AnimatedBlockRenderer extends GeoBlockRenderer<AnimatedBlockEntity> {
    public AnimatedBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new AnimatedBlockModel());
    }
}

package net.flabu.testmod.item.client;

import mod.azure.azurelib.model.GeoModel;
import mod.azure.azurelib.renderer.GeoItemRenderer;
import net.flabu.testmod.item.AnimatedBlockItem;

public class AnimatedBlockItemRenderer extends GeoItemRenderer<AnimatedBlockItem> {
    public AnimatedBlockItemRenderer() {
        super(new AnimatedBlockItemModel());
    }
}

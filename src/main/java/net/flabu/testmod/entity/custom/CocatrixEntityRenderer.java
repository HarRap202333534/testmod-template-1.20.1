package net.flabu.testmod.entity.custom;

import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.flabu.testmod.Testmod;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class CocatrixEntityRenderer extends GeoEntityRenderer<CocatrixEntity> {
    public CocatrixEntityRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new CocatrixModel());
    }

    @Override
    public Identifier getTextureLocation(CocatrixEntity animatable) {
        return new Identifier(Testmod.MOD_ID, "textures/texture.png");
    }

    @Override
    public RenderLayer getRenderType(CocatrixEntity animatable, Identifier texture, @Nullable VertexConsumerProvider bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }

    @Override
    public void render(CocatrixEntity entity, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}

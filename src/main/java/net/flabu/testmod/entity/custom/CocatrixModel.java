package net.flabu.testmod.entity.custom;

import mod.azure.azurelib.constant.DataTickets;
import mod.azure.azurelib.core.animatable.model.CoreGeoBone;
import mod.azure.azurelib.core.animation.AnimationState;
import mod.azure.azurelib.model.GeoModel;
import mod.azure.azurelib.model.data.EntityModelData;
import net.flabu.testmod.Testmod;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class CocatrixModel extends GeoModel<CocatrixEntity> {
    private static final Identifier model = new Identifier(Testmod.MOD_ID, "geo/cocatrix_1.geo.json");
    private static final Identifier texture = new Identifier(Testmod.MOD_ID, "textures/texture.png");
    private static final Identifier animation = new Identifier(Testmod.MOD_ID, "animations/model.animation.json");

    @Override
    public Identifier getModelResource(CocatrixEntity cocatrixEntity) {
        return this.model;
    }

    @Override
    public Identifier getTextureResource(CocatrixEntity cocatrixEntity) {
        return this.texture;
    }

    @Override
    public Identifier getAnimationResource(CocatrixEntity cocatrixEntity) {
        return this.animation;
    }

    @Override
    public void setCustomAnimations(CocatrixEntity animatable, long instanceId, AnimationState<CocatrixEntity> animationState) {
        CoreGeoBone tete = getAnimationProcessor().getBone("tete");

        if(tete != null){
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            tete.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            tete.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}

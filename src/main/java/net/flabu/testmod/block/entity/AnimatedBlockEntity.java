package net.flabu.testmod.block.entity;

import mod.azure.azurelib.animatable.GeoBlockEntity;
import mod.azure.azurelib.core.animatable.GeoAnimatable;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.*;
import mod.azure.azurelib.core.object.PlayState;
import mod.azure.azurelib.util.AzureLibUtil;
import mod.azure.azurelib.util.ClientUtils;
import mod.azure.azurelib.util.RenderUtils;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.flabu.testmod.item.CustomModItems;
import net.flabu.testmod.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.net.UnknownServiceException;
import java.util.function.Predicate;

public class AnimatedBlockEntity extends BlockEntity implements GeoBlockEntity, UseBlockCallback {
    private AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);

    public AnimatedBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ANIMATED_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", event -> PlayState.CONTINUE)
                .triggerableAnim("animation.model.rolling_stone", RawAnimation.begin().thenPlay("animation.model.rolling_stone")));
    }

    public void used(){
        this.triggerAnim("controller", "animation.model.rolling_stone");
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object blockEntity) {
        return RenderUtils.getCurrentTick();
    }

    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        ItemStack stack = player.getMainHandStack();
        if(stack.isOf(ModItems.CUSTOM_TEST) || stack.isOf(ModItems.CUSTOM_TEST2)){
            this.triggerAnim("controller", "animation.model.rolling_stone");
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}

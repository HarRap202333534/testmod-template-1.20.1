package net.flabu.testmod.mixin;

import com.google.common.collect.ImmutableMap;
import net.flabu.testmod.Testmod;
import net.flabu.testmod.util.ExampleDummyPlayerRenderer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.EntityRenderers;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(EntityRenderers.class)
public interface EntityRenderersAccessor {
    @Accessor("PLAYER_RENDERER_FACTORIES")
    public static void setRenderer(Map<String, EntityRendererFactory<AbstractClientPlayerEntity>> map) {
        map.put("default", context -> new ExampleDummyPlayerRenderer(context));
        Testmod.LOGGER.info("yyyyyyy");
    }

    @Accessor("PLAYER_RENDERER_FACTORIES")
    public static Map<String, EntityRendererFactory<AbstractClientPlayerEntity>> getRenderer(){
        return ImmutableMap.of("default", context -> new PlayerEntityRenderer(context, false), "slim", context -> new PlayerEntityRenderer(context, true));
    }
}

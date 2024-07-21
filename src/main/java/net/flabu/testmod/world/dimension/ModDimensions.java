package net.flabu.testmod.world.dimension;

import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.flabu.testmod.Testmod;
import net.flabu.testmod.block.ModBlocks;
import net.flabu.testmod.block.entity.AnimatedBlockEntity;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.OptionalLong;

public class ModDimensions {
    public static final RegistryKey<DimensionOptions> INVERTED_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            new Identifier(Testmod.MOD_ID, "invert"));
    public static final RegistryKey<World> INVERTED_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            new Identifier(Testmod.MOD_ID, "invert"));
    public static final RegistryKey<DimensionType> INVERTED_DIM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            new Identifier(Testmod.MOD_ID, "invert_type"));



    public static void bootstrapType(Registerable<DimensionType> context){
        context.register(INVERTED_DIM_TYPE, new DimensionType(
                OptionalLong.of(12000),
                false,
                false,
                false,
                false,
                1.0,
                true,
                true,
                0,
                256,
                256,
                BlockTags.INFINIBURN_END,
                DimensionTypes.OVERWORLD_ID,
                1.0f,
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)));
    }

    public static void register(){
        Testmod.LOGGER.info("Registering Dimensions for " + Testmod.MOD_ID);
    }
}

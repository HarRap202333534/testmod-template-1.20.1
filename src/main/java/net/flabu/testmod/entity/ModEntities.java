package net.flabu.testmod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.mixin.dimension.RegistryCodecsMixin;
import net.flabu.testmod.Testmod;
import net.flabu.testmod.entity.custom.CocatrixEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<CocatrixEntity> COCATRIX = Registry.register(Registries.ENTITY_TYPE, new Identifier(Testmod.MOD_ID, "cocatrix"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CocatrixEntity::new).dimensions(EntityDimensions.fixed(3f, 5f)).build());
}

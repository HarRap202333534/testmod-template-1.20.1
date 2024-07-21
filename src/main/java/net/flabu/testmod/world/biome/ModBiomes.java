package net.flabu.testmod.world.biome;

import net.flabu.testmod.Testmod;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class ModBiomes {
    public static final RegistryKey<Biome> INVERTED_BIOME = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(Testmod.MOD_ID, "inverted_biome"));

    public static void bootstrap(Registerable<Biome> ctx){
        ctx.register(INVERTED_BIOME, CustomModBiomes.invertedBiome(ctx));
    }
}

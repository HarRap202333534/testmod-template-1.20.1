package net.flabu.testmod;

import mod.azure.azurelib.AzureLib;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.flabu.testmod.block.ModBlocks;
import net.flabu.testmod.block.entity.ModBlockEntities;
import net.flabu.testmod.entity.ModEntities;
import net.flabu.testmod.entity.custom.CocatrixEntity;
import net.flabu.testmod.event.AttackEntityHandler;
import net.flabu.testmod.item.ModItems;
import net.flabu.testmod.sound.ModSounds;
import net.flabu.testmod.world.dimension.ModDimensions;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Testmod implements ModInitializer {
    public static final String MOD_ID = "testmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		AttackEntityCallback.EVENT.register(new AttackEntityHandler());

		ModBlockEntities.registerAllBlockEntities();

		AzureLib.initialize();
		FabricDefaultAttributeRegistry.register(ModEntities.COCATRIX, CocatrixEntity.setAttributes());
		ModSounds.registerSounds();

		ModDimensions.register();
	}
}
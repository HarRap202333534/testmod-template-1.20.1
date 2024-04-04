package net.flabu.testmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.flabu.testmod.event.AttackEntityHandler;
import net.flabu.testmod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Testmod implements ModInitializer {
    public static final String MOD_ID = "testmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		AttackEntityCallback.EVENT.register(new AttackEntityHandler());
	}
}
package net.flabu.testmod.item;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.flabu.testmod.Testmod;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item TEST = registerItem("test", new Item(new FabricItemSettings()));

    public static final Item CUSTOM_TEST = registerItem("custom_test", new CustomModItems(ModToolMaterial.TEST, 0, 2f, new FabricItemSettings()));
    public static final Item CUSTOM_TEST2 = registerItem("custom_test2", new CustomModItems(ModToolMaterial.TEST, 4, 2f, new FabricItemSettings()));

    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(TEST);
    }
    private static void addItemsToCombatItemGroup(FabricItemGroupEntries entries) {
        entries.add(CUSTOM_TEST);
        entries.add(CUSTOM_TEST2);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Testmod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Testmod.LOGGER.info("Registering Mod Items for " + Testmod.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientTabItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatItemGroup);
    }
}

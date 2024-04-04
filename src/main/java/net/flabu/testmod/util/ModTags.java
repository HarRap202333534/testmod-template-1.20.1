package net.flabu.testmod.util;

import net.flabu.testmod.Testmod;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> MODDED_WEPAONS =
                createTag("modded_weapons");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(Testmod.MOD_ID, name));
        }
    }
}

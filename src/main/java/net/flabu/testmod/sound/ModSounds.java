package net.flabu.testmod.sound;
;
import net.flabu.testmod.Testmod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final Identifier GRINDSTONE_USE_ID = Identifier.of("testmod", "grindstone_use_sound");
    public static final SoundEvent GRINDSTONE_USE_EVENT = SoundEvent.of(GRINDSTONE_USE_ID);

    public static final SoundEvent[] CUSTOM_SOUND_EVENTS = {
            GRINDSTONE_USE_EVENT
    };
    public static final Identifier[] CUSTOM_SOUND_IDENTIFIERS = {
            GRINDSTONE_USE_ID
    };

    public static void registerSounds(){
        Testmod.LOGGER.info("Registering sounds for " + Testmod.MOD_ID);
        for(int i = 0; i < CUSTOM_SOUND_EVENTS.length; i++){
            Registry.register(Registries.SOUND_EVENT, CUSTOM_SOUND_IDENTIFIERS[i], CUSTOM_SOUND_EVENTS[i]);
        }
    }
}

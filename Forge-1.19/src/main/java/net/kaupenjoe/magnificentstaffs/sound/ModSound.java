package net.kaupenjoe.magnificentstaffs.sound;

import net.kaupenjoe.magnificentstaffs.StaffsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSound {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, StaffsMod.MOD_ID);

    public static RegistryObject<SoundEvent> MAGIC_SHOOT = registerSoundEvents("magic-spell-base-lower");
    public static RegistryObject<SoundEvent> MAGIC_IMPACT_1 = registerSoundEvents("magic-impact-1");
    public static RegistryObject<SoundEvent> MAGIC_MISSLE = registerSoundEvents("magic-missle");
    public static RegistryObject<SoundEvent> BOULDER_SOUND = registerSoundEvents("boulder-sound");




    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        ResourceLocation id = new ResourceLocation(StaffsMod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}

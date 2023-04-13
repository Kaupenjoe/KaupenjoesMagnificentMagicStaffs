package net.kaupenjoe.magnificentstaffs.particles;

import net.kaupenjoe.magnificentstaffs.StaffsMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, StaffsMod.MOD_ID);

    public static final RegistryObject<SimpleParticleType> MAGIC_PARTICLES =
            PARTICLE_TYPES.register("magic_particles", () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> AMETHYST_MAGIC_PARTICLES =
            PARTICLE_TYPES.register("amethyst_magic_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SAPPHIRE_MAGIC_PARTICLES =
            PARTICLE_TYPES.register("sapphire_magic_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> DIAMOND_MAGIC_PARTICLES =
            PARTICLE_TYPES.register("diamond_magic_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> RUBY_MAGIC_PARTICLES =
            PARTICLE_TYPES.register("ruby_magic_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> EMERALD_MAGIC_PARTICLES =
            PARTICLE_TYPES.register("emerald_magic_particles", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}

package net.kaupenjoe.magnificentstaffs.particles;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.kaupenjoe.magnificentstaffs.StaffsMod;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final DefaultParticleType MAGIC_PARTICLES =
            Registry.register(Registries.PARTICLE_TYPE, new Identifier(StaffsMod.MOD_ID,"magic_particles"), FabricParticleTypes.simple());

    public static final DefaultParticleType AMETHYST_MAGIC_PARTICLES =
            Registry.register(Registries.PARTICLE_TYPE, new Identifier(StaffsMod.MOD_ID,"amethyst_magic_particles"), FabricParticleTypes.simple());
    public static final DefaultParticleType SAPPHIRE_MAGIC_PARTICLES =
            Registry.register(Registries.PARTICLE_TYPE, new Identifier(StaffsMod.MOD_ID,"sapphire_magic_particles"),FabricParticleTypes.simple());
    public static final DefaultParticleType DIAMOND_MAGIC_PARTICLES =
            Registry.register(Registries.PARTICLE_TYPE, new Identifier(StaffsMod.MOD_ID,"diamond_magic_particles"),FabricParticleTypes.simple());
    public static final DefaultParticleType RUBY_MAGIC_PARTICLES =
            Registry.register(Registries.PARTICLE_TYPE, new Identifier(StaffsMod.MOD_ID,"ruby_magic_particles"),FabricParticleTypes.simple());
    public static final DefaultParticleType EMERALD_MAGIC_PARTICLES =
            Registry.register(Registries.PARTICLE_TYPE, new Identifier(StaffsMod.MOD_ID,"emerald_magic_particles"),FabricParticleTypes.simple());



    public static void register() {

    }
}

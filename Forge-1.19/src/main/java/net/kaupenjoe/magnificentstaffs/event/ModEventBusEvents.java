package net.kaupenjoe.magnificentstaffs.event;

import net.kaupenjoe.magnificentstaffs.StaffsMod;
import net.kaupenjoe.magnificentstaffs.entity.client.BlizzardMagicProjectileModel;
import net.kaupenjoe.magnificentstaffs.entity.client.MagicProjectileModel;
import net.kaupenjoe.magnificentstaffs.entity.layers.ModModelLayers;
import net.kaupenjoe.magnificentstaffs.particles.ModParticles;
import net.kaupenjoe.magnificentstaffs.particles.custom.*;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = StaffsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.MAGIC_PROJECTILE_LAYER, MagicProjectileModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.BLIZZARD_PROJECTILE_LAYER, BlizzardMagicProjectileModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.MAGIC_PARTICLES.get(), MagicParticle.Provider::new);

        event.registerSpriteSet(ModParticles.AMETHYST_MAGIC_PARTICLES.get(), AmethystMagicParticle.Provider::new);
        event.registerSpriteSet(ModParticles.SAPPHIRE_MAGIC_PARTICLES.get(), SapphireMagicParticle.Provider::new);
        event.registerSpriteSet(ModParticles.DIAMOND_MAGIC_PARTICLES.get(), DiamondMagicParticle.Provider::new);
        event.registerSpriteSet(ModParticles.RUBY_MAGIC_PARTICLES.get(), RubyMagicParticle.Provider::new);
        event.registerSpriteSet(ModParticles.EMERALD_MAGIC_PARTICLES.get(), EmeraldMagicParticle.Provider::new);

        event.registerSpriteSet(ModParticles.RADIATION_MAGIC_PARTICLES.get(), RadiationMagicParticle.Provider::new);
        event.registerSpriteSet(ModParticles.VENOM_MAGIC_PARTICLES.get(), VenomMagicParticle.Provider::new);

        event.registerSpriteSet(ModParticles.SNOW_MAGIC_PARTICLES.get(), SnowMagicParticle.Provider::new);
    }
}

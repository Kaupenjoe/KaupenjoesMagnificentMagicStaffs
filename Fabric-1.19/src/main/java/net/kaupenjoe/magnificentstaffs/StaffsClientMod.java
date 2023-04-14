package net.kaupenjoe.magnificentstaffs;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.kaupenjoe.magnificentstaffs.entity.ModEntities;
import net.kaupenjoe.magnificentstaffs.entity.client.MagicProjectileModel;
import net.kaupenjoe.magnificentstaffs.entity.client.MagicProjectileRenderer;
import net.kaupenjoe.magnificentstaffs.entity.layers.ModModelLayers;
import net.kaupenjoe.magnificentstaffs.particles.ModParticles;
import net.kaupenjoe.magnificentstaffs.particles.custom.*;

public class StaffsClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.SAPPHIRE_MAGIC_PARTICLES, SapphireMagicParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.AMETHYST_MAGIC_PARTICLES, AmethystMagicParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.RUBY_MAGIC_PARTICLES, RubyMagicParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.EMERALD_MAGIC_PARTICLES, EmeraldMagicParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.DIAMOND_MAGIC_PARTICLES, DiamondMagicParticle.Provider::new);

        EntityRendererRegistry.register(ModEntities.MAGIC_PROJECTILE, MagicProjectileRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MAGIC_PROJECTILE_LAYER, MagicProjectileModel::createBodyLayer);
    }
}

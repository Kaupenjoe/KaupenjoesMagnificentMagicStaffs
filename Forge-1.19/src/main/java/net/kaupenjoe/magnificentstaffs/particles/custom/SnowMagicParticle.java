package net.kaupenjoe.magnificentstaffs.particles.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector4f;

public class SnowMagicParticle extends MagicParticle {
    protected SnowMagicParticle(ClientLevel level, double xCoord, double yCoord, double zCoord, SpriteSet spriteSet, double xd, double yd, double zd) {
        super(level, xCoord, yCoord, zCoord, spriteSet, xd, yd, zd);
        
        Vector4f colorVector = new Vector4f(0.75f, 0.95f, 0.94f, 0.8f);
        this.rCol = colorVector.x;
        this.gCol = colorVector.y;
        this.bCol = colorVector.z;
        this.alpha = colorVector.w;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new SnowMagicParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}

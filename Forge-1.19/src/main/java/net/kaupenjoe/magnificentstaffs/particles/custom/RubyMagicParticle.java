package net.kaupenjoe.magnificentstaffs.particles.custom;

import net.kaupenjoe.magnificentstaffs.entity.custom.BasicMagicProjectileEntity;
import net.kaupenjoe.magnificentstaffs.util.MagicColorUtils;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector4f;

public class RubyMagicParticle extends MagicParticle {
    protected RubyMagicParticle(ClientLevel level, double xCoord, double yCoord, double zCoord, SpriteSet spriteSet, double xd, double yd, double zd) {
        super(level, xCoord, yCoord, zCoord, spriteSet, xd, yd, zd);
        
        Vector4f colorVector = MagicColorUtils.PROJECTILE_VECTOR.get(BasicMagicProjectileEntity.MagicProjectileType.RUBY);
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
            return new RubyMagicParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}

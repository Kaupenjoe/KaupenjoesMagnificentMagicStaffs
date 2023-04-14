package net.kaupenjoe.magnificentstaffs.particles.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kaupenjoe.magnificentstaffs.entity.custom.BasicMagicProjectileEntity;
import net.kaupenjoe.magnificentstaffs.util.MagicColorUtils;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import org.joml.Vector4f;

public class AmethystMagicParticle extends MagicParticle {
    protected AmethystMagicParticle(ClientWorld level, double xCoord, double yCoord, double zCoord, SpriteProvider spriteSet, double xd, double yd, double zd) {
        super(level, xCoord, yCoord, zCoord, spriteSet, xd, yd, zd);

        Vector4f colorVector = MagicColorUtils.PROJECTILE_VECTOR.get(BasicMagicProjectileEntity.MagicProjectileType.AMETHYST);
        this.red = colorVector.x;
        this.green = colorVector.y;
        this.blue = colorVector.z;
        this.alpha = colorVector.w;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Provider implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider sprites;

        public Provider(SpriteProvider spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(DefaultParticleType particleType, ClientWorld level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new AmethystMagicParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}

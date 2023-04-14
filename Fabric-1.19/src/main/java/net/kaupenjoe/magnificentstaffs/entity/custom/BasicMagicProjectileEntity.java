package net.kaupenjoe.magnificentstaffs.entity.custom;

import net.kaupenjoe.magnificentstaffs.entity.ModEntities;
import net.kaupenjoe.magnificentstaffs.particles.ModParticles;
import net.minecraft.block.AbstractBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class BasicMagicProjectileEntity extends ProjectileEntity {
    public enum MagicProjectileType {
        SAPPHIRE,
        DIAMOND,
        RUBY,
        AMETHYST,
        EMERALD
    }

    private static final TrackedData<Integer> TYPE =
            DataTracker.registerData(BasicMagicProjectileEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public BasicMagicProjectileEntity(EntityType<? extends ProjectileEntity> entityType, World level) {
        super(entityType, level);
    }

    public BasicMagicProjectileEntity(World level, PlayerEntity player) {
        this(ModEntities.MAGIC_PROJECTILE, level);
        setOwner(player);
        BlockPos blockpos = player.getBlockPos();
        double d0 = (double)blockpos.getX() + 0.5D;
        double d1 = (double)blockpos.getY() + 1.75D;
        double d2 = (double)blockpos.getZ() + 0.5D;
        this.setVelocity(d0, d1, d2, this.getYaw(), this.getPitch());
    }

    public void setType(MagicProjectileType type) {
        this.dataTracker.set(TYPE, type.ordinal());
    }

    public int getProjectileType() {
        return this.dataTracker.get(TYPE);
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(TYPE, 0);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.age >= 300) {
            this.remove(RemovalReason.DISCARDED);
        }
        Vec3d vec3 = this.getPos();
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        this.updateRotation();

        double d5 = vec3.x;
        double d6 = vec3.y;
        double d7 = vec3.z;

        for(int i = 1; i < 5; ++i) {
            this.world.addParticle(getParticleType(), d0-(d5*2), d1-(d6*2), d2-(d7*2),
                    -d5, -d6 - 0.1D, -d7);
        }

        if (this.world.getStatesInBox(this.getBoundingBox()).noneMatch(AbstractBlock.AbstractBlockState::isAir)) {
            this.discard();
        } else if (this.isSubmergedInWater()) {
            this.discard();
        } else {
            this.setVelocity(vec3.multiply(0.99F));
            this.setPos(d0, d1, d2);
        }
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    public DefaultParticleType getParticleType() {
        return switch (this.dataTracker.get(TYPE)) {
            case 1 -> ModParticles.DIAMOND_MAGIC_PARTICLES;
            case 2 -> ModParticles.RUBY_MAGIC_PARTICLES;
            case 3 -> ModParticles.AMETHYST_MAGIC_PARTICLES;
            case 4 -> ModParticles.EMERALD_MAGIC_PARTICLES;
            default -> ModParticles.SAPPHIRE_MAGIC_PARTICLES;
        };
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        for(int x = 0; x < 360; ++x) {
            for(int y = 0; y < 360; ++y) {
                if(x % 20 == 0 && y % 20 == 0) {
                    this.world.addParticle(getParticleType(), this.getX(), this.getY(), this.getZ(),
                            Math.cos(x) * 0.15d, Math.cos(y) * 0.15d, Math.sin(x) * 0.15d);
                }
            }
        }
    }
}

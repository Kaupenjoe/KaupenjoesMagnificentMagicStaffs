package net.kaupenjoe.magnificentstaffs.entity.custom;

import net.kaupenjoe.magnificentstaffs.entity.ModEntities;
import net.kaupenjoe.magnificentstaffs.particles.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class BasicMagicProjectileEntity extends Projectile {
    public enum MagicProjectileType {
        SAPPHIRE,
        DIAMOND,
        RUBY,
        AMETHYST,
        EMERALD
    }

    private static final EntityDataAccessor<Integer> TYPE =
            SynchedEntityData.defineId(BasicMagicProjectileEntity.class, EntityDataSerializers.INT);

    public BasicMagicProjectileEntity(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    public BasicMagicProjectileEntity(Level level, Player player) {
        this(ModEntities.MAGIC_PROJECTILE.get(), level);
        setOwner(player);
        BlockPos blockpos = player.blockPosition();
        double d0 = (double)blockpos.getX() + 0.5D;
        double d1 = (double)blockpos.getY() + 1.75D;
        double d2 = (double)blockpos.getZ() + 0.5D;
        this.moveTo(d0, d1, d2, this.getYRot(), this.getXRot());
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(TYPE, 0);
    }

    public void setType(MagicProjectileType type) {
        this.entityData.set(TYPE, type.ordinal());
    }

    public int getProjectileType() {
        return this.entityData.get(TYPE);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.tickCount >= 300) {
            this.remove(RemovalReason.DISCARDED);
        }
        Vec3 vec3 = this.getDeltaMovement();
        HitResult hitresult = ProjectileUtil.getHitResult(this, this::canHitEntity);
        if (hitresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult))
            this.onHit(hitresult);
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        this.updateRotation();

        double d5 = vec3.x;
        double d6 = vec3.y;
        double d7 = vec3.z;

        for(int i = 1; i < 5; ++i) {
            this.level.addParticle(getParticleType(), d0-(d5*2), d1-(d6*2), d2-(d7*2),
                    -d5, -d6 - 0.1D, -d7);
        }

        if (this.level.getBlockStates(this.getBoundingBox()).noneMatch(BlockBehaviour.BlockStateBase::isAir)) {
            this.discard();
        } else if (this.isInWaterOrBubble()) {
            this.discard();
        } else {
            this.setDeltaMovement(vec3.scale(0.99F));
            this.setPos(d0, d1, d2);
        }
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public SimpleParticleType getParticleType() {
        return switch (this.entityData.get(TYPE)) {
            case 1 -> ModParticles.DIAMOND_MAGIC_PARTICLES.get();
            case 2 -> ModParticles.RUBY_MAGIC_PARTICLES.get();
            case 3 -> ModParticles.AMETHYST_MAGIC_PARTICLES.get();
            case 4 -> ModParticles.EMERALD_MAGIC_PARTICLES.get();
            default -> ModParticles.SAPPHIRE_MAGIC_PARTICLES.get();
        };
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);

        for(int x = 0; x < 360; ++x) {
            for(int y = 0; y < 360; ++y) {
                if(x % 20 == 0 && y % 20 == 0) {
                    this.level.addParticle(getParticleType(), this.getX(), this.getY(), this.getZ(),
                            Math.cos(x) * 0.15d, Math.cos(y) * 0.15d, Math.sin(x) * 0.15d);
                }
            }
        }
    }
}

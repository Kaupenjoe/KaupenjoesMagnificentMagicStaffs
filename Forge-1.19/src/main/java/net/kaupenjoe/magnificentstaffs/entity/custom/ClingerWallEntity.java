package net.kaupenjoe.magnificentstaffs.entity.custom;

import net.kaupenjoe.magnificentstaffs.entity.ModEntities;
import net.kaupenjoe.magnificentstaffs.particles.ModParticles;
import net.kaupenjoe.magnificentstaffs.sound.ModSound;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ClingerWallEntity extends Entity {
    private AABB hurtArea;
    private int lifeTicks = 300;

    public ClingerWallEntity(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
    }

    public ClingerWallEntity(Level level, BlockPos position) {
        this(ModEntities.CLINGER_WALL_ENTITY.get(), level);
        this.moveTo(position.getX() + 0.5, position.getY(), position.getZ() + 0.5);
        hurtArea = new AABB(position.getX() + 0.5, position.getY() + 1, position.getZ() + 0.5,
                position.getX() + 0.5, position.getY() + 4, position.getZ() + 0.5);
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    public void tick() {
        super.tick();
        if (this.tickCount >= lifeTicks) {
            this.remove(RemovalReason.DISCARDED);
        }

        if(this.tickCount % 10 == 0 && !level.isClientSide() && hurtArea != null) {
            List<Entity> toHit = this.level.getEntities(this, hurtArea.inflate(0.5d));
            for (Entity entity : toHit) {
                entity.hurt(this.damageSources().magic(), 1f);
            }
        }

        if(tickCount % 5 == 0) {
            this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 1, this.getZ(), 0f, 0f, 0f);
            this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 2, this.getZ(), 0f, 0f, 0f);
            this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 3, this.getZ(), 0f, 0f, 0f);
            this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 4, this.getZ(), 0f, 0f, 0f);
        }
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}

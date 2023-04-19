package net.kaupenjoe.magnificentstaffs.entity.custom;

import net.kaupenjoe.magnificentstaffs.entity.ModEntities;
import net.kaupenjoe.magnificentstaffs.particles.ModParticles;
import net.kaupenjoe.magnificentstaffs.sound.ModSound;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
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

public class FallingMagicProjectileEntity extends Projectile {
    public enum FallingMagicProjectileType {
        METEOR,
        BLIZZARD
    }

    private static final EntityDataAccessor<Integer> TYPE =
            SynchedEntityData.defineId(FallingMagicProjectileEntity.class, EntityDataSerializers.INT);

    public FallingMagicProjectileEntity(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    public FallingMagicProjectileEntity(Level level, Player player, BlockPos spawnLoc) {
        this(ModEntities.MAGIC_PROJECTILE.get(), level);
        setOwner(player);

        double d0 = (double) spawnLoc.getX() + 0.5D;
        double d1 = (double) spawnLoc.getY();
        double d2 = (double) spawnLoc.getZ() + 0.5D;
        this.moveTo(d0, d1, d2, this.getYRot(), this.getXRot());
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(TYPE, 0);
    }

    public void setType(FallingMagicProjectileType type) {
        this.entityData.set(TYPE, type.ordinal());
    }

    public int getProjectileType() {
        return this.entityData.get(TYPE);
    }

    @Override
    public void tick() {
        super.tick();
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

        for(int i = 1; i < 3; ++i) {
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
            default -> ModParticles.SAPPHIRE_MAGIC_PARTICLES.get();
            case 1 -> ModParticles.DIAMOND_MAGIC_PARTICLES.get();
        };
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity hitEntity = hitResult.getEntity();
        Entity owner = this.getOwner();

        if(hitEntity == owner && this.level.isClientSide()) {
            return;
        }

        this.level.playSound(null, this.getX(), this.getY(), this.getZ(), ModSound.MAGIC_IMPACT_1.get(), SoundSource.NEUTRAL,
                2F, 1F);

        LivingEntity livingentity = owner instanceof LivingEntity ? (LivingEntity)owner : null;
        float damage = switch (this.entityData.get(TYPE)) {
            default -> 3f;
            case 1 -> 9f;
            case 2 -> 5f;
            case 3 -> 7f;
            case 4 -> 4f;
            case 5, 6 -> 2f;
        };
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        for(int x = 0; x < 18; ++x) {
            for(int y = 0; y < 18; ++y) {
                this.level.addParticle(getParticleType(), this.getX(), this.getY(), this.getZ(),
                        Math.cos(x*20) * 0.15d, Math.cos(y*20) * 0.15d, Math.sin(x*20) * 0.15d);
            }
        }

        this.level.explode(this, this.getX(), this.getY(), this.getZ(), 3f, Level.ExplosionInteraction.MOB);
        this.discard();
    }

    private void destroy() {
        this.discard();
        this.level.gameEvent(GameEvent.ENTITY_DAMAGE, this.position(), GameEvent.Context.of(this));
    }
}

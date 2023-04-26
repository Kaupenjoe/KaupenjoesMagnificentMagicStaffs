package net.kaupenjoe.magnificentstaffs.entity.custom;

import net.kaupenjoe.magnificentstaffs.entity.ModEntities;
import net.kaupenjoe.magnificentstaffs.particles.ModParticles;
import net.kaupenjoe.magnificentstaffs.sound.ModSound;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class BouncingProjectileEntity extends Projectile {
    private double yValue_ = 0.0;
    private int bounces = 0;
    private static final int MAX_BOUNCES = 6;

    public BouncingProjectileEntity(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    public BouncingProjectileEntity(Level level, Player player) {
        this(ModEntities.BOUNCING_PROJECTILE.get(), level);
        setOwner(player);
        BlockPos blockpos = player.blockPosition();
        double d0 = (double)blockpos.getX() + 0.5D;
        double d1 = (double)blockpos.getY() + 1.75D;
        double d2 = (double)blockpos.getZ() + 0.5D;
        this.moveTo(d0, d1, d2, this.getYRot(), this.getXRot());
    }

    @Override
    protected void defineSynchedData() {

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
            this.level.addParticle(ParticleTypes.CRIT, d0-(d5*2), d1-(d6*2), d2-(d7*2),
                    -d5, -d6 - 0.1D, -d7);
        }
        if (this.isInWaterOrBubble()) {
            this.discard();
        } else {
            if(yValue_ != 0.0) {
                float yMultiplier = (vec3.y <= 0.1f) ? 1.01f : 0.9f;

                Vec3 newMovement = new Vec3(vec3.x * 0.92f, yValue_ * yMultiplier, vec3.z * 0.92f);
                this.setDeltaMovement(newMovement);
                this.setPos(d0, d1, d2);
                yValue_ = 0.0;
            } else {
                float yMultiplier;
                if(bounces == 0) {
                    yMultiplier = -1.05f;

                    Vec3 newMovement = new Vec3(vec3.x * 0.99f, Math.abs(-0.25f) * yMultiplier, vec3.z * 0.99f);
                    this.setDeltaMovement(newMovement);
                } else {
                    yMultiplier = (vec3.y <= 0.2f) ? -1.01f : 0.9f;

                    Vec3 newMovement = new Vec3(vec3.x * 0.99f, Math.abs(vec3.y) * yMultiplier, vec3.z * 0.99f);
                    this.setDeltaMovement(newMovement);
                }
                this.setPos(d0, d1, d2);
            }
        }
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        if(hitResult.getDirection() == Direction.UP) {
            yValue_ = (MAX_BOUNCES - bounces) * 0.1 * this.random.nextDouble();
            this.bounces++;
            this.level.playSound(null, this.getX(), this.getY(), this.getZ(), ModSound.BOULDER_SOUND.get(), SoundSource.NEUTRAL, 1f, 1f);
        }

        if(bounces >= MAX_BOUNCES) {
            this.discard();
        }

        super.onHitBlock(hitResult);
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
        float damage = 7.5f;
        hitEntity.hurt(this.damageSources().mobProjectile(this, livingentity), damage);
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        for(int x = 0; x < 18; ++x) {
            for(int y = 0; y < 18; ++y) {
                this.level.addParticle(ParticleTypes.CRIT, this.getX(), this.getY(), this.getZ(),
                        Math.cos(x*20) * 0.15d, Math.cos(y*20) * 0.15d, Math.sin(x*20) * 0.15d);
            }
        }
    }

    private void destroy() {
        this.discard();
        this.level.gameEvent(GameEvent.ENTITY_DAMAGE, this.position(), GameEvent.Context.of(this));
    }
}

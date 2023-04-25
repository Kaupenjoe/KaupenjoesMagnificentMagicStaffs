package net.kaupenjoe.magnificentstaffs.item.custom;

import net.kaupenjoe.magnificentstaffs.entity.custom.BouncingMagicProjectileEntity;
import net.kaupenjoe.magnificentstaffs.entity.custom.SeekingMagicProjectileEntity;
import net.kaupenjoe.magnificentstaffs.sound.ModSound;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class SpectreStaff extends Item {
    public SpectreStaff(Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSound.MAGIC_MISSLE.get(), SoundSource.NEUTRAL,
                1.5F, 1F);
        player.getCooldowns().addCooldown(this, 30);
        if (!level.isClientSide) {
            List<Entity> entities = level.getEntities(player, new AABB(player.blockPosition()).inflate(15, 15, 15), Entity::isAlive);
            Entity closest = null;
            for (Entity e : entities) {
                if(closest == null) {
                    closest = e;
                    continue;
                }

                if(e.distanceTo(player) < closest.distanceTo(player)) {
                    closest = e;
                }
            }
            if(closest != null) {
                SeekingMagicProjectileEntity magicProjectile = new SeekingMagicProjectileEntity(level, player, closest);
                magicProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1F, 0.25F);
                level.addFreshEntity(magicProjectile);
            } else {
                return InteractionResultHolder.consume(itemstack);
            }
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            itemstack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }
}

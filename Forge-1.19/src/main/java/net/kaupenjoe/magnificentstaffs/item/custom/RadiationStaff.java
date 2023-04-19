package net.kaupenjoe.magnificentstaffs.item.custom;

import net.kaupenjoe.magnificentstaffs.entity.custom.BasicMagicProjectileEntity;
import net.kaupenjoe.magnificentstaffs.sound.ModSound;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RadiationStaff extends Item {
    public RadiationStaff(Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSound.MAGIC_MISSLE.get(), SoundSource.NEUTRAL,
                1.5F, 1F);
        player.getCooldowns().addCooldown(this, 40);
        if (!level.isClientSide) {
            int numberOfProjectiles = level.getRandom().nextInt(3, 5);

            for (int i = 0; i < numberOfProjectiles; i++) {
                float[] addedSpread = getSpread(numberOfProjectiles);

                BasicMagicProjectileEntity magicProjectile = new BasicMagicProjectileEntity(level, player);
                magicProjectile.setType(BasicMagicProjectileEntity.MagicProjectileType.RADIATION);
                magicProjectile.shootFromRotation(player, player.getXRot(), player.getYRot() + addedSpread[i], 0.0F, 1.5F, 0.25F);
                level.addFreshEntity(magicProjectile);
            }
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            itemstack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    private float[] getSpread(int numberOfProjectiles) {
        if(numberOfProjectiles == 3) {
            return new float[]{-10f, 0f, 10f};
        } else {
            return new float[]{-10f, -5f, 5f, 10f};
        }
    }
}

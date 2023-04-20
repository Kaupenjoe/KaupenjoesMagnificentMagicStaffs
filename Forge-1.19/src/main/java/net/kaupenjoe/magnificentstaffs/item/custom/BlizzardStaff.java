package net.kaupenjoe.magnificentstaffs.item.custom;

import net.kaupenjoe.magnificentstaffs.entity.custom.BlizzardMagicProjectileEntity;
import net.kaupenjoe.magnificentstaffs.sound.ModSound;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.joml.Vector3d;

public class BlizzardStaff extends Item {
    private float maxDistance = 32f;

    public BlizzardStaff(Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSound.MAGIC_MISSLE.get(), SoundSource.NEUTRAL,
                1.5F, 1F);
        // player.getCooldowns().addCooldown(this, 10);

        BlockPos blockPlayerIsLookingAt = level.clip(new ClipContext(player.getEyePosition(1f),
                (player.getEyePosition(1f).add(player.getViewVector(1f).scale(maxDistance))),
                ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player)).getBlockPos();
        if(level.getBlockState(blockPlayerIsLookingAt).is(Blocks.AIR)) {
            return InteractionResultHolder.consume(itemstack);
        }

        if (!level.isClientSide) {
            BlockPos[] spawnPos = getSpawnLocations(level, blockPlayerIsLookingAt);
            BlockPos blockpos = spawnPos[level.getRandom().nextInt(0, spawnPos.length)];
            Vector3d direction = new Vector3d(blockPlayerIsLookingAt.getX() - blockpos.getX(),blockPlayerIsLookingAt.getY() - blockpos.getY(), blockPlayerIsLookingAt.getZ() - blockpos.getZ());

            BlizzardMagicProjectileEntity magicProjectile = new BlizzardMagicProjectileEntity(level, player, blockpos);
            magicProjectile.shoot(direction.x, direction.y, direction.z,0.5F, 1.0F);
            level.addFreshEntity(magicProjectile);

            player.awardStat(Stats.ITEM_USED.get(this));
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            itemstack.hurtAndBreak(5, player, p -> p.broadcastBreakEvent(hand));
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int count, boolean p_41408_) {
        super.inventoryTick(stack, level, entity, count, p_41408_);
        if(count % 20 == 0 && stack.getDamageValue() >= 1) {
            // HEALING IT EVERY 20 TICKS
            stack.setDamageValue(stack.getDamageValue() - 1);
        }
    }

    private BlockPos[] getSpawnLocations(Level level, BlockPos pos) {
        BlockPos[] positions = new BlockPos[25];
        pos = pos.above(level.getRandom().nextInt(8, 12));

        positions[0] = new BlockPos(pos.getX()-2, pos.getY(), pos.getZ());
        positions[1] = new BlockPos(pos.getX()-1, pos.getY(), pos.getZ());
        positions[2] = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
        positions[3] = new BlockPos(pos.getX()+1, pos.getY(), pos.getZ());
        positions[4] = new BlockPos(pos.getX()+2, pos.getY(), pos.getZ());

        positions[5] = new BlockPos(pos.getX()-2, pos.getY(), pos.getZ()-2);
        positions[6] = new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()-2);
        positions[7] = new BlockPos(pos.getX(), pos.getY(), pos.getZ()-2);
        positions[8] = new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()-2);
        positions[9] = new BlockPos(pos.getX()+2, pos.getY(), pos.getZ()-2);

        positions[10] = new BlockPos(pos.getX()-2, pos.getY(), pos.getZ()-1);
        positions[11] = new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()-1);
        positions[12] = new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1);
        positions[13] = new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()-1);
        positions[14] = new BlockPos(pos.getX()+2, pos.getY(), pos.getZ()-1);

        positions[15] = new BlockPos(pos.getX()-2, pos.getY(), pos.getZ()+1);
        positions[16] = new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()+1);
        positions[17] = new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1);
        positions[18] = new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()+1);
        positions[19] = new BlockPos(pos.getX()+2, pos.getY(), pos.getZ()+1);

        positions[20] = new BlockPos(pos.getX()-2, pos.getY(), pos.getZ()+2);
        positions[21] = new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()+2);
        positions[22] = new BlockPos(pos.getX(), pos.getY(), pos.getZ()+2);
        positions[23] = new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()+2);
        positions[24] = new BlockPos(pos.getX()+2, pos.getY(), pos.getZ()+2);


        return positions;
    }
}

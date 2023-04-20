package net.kaupenjoe.magnificentstaffs.item.custom;

import net.kaupenjoe.magnificentstaffs.entity.custom.ClingerWallEntity;
import net.kaupenjoe.magnificentstaffs.sound.ModSound;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class ClingerStaff extends Item {
    public static final int HURT_MULTIPLIER = 8;
    private float maxDistance = 32f;

    public ClingerStaff(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(itemstack.getTag() != null && !itemstack.getTag().getCompound("first-location").isEmpty()) {
            player.startUsingItem(hand);
            return InteractionResultHolder.pass(itemstack);
        }

        level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSound.MAGIC_MISSLE.get(), SoundSource.NEUTRAL,
                1.5F, 1F);
        // player.getCooldowns().addCooldown(this, 10);

        BlockPos blockPlayerIsLookingAt = level.clip(new ClipContext(player.getEyePosition(1f),
                (player.getEyePosition(1f).add(player.getViewVector(1f).scale(maxDistance))),
                ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player)).getBlockPos();
        if(level.getBlockState(blockPlayerIsLookingAt).is(Blocks.AIR)) {
            return InteractionResultHolder.consume(itemstack);
        }

        if(itemstack.getTag() == null) {
            itemstack.setTag(new CompoundTag());
        }
        itemstack.getTag().put("first-location", NbtUtils.writeBlockPos(blockPlayerIsLookingAt));
        player.startUsingItem(hand);
        if(!level.isClientSide()) {
            // player.level.setBlock(blockPlayerIsLookingAt, Blocks.IRON_BLOCK.defaultBlockState(), 3);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @Override
    public int getUseDuration(ItemStack itemStack) {
        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.NONE;
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        super.onStopUsing(stack, entity, count);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int count) {
        if(entity instanceof Player player) {
            player.level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSound.MAGIC_MISSLE.get(), SoundSource.NEUTRAL,
                    1.5F, 1F);
            int blocksSpawned = 0;
            // player.getCooldowns().addCooldown(this, 10);

            BlockPos blockPlayerIsLookingAt = player.level.clip(new ClipContext(player.getEyePosition(1f),
                    (player.getEyePosition(1f).add(player.getViewVector(1f).scale(maxDistance))),
                    ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player)).getBlockPos();
            if (player.level.getBlockState(blockPlayerIsLookingAt).is(Blocks.AIR)) {
                return;
            }

            if (!player.level.isClientSide) {
                List<BlockPos> baseBlocks = new ArrayList<>();
                BlockPos firstPos = NbtUtils.readBlockPos(stack.getTag().getCompound("first-location"));
                Vec3 direction = new Vec3(blockPlayerIsLookingAt.getX() - firstPos.getX(), blockPlayerIsLookingAt.getY() - firstPos.getY(),
                        blockPlayerIsLookingAt.getZ() - firstPos.getZ());
                direction = direction.normalize();
                for(int i = 0; i < 45; i++) {
                    BlockPos blockPosToSave = new BlockPos(firstPos.offset((int)Math.round(direction.x * i), (int)Math.round(direction.y * i), (int)Math.round(direction.z * i)));
                    if(player.level.getBlockState(blockPlayerIsLookingAt).is(Blocks.AIR)) {
                        continue;
                    }

                    if(blockPosToSave.equals(blockPlayerIsLookingAt)) {
                        blockPosToSave.atY(level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, firstPos).getY());
                        baseBlocks.add(blockPosToSave);
                        break;
                    }

                    blockPosToSave.atY(level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, firstPos).getY());
                    baseBlocks.add(blockPosToSave);
                    blocksSpawned = i;
                }

                for (BlockPos position : baseBlocks) {
                    level.addFreshEntity(new ClingerWallEntity(level, position));
                    // player.level.setBlock(position, Blocks.GOLD_BLOCK.defaultBlockState(), 3);
                    // for(int i = 1; i < 5; i++) {
                    //     if(player.level.getBlockState(position.above(i)).is(Blocks.AIR)) {
                    //         player.level.setBlock(position.above(i), Blocks.ACACIA_FENCE.defaultBlockState(), 3);
                    //     }
                    // }
                }
            }
            stack.getTag().put("first-location", new CompoundTag());

            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                stack.hurtAndBreak(blocksSpawned * HURT_MULTIPLIER, player, p -> p.broadcastBreakEvent(player.getUsedItemHand()));
            }
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int count, boolean p_41408_) {
        super.inventoryTick(stack, level, entity, count, p_41408_);
        if(count % 20 == 0 && stack.getDamageValue() >= 1) {
            // HEALING IT EVERY 20 TICKS
            stack.setDamageValue(stack.getDamageValue() - 1);
        }
    }
}

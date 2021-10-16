package com.github.spaceman;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LanternBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.World;

public class LanternButtonBlock extends LanternBlock {
    public static final BooleanProperty POWERED = Properties.POWERED;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public LanternButtonBlock(Settings settings) {
        super(settings);
        this.setDefaultState(getStateManager().getDefaultState().with(POWERED, false).with(HANGING, false).with(WATERLOGGED, false));
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if ((Boolean) state.get(POWERED)) {
            return ActionResult.CONSUME;
        } else {
            this.powerOn(state, world, pos);
            this.playClickSound(player, world, state, pos, true);
            return ActionResult.SUCCESS;
        }
    }

    public void powerOn(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, (BlockState) state.with(POWERED, true), 3);
        this.updateNeighbors(state, world, pos);
        world.getBlockTickScheduler().schedule(pos, this, this.getPressTicks());
    }

    private int getPressTicks() {
        return 20;
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if ((Boolean) state.get(POWERED)) {
            world.setBlockState(pos, (BlockState) state.with(POWERED, false), 3);
            this.updateNeighbors(state, world, pos);
            this.playClickSound((PlayerEntity) null, world, state, pos, false);
        }
    }

    private void playClickSound(PlayerEntity player, WorldAccess world, BlockState state, BlockPos pos, boolean powered) {
        world.playSound((Boolean) state.get(POWERED) ? player : null, pos, this.getClickSound((Boolean) state.get(POWERED)), SoundCategory.BLOCKS, 0.3F, powered ? 0.6F : 0.5F);

    }

    protected SoundEvent getClickSound(boolean powered) {
        return SoundEvents.BLOCK_LANTERN_HIT;
    }

    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean notify) {
        if (!notify && state.getBlock() != newState.getBlock()) {
            if ((Boolean) state.get(POWERED)) {
                this.updateNeighbors(state, world, pos);
            }
            super.onStateReplaced(state, world, pos, newState, notify);
        }
    }

    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return (Boolean) state.get(POWERED) ? 15 : 0;
    }

    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return (Boolean) state.get(POWERED) && attachedDirection(state) == direction ? 15 : 0;
    }

    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED, HANGING, WATERLOGGED);
    }

    private void updateNeighbors(BlockState state, World world, BlockPos pos) {
        world.updateNeighborsAlways(pos, this);
        world.updateNeighborsAlways(pos.offset(attachedDirection(state).getOpposite()), this);
    }
}

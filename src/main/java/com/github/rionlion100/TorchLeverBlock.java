package com.github.rionlion100;

import java.util.Random;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
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
import net.minecraft.world.World;

public class TorchLeverBlock extends WallTorchBlock{
    public static final BooleanProperty POWERED = Properties.POWERED;

    public TorchLeverBlock(Settings settings) {
        super(settings, null);
        this.setDefaultState(getStateManager().getDefaultState().with(POWERED, false).with(FACING,Direction.NORTH));
        }

    public void onBlockRemoved(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!moved && state.getBlock() != newState.getBlock()) {
           if ((Boolean)state.get(POWERED)) {
              this.updateNeighbors(state, world, pos);
           }
  
           super.onStateReplaced(state, world, pos, newState, moved);
        }
     }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            flip(state, world, pos);
            updateNeighbors(state, world, pos);
            return ActionResult.SUCCESS;
        }
    }

    private static void flip(BlockState state, World world, BlockPos pos) {
        if (!state.get(POWERED)) {
            world.setBlockState(pos, state.with(POWERED, true), 3);
        }
        else{
            world.setBlockState(pos, state.with(POWERED, false), 3);
        }
        float f = (Boolean)state.get(POWERED) ? 0.6F : 0.5F;
        world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, f);
    }
    
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
        builder.add(FACING);
    }
    @Override
    public String getTranslationKey() {
        return "block.secretrooms.torch_lever";
    }
    @Override
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!state.get(POWERED)) {
            Direction direction = (Direction)state.get(FACING);
            double d = (double)pos.getX() + 0.5D;
            double e = (double)pos.getY() + 0.7D;
            double f = (double)pos.getZ() + 0.5D;
            Direction direction2 = direction.getOpposite();
            world.addParticle(ParticleTypes.SMOKE, d + 0.27D * (double)direction2.getOffsetX(), e + 0.22D, f + 0.27D * (double)direction2.getOffsetZ(), 0.0D, 0.0D, 0.0D);
            world.addParticle(ParticleTypes.FLAME, d + 0.27D * (double)direction2.getOffsetX(), e + 0.22D, f + 0.27D * (double)direction2.getOffsetZ(), 0.0D, 0.0D, 0.0D);
        }
    }
    public int getWeakRedstonePower(BlockState state, BlockView view, BlockPos pos, Direction facing) {
        return (Boolean)state.get(POWERED) ? 15 : 0;
     }
  
     public int getStrongRedstonePower(BlockState state, BlockView view, BlockPos pos, Direction facing) {
        return (Boolean)state.get(POWERED) && state.get(FACING) == facing ? 15 : 0;
     }
  
     public boolean emitsRedstonePower(BlockState state) {
        return true;
     }
  
     private void updateNeighbors(BlockState state, World world, BlockPos pos) {
        world.updateNeighborsAlways(pos, this);
        world.updateNeighborsAlways(pos.offset(state.get(FACING).getOpposite()), this);
     }
}
package com.github.rionlion100;

import java.util.Iterator;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

class RedstoneChainBlock extends Block implements Waterloggable {
    public static final VoxelShape SHAPE = Block.createCuboidShape(6.5D, 0.0D, 6.5D, 9.5D, 16.0D, 9.5D);
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final IntProperty POWER = Properties.POWER;
    private boolean wiresGivePower;
 
    public RedstoneChainBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(WATERLOGGED, false).with(POWER, 0));

    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED,POWER);
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }
  
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }  
    
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        boolean bl = fluidState.getFluid() == Fluids.WATER;
        return (BlockState)super.getPlacementState(ctx).with(WATERLOGGED, bl);
    }

  
     private int method_27842(World world, BlockPos blockPos) {
        this.wiresGivePower = false;
        int i = world.getReceivedRedstonePower(blockPos);
        this.wiresGivePower = true;
        int j = 0;
        if (i < 15) {
           Iterator<Direction> var5 = Direction.Type.VERTICAL.iterator();
  
           while(true) {
              while(var5.hasNext()) {
                 Direction direction = (Direction)var5.next();
                 BlockPos blockPos2 = blockPos.offset(direction);
                 BlockState blockState = world.getBlockState(blockPos2);
                 j = Math.max(j, this.increasePower(blockState));
                 BlockPos blockPos3 = blockPos.up();
                 if (blockState.isSolidBlock(world, blockPos2) && !world.getBlockState(blockPos3).isSolidBlock(world, blockPos3)) {
                    j = Math.max(j, this.increasePower(world.getBlockState(blockPos2.up())));
                 } else if (!blockState.isSolidBlock(world, blockPos2)) {
                    j = Math.max(j, this.increasePower(world.getBlockState(blockPos2.down())));
                 }
              }
  
              return Math.max(i, j - 1);
           }
        } else {
           return Math.max(i, j - 1);
        }
     }
     private void update(World world, BlockPos pos, BlockState state) {
        int i = this.method_27842(world, pos);
        if ((Integer)state.get(POWER) != i) {
           if (world.getBlockState(pos) == state) {
              world.setBlockState(pos, (BlockState)state.with(POWER, i), 2);
           }
  
           Set<BlockPos> set = Sets.newHashSet();
           set.add(pos);
           Direction[] var6 = Direction.values();
           int var7 = var6.length;
  
           for(int var8 = 0; var8 < var7; ++var8) {
              Direction direction = var6[var8];
              set.add(pos.offset(direction));
           }
  
           Iterator<BlockPos> var10 = set.iterator();
  
           while(var10.hasNext()) {
              BlockPos blockPos = (BlockPos)var10.next();
              world.updateNeighborsAlways(blockPos, this);
           }
        }
  
     }
  
     private int increasePower(BlockState state) {
        return state.isOf(this) ? (Integer)state.get(POWER) : 0;
     }
  
     private void updateNeighbors(World world, BlockPos pos) {
        if (world.getBlockState(pos).isOf(this)) {
           world.updateNeighborsAlways(pos, this);
           Direction[] var3 = Direction.values();
           int var4 = var3.length;
  
           for(int var5 = 0; var5 < var4; ++var5) {
              Direction direction = var3[var5];
              world.updateNeighborsAlways(pos.offset(direction), this);
           }
  
        }
     }
  
     public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!oldState.isOf(state.getBlock()) && !world.isClient) {
           this.update(world, pos, state);
           Iterator<Direction> var6 = Direction.Type.VERTICAL.iterator();
  
           while(var6.hasNext()) {
              Direction direction = (Direction)var6.next();
              world.updateNeighborsAlways(pos.offset(direction), this);
           }
  
           this.method_27844(world, pos);
        }
     }
  
     public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!moved && !state.isOf(newState.getBlock())) {
           super.onStateReplaced(state, world, pos, newState, moved);
           if (!world.isClient) {
              Direction[] var6 = Direction.values();
              int var7 = var6.length;
  
              for(int var8 = 0; var8 < var7; ++var8) {
                 Direction direction = var6[var8];
                 world.updateNeighborsAlways(pos.offset(direction), this);
              }
  
              this.update(world, pos, state);
              this.method_27844(world, pos);
           }
        }
     }
  
     private void method_27844(World world, BlockPos blockPos) {
        Iterator<Direction> var3 = Direction.Type.HORIZONTAL.iterator();
  
        Direction direction2;
        while(var3.hasNext()) {
           direction2 = (Direction)var3.next();
           this.updateNeighbors(world, blockPos.offset(direction2));
        }
  
        var3 = Direction.Type.HORIZONTAL.iterator();
  
        while(var3.hasNext()) {
           direction2 = (Direction)var3.next();
           BlockPos blockPos2 = blockPos.offset(direction2);
           if (world.getBlockState(blockPos2).isSolidBlock(world, blockPos2)) {
              this.updateNeighbors(world, blockPos2.up());
           } else {
              this.updateNeighbors(world, blockPos2.down());
           }
        }
  
     }
  
     public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (!world.isClient) {
           if (state.canPlaceAt(world, pos)) {
              this.update(world, pos, state);
           } else {
              dropStacks(state, world, pos);
              world.removeBlock(pos, false);
           }
  
        }
     }
  
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return !this.wiresGivePower ? 0 : state.getWeakRedstonePower(world, pos, direction);
    }
  
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (this.wiresGivePower && direction == Direction.DOWN) {
           int i = (Integer)state.get(POWER);
           if (i == 0) {
              return 0;
           } else {
              return direction != Direction.DOWN ? 0 : i;
           }
        } else {
           return 0;
        }
    }
  

  
     public boolean emitsRedstonePower(BlockState state) {
        return this.wiresGivePower;
     }
}

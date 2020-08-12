package com.github.rionlion100;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Sets;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.DustParticleEffect;
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
      this.setDefaultState((BlockState) ((BlockState) this.stateManager.getDefaultState()).with(WATERLOGGED, false).with(POWER, 0));

   }

   @Override
   public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return SHAPE;
   }

   @Override
   protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
      builder.add(WATERLOGGED, POWER);
   }

   public FluidState getFluidState(BlockState state) {
      return (Boolean) state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
   }

   public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
      return false;
   }

   public BlockState getPlacementState(ItemPlacementContext ctx) {
      FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
      boolean bl = fluidState.getFluid() == Fluids.WATER;
      return (BlockState) super.getPlacementState(ctx).with(WATERLOGGED, bl);
   }

   private int recievedPower(World world, BlockPos blockPos) {
      this.wiresGivePower = false;
      int i = 0;
      Iterator<Direction> verticalIterator = Direction.Type.VERTICAL.iterator();
      Iterator<Direction> horizontalIterator = Direction.Type.HORIZONTAL.iterator();
      while (verticalIterator.hasNext()) {
         Direction verticalDirection = (Direction) verticalIterator.next();
         int k = 0;
         if (verticalDirection == Direction.UP && !world.getBlockState(blockPos.up(2)).isOf(Blocks.REDSTONE_WIRE)) {
            k = world.getEmittedRedstonePower(blockPos.offset(verticalDirection), verticalDirection);
         } else if (verticalDirection == Direction.DOWN) {
            boolean j = false;
            while (horizontalIterator.hasNext()) {
               Direction horizontalDirection = (Direction) horizontalIterator.next();
               if (world.getBlockState(blockPos.down().offset(horizontalDirection)).isOf(Blocks.REDSTONE_WIRE)) {
                  j = true;
               }
            }
            if (!j) {
               k = world.getEmittedRedstonePower(blockPos.offset(verticalDirection), verticalDirection);
            }
         }
         if (k > i) {
            i = k;
         }
      }
      this.wiresGivePower = true;
      int j = 0;
      if (i < 15) {
         Iterator<Direction> verticalDirection2 = Direction.Type.VERTICAL.iterator();

         while (true) {
            while (verticalDirection2.hasNext()) {
               Direction direction = (Direction) verticalDirection2.next();
               BlockPos blockPos2 = blockPos.offset(direction);
               BlockState blockState2 = world.getBlockState(blockPos2);
               j = Math.max(j, this.increasePower(blockState2));
            }

            return Math.max(i, j - 1);
         }
      } else {
         return Math.max(i, j - 1);
      }
   }

   private void update(World world, BlockPos pos, BlockState state) {
      int i = this.recievedPower(world, pos);
      if ((Integer) state.get(POWER) != i) {
         if (world.getBlockState(pos) == state) {
            world.setBlockState(pos, (BlockState) state.with(POWER, i), 2);
         }

         Set<BlockPos> set = Sets.newHashSet();
         set.add(pos);
         Direction[] directions = Direction.values();

         for (int j = 0; j < directions.length; ++j) {
            Direction direction = directions[j];
            set.add(pos.offset(direction));
         }

         Iterator<BlockPos> blockUpdates = set.iterator();

         while (blockUpdates.hasNext()) {
            BlockPos blockPos = (BlockPos) blockUpdates.next();
            world.updateNeighborsAlways(blockPos, this);
         }
      }

   }

   private int increasePower(BlockState state) {
      return state.isOf(this) ? (Integer) state.get(POWER) : 0;
   }

   public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
      if (!oldState.isOf(state.getBlock()) && !world.isClient) {
         this.update(world, pos, state);
         Iterator<Direction> verticalDirections = Direction.Type.VERTICAL.iterator();

         while (verticalDirections.hasNext()) {
            Direction direction = (Direction) verticalDirections.next();
            world.updateNeighborsAlways(pos.offset(direction), this);
         }
      }
   }

   public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
      if (!moved && !state.isOf(newState.getBlock())) {
         super.onStateReplaced(state, world, pos, newState, moved);
         if (!world.isClient) {
            Direction[] directions = Direction.values();

            for (int i = 0; i < directions.length; ++i) {
               Direction direction = directions[i];
               world.updateNeighborsAlways(pos.offset(direction), this);
            }

            this.update(world, pos, state);
         }
      }
   }

   public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos,
         boolean notify) {
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
      return !this.wiresGivePower ? 0 : getWeakRedstonePower(state, world, pos, direction);
   }

   public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
      if (this.wiresGivePower) {
         return ((direction == Direction.DOWN) | (direction == Direction.UP)) ? (Integer) state.get(POWER) : 0;
      } else {
         return 0;
      }
   }

   public boolean emitsRedstonePower(BlockState state) {
      return this.wiresGivePower;
   }

   @Override
   @Environment(EnvType.CLIENT)
   public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
      if (random.nextInt(state.get(POWER) + 1) > 0) {
         double xPos = (double) pos.getX() + 0.5D;
         double yPos = (double) pos.getY() + random.nextDouble();
         double zPos = (double) pos.getZ() + 0.5D;

         world.addParticle(new DustParticleEffect(1.0F, 0.0F, 0.0F, 0.5F), xPos, yPos, zPos, 0.0D, 0.0D, 0.0D);
      }
   }
}

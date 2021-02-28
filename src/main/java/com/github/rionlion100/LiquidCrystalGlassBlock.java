package com.github.rionlion100;

import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LiquidCrystalGlassBlock extends AbstractGlassBlock {
   public static final BooleanProperty POWERED = Properties.POWERED;

   public LiquidCrystalGlassBlock(Settings settings) {
      super(settings);
      this.setDefaultState(getStateManager().getDefaultState().with(POWERED, false));
   }

   @Override
   protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
      builder.add(POWERED);
   }

   @Override
   public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
      neighborUpdate(state, world, pos, this, pos, false);
   }

   public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
      boolean currentState = state.get(POWERED);
      boolean recievingPower = world.isReceivingRedstonePower(pos);
      boolean neighborHasPower = world.getBlockState(fromPos).isOf(this) && world.getBlockState(fromPos).get(POWERED);
      if(currentState != (recievingPower|neighborHasPower)){
            world.setBlockState(pos, (BlockState) state.cycle(POWERED), 2);
            world.updateNeighbors(pos, block);
      }
   }
}

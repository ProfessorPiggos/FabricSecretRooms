package com.github.rionlion100;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.GlassBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

public class OneWayGlassBlock extends GlassBlock{
    public static final DirectionProperty FACING = FacingBlock.FACING;
    
    public OneWayGlassBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(Properties.FACING, Direction.NORTH));
    }
    
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getPlayerLookDirection().getOpposite();
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(direction.getOpposite()));
        return blockState.getBlock() == this && blockState.get(FACING) == direction.getOpposite() ? (BlockState)this.getDefaultState().with(FACING, direction.getOpposite()) : (BlockState)this.getDefaultState().with(FACING, direction);
     }
}
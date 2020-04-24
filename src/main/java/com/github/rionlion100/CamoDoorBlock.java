package com.github.rionlion100;

import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CamoDoorBlock extends DoorBlock {

    private BlockSoundGroup soundGroup;

    protected CamoDoorBlock(Settings settings, BlockSoundGroup soundGroupIn) {
        super(settings);
        this.soundGroup = soundGroupIn;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hit) {
        state = (BlockState) state.cycle(OPEN);
        world.setBlockState(pos, state, 10);
        world.playSound(player, pos, (Boolean)state.get(OPEN) ? this.getCloseSoundEvent() : this.getOpenSoundEvent(), SoundCategory.BLOCKS, 1.0F, 1.0F);
        return ActionResult.SUCCESS;
    }
     

    private SoundEvent getOpenSoundEvent() {
            
        return soundGroup.getPlaceSound();
    }
    
    private SoundEvent getCloseSoundEvent() {
            
        return soundGroup.getPlaceSound();
    }
}
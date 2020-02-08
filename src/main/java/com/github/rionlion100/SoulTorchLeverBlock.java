package com.github.rionlion100;

import java.util.Random;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class SoulTorchLeverBlock extends TorchLeverBlock {

    public SoulTorchLeverBlock(Settings settings, ParticleEffect particleEffect) {
        super(settings, particleEffect);
    }
    public int getLuminance(BlockState state) {
        return state.get(POWERED) ? super.getLuminance(state) : 10;
    }
    @Override
    public String getTranslationKey() {
        return "block.secretrooms.soul_fire_torch_lever";
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
            world.addParticle(ParticleTypes.SOUL_FIRE_FLAME, d + 0.27D * (double)direction2.getOffsetX(), e + 0.22D, f + 0.27D * (double)direction2.getOffsetZ(), 0.0D, 0.0D, 0.0D);
        }
    }
}

package net.nomuratbm.pharmaceuticalsmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SparklingSugarBlock extends Block {

    public static final VoxelShape SHAPE =
            Block.box(6.0d, 0.0d, 6.0d, 10.0d, 4.0d, 10.0d);

    public SparklingSugarBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos position, CollisionContext context) {
        return SHAPE;
    }
}

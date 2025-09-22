package net.nomuratbm.pharmaceuticalsmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class PharmaceuticalsTableBlock extends Block {

    public PharmaceuticalsTableBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        else {
            pPlayer.openMenu(pState.getMenuProvider(pLevel, pPos));
            return InteractionResult.CONSUME;
        }
    }

}

package net.nomuratbm.pharmaceuticalsmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.nomuratbm.pharmaceuticalsmod.block.ModBlocks;

public class CreditCardItem extends Item {
    public CreditCardItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos position = pContext.getClickedPos();
        BlockState state = level.getBlockState(position);
        Player player = pContext.getPlayer();

        if(!level.isClientSide && state.is(ModBlocks.SPARKLINGSUGAR.get())) {
            level.setBlock(position, ModBlocks.SPARKLINGSUGAR_LINES.get().defaultBlockState(), 3);
            pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                    p -> player.broadcastBreakEvent(player.getUsedItemHand()));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}

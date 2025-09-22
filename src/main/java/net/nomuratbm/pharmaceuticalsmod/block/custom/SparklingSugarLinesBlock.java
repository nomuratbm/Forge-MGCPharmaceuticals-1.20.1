package net.nomuratbm.pharmaceuticalsmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SparklingSugarLinesBlock extends CakeBlock {
    public static final IntegerProperty BITES = CakeBlock.BITES;

    private static final VoxelShape STRIP_1 = Block.box(0.0D, 0.0D, 0.0D, 2.66D, 1.0D, 16.0D);
    private static final VoxelShape STRIP_2 = Block.box(2.66D,0.0D,0.0D,5.33D,1.0D,16.0D);
    private static final VoxelShape STRIP_3 = Block.box(5.33D,0.0D,0.0D,8.0D,1.0D,16.0D);
    private static final VoxelShape STRIP_4 = Block.box(8.0D,0.0D,0.0D,10.66D,1.0D,16.0D);
    private static final VoxelShape STRIP_5 = Block.box(10.66D,0.0D,0.0D,13.33D,1.0D,16.0D);
    private static final VoxelShape STRIP_6 = Block.box(13.33D,0.0D,0.0D,16.0D,1.0D,16.0D);

    private static final VoxelShape[] SHAPES = new VoxelShape[] {
            Shapes.or(STRIP_1, STRIP_2, STRIP_3, STRIP_4, STRIP_5, STRIP_6), // bites=0
            Shapes.or(STRIP_2, STRIP_3, STRIP_4, STRIP_5, STRIP_6),          // bites=1
            Shapes.or(STRIP_3, STRIP_4, STRIP_5, STRIP_6),                  // bites=2
            Shapes.or(STRIP_4, STRIP_5, STRIP_6),                          // bites=3
            Shapes.or(STRIP_5, STRIP_6),                                    // bites=4
            STRIP_6                                                         // bites=5
    };

    public SparklingSugarLinesBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(BITES, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(BITES);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        if(pLevel.isClientSide) {
            if(eat(pLevel, pPos, pState, pPlayer).consumesAction()) {
                return InteractionResult.SUCCESS;
            }
            if(itemstack.isEmpty()) {
                return InteractionResult.CONSUME;
            }
        }
        return eat(pLevel, pPos, pState, pPlayer);
    }

    protected static InteractionResult eat(LevelAccessor pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        if (!pPlayer.canEat(false)) {
            return InteractionResult.PASS;
        } else {
            pPlayer.awardStat(Stats.EAT_CAKE_SLICE);
            pPlayer.getFoodData().eat(2, 0.1F);
            if(!pLevel.isClientSide()) {
                pPlayer.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 20 * 30, 0));
                pPlayer.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 20 * 60, 1));
                pPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20 * 60, 0));
            }
            int i = pState.getValue(BITES);
            pLevel.gameEvent(pPlayer, GameEvent.EAT, pPos);
            if (i < 6) {
                pLevel.setBlock(pPos, pState.setValue(BITES, Integer.valueOf(i + 1)), 3);
            } else {
                pLevel.removeBlock(pPos, false);
                pLevel.gameEvent(pPlayer, GameEvent.BLOCK_DESTROY, pPos);
            }

            return InteractionResult.SUCCESS;
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        int bites = state.getValue(BITES);
        int idx = Mth.clamp(bites, 0, SHAPES.length - 1);
        return SHAPES[idx];
    }
}

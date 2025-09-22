package net.nomuratbm.pharmaceuticalsmod.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nomuratbm.pharmaceuticalsmod.PharmaceuticalsMod;
import net.nomuratbm.pharmaceuticalsmod.block.custom.PowerleafCropBlock;
import net.nomuratbm.pharmaceuticalsmod.block.custom.SparklingSugarBlock;
import net.nomuratbm.pharmaceuticalsmod.block.custom.SparklingSugarLinesBlock;
import net.nomuratbm.pharmaceuticalsmod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PharmaceuticalsMod.MOD_ID);

    public static final RegistryObject<Block> POWERLEAF_CROP = BLOCKS.register("powerleaf_crop",
            () -> new PowerleafCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));
    public static final RegistryObject<Block> RAW_LEAFWRAP_BLOCK = registerBlock("raw_leafwrap_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).sound(SoundType.WOOL).strength(0.2F)));
    public static final RegistryObject<Block> COOKED_LEAFWRAP_BLOCK = registerBlock("cooked_leafwrap_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).sound(SoundType.WOOL).strength(0.2F)));
    public static final RegistryObject<Block> SPARKLINGSUGAR = BLOCKS.register("sparklingsugar",
            () -> new SparklingSugarBlock(BlockBehaviour.Properties
                    .copy(Blocks.CAKE)
                    .noOcclusion()
                    .strength(0.2f)
                    .sound(SoundType.SWEET_BERRY_BUSH)));
    public static final RegistryObject<Block> SPARKLINGSUGAR_LINES = BLOCKS.register("sparklingsugar_lines",
            () -> new SparklingSugarLinesBlock(BlockBehaviour.Properties
                    .copy(Blocks.CAKE)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

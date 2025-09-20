package net.nomuratbm.pharmaceuticalsmod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nomuratbm.pharmaceuticalsmod.PharmaceuticalsMod;
import net.nomuratbm.pharmaceuticalsmod.block.ModBlocks;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PharmaceuticalsMod.MOD_ID);

    public static final RegistryObject<Item> POWERLEAF = ITEMS.register("powerleaf",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> POWERLEAF_SEEDS = ITEMS.register("powerleaf_seeds",
            () -> new ItemNameBlockItem(ModBlocks.POWERLEAF_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> RAW_LEAFWRAP = ITEMS.register("raw_leafwrap",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_LEAFWRAP)));
    public static final RegistryObject<Item> COOKED_LEAFWRAP = ITEMS.register("cooked_leafwrap",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_LEAFWRAP)));
    public static final RegistryObject<Item> SPARKLINGSUGAR = ITEMS.register("sparklingsugar",
            () -> new Item(new Item.Properties().food(ModFoods.SPARKLINGSUGAR)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}

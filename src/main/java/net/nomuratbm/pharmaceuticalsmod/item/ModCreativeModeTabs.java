package net.nomuratbm.pharmaceuticalsmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.nomuratbm.pharmaceuticalsmod.PharmaceuticalsMod;
import net.nomuratbm.pharmaceuticalsmod.block.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PharmaceuticalsMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> PHARMACEUTICALS_TAB = CREATIVE_MODE_TABS.register("pharmaceuticals_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.POWERLEAF.get()))
                    .title(Component.translatable("creativetab.pharmaceuticals_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.POWERLEAF.get());
                        output.accept(ModItems.POWERLEAF_SEEDS.get());
                        output.accept(ModItems.RAW_LEAFWRAP.get());
                        output.accept(ModItems.COOKED_LEAFWRAP.get());

                        output.accept(ModBlocks.RAW_LEAFWRAP_BLOCK.get());
                        output.accept(ModBlocks.COOKED_LEAFWRAP_BLOCK.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

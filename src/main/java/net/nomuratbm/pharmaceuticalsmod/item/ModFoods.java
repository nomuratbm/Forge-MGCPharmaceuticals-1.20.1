package net.nomuratbm.pharmaceuticalsmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties RAW_LEAFWRAP = new FoodProperties.Builder()
            .nutrition(1).fast()
            .saturationMod(0.05f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 20 * 30), 1f)
            .build();

    public static final FoodProperties COOKED_LEAFWRAP = new FoodProperties.Builder()
            .nutrition(2).fast()
            .saturationMod(0.2f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 15, 1), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 20 * 10), 1f)
            .build();
    public static final FoodProperties BROWNIE = new FoodProperties.Builder()
            .nutrition(2).fast()
            .saturationMod(0.5f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 30, 1), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 20 * 30, 1),1f)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 20 * 30, 1),1f)
            .build();

}

package com.ororura.fastesthopper.mixin;

import com.ororura.fastesthopper.configs.Config;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;


@Mixin(HopperBlockEntity.class)
public class MixinHopperBlockEntity {

    @Unique
    private static final Config fastesthopper$config = Config.loadConfig();

    @ModifyConstant(method = "tryMoveItems(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/entity/HopperBlockEntity;Ljava/util/function/BooleanSupplier;)Z", constant = @Constant(intValue = 8))
    private static int modifyCooldownTime(int original) {
        return fastesthopper$config.getCooldownValue();
    }
}
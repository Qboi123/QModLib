package com.qsoftware.modlib.common.interfaces;

import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Update item for FOV (Field of View).
 *
 * @author Qboi123
 */

/**
 * Implemented on Items which update/alter FOV under certain conditions.
 */
@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public interface FOVUpdateItem {
    float getFOVMod(ItemStack stack, PlayerEntity player);
}
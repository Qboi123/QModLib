package com.qsoftware.modlib.api.recipes.cache;

import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import com.qsoftware.modlib.api.annotations.NonNull;
import com.qsoftware.modlib.api.chemical.gas.Gas;
import com.qsoftware.modlib.api.chemical.gas.GasStack;
import com.qsoftware.modlib.api.recipes.ItemStackToGasRecipe;
import com.qsoftware.modlib.api.recipes.cache.chemical.ItemStackToChemicalCachedRecipe;
import com.qsoftware.modlib.api.recipes.inputs.IInputHandler;
import com.qsoftware.modlib.api.recipes.outputs.IOutputHandler;
import net.minecraft.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
public class ItemStackToGasCachedRecipe extends ItemStackToChemicalCachedRecipe<Gas, GasStack, ItemStackToGasRecipe> {

    public ItemStackToGasCachedRecipe(ItemStackToGasRecipe recipe, IInputHandler<@NonNull ItemStack> inputHandler, IOutputHandler<@NonNull GasStack> outputHandler) {
        super(recipe, inputHandler, outputHandler);
    }
}
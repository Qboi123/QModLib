package com.qsoftware.modlib.api.recipes.cache;

import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import com.qsoftware.modlib.api.annotations.NonNull;
import com.qsoftware.modlib.api.chemical.gas.Gas;
import com.qsoftware.modlib.api.chemical.gas.GasStack;
import com.qsoftware.modlib.api.recipes.GasToGasRecipe;
import com.qsoftware.modlib.api.recipes.cache.chemical.ChemicalToChemicalCachedRecipe;
import com.qsoftware.modlib.api.recipes.inputs.IInputHandler;
import com.qsoftware.modlib.api.recipes.inputs.chemical.GasStackIngredient;
import com.qsoftware.modlib.api.recipes.outputs.IOutputHandler;

import javax.annotation.ParametersAreNonnullByDefault;

@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
public class GasToGasCachedRecipe extends ChemicalToChemicalCachedRecipe<Gas, GasStack, GasStackIngredient, GasToGasRecipe> {

    public GasToGasCachedRecipe(GasToGasRecipe recipe, IInputHandler<@NonNull GasStack> inputHandler, IOutputHandler<@NonNull GasStack> outputHandler) {
        super(recipe, inputHandler, outputHandler);
    }
}
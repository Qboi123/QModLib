package com.qsoftware.modlib.api.recipes.cache;

import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import com.qsoftware.modlib.api.annotations.NonNull;
import com.qsoftware.modlib.api.chemical.gas.Gas;
import com.qsoftware.modlib.api.chemical.gas.GasStack;
import com.qsoftware.modlib.api.recipes.ItemStackGasToItemStackRecipe;
import com.qsoftware.modlib.api.recipes.cache.chemical.ItemStackChemicalToItemStackCachedRecipe;
import com.qsoftware.modlib.api.recipes.inputs.IInputHandler;
import com.qsoftware.modlib.api.recipes.inputs.ILongInputHandler;
import com.qsoftware.modlib.api.recipes.inputs.chemical.GasStackIngredient;
import com.qsoftware.modlib.api.recipes.outputs.IOutputHandler;
import net.minecraft.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.LongSupplier;

@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
public class ItemStackGasToItemStackCachedRecipe<RECIPE extends ItemStackGasToItemStackRecipe> extends
        ItemStackChemicalToItemStackCachedRecipe<Gas, GasStack, GasStackIngredient, RECIPE> {

    public ItemStackGasToItemStackCachedRecipe(RECIPE recipe, IInputHandler<@NonNull ItemStack> itemInputHandler,
                                               ILongInputHandler<@NonNull GasStack> gasInputHandler, LongSupplier gasUsage, IOutputHandler<@NonNull ItemStack> outputHandler) {
        super(recipe, itemInputHandler, gasInputHandler, gasUsage, outputHandler);
    }
}
package com.qsoftware.modlib.api.recipes;

import com.qsoftware.modlib.api.chemical.gas.Gas;
import com.qsoftware.modlib.api.chemical.gas.GasStack;
import com.qsoftware.modlib.api.recipes.chemical.ItemStackToChemicalRecipe;
import com.qsoftware.modlib.api.recipes.inputs.ItemStackIngredient;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class ItemStackToGasRecipe extends ItemStackToChemicalRecipe<Gas, GasStack> {

    public ItemStackToGasRecipe(ResourceLocation id, ItemStackIngredient input, GasStack output) {
        super(id, input, output);
    }

    @Override
    public GasStack getOutput(ItemStack input) {
        return output.copy();
    }
}
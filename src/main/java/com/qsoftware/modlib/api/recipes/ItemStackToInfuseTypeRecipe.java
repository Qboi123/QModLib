package com.qsoftware.modlib.api.recipes;

import com.qsoftware.modlib.api.chemical.infuse.InfuseType;
import com.qsoftware.modlib.api.chemical.infuse.InfusionStack;
import com.qsoftware.modlib.api.recipes.chemical.ItemStackToChemicalRecipe;
import com.qsoftware.modlib.api.recipes.inputs.ItemStackIngredient;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class ItemStackToInfuseTypeRecipe extends ItemStackToChemicalRecipe<InfuseType, InfusionStack> {

    public ItemStackToInfuseTypeRecipe(ResourceLocation id, ItemStackIngredient input, InfusionStack output) {
        super(id, input, output);
    }

    @Override
    public InfusionStack getOutput(ItemStack input) {
        return output.copy();
    }
}
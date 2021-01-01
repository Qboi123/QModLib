package com.qsoftware.modlib.api.recipes.cache;

import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import com.qsoftware.modlib.api.annotations.NonNull;
import com.qsoftware.modlib.api.chemical.slurry.Slurry;
import com.qsoftware.modlib.api.chemical.slurry.SlurryStack;
import com.qsoftware.modlib.api.recipes.FluidSlurryToSlurryRecipe;
import com.qsoftware.modlib.api.recipes.cache.chemical.FluidChemicalToChemicalCachedRecipe;
import com.qsoftware.modlib.api.recipes.inputs.IInputHandler;
import com.qsoftware.modlib.api.recipes.inputs.chemical.SlurryStackIngredient;
import com.qsoftware.modlib.api.recipes.outputs.IOutputHandler;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.ParametersAreNonnullByDefault;

@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
public class FluidSlurryToSlurryCachedRecipe extends FluidChemicalToChemicalCachedRecipe<Slurry, SlurryStack, SlurryStackIngredient, FluidSlurryToSlurryRecipe> {

    public FluidSlurryToSlurryCachedRecipe(FluidSlurryToSlurryRecipe recipe, IInputHandler<@NonNull FluidStack> fluidInputHandler,
                                           IInputHandler<@NonNull SlurryStack> slurryInputHandler, IOutputHandler<@NonNull SlurryStack> outputHandler) {
        super(recipe, fluidInputHandler, slurryInputHandler, outputHandler);
    }
}
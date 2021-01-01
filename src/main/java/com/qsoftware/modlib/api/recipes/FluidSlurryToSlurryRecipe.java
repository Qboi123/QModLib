package com.qsoftware.modlib.api.recipes;

import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import com.qsoftware.modlib.api.chemical.slurry.Slurry;
import com.qsoftware.modlib.api.chemical.slurry.SlurryStack;
import com.qsoftware.modlib.api.recipes.chemical.FluidChemicalToChemicalRecipe;
import com.qsoftware.modlib.api.recipes.inputs.FluidStackIngredient;
import com.qsoftware.modlib.api.recipes.inputs.chemical.SlurryStackIngredient;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.ParametersAreNonnullByDefault;

@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class FluidSlurryToSlurryRecipe extends FluidChemicalToChemicalRecipe<Slurry, SlurryStack, SlurryStackIngredient> {

    public FluidSlurryToSlurryRecipe(ResourceLocation id, FluidStackIngredient fluidInput, SlurryStackIngredient slurryInput, SlurryStack output) {
        super(id, fluidInput, slurryInput, output);
    }

    @Override
    public SlurryStack getOutput(FluidStack fluidStack, SlurryStack slurryStack) {
        return output.copy();
    }
}
package com.qsoftware.modlib.api.datagen.recipe.builder;

import com.google.gson.JsonObject;
import com.qsoftware.modlib.api.JsonConstants;
import com.qsoftware.modlib.api.SerializerHelper;
import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import com.qsoftware.modlib.api.chemical.slurry.SlurryStack;
import com.qsoftware.modlib.api.datagen.recipe.MekanismRecipeBuilder;
import com.qsoftware.modlib.api.recipes.inputs.FluidStackIngredient;
import com.qsoftware.modlib.api.recipes.inputs.chemical.SlurryStackIngredient;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class FluidSlurryToSlurryRecipeBuilder extends MekanismRecipeBuilder<FluidSlurryToSlurryRecipeBuilder> {

    private final SlurryStackIngredient slurryInput;
    private final FluidStackIngredient fluidInput;
    private final SlurryStack output;

    protected FluidSlurryToSlurryRecipeBuilder(FluidStackIngredient fluidInput, SlurryStackIngredient slurryInput, SlurryStack output) {
        super(mekSerializer("washing"));
        this.fluidInput = fluidInput;
        this.slurryInput = slurryInput;
        this.output = output;
    }

    public static FluidSlurryToSlurryRecipeBuilder washing(FluidStackIngredient fluidInput, SlurryStackIngredient slurryInput, SlurryStack output) {
        if (output.isEmpty()) {
            throw new IllegalArgumentException("This washing recipe requires a non empty slurry output.");
        }
        return new FluidSlurryToSlurryRecipeBuilder(fluidInput, slurryInput, output);
    }

    @Override
    protected FluidSlurryToSlurryRecipeResult getResult(ResourceLocation id) {
        return new FluidSlurryToSlurryRecipeResult(id);
    }

    public class FluidSlurryToSlurryRecipeResult extends RecipeResult {

        protected FluidSlurryToSlurryRecipeResult(ResourceLocation id) {
            super(id);
        }

        @Override
        public void serialize(@Nonnull JsonObject json) {
            json.add(JsonConstants.FLUID_INPUT, fluidInput.serialize());
            json.add(JsonConstants.SLURRY_INPUT, slurryInput.serialize());
            json.add(JsonConstants.OUTPUT, SerializerHelper.serializeSlurryStack(output));
        }
    }
}
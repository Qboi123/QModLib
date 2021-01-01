package com.qsoftware.modlib.api.datagen.recipe.builder;

import com.google.gson.JsonObject;
import com.qsoftware.modlib.api.JsonConstants;
import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import com.qsoftware.modlib.api.chemical.Chemical;
import com.qsoftware.modlib.api.chemical.ChemicalStack;
import com.qsoftware.modlib.api.chemical.gas.Gas;
import com.qsoftware.modlib.api.chemical.gas.GasStack;
import com.qsoftware.modlib.api.chemical.infuse.InfuseType;
import com.qsoftware.modlib.api.chemical.infuse.InfusionStack;
import com.qsoftware.modlib.api.datagen.recipe.MekanismRecipeBuilder;
import com.qsoftware.modlib.api.recipes.inputs.ItemStackIngredient;
import com.qsoftware.modlib.api.recipes.inputs.chemical.ChemicalIngredientDeserializer;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ItemStackToChemicalRecipeBuilder<CHEMICAL extends Chemical<CHEMICAL>, STACK extends ChemicalStack<CHEMICAL>> extends
        MekanismRecipeBuilder<ItemStackToChemicalRecipeBuilder<CHEMICAL, STACK>> {

    private final ChemicalIngredientDeserializer<CHEMICAL, STACK, ?> outputSerializer;
    private final ItemStackIngredient input;
    private final STACK output;

    protected ItemStackToChemicalRecipeBuilder(ResourceLocation serializerName, ItemStackIngredient input, STACK output,
                                               ChemicalIngredientDeserializer<CHEMICAL, STACK, ?> outputSerializer) {
        super(serializerName);
        this.input = input;
        this.output = output;
        this.outputSerializer = outputSerializer;
    }

    public static ItemStackToChemicalRecipeBuilder<Gas, GasStack> gasConversion(ItemStackIngredient input, GasStack output) {
        if (output.isEmpty()) {
            throw new IllegalArgumentException("This gas conversion recipe requires a non empty gas output.");
        }
        return new ItemStackToChemicalRecipeBuilder<>(mekSerializer("gas_conversion"), input, output, ChemicalIngredientDeserializer.GAS);
    }

    public static ItemStackToChemicalRecipeBuilder<Gas, GasStack> oxidizing(ItemStackIngredient input, GasStack output) {
        if (output.isEmpty()) {
            throw new IllegalArgumentException("This oxidizing recipe requires a non empty gas output.");
        }
        return new ItemStackToChemicalRecipeBuilder<>(mekSerializer("oxidizing"), input, output, ChemicalIngredientDeserializer.GAS);
    }

    public static ItemStackToChemicalRecipeBuilder<InfuseType, InfusionStack> infusionConversion(ItemStackIngredient input, InfusionStack output) {
        if (output.isEmpty()) {
            throw new IllegalArgumentException("This infusion conversion recipe requires a non empty infusion output.");
        }
        return new ItemStackToChemicalRecipeBuilder<>(mekSerializer("infusion_conversion"), input, output, ChemicalIngredientDeserializer.INFUSION);
    }

    @Override
    protected ItemStackToChemicalRecipeResult getResult(ResourceLocation id) {
        return new ItemStackToChemicalRecipeResult(id);
    }

    public class ItemStackToChemicalRecipeResult extends RecipeResult {

        protected ItemStackToChemicalRecipeResult(ResourceLocation id) {
            super(id);
        }

        @Override
        public void serialize(@Nonnull JsonObject json) {
            json.add(JsonConstants.INPUT, input.serialize());
            json.add(JsonConstants.OUTPUT, outputSerializer.serializeStack(output));
        }
    }
}
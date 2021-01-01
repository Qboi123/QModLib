package com.qsoftware.modlib.api.datagen.recipe.builder;

import com.google.gson.JsonObject;
import com.qsoftware.modlib.api.JsonConstants;
import com.qsoftware.modlib.api.SerializerHelper;
import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import com.qsoftware.modlib.api.chemical.ChemicalStack;
import com.qsoftware.modlib.api.chemical.merged.BoxedChemicalStack;
import com.qsoftware.modlib.api.datagen.recipe.MekanismRecipeBuilder;
import com.qsoftware.modlib.api.recipes.inputs.ItemStackIngredient;
import com.qsoftware.modlib.api.recipes.inputs.chemical.GasStackIngredient;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ChemicalDissolutionRecipeBuilder extends MekanismRecipeBuilder<ChemicalDissolutionRecipeBuilder> {

    private final ItemStackIngredient itemInput;
    private final GasStackIngredient gasInput;
    private final BoxedChemicalStack output;

    protected ChemicalDissolutionRecipeBuilder(ResourceLocation serializerName, ItemStackIngredient itemInput, GasStackIngredient gasInput,
                                               ChemicalStack<?> output) {
        super(serializerName);
        this.itemInput = itemInput;
        this.gasInput = gasInput;
        this.output = BoxedChemicalStack.box(output);
    }

    public static ChemicalDissolutionRecipeBuilder dissolution(ItemStackIngredient itemInput, GasStackIngredient gasInput, ChemicalStack<?> output) {
        if (output.isEmpty()) {
            throw new IllegalArgumentException("This dissolution chamber recipe requires a non empty chemical output.");
        }
        return new ChemicalDissolutionRecipeBuilder(mekSerializer("dissolution"), itemInput, gasInput, output);
    }

    @Override
    protected ChemicalDissolutionRecipeResult getResult(ResourceLocation id) {
        return new ChemicalDissolutionRecipeResult(id);
    }

    public class ChemicalDissolutionRecipeResult extends RecipeResult {

        protected ChemicalDissolutionRecipeResult(ResourceLocation id) {
            super(id);
        }

        @Override
        public void serialize(@Nonnull JsonObject json) {
            json.add(JsonConstants.ITEM_INPUT, itemInput.serialize());
            json.add(JsonConstants.GAS_INPUT, gasInput.serialize());
            json.add(JsonConstants.OUTPUT, SerializerHelper.serializeBoxedChemicalStack(output));
        }
    }
}
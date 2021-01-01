package com.qsoftware.modlib.api.recipes.inputs.chemical;

import com.qsoftware.modlib.api.annotations.NonNull;
import com.qsoftware.modlib.api.chemical.Chemical;
import com.qsoftware.modlib.api.chemical.ChemicalStack;
import com.qsoftware.modlib.api.recipes.inputs.InputIngredient;

import javax.annotation.Nonnull;

public interface IChemicalStackIngredient<CHEMICAL extends Chemical<CHEMICAL>, STACK extends ChemicalStack<CHEMICAL>> extends InputIngredient<@NonNull STACK> {

    boolean testType(@Nonnull CHEMICAL chemical);

    /**
     * @apiNote This is for use in implementations and should probably not be accessed for other purposes
     */
    ChemicalIngredientInfo<CHEMICAL, STACK> getIngredientInfo();
}
package com.qsoftware.modlib.api.datagen.recipe;

import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import net.minecraft.advancements.ICriterionInstance;

import javax.annotation.ParametersAreNonnullByDefault;

@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
public class RecipeCriterion {

    public final String name;
    public final ICriterionInstance criterion;

    private RecipeCriterion(String name, ICriterionInstance criterion) {
        this.name = name;
        this.criterion = criterion;
    }

    public static RecipeCriterion of(String name, ICriterionInstance criterion) {
        return new RecipeCriterion(name, criterion);
    }
}
package com.qsoftware.modlib.api.recipes.inputs.chemical;

import com.google.gson.JsonElement;
import com.qsoftware.modlib.api.chemical.gas.Gas;
import com.qsoftware.modlib.api.chemical.gas.GasStack;
import com.qsoftware.modlib.api.providers.IGasProvider;
import com.qsoftware.modlib.api.recipes.inputs.chemical.ChemicalStackIngredient.MultiIngredient;
import com.qsoftware.modlib.api.recipes.inputs.chemical.ChemicalStackIngredient.SingleIngredient;
import com.qsoftware.modlib.api.recipes.inputs.chemical.ChemicalStackIngredient.TaggedIngredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tags.ITag;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface GasStackIngredient extends IChemicalStackIngredient<Gas, GasStack> {

    static GasStackIngredient from(@Nonnull GasStack instance) {
        return from(instance.getType(), instance.getAmount());
    }

    static GasStackIngredient from(@Nonnull IGasProvider gas, long amount) {
        return new Single(gas.getStack(amount));
    }

    static GasStackIngredient from(@Nonnull ITag<Gas> tag, long amount) {
        return new Tagged(tag, amount);
    }

    static GasStackIngredient read(PacketBuffer buffer) {
        return ChemicalIngredientDeserializer.GAS.read(buffer);
    }

    static GasStackIngredient deserialize(@Nullable JsonElement json) {
        return ChemicalIngredientDeserializer.GAS.deserialize(json);
    }

    static GasStackIngredient createMulti(GasStackIngredient... ingredients) {
        return ChemicalIngredientDeserializer.GAS.createMulti(ingredients);
    }

    @Override
    default ChemicalIngredientInfo<Gas, GasStack> getIngredientInfo() {
        return ChemicalIngredientInfo.GAS;
    }

    class Single extends SingleIngredient<Gas, GasStack> implements GasStackIngredient {

        protected Single(@Nonnull GasStack stack) {
            super(stack);
        }
    }

    class Tagged extends TaggedIngredient<Gas, GasStack> implements GasStackIngredient {

        protected Tagged(@Nonnull ITag<Gas> tag, long amount) {
            super(tag, amount);
        }
    }

    class Multi extends MultiIngredient<Gas, GasStack, GasStackIngredient> implements GasStackIngredient {

        protected Multi(@Nonnull GasStackIngredient... ingredients) {
            super(ingredients);
        }
    }
}
package com.qsoftware.modlib.api.recipes;

import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import com.qsoftware.modlib.api.annotations.NonNull;
import com.qsoftware.modlib.api.chemical.Chemical;
import com.qsoftware.modlib.api.chemical.ChemicalStack;
import com.qsoftware.modlib.api.chemical.ChemicalType;
import com.qsoftware.modlib.api.chemical.merged.BoxedChemicalStack;
import com.qsoftware.modlib.api.recipes.inputs.chemical.IChemicalStackIngredient;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Contract;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class ChemicalCrystallizerRecipe extends MekanismRecipe implements Predicate<@NonNull BoxedChemicalStack> {

    private final ChemicalType chemicalType;
    private final IChemicalStackIngredient<?, ?> input;
    private final ItemStack output;

    public ChemicalCrystallizerRecipe(ResourceLocation id, IChemicalStackIngredient<?, ?> input, ItemStack output) {
        super(id);
        this.input = input;
        this.chemicalType = ChemicalType.getTypeFor(input);
        this.output = output.copy();
    }

    @Contract(value = "_ -> new", pure = true)
    public ItemStack getOutput(BoxedChemicalStack input) {
        return output.copy();
    }

    public List<ItemStack> getOutputDefinition() {
        return output.isEmpty() ? Collections.emptyList() : Collections.singletonList(output);
    }

    @Override
    public boolean test(BoxedChemicalStack chemicalStack) {
        return chemicalType == chemicalStack.getChemicalType() && testInternal(chemicalStack.getChemicalStack());
    }

    public boolean test(ChemicalStack<?> stack) {
        return chemicalType == ChemicalType.getTypeFor(stack) && testInternal(stack);
    }

    private <CHEMICAL extends Chemical<CHEMICAL>, STACK extends ChemicalStack<CHEMICAL>> boolean testInternal(STACK stack) {
        return ((IChemicalStackIngredient<CHEMICAL, STACK>) input).test(stack);
    }

    public IChemicalStackIngredient<?, ?> getInput() {
        return input;
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeEnumValue(chemicalType);
        input.write(buffer);
        buffer.writeItemStack(output);
    }
}
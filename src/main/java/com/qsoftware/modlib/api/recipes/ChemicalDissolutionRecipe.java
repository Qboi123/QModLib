package com.qsoftware.modlib.api.recipes;

import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import com.qsoftware.modlib.api.annotations.NonNull;
import com.qsoftware.modlib.api.chemical.ChemicalStack;
import com.qsoftware.modlib.api.chemical.gas.GasStack;
import com.qsoftware.modlib.api.chemical.merged.BoxedChemicalStack;
import com.qsoftware.modlib.api.recipes.inputs.ItemStackIngredient;
import com.qsoftware.modlib.api.recipes.inputs.chemical.GasStackIngredient;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Contract;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.BiPredicate;

/**
 * Inputs: ItemStack + GasStack Output: GasStack
 * <p>
 * Chemical Dissolution Chamber
 *
 * @apiNote The gas input is a base value, and will still be multiplied by a per tick usage
 */
@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class ChemicalDissolutionRecipe extends MekanismRecipe implements BiPredicate<@NonNull ItemStack, @NonNull GasStack> {

    private final ItemStackIngredient itemInput;
    private final GasStackIngredient gasInput;
    private final BoxedChemicalStack output;

    public ChemicalDissolutionRecipe(ResourceLocation id, ItemStackIngredient itemInput, GasStackIngredient gasInput, ChemicalStack<?> output) {
        super(id);
        this.itemInput = itemInput;
        this.gasInput = gasInput;
        this.output = BoxedChemicalStack.box(output);
    }

    public ItemStackIngredient getItemInput() {
        return itemInput;
    }

    public GasStackIngredient getGasInput() {
        return gasInput;
    }

    @Contract(value = "_, _ -> new", pure = true)
    public BoxedChemicalStack getOutput(ItemStack inputItem, GasStack inputGas) {
        return output.isEmpty() ? BoxedChemicalStack.EMPTY : BoxedChemicalStack.box(output.getChemicalStack().copy());
    }

    @Override
    public boolean test(ItemStack itemStack, GasStack gasStack) {
        return itemInput.test(itemStack) && gasInput.test(gasStack);
    }

    public BoxedChemicalStack getOutputDefinition() {
        return output;
    }

    @Override
    public void write(PacketBuffer buffer) {
        itemInput.write(buffer);
        gasInput.write(buffer);
        buffer.writeEnumValue(output.getChemicalType());
        output.getChemicalStack().writeToPacket(buffer);
    }
}
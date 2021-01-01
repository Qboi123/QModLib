package com.qsoftware.modlib.api.recipes.chemical;

import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import com.qsoftware.modlib.api.annotations.NonNull;
import com.qsoftware.modlib.api.chemical.Chemical;
import com.qsoftware.modlib.api.chemical.ChemicalStack;
import com.qsoftware.modlib.api.recipes.MekanismRecipe;
import com.qsoftware.modlib.api.recipes.inputs.ItemStackIngredient;
import com.qsoftware.modlib.api.recipes.inputs.chemical.IChemicalStackIngredient;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Contract;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class ItemStackChemicalToItemStackRecipe<CHEMICAL extends Chemical<CHEMICAL>, STACK extends ChemicalStack<CHEMICAL>,
        INGREDIENT extends IChemicalStackIngredient<CHEMICAL, STACK>> extends MekanismRecipe implements BiPredicate<@NonNull ItemStack, @NonNull STACK> {

    private final ItemStackIngredient itemInput;
    private final INGREDIENT chemicalInput;
    private final ItemStack output;

    public ItemStackChemicalToItemStackRecipe(ResourceLocation id, ItemStackIngredient itemInput, INGREDIENT chemicalInput, ItemStack output) {
        super(id);
        this.itemInput = itemInput;
        this.chemicalInput = chemicalInput;
        this.output = output;
    }

    public ItemStackIngredient getItemInput() {
        return itemInput;
    }

    public INGREDIENT getChemicalInput() {
        return chemicalInput;
    }

    @Contract(value = "_, _ -> new", pure = true)
    public ItemStack getOutput(ItemStack inputItem, STACK inputChemical) {
        return output.copy();
    }

    @Override
    public boolean test(ItemStack itemStack, STACK gasStack) {
        return itemInput.test(itemStack) && chemicalInput.test(gasStack);
    }

    public @NonNull List<@NonNull ItemStack> getOutputDefinition() {
        return output.isEmpty() ? Collections.emptyList() : Collections.singletonList(output);
    }

    @Override
    public void write(PacketBuffer buffer) {
        itemInput.write(buffer);
        chemicalInput.write(buffer);
        buffer.writeItemStack(output);
    }
}
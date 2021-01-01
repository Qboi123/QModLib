package com.qsoftware.modlib.api.recipes;

import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import com.qsoftware.modlib.api.annotations.NonNull;
import com.qsoftware.modlib.api.recipes.inputs.ItemStackIngredient;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Contract;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * Inputs: ItemStack (main item) + ItemStack (material to combine with) Output: ItemStack (combined)
 */
@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class CombinerRecipe extends MekanismRecipe implements BiPredicate<@NonNull ItemStack, @NonNull ItemStack> {

    private final ItemStackIngredient mainInput;
    private final ItemStackIngredient extraInput;
    private final ItemStack output;

    public CombinerRecipe(ResourceLocation id, ItemStackIngredient mainInput, ItemStackIngredient extraInput, ItemStack output) {
        super(id);
        this.mainInput = mainInput;
        this.extraInput = extraInput;
        this.output = output.copy();
    }

    @Override
    public boolean test(@Nonnull ItemStack input, @Nonnull ItemStack extra) {
        return mainInput.test(input) && extraInput.test(extra);
    }

    public ItemStackIngredient getMainInput() {
        return mainInput;
    }

    public ItemStackIngredient getExtraInput() {
        return extraInput;
    }

    @Contract(value = "_, _ -> new", pure = true)
    public ItemStack getOutput(@Nonnull ItemStack input, @Nonnull ItemStack extra) {
        return output.copy();
    }

    /**
     * For JEI, gets a display stack
     *
     * @return Representation of output, MUST NOT be modified
     */
    public List<ItemStack> getOutputDefinition() {
        return output.isEmpty() ? Collections.emptyList() : Collections.singletonList(output);
    }

    @Override
    public void write(PacketBuffer buffer) {
        mainInput.write(buffer);
        extraInput.write(buffer);
        buffer.writeItemStack(output);
    }
}
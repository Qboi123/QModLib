package com.qsoftware.modlib.api.recipes;

import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import com.qsoftware.modlib.api.chemical.gas.GasStack;
import com.qsoftware.modlib.api.recipes.inputs.FluidStackIngredient;
import com.qsoftware.modlib.api.recipes.inputs.chemical.GasStackIngredient;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Contract;

import javax.annotation.ParametersAreNonnullByDefault;

@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class RotaryRecipe extends MekanismRecipe {

    private static final GasStackIngredient EMPTY_GAS_INPUT = GasStackIngredient.from(GasStack.EMPTY);
    private static final FluidStackIngredient EMPTY_FLUID_INPUT = FluidStackIngredient.from(FluidStack.EMPTY);
    private final GasStackIngredient gasInput;
    private final FluidStackIngredient fluidInput;
    private final FluidStack fluidOutput;
    private final GasStack gasOutput;
    private boolean hasGasToFluid = true;
    private boolean hasFluidToGas = true;

    public RotaryRecipe(ResourceLocation id, FluidStackIngredient fluidInput, GasStack gasOutput) {
        this(id, fluidInput, EMPTY_GAS_INPUT, gasOutput, FluidStack.EMPTY);
        hasGasToFluid = false;
    }

    public RotaryRecipe(ResourceLocation id, GasStackIngredient gasInput, FluidStack fluidOutput) {
        this(id, EMPTY_FLUID_INPUT, gasInput, GasStack.EMPTY, fluidOutput);
        hasFluidToGas = false;
    }

    public RotaryRecipe(ResourceLocation id, FluidStackIngredient fluidInput, GasStackIngredient gasInput, GasStack gasOutput, FluidStack fluidOutput) {
        super(id);
        this.gasInput = gasInput;
        this.fluidInput = fluidInput;
        this.gasOutput = gasOutput;
        this.fluidOutput = fluidOutput;
    }

    /**
     * @return True if this recipe knows how to convert a gas to a fluid, false otherwise.
     */
    public boolean hasGasToFluid() {
        return hasGasToFluid;
    }

    /**
     * @return True if this recipe knows how to convert a fluid to a gas, false otherwise.
     */
    public boolean hasFluidToGas() {
        return hasFluidToGas;
    }

    public boolean test(FluidStack fluidStack) {
        return hasFluidToGas() && fluidInput.test(fluidStack);
    }

    public boolean test(GasStack gasStack) {
        return hasGasToFluid() && gasInput.test(gasStack);
    }

    public FluidStackIngredient getFluidInput() {
        return fluidInput;
    }

    public GasStackIngredient getGasInput() {
        return gasInput;
    }

    public GasStack getGasOutputRepresentation() {
        return gasOutput;
    }

    public FluidStack getFluidOutputRepresentation() {
        return fluidOutput;
    }

    @Contract(value = "_ -> new", pure = true)
    public GasStack getGasOutput(FluidStack input) {
        return gasOutput.copy();
    }

    @Contract(value = "_ -> new", pure = true)
    public FluidStack getFluidOutput(GasStack input) {
        return fluidOutput.copy();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeBoolean(hasFluidToGas);
        if (hasFluidToGas) {
            fluidInput.write(buffer);
            gasOutput.writeToPacket(buffer);
        }
        buffer.writeBoolean(hasGasToFluid);
        if (hasGasToFluid) {
            gasInput.write(buffer);
            fluidOutput.writeToPacket(buffer);
        }
    }
}
package com.qsoftware.modlib.api.recipes.outputs;

import com.qsoftware.modlib.api.chemical.ChemicalStack;
import com.qsoftware.modlib.api.chemical.IChemicalTank;
import com.qsoftware.modlib.api.chemical.merged.BoxedChemicalStack;
import com.qsoftware.modlib.api.chemical.merged.MergedChemicalTank;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

@ParametersAreNonnullByDefault
public class BoxedChemicalOutputHandler {

    private final MergedChemicalTank chemicalTank;

    public BoxedChemicalOutputHandler(MergedChemicalTank chemicalTank) {
        this.chemicalTank = Objects.requireNonNull(chemicalTank);
    }

    public void handleOutput(BoxedChemicalStack toOutput, int operations) {
        handleOutput(chemicalTank.getTankForType(toOutput.getChemicalType()), toOutput.getChemicalStack(), operations);
    }

    private <STACK extends ChemicalStack<?>> void handleOutput(IChemicalTank<?, ?> tank, STACK stack, int operations) {
        OutputHelper.handleOutput((IChemicalTank<?, STACK>) tank, stack, operations);
    }

    public int operationsRoomFor(BoxedChemicalStack toOutput, int currentMax) {
        return operationsRoomFor(chemicalTank.getTankForType(toOutput.getChemicalType()), toOutput.getChemicalStack(), currentMax);
    }

    private <STACK extends ChemicalStack<?>> int operationsRoomFor(IChemicalTank<?, ?> tank, STACK stack, int currentMax) {
        return OutputHelper.operationsRoomFor((IChemicalTank<?, STACK>) tank, stack, currentMax);
    }
}
package com.qsoftware.modlib.api.chemical.gas;

import com.qsoftware.modlib.api.chemical.IChemicalHandler;
import com.qsoftware.modlib.api.chemical.IMekanismChemicalHandler;
import com.qsoftware.modlib.api.chemical.ISidedChemicalHandler;

public interface IGasHandler extends IChemicalHandler<Gas, GasStack>, IEmptyGasProvider {

    /**
     * A sided variant of {@link IGasHandler}
     */
    interface ISidedGasHandler extends ISidedChemicalHandler<Gas, GasStack>, IGasHandler {
    }

    interface IMekanismGasHandler extends IMekanismChemicalHandler<Gas, GasStack, IGasTank>, ISidedGasHandler {
    }
}
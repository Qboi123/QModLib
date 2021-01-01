package com.qsoftware.modlib.api.providers;

import com.qsoftware.modlib.api.chemical.gas.Gas;
import com.qsoftware.modlib.api.chemical.gas.GasStack;

import javax.annotation.Nonnull;

public interface IGasProvider extends IChemicalProvider<Gas> {

    @Nonnull
    @Override
    default GasStack getStack(long size) {
        return new GasStack(getChemical(), size);
    }
}
package com.qsoftware.modlib.api.providers;

import com.qsoftware.modlib.api.chemical.infuse.InfuseType;
import com.qsoftware.modlib.api.chemical.infuse.InfusionStack;

import javax.annotation.Nonnull;

public interface IInfuseTypeProvider extends IChemicalProvider<InfuseType> {

    @Nonnull
    @Override
    default InfusionStack getStack(long size) {
        return new InfusionStack(getChemical(), size);
    }
}
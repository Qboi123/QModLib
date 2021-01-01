package com.qsoftware.modlib.api.chemical.infuse;

import com.qsoftware.modlib.api.chemical.IEmptyStackProvider;

import javax.annotation.Nonnull;

public interface IEmptyInfusionProvider extends IEmptyStackProvider<InfuseType, InfusionStack> {

    @Nonnull
    @Override
    default InfusionStack getEmptyStack() {
        return InfusionStack.EMPTY;
    }
}
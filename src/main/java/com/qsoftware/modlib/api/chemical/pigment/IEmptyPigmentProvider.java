package com.qsoftware.modlib.api.chemical.pigment;

import com.qsoftware.modlib.api.chemical.IEmptyStackProvider;

import javax.annotation.Nonnull;

public interface IEmptyPigmentProvider extends IEmptyStackProvider<Pigment, PigmentStack> {

    @Nonnull
    @Override
    default PigmentStack getEmptyStack() {
        return PigmentStack.EMPTY;
    }
}
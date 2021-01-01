package com.qsoftware.modlib.api.providers;

import com.qsoftware.modlib.api.chemical.slurry.Slurry;
import com.qsoftware.modlib.api.chemical.slurry.SlurryStack;

import javax.annotation.Nonnull;

public interface ISlurryProvider extends IChemicalProvider<Slurry> {

    @Nonnull
    @Override
    default SlurryStack getStack(long size) {
        return new SlurryStack(getChemical(), size);
    }
}
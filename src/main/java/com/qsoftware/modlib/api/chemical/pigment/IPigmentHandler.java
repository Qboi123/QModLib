package com.qsoftware.modlib.api.chemical.pigment;

import com.qsoftware.modlib.api.chemical.IChemicalHandler;
import com.qsoftware.modlib.api.chemical.IMekanismChemicalHandler;
import com.qsoftware.modlib.api.chemical.ISidedChemicalHandler;

public interface IPigmentHandler extends IChemicalHandler<Pigment, PigmentStack>, IEmptyPigmentProvider {

    /**
     * A sided variant of {@link IPigmentHandler}
     */
    interface ISidedPigmentHandler extends ISidedChemicalHandler<Pigment, PigmentStack>, IPigmentHandler {
    }

    interface IMekanismPigmentHandler extends IMekanismChemicalHandler<Pigment, PigmentStack, IPigmentTank>, ISidedPigmentHandler {
    }
}
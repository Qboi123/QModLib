package com.qsoftware.modlib.api.chemical.infuse;

import com.qsoftware.modlib.api.chemical.IChemicalHandler;
import com.qsoftware.modlib.api.chemical.IMekanismChemicalHandler;
import com.qsoftware.modlib.api.chemical.ISidedChemicalHandler;

public interface IInfusionHandler extends IChemicalHandler<InfuseType, InfusionStack>, IEmptyInfusionProvider {

    /**
     * A sided variant of {@link IInfusionHandler}
     */
    interface ISidedInfusionHandler extends ISidedChemicalHandler<InfuseType, InfusionStack>, IInfusionHandler {
    }

    interface IMekanismInfusionHandler extends IMekanismChemicalHandler<InfuseType, InfusionStack, IInfusionTank>, ISidedInfusionHandler {
    }
}
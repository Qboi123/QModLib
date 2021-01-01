package com.qsoftware.modlib.api.chemical.gas;

import com.qsoftware.modlib.api.MekanismAPI;
import com.qsoftware.modlib.api.NBTConstants;
import com.qsoftware.modlib.api.chemical.Chemical;
import com.qsoftware.modlib.api.chemical.ChemicalTags;
import com.qsoftware.modlib.api.chemical.ChemicalUtils;
import com.qsoftware.modlib.api.providers.IGasProvider;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Gas - a class used to set specific properties of gases when used or seen in-game.
 *
 * @author aidancbrady
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class Gas extends Chemical<Gas> implements IGasProvider {

    public Gas(GasBuilder builder) {
        super(builder, ChemicalTags.GAS);
    }

    /**
     * Returns the Gas stored in the defined tag compound.
     *
     * @param nbtTags - tag compound to get the Gas from
     * @return Gas stored in the tag compound
     */
    public static Gas readFromNBT(@Nullable CompoundNBT nbtTags) {
        return ChemicalUtils.readChemicalFromNBT(nbtTags, MekanismAPI.EMPTY_GAS, NBTConstants.GAS_NAME, Gas::getFromRegistry);
    }

    public static Gas getFromRegistry(@Nullable ResourceLocation name) {
        return ChemicalUtils.readChemicalFromRegistry(name, MekanismAPI.EMPTY_GAS, MekanismAPI.gasRegistry());
    }

    @Override
    public CompoundNBT write(CompoundNBT nbtTags) {
        nbtTags.putString(NBTConstants.GAS_NAME, getRegistryName().toString());
        return nbtTags;
    }

    @Override
    public String toString() {
        return "[Gas: " + getRegistryName() + "]";
    }

    @Override
    public final boolean isEmptyType() {
        return this == MekanismAPI.EMPTY_GAS;
    }

    @Override
    protected String getDefaultTranslationKey() {
        return Util.makeTranslationKey("gas", getRegistryName());
    }
}
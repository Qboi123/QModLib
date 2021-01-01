package com.qsoftware.modlib.api;

import com.qsoftware.modlib.api.chemical.gas.EmptyGas;
import com.qsoftware.modlib.api.chemical.gas.Gas;
import com.qsoftware.modlib.api.chemical.infuse.EmptyInfuseType;
import com.qsoftware.modlib.api.chemical.infuse.InfuseType;
import com.qsoftware.modlib.api.chemical.pigment.EmptyPigment;
import com.qsoftware.modlib.api.chemical.pigment.Pigment;
import com.qsoftware.modlib.api.chemical.slurry.EmptySlurry;
import com.qsoftware.modlib.api.chemical.slurry.Slurry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

public class MekanismAPI {

    /**
     * The version of the api classes - may not always match the mod's version
     */
    public static final String API_VERSION = "10.0.14";
    public static final String MEKANISM_MODID = "mekanism";
    public static final Logger logger = LogManager.getLogger(MEKANISM_MODID + "_api");
    //Note: None of the empty variants support registry replacement
    @Nonnull
    public static final Gas EMPTY_GAS = new EmptyGas();
    @Nonnull
    public static final InfuseType EMPTY_INFUSE_TYPE = new EmptyInfuseType();
    @Nonnull
    public static final Pigment EMPTY_PIGMENT = new EmptyPigment();
    @Nonnull
    public static final Slurry EMPTY_SLURRY = new EmptySlurry();
    /**
     * QForgeUtils debug mode
     */
    public static boolean debug = false;
    private static IForgeRegistry<Gas> GAS_REGISTRY;
    private static IForgeRegistry<InfuseType> INFUSE_TYPE_REGISTRY;
    private static IForgeRegistry<Pigment> PIGMENT_REGISTRY;
    private static IForgeRegistry<Slurry> SLURRY_REGISTRY;

    private MekanismAPI() {
    }

    @Nonnull
    public static IForgeRegistry<Gas> gasRegistry() {
        if (GAS_REGISTRY == null) {
            GAS_REGISTRY = RegistryManager.ACTIVE.getRegistry(Gas.class);
        }
        return GAS_REGISTRY;
    }

    @Nonnull
    public static IForgeRegistry<InfuseType> infuseTypeRegistry() {
        if (INFUSE_TYPE_REGISTRY == null) {
            INFUSE_TYPE_REGISTRY = RegistryManager.ACTIVE.getRegistry(InfuseType.class);
        }
        return INFUSE_TYPE_REGISTRY;
    }

    @Nonnull
    public static IForgeRegistry<Pigment> pigmentRegistry() {
        if (PIGMENT_REGISTRY == null) {
            PIGMENT_REGISTRY = RegistryManager.ACTIVE.getRegistry(Pigment.class);
        }
        return PIGMENT_REGISTRY;
    }

    @Nonnull
    public static IForgeRegistry<Slurry> slurryRegistry() {
        if (SLURRY_REGISTRY == null) {
            SLURRY_REGISTRY = RegistryManager.ACTIVE.getRegistry(Slurry.class);
        }
        return SLURRY_REGISTRY;
    }
}
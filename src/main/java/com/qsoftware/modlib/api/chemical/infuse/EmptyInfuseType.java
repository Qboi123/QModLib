package com.qsoftware.modlib.api.chemical.infuse;

import com.qsoftware.modlib.api.MekanismAPI;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Set;

public final class EmptyInfuseType extends InfuseType {

    public EmptyInfuseType() {
        super(InfuseTypeBuilder.builder().hidden());
        setRegistryName(new ResourceLocation(MekanismAPI.MEKANISM_MODID, "empty_infuse_type"));
    }

    @Override
    public boolean isIn(@Nonnull ITag<InfuseType> tags) {
        //Empty infuse type is in no tags
        return false;
    }

    @Nonnull
    @Override
    public Set<ResourceLocation> getTags() {
        return Collections.emptySet();
    }
}
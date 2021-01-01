package com.qsoftware.modlib.silentlib.registry;

import com.qsoftware.modlib.api.providers.IEntityTypeProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nonnull;

public class EntityTypeRegistryObject<ENTITY extends Entity> extends RegistryObjectWrapper<EntityType<ENTITY>> implements IEntityTypeProvider {

    public EntityTypeRegistryObject(RegistryObject<EntityType<ENTITY>> registryObject) {
        super(registryObject);
    }

    @Nonnull
    @Override
    public EntityType<ENTITY> getEntityType() {
        return get();
    }
}
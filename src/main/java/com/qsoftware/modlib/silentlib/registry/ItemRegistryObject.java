package com.qsoftware.modlib.silentlib.registry;

import com.qsoftware.modlib.api.providers.IItemProvider;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ItemRegistryObject<T extends Item> extends RegistryObjectWrapper<T> implements IItemProvider {
    public ItemRegistryObject(RegistryObject<T> item) {
        super(item);
    }

    @Override
    public T asItem() {
        return registryObject.get();
    }
}

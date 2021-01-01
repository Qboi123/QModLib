package com.qsoftware.modlib.common;

import com.qsoftware.modlib.api.providers.IItemProvider;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;

public class ItemProvider implements IItemProvider {
    private final Item item;

    public ItemProvider(Item item) {
        this.item = item;
    }

    @NotNull
    @Override
    public Item asItem() {
        return item;
    }
}

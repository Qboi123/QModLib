package com.qsoftware.modlib.common;

import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import com.qsoftware.modlib.api.providers.IItemProvider;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("unused")
@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ItemSelfProvider implements IItemProvider {
    private final Item item;

    public ItemSelfProvider(Item item) {
        this.item = item;
    }

    @NotNull
    @Override
    public Item asItem() {
        return item;
    }
}

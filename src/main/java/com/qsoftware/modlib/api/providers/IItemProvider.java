package com.qsoftware.modlib.api.providers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public interface IItemProvider extends IBaseProvider, net.minecraft.util.IItemProvider {

    @Nonnull
    Item asItem();

    @Nonnull
    default ItemStack asItemStack() {
        return asItemStack(1);
    }

    @Nonnull
    default ItemStack asItemStack(int size) {
        return new ItemStack(asItem(), size);
    }

    default boolean itemMatches(ItemStack otherStack) {
        return itemMatches(otherStack.getItem());
    }

    default boolean itemMatches(Item other) {
        return asItem() == other;
    }

    @Override
    default ResourceLocation getRegistryName() {
        return asItem().getRegistryName();
    }

    @Override
    default String getTranslationKey() {
        return asItem().getTranslationKey();
    }
}
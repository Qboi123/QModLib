package com.qsoftware.modlib.api.providers;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public interface IBlockProvider extends IItemProvider, com.qsoftware.modlib.silentlib.block.IBlockProvider {

    @Nonnull
    Block asBlock();

    default boolean blockMatches(ItemStack otherStack) {
        Item item = otherStack.getItem();
        return item instanceof BlockItem && blockMatches(((BlockItem) item).getBlock());
    }

    default boolean blockMatches(Block other) {
        return asBlock() == other;
    }

    @NotNull
    @Override
    default Item asItem() {
        return asBlock().asItem();
    }

    @Override
    default ResourceLocation getRegistryName() {
        //Make sure to use the block's registry name in case it somehow doesn't match
        return asBlock().getRegistryName();
    }

    @Override
    default String getTranslationKey() {
        return asBlock().getTranslationKey();
    }
}
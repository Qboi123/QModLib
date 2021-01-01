package com.qsoftware.modlib.silentlib.crafting.recipe;

import com.qsoftware.modlib.QModLib;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.ShapelessRecipe;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class DamageItemRecipe extends ExtendedShapelessRecipe {
    public static final Serializer<DamageItemRecipe> SERIALIZER = new Serializer<>(
            DamageItemRecipe::new,
            (json, recipe) -> recipe.damageToItems = JSONUtils.getInt(json, "damage", 1),
            (buffer, recipe) -> recipe.damageToItems = buffer.readVarInt(),
            (buffer, recipe) -> buffer.writeVarInt(recipe.damageToItems)
    );

    private int damageToItems = 1;

    public DamageItemRecipe(ShapelessRecipe recipe) {
        super(recipe);
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public boolean matches(CraftingInventory inv, World worldIn) {
        return getBaseRecipe().matches(inv, worldIn);
    }

    @Override
    public ItemStack getCraftingResult(CraftingInventory inv) {
        return getBaseRecipe().getCraftingResult(inv);
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInventory inv) {
        NonNullList<ItemStack> list = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

        for (int i = 0; i < list.size(); ++i) {
            ItemStack stack = inv.getStackInSlot(i);
            if (stack.hasContainerItem()) {
                list.set(i, stack.getContainerItem());
            } else if (stack.getMaxDamage() > 0) {
                ItemStack tool = stack.copy();
                if (tool.attemptDamageItem(this.damageToItems, QModLib.RANDOM, null)) {
                    tool.shrink(1);
                }
                list.set(i, tool);
            }
        }

        return list;
    }
}

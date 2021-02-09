package com.qsoftware.modlib.utils.helpers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * NBT helper.
 *
 * @author Qboi123
 */
public class NBTHelper {
    /**
     * @deprecated Use the write method of the object itself.
     */
    public static CompoundNBT toNBT(Object o) {
        if (o instanceof ItemStack) {
            return writeItemStack((ItemStack) o);
        }

        return null;
    }

    /**
     * @deprecated Use {@link ItemStack#write(CompoundNBT)} instead.
     */
    @SuppressWarnings("ConstantConditions")
    @Deprecated
    private static CompoundNBT writeItemStack(ItemStack i) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("count", i.getCount());
        nbt.putString("item", i.getItem().getRegistryName().toString());
        nbt.putByte("type", (byte) 0);
        return nbt;
    }

    /**
     * @deprecated Use the read method of the object / class itself.
     */
    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    @Deprecated
    @Nullable
    public static Object fromNBT(@Nonnull CompoundNBT compound) {
        switch (compound.getByte("type")) {
            case 0:
                return readItemStack(compound);
            default:
                return null;
        }
    }

    /**
     * @deprecated Use {@link ItemStack#read(CompoundNBT)} instead.
     */
    @Deprecated
    private static ItemStack readItemStack(CompoundNBT compound) {
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(compound.getString("item")));
        int count = compound.getInt("count");
        return new ItemStack(item, count);
    }
}

package com.qsoftware.modlib.api;

import com.qsoftware.modlib.api.chemical.IChemicalTank;
import com.qsoftware.modlib.api.energy.IEnergyContainer;
import com.qsoftware.modlib.api.heat.IHeatCapacitor;
import com.qsoftware.modlib.api.inventory.IInventorySlot;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fluids.IFluidTank;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class DataHandlerUtils {

    private DataHandlerUtils() {
    }

    /**
     * Helper to read and load a list of containers from a {@link ListNBT}
     */
    public static void readContainers(List<? extends INBTSerializable<CompoundNBT>> containers, ListNBT storedContainers) {
        readContents(containers, storedContainers, getTagByType(containers));
    }

    /**
     * Helper to read and load a list of containers to a {@link ListNBT}
     */
    public static ListNBT writeContainers(List<? extends INBTSerializable<CompoundNBT>> containers) {
        return writeContents(containers, getTagByType(containers));
    }

    /**
     * Helper to read and load a list of handler contents from a {@link ListNBT}
     */
    public static void readContents(List<? extends INBTSerializable<CompoundNBT>> contents, ListNBT storedContents, String key) {
        int size = contents.size();
        for (int tagCount = 0; tagCount < storedContents.size(); tagCount++) {
            CompoundNBT tagCompound = storedContents.getCompound(tagCount);
            byte id = tagCompound.getByte(key);
            if (id >= 0 && id < size) {
                contents.get(id).deserializeNBT(tagCompound);
            }
        }
    }

    /**
     * Helper to read and load a list of handler contents to a {@link ListNBT}
     */
    public static ListNBT writeContents(List<? extends INBTSerializable<CompoundNBT>> contents, String key) {
        ListNBT storedContents = new ListNBT();
        for (int tank = 0; tank < contents.size(); tank++) {
            CompoundNBT tagCompound = contents.get(tank).serializeNBT();
            if (!tagCompound.isEmpty()) {
                tagCompound.putByte(key, (byte) tank);
                storedContents.add(tagCompound);
            }
        }
        return storedContents;
    }

    // keep this only for backwards compat
    private static String getTagByType(List<? extends INBTSerializable<CompoundNBT>> containers) {
        if (containers.isEmpty()) {
            return NBTConstants.CONTAINER;
        }
        INBTSerializable<CompoundNBT> obj = containers.get(0);
        if (obj instanceof IChemicalTank || obj instanceof IFluidTank) {
            return NBTConstants.TANK;
        } else if (obj instanceof IHeatCapacitor || obj instanceof IEnergyContainer) {
            return NBTConstants.CONTAINER;
        } else if (obj instanceof IInventorySlot) {
            return NBTConstants.SLOT;
        }
        return NBTConstants.CONTAINER;
    }

    public static int getMaxId(ListNBT storedContents, String key) {
        int maxId = -1;
        for (int tagCount = 0; tagCount < storedContents.size(); tagCount++) {
            byte id = storedContents.getCompound(tagCount).getByte(key);
            if (id > maxId) {
                maxId = id;
            }
        }
        return maxId + 1;
    }
}
package com.qsoftware.modlib.common.enums;

import com.google.common.annotations.Beta;
import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import mcp.MethodsReturnNonnullByDefault;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@Beta
@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public enum VMType {
    VMWARE("VMware, Inc.", "VMware");

    private final String manufacturer;
    private final String name;

    VMType(String manufacturer, String name) {
        this.manufacturer = manufacturer;
        this.name = name;
    }

    @Nullable
    public static VMType getFromManufacturer(String manufacturer) {
        for (VMType vmType : values()) {
            if (vmType.manufacturer.equals(manufacturer)) {
                return vmType;
            }
        }
        return null;
    }

    public String getName() {
        return this.name;
    }
}

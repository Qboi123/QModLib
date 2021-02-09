package com.qsoftware.modlib.common;

import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import com.qsoftware.modlib.common.interfaces.Formattable;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.ParametersAreNonnullByDefault;

@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class Angle implements Formattable {
    private final double degrees;

    public Angle(double degrees) {
        this.degrees = degrees;
    }
    
    public String toFormattedString() {
        return TextFormatting.BLUE.toString() + this.degrees + ((char)0xb0);
    }
}

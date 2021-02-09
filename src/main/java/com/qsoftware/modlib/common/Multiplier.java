package com.qsoftware.modlib.common;

import com.qsoftware.modlib.common.interfaces.Formattable;
import com.qsoftware.modlib.utils.helpers.MathHelper;
import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("unused")
@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class Multiplier implements Formattable {
    private final double value;

    public Multiplier(double value) {
        this.value = value;
    }

    @Override
    public String toFormattedString() {
        if (MathHelper.getDecimalPlaces(value) == 0) {
            return TextFormatting.GOLD.toString() + Math.round(value) + TextFormatting.GRAY + "x";
        }

        return TextFormatting.GOLD.toString() + value + TextFormatting.GRAY + "x";
    }

    public double getValue() {
        return value;
    }

    public Percentage toPercentage() {
        return new Percentage(value);
    }
}

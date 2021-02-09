package com.qsoftware.modlib.common;

import com.qsoftware.modlib.common.interfaces.Formattable;
import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.ParametersAreNonnullByDefault;

@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class Percentage implements Formattable {
    private double percentage;

    public Percentage(double value) {
        this.percentage = value * 100;
    }

    public Percentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toFormattedString() {
        return TextFormatting.BLUE.toString() + Math.round(percentage) + "%";
    }

    public double getPercentage() {
        return percentage;
    }

    public double getValue() {
        return percentage / 100;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public void setValue(double value) {
        this.percentage = value * 100;
    }

    public Multiplier toMultiplier() {
        return new Multiplier(this.percentage / 100);
    }
}

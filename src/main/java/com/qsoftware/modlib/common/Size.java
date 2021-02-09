package com.qsoftware.modlib.common;

import com.qsoftware.modlib.common.interfaces.Formattable;
import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("unused")
@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class Size implements Formattable {
    private final int width;
    private final int height;

    public Size(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toFormattedString() {
        return TextFormatting.GOLD.toString() + this.width + TextFormatting.GRAY + " x " + TextFormatting.GOLD + this.height;
    }
}

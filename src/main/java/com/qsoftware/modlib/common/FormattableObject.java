package com.qsoftware.modlib.common;

import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import com.qsoftware.modlib.common.interfaces.Formattable;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("unused")
@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class FormattableObject implements Formattable {
    @Override
    public String toFormattedString() {
        return TextFormatting.AQUA + getClass().getPackage().getName().replaceAll("\\.", TextFormatting.GRAY + "." + TextFormatting.AQUA) +
                TextFormatting.GRAY + "." +
                TextFormatting.DARK_AQUA + getClass().getSimpleName() +
                TextFormatting.GRAY + "@" +
                TextFormatting.GREEN + Integer.toHexString(hashCode());
    }
}

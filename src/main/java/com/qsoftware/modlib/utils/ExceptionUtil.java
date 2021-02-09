package com.qsoftware.modlib.utils;

public final class ExceptionUtil {
    private ExceptionUtil() {
        throw utilityConstructor();
    }

    public static IllegalAccessError utilityConstructor() {
        return new IllegalAccessError("Tried to initialize constructor of utility class.");
    }
}

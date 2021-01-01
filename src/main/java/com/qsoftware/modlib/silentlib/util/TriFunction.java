package com.qsoftware.modlib.silentlib.util;

public interface TriFunction<A, B, C, R> {
    R apply(A a, B b, C c);
}

package com.qsoftware.modlib.common;

import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import mcp.MethodsReturnNonnullByDefault;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;
import java.util.function.Predicate;

@SuppressWarnings("unused")
@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class Ticker {
    private int ticks;
    private final Predicate<Ticker> autoReset;
    private final Consumer<Ticker> onTick;

    public Ticker(int startValue) {
        this(startValue, (ticker) -> false);
    }

    public Ticker(int startValue, @NotNull Predicate<Ticker> autoReset) {
        this(startValue, autoReset, (ticker) -> {});
    }

    public Ticker(int startValue, @NotNull Predicate<Ticker> autoReset, @NotNull Consumer<Ticker> onTick) {
        this.ticks = startValue;
        this.autoReset = autoReset;
        this.onTick = onTick;
    }
    
    public void advance() {
        this.ticks++;
        onTick.accept(this);
        if (autoReset.test(this)) {
            reset();
        }
    }
    
    public void reset() {
        this.ticks = 0;
    }

    public int getTicks() {
        return ticks;
    }
}

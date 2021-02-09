package com.qsoftware.modlib.utils.helpers;

import com.qsoftware.modlib.utils.ExceptionUtil;

public abstract class UtilityClass {
    protected UtilityClass() {
        throw ExceptionUtil.utilityConstructor();
    }
}

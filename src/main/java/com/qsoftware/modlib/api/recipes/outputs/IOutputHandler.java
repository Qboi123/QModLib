package com.qsoftware.modlib.api.recipes.outputs;

public interface IOutputHandler<OUTPUT> {

    void handleOutput(OUTPUT toOutput, int operations);

    int operationsRoomFor(OUTPUT toOutput, int currentMax);
}
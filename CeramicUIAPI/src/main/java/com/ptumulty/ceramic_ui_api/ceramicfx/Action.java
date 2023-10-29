package com.ptumulty.ceramic_ui_api.ceramicfx;

public interface Action<E extends Exception>
{
    void doAction() throws E;
}

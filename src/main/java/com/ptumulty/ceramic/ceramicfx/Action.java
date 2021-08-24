package com.ptumulty.ceramic.ceramicfx;

public interface Action<E extends Exception>
{
    void doAction() throws E;
}

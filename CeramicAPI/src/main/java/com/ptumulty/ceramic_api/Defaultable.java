package com.ptumulty.ceramic_api;

public interface Defaultable<T>
{
    boolean isModified();

    void restoreDefault();

    void setDefault(T value);

    T getDefault();
}

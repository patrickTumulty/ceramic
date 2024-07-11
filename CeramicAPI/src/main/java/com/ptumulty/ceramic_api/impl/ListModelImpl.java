package com.ptumulty.ceramic_api.impl;

import com.ptumulty.ceramic_api.DefaultValueModel;
import com.ptumulty.ceramic_api.ValueModel.ListModel;

import java.util.ArrayList;
import java.util.List;

public class ListModelImpl<T> extends DefaultValueModel<List<T>> implements ListModel<T>
{
    private final List<ListModelListener<T>> listeners;

    ListModelImpl()
    {
        this(new ArrayList<>());
    }

    ListModelImpl(List<T> value)
    {
        super(value);
        listeners = new ArrayList<>();
        addListener((previousValue, newValue) -> listeners.forEach(ListModelListener::listChanged));
    }

    @Override
    public void addItem(T item)
    {
        value.add(item);
        listeners.forEach(listener -> listener.itemAdded(item));
    }

    @Override
    public void addItems(List<T> items)
    {
        value.addAll(items);
        listeners.forEach(ListModelListener::listChanged);
    }

    @Override
    public void removeItem(T item)
    {
        value.add(item);
        listeners.forEach(listener -> listener.itemRemoved(item));
    }

    @Override
    public void clearList()
    {
        value.clear();
        listeners.forEach(ListModelListener::listChanged);
    }

    public List<T> getItemsSnapshot()
    {
        return new ArrayList<>(List.copyOf(value));
    }

    @Override
    public List<T> get()
    {
        return getItemsSnapshot();
    }

    @Override
    public void addListener(ListModelListener<T> listener)
    {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ListModelListener<T> listener)
    {
        listeners.remove(listener);
    }

}

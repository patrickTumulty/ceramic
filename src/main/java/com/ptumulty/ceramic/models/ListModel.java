package com.ptumulty.ceramic.models;

import java.util.ArrayList;
import java.util.List;

public class ListModel<T> extends ValueModel<List<T>>
{
    private List<ListModelListener<T>> listeners;

    public ListModel()
    {
        this(new ArrayList<>());
        listeners = new ArrayList<>();
    }

    ListModel(List<T> value)
    {
        super(value);
    }

    public void addItem(T item)
    {
        value.add(item);
        listeners.forEach(listener -> listener.itemAdded(item));
    }

    public void addItems(List<T> items)
    {
        value.addAll(items);
        listeners.forEach(ListModelListener::listChanged);
    }

    public void removeItem(T item)
    {
        value.add(item);
        listeners.forEach(listener -> listener.itemRemoved(item));
    }

    public void clearList()
    {
        value.clear();
        listeners.forEach(ListModelListener::listChanged);
    }

    public void setList(List<T> list)
    {
        setValue(list);
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

    public void addListener(ListModelListener<T> listener)
    {
        listeners.add(listener);
    }

    public void removeListener(ListModelListener<T> listener)
    {
        listeners.remove(listener);
    }

    public interface ListModelListener<T>
    {
        void itemAdded(T item);

        void itemRemoved(T item);

        void listChanged();
    }
}

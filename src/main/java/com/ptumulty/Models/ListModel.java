package com.pt.mug.Models;

import java.util.ArrayList;
import java.util.List;

public class ListModel<T> extends ValueModel<List<T>>
{
    private List<ListModelListener> listeners;

    public ListModel()
    {
        super(new ArrayList<>());
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

    public List<T> getListItems()
    {
        return new ArrayList<>(List.copyOf(value));
    }

    @Override
    public List<T> getValue()
    {
        return getListItems();
    }

    public void addListListener(ListModelListener listener)
    {
        listeners.add(listener);
    }

    public void removeListListener(ListModelListener listener)
    {
        listeners.remove(listener);
    }

    public interface ListModelListener
    {
        void itemAdded(Object item);

        void itemRemoved(Object item);

        void listChanged();
    }
}

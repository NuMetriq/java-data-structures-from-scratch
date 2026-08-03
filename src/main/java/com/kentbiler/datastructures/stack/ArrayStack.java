package com.kentbiler.datastructures.stack;

import com.kentbiler.datastructures.list.DynamicArray;

public class ArrayStack<T> {

    private final DynamicArray<T> elements = new DynamicArray<>();

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }
}

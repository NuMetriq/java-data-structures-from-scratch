package com.kentbiler.datastructures.stack;

import com.kentbiler.datastructures.list.DynamicArray;

import java.util.NoSuchElementException;

public class ArrayStack<T> {

    private final DynamicArray<T> elements = new DynamicArray<>();

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void push(T value) {
        elements.add(value);
    }

    public T peek() {
        if (elements.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }

        return elements.get(elements.size() - 1);
    }

    public T pop() {
        if (elements.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }

        return elements.remove(elements.size() - 1);
    }
}

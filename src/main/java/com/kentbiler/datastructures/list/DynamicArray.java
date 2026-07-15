package com.kentbiler.datastructures.list;

public class DynamicArray<T> {

    private Object[] elements;
    private int size;

    public DynamicArray() {
        elements = new Object[10];
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return elements.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T value) {
        if (size == elements.length) {
            grow();
        }

        elements[size] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                "Index: " + index + ", Size: " + size
            );
        }
    }

    private void grow() {
        Object[] largerArray = new Object[elements.length * 2];

        for (int i = 0; i < elements.length; i++) {
            largerArray[i] = elements[i];
        }

        elements = largerArray;
    }
}

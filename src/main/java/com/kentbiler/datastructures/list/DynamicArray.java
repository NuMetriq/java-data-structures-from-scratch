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

    @SuppressWarnings("unchecked")
    public T set(int index, T value) {
        checkIndex(index);

        T previousValue = (T) elements[index];
        elements[index] = value;

        return previousValue;
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);

        T removedValue = (T) elements[index];

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        size--;
        elements[size] = null;

        return removedValue;
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

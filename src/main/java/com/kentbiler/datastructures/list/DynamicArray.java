package com.kentbiler.datastructures.list;

import java.util.Objects;

public class DynamicArray<T> {

    private Object[] elements;
    private int size;

    public DynamicArray() {
        elements = new Object[10];
    }

    public DynamicArray(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException(
                "Initial capacity cannot be negative"
            );
        }

        elements = new Object[initialCapacity];
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

    public void add(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                "Index: " + index + ", Size: " + size
            );
        }

        if (size == elements.length) {
            grow();
        }

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = value;
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
        int newCapacity = elements.length == 0 ? 1 : elements.length * 2;
        Object[] largerArray = new Object[newCapacity];

        for (int i = 0; i < elements.length; i++) {
            largerArray[i] = elements[i];
        }

        elements = largerArray;
    }

    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], value)) {
                return i;
            }
        }

        return -1;
    }

    public int lastIndexOf(T value) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(elements[i], value)) {
                return i;
            }
        }

        return -1;
    }

    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }

        size = 0;
    }

    public boolean remove(T value) {
        int index = indexOf(value);

        if(index == -1) {
            return false;
        }

        remove(index);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            result.append(elements[i]);

            if (i < size - 1) {
                result.append(", ");
            }
        }

        result.append("]");
        return result.toString();
    }

    public Object[] toArray() {
        Object[] result = new Object[size];

        for (int i = 0; i < size; i++) {
            result[i] = elements[i];
        }

        return result;
    }
}

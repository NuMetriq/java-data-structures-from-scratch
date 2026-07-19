package com.kentbiler.datastructures.list;

import java.util.Objects;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class DynamicArray<T> implements Iterable<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;
    private int size;
    private int modCount;

    public DynamicArray() {
        elements = new Object[DEFAULT_CAPACITY];
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
        modCount++;
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
        modCount++;
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
        modCount++;

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
        int newCapacity = elements.length == 0
            ? 1
            : elements.length * 2;

        resize(newCapacity);
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
        modCount++;
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

    public void ensureCapacity(int minimumCapacity) {
        if (minimumCapacity < 0) {
            throw new IllegalArgumentException(
                "Minimum capacity cannot be negative"
            );
        }

        if (minimumCapacity > elements.length) {
            resize(minimumCapacity);
        }
    }

    public void trimToSize() {
        resize(size);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int currentIndex;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                return currentIndex < size;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements");
                }

                return get(currentIndex++);
            }
        };
    }

    private void resize(int newCapacity) {
        Object[] resizedArray = new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            resizedArray[i] = elements[i];
        }

        elements = resizedArray;
    }
}

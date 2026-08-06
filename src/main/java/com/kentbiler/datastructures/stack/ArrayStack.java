package com.kentbiler.datastructures.stack;

import com.kentbiler.datastructures.list.DynamicArray;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<T> implements Iterable<T> {

    private final DynamicArray<T> elements = new DynamicArray<>();

    private int modCount;

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void push(T value) {
        elements.add(value);
        modCount++;
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

        T value = elements.remove(elements.size() - 1);
        modCount++;
        return value;
    }

    public void clear() {
        if (!elements.isEmpty()) {
            elements.clear();
            modCount++;
        }
    }

    @Override
    public String toString() {
        return elements.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int currentIndex = elements.size() - 1;
            private final int expectedModCount = modCount;

            private void checkForModification() {
                if (expectedModCount != ArrayStack.this.modCount) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override
            public boolean hasNext() {
                checkForModification();
                return currentIndex >= 0;
            }

            @Override
            public T next() {
                checkForModification();

                if (currentIndex < 0) {
                    throw new NoSuchElementException();
                }

                return elements.get(currentIndex--);
            }
        };
    }
}

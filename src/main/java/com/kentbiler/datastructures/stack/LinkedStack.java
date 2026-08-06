package com.kentbiler.datastructures.stack;

import com.kentbiler.datastructures.list.SinglyLinkedList;

import java.util.Iterator;

public class LinkedStack<T> implements Stack<T>, Iterable<T> {

    private final SinglyLinkedList<T> elements = new SinglyLinkedList<>();

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public void clear() {
        elements.clear();
    }

    @Override
    public void push(T value) {
       elements.addFirst(value);
    }

    @Override
    public T peek() {
        return elements.getFirst();
    }

    @Override
    public T pop() {
        return elements.removeFirst();
    }

    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }
}

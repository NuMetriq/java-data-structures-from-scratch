package com.kentbiler.datastructures.queue;

import com.kentbiler.datastructures.list.DoublyLinkedList;

public class LinkedDeque<T> implements Deque<T> {

    private final DoublyLinkedList<T> elements = new DoublyLinkedList<>();

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public void addFirst(T value) {
        elements.addFirst(value);
    }

    @Override
    public void addLast(T value) {
        elements.addLast(value);
    }

    @Override
    public T removeFirst() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T removeLast() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T peekFirst() {
        return elements.getFirst();
    }

    @Override
    public T peekLast() {
        return elements.getLast();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
}

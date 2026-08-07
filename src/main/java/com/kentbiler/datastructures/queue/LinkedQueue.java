package com.kentbiler.datastructures.queue;

import com.kentbiler.datastructures.list.DoublyLinkedList;

public class LinkedQueue<T> implements Queue<T> {

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
    public void enqueue(T value) {
        elements.addLast(value);
    }

    @Override
    public T dequeue() {
        return elements.removeFirst();
    }

    @Override
    public T peek() {
        return elements.getFirst();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
}

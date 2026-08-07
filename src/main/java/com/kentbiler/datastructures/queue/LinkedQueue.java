package com.kentbiler.datastructures.queue;

import com.kentbiler.datastructures.list.DoublyLinkedList;

import java.util.Iterator;

public class LinkedQueue<T> implements Queue<T>, Iterable<T> {

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
        elements.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }
}

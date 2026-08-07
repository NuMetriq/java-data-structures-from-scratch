package com.kentbiler.datastructures.queue;

import com.kentbiler.datastructures.list.DoublyLinkedList;

import java.util.Iterator;

public class LinkedDeque<T> implements Deque<T>, Iterable<T> {

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
        return elements.removeFirst();
    }

    @Override
    public T removeLast() {
        return elements.removeLast();
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
        elements.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }
}

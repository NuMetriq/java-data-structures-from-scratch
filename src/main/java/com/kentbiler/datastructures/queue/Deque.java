package com.kentbiler.datastructures.queue;

public interface Deque<T> {

    void addFirst(T value);

    void addLast(T value);

    T removeFirst();

    T removeLast();

    T peekFirst();

    T peekLast();

    int size();

    boolean isEmpty();

    void clear();
}

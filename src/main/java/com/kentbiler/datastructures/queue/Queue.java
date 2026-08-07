package com.kentbiler.datastructures.queue;

public interface Queue<T> {

    void enqueue(T value);

    T dequeue();

    T peek();

    int size();

    boolean isEmpty();

    void clear();
}

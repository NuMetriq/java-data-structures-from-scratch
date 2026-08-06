package com.kentbiler.datastructures.stack;

public interface Stack<T> {

    void push(T value);

    T pop();

    T peek();

    int size();

    boolean isEmpty();

    void clear();
}

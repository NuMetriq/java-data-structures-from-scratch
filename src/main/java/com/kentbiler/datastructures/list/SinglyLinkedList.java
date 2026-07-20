package com.kentbiler.datastructures.list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {

    private static class Node<T> {
        private T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value);

        newNode.next = head;
        head = newNode;
        size++;
    }

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }

        return head.value;
    }
}

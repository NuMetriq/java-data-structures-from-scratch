package com.kentbiler.datastructures.list;

import java.util.NoSuchElementException;

public class DoublyLinkedList<T> {
    private static class Node<T> {
        private T value;
        private Node<T> previous;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }

        size++;
    }

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }

        return head.value;
    }

    public void addLast(T value) {
        Node<T> newNode = new Node<>(value);

        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.previous = tail;
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    public T getLast() {
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }

        return tail.value;
    }

    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }

        T removedValue = head.value;

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.previous = null;
        }

        size--;

        return removedValue;
    }

    public T removeLast() {
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }

        T removedValue = tail.value;

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.previous;
            tail.next = null;
        }

        size--;

        return removedValue;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                "Index: " + index + ", Size: " + size
            );
        }
    }

    private Node<T> nodeAt(int index) {
        if (index < size / 2) {
            Node<T> current = head;

            for (int i = 0; i < index; i++) {
                current = current.next;
            }

            return current;
        }

        Node<T> current = tail;

        for (int i = size - 1; i > index; i--) {
            current = current.previous;
        }

        return current;
    }

    public T get(int index) {
        checkIndex(index);
        return nodeAt(index).value;
    }

    public T set(int index, T value) {
        checkIndex(index);

        Node<T> node = nodeAt(index);
        T previousValue = node.value;

        node.value = value;

        return previousValue;
    }
}

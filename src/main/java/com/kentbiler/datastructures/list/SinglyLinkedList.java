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

    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }

        T removedValue = head.value;
        head = head.next;
        size--;

        return removedValue;
    }

    public void addLast(T value) {
        Node<T> newNode = new Node<>(value);

        if (head == null) {
            head = newNode;
            size++;
            return;
        }

        Node<T> current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        size++;
    }

    public T getLast() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }

        Node<T> current = head;

        while (current.next != null) {
            current = current.next;
        }

        return current.value;
    }

    public T removeLast() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }

        if (head.next == null) {
            T removedValue = head.value;
            head = null;
            size--;
            return removedValue;
        }

        Node<T> current = head;

        while (current.next.next != null) {
            current = current.next;
        }

        T removedValue = current.next.value;
        current.next = null;
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

    public T get(int index) {
        checkIndex(index);

        Node<T> current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.value;
    }

    public T set(int index, T value) {
        checkIndex(index);

        Node<T> current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        T previousValue = current.value;
        current.value = value;

        return previousValue;
    }

    public void add(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                "Index: " + index + ", Size: " + size
            );
        }

        if (index == 0) {
            addFirst(value);
            return;
        }

        Node<T> previous = head;

        for (int i = 0; i < index - 1; i++) {
            previous = previous.next;
        }

        Node<T> newNode = new Node<>(value);
        newNode.next = previous.next;
        previous.next = newNode;
        size++;
    }

    public T remove(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        Node<T> previous = head;

        for (int i = 0; i < index - 1; i++) {
            previous = previous.next;
        }

        T removedValue = previous.next.value;
        previous.next = previous.next.next;
        size--;

        return removedValue;
    }
}

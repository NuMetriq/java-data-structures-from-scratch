package com.kentbiler.datastructures.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> implements Iterable<T> {

    private static final class Node<T> {
        private T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private int size;
    private int modCount;

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
        modCount++;
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
        modCount++;

        return removedValue;
    }

    public void addLast(T value) {
        Node<T> newNode = new Node<>(value);

        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }

        size++;
        modCount++;
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
            modCount++;

            return removedValue;
        }

        Node<T> previous = head;
        Node<T> current = head.next;

        while (current.next != null) {
            previous = current;
            current = current.next;
        }

        previous.next = null;

        size--;
        modCount++;

        return current.value;
    }

    public void add(int index, T value) {
        checkPositionIndex(index);

        if (index == 0) {
            addFirst(value);
            return;
        }

        if (index == size) {
            addLast(value);
            return;
        }

        Node<T> previous = nodeAt(index - 1);
        Node<T> newNode = new Node<>(value);

        newNode.next = previous.next;
        previous.next = newNode;

        size++;
        modCount++;
    }

    public T get(int index) {
        checkElementIndex(index);
        return nodeAt(index).value;
    }

    public T set(int index, T value) {
        checkElementIndex(index);

        Node<T> node = nodeAt(index);
        T previousValue = node.value;

        node.value = value;

        return previousValue;
    }

    public T remove(int index) {
        checkElementIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        if (index == size - 1) {
            return removeLast();
        }

        Node<T> previous = nodeAt(index - 1);
        Node<T> removedNode = previous.next;

        previous.next = removedNode.next;

        size--;
        modCount++;

        return removedNode.value;
    }

    public int indexOf(T value) {
        Node<T> current = head;
        int index = 0;

        while (current != null) {
            if (Objects.equals(current.value, value)) {
                return index;
            }

            current = current.next;
            index++;
        }

        return -1;
    }

    public int lastIndexOf(T value) {
        Node<T> current = head;
        int currentIndex = 0;
        int lastMatchingIndex = -1;

        while (current != null) {
            if (Objects.equals(current.value, value)) {
                lastMatchingIndex = currentIndex;
            }

            current = current.next;
            currentIndex++;
        }

        return lastMatchingIndex;
    }

    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    public boolean remove(T value) {
        Node<T> previous = null;
        Node<T> current = head;

        while (current != null) {
            if (Objects.equals(current.value, value)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }

                size--;
                modCount++;
                return true;
            }

            previous = current;
            current = current.next;
        }

        return false;
    }

    public void clear() {
        if (size == 0) {
            return;
        }

        head = null;
        size = 0;
        modCount++;
    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        Node<T> current = head;
        int index = 0;

        while (current != null) {
            result[index] = current.value;
            current = current.next;
            index++;
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<T> current = head;

        while (current != null) {
            result.append(current.value);

            if (current.next != null) {
                result.append(", ");
            }

            current = current.next;
        }

        result.append("]");
        return result.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private Node<T> current = head;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                checkForConcurrentModification();
                return current != null;
            }

            @Override
            public T next() {
                checkForConcurrentModification();

                if (current == null) {
                    throw new NoSuchElementException("No more elements");
                }

                T value = current.value;
                current = current.next;

                return value;
            }

            private void checkForConcurrentModification() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException(
                        "List changed after iterator creation"
                    );
                }
            }
        };
    }

    private Node<T> nodeAt(int index) {
        Node<T> current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                "Index: " + index + ", Size: " + size
            );
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                "Index: " + index + ", Size: " + size
            );
        }
    }
}

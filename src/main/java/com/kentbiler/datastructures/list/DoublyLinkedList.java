package com.kentbiler.datastructures.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class DoublyLinkedList<T> implements Iterable<T> {

    private static final class Node<T> {
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

        if (head == null) {
            tail = newNode;
        } else {
            head.previous = newNode;
        }

        head = newNode;
        size++;
        modCount++;
    }

    public void addLast(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.previous = tail;

        if (tail == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }

        tail = newNode;
        size++;
        modCount++;
    }

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }

        return head.value;
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

        return unlink(head);
    }

    public T removeLast() {
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }

        return unlink(tail);
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

        Node<T> nextNode = nodeAt(index);
        Node<T> previousNode = nextNode.previous;
        Node<T> newNode = new Node<>(value);

        newNode.previous = previousNode;
        newNode.next = nextNode;

        previousNode.next = newNode;
        nextNode.previous = newNode;

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
        return unlink(nodeAt(index));
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
        Node<T> current = tail;
        int index = size - 1;

        while (current != null) {
            if (Objects.equals(current.value, value)) {
                return index;
            }

            current = current.previous;
            index--;
        }

        return -1;
    }

    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    public boolean remove(T value) {
        Node<T> current = head;

        while (current != null) {
            if (Objects.equals(current.value, value)) {
                unlink(current);
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public void clear() {
        if (size == 0) {
            return;
        }

        Node<T> current = head;

        while (current != null) {
            Node<T> nextNode = current.next;

            current.previous = null;
            current.next = null;

            current = nextNode;
        }

        head = null;
        tail = null;
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

    public Iterator<T> descendingIterator() {
        return new Iterator<>() {

            private Node<T> current = tail;
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
                current = current.previous;

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

    private T unlink(Node<T> node) {
        Node<T> previousNode = node.previous;
        Node<T> nextNode = node.next;

        if (previousNode == null) {
            head = nextNode;
        } else {
            previousNode.next = nextNode;
        }

        if (nextNode == null) {
            tail = previousNode;
        } else {
            nextNode.previous = previousNode;
        }

        node.previous = null;
        node.next = null;

        size--;
        modCount++;

        return node.value;
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

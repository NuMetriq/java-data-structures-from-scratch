package com.kentbiler.datastructures.tree;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> root;
    private int size;

    private static class Node<T> {
        private T value;
        private Node<T> left;
        private Node<T> right;

        private Node(T value) {
            this.value = value;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T value) {
        if (root == null) {
            root = new Node<>(value);
            size++;
            return;
        }

        Node<T> current = root;

        while (true) {
            int comparison = value.compareTo(current.value);

            if (comparison < 0) {
                if (current.left == null) {
                    current.left = new Node<>(value);
                    size++;
                    return;
                }

                current = current.left;
            } else if (comparison > 0) {
                if (current.right == null) {
                    current.right = new Node<>(value);
                    size++;
                    return;
                }

                current = current.right;
            } else {
                return;
            }
        }
    }

    public boolean contains(T value) {
        Node<T> current = root;

        while (current != null) {
            int comparison = value.compareTo(current.value);

            if (comparison < 0) {
                current = current.left;
            } else if (comparison > 0) {
                current = current.right;
            } else {
                return true;
            }
        }

        return false;
    }
}

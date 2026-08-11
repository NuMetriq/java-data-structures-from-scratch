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
}

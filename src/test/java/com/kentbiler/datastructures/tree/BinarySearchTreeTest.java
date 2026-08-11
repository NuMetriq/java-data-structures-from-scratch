package com.kentbiler.datastructures.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinarySearchTreeTest {

    @Test
    void newTreeIsEmpty() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }
}

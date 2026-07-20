package com.kentbiler.datastructures.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SinglyLinkedListTest {

    @Test
    void newListIsEmpty() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    void addFirstStoresElementAtBeginning() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addFirst("Menger");
        list.addFirst("Aristotle");

        assertEquals(2, list.size());
        assertEquals("Aristotle", list.getFirst());
    }
}

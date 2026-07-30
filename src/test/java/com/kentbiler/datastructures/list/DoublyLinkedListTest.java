package com.kentbiler.datastructures.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DoublyLinkedListTest {

    @Test
    void newListIsEmpty() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    void addFirstStoresElementAtBeginning() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addFirst("Menger");
        list.addFirst("Aristotle");

        assertEquals(2, list.size());
        assertEquals("Aristotle", list.getFirst());
    }

    @Test
    void addLastStoresElementAtEnd() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        assertEquals(2, list.size());
        assertEquals("Aristotle", list.getFirst());
        assertEquals("Menger", list.getLast());
    }

    @Test
    void removeFirstReturnsAndRemovesBeginningElement() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        String removed = list.removeFirst();

        assertEquals("Aristotle", removed);
        assertEquals("Menger", list.getFirst());
        assertEquals(1, list.size());
    }
}

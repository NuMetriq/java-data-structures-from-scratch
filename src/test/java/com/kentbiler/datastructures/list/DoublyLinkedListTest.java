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

    @Test
    void removeLastReturnsAndRemovesFinalElement() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        String removed = list.removeLast();

        assertEquals("Menger", removed);
        assertEquals("Aristotle", list.getLast());
        assertEquals(1, list.size());
    }

    @Test
    void getReturnsElementAtIndex() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");
        list.addLast("Veatch");

        assertEquals("Aristotle", list.get(0));
        assertEquals("Menger", list.get(1));
        assertEquals("Veatch", list.get(2));
    }

    @Test
    void setReplacesAndReturnsPreviousValue() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        String previousValue = list.set(1, "Veatch");

        assertEquals("Menger", previousValue);
        assertEquals("Veatch", list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    void addAtIndexInsertsAndReconnectsBothDirections() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Veatch");

        list.add(1, "Menger");

        assertEquals("Aristotle", list.get(0));
        assertEquals("Menger", list.get(1));
        assertEquals("Veatch", list.get(2));
        assertEquals(3, list.size());
    }

    @Test
    void removeAtIndexReturnsElementAndReconnectsList() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");
        list.addLast("Veatch");

        String removed = list.remove(1);

        assertEquals("Menger", removed);
        assertEquals("Aristotle", list.get(0));
        assertEquals("Veatch", list.get(1));
        assertEquals(2, list.size());
    }
}

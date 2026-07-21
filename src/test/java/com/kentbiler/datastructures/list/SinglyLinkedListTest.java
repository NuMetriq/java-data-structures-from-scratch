package com.kentbiler.datastructures.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    void removeFirstReturnsAndRemovesBeginningElement() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addFirst("Menger");
        list.addFirst("Aristotle");

        String removed = list.removeFirst();

        assertEquals("Aristotle", removed);
        assertEquals("Menger", list.getFirst());
        assertEquals(1, list.size());
    }

    @Test
    void addLastStoresElementAtEnd() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        assertEquals(2, list.size());
        assertEquals("Aristotle", list.getFirst());
        assertEquals("Menger", list.getLast());
    }

    @Test
    void removeLastReturnsAndRemovesFinalElement() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");
        list.addLast("Veatch");

        String removed = list.removeLast();

        assertEquals("Veatch", removed);
        assertEquals("Menger", list.getLast());
        assertEquals(2, list.size());
    }

    @Test
    void getReturnsElementAtIndex() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");
        list.addLast("Veatch");

        assertEquals("Aristotle", list.get(0));
        assertEquals("Menger", list.get(1));
        assertEquals("Veatch", list.get(2));
    }

    @Test
    void getRejectsInvalidIndexes() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");

        assertThrows(
            IndexOutOfBoundsException.class,
            () -> list.get(-1)
        );

        assertThrows(
            IndexOutOfBoundsException.class,
            () -> list.get(1)
        );
    }

    @Test
    void setReplacesAndReturnsPreviousValue() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        String previousValue = list.set(1, "Veatch");

        assertEquals("Menger", previousValue);
        assertEquals("Veatch", list.get(1));
        assertEquals(2, list.size());
    }
}

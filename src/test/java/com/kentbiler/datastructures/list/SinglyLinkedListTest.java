package com.kentbiler.datastructures.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    @Test
    void addAtIndexInsertsAndShiftsLaterElements() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

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
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");
        list.addLast("Veatch");

        String removed = list.remove(1);

        assertEquals("Menger", removed);
        assertEquals("Aristotle", list.get(0));
        assertEquals("Veatch", list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    void indexOfReturnsFirstMatchingIndex() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");
        list.addLast("Veatch");
        list.addLast("Menger");

        assertEquals(1, list.indexOf("Menger"));
        assertEquals(-1, list.indexOf("Rand"));
    }

    @Test
    void containsReturnsWhetherValueExists() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        assertTrue(list.contains("Menger"));
        assertFalse(list.contains("Veatch"));
    }

    @Test
    void removeByValueRemovesFirstMatchingElement() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");
        list.addLast("Veatch");
        list.addLast("Menger");

        boolean removed = list.remove("Menger");

        assertTrue(removed);
        assertEquals(3, list.size());
        assertEquals("Veatch", list.get(1));
        assertEquals(2, list.indexOf("Menger"));
    }

    @Test
    void clearRemovesAllElements() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        list.clear();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    void lastIndexOfReturnsFinalMatchingIndex() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");
        list.addLast("Veatch");
        list.addLast("Menger");

        assertEquals(3, list.lastIndexOf("Menger"));
        assertEquals(-1, list.lastIndexOf("Rand"));
    }

    @Test
    void toStringDisplaysElementsInOrder() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");
        list.addLast("Veatch");

        assertEquals("[Aristotle, Menger, Veatch]", list.toString());
    }

    @Test
    void iteratorVisitsElementsInOrder() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");
        list.addLast("Veatch");

        StringBuilder result = new StringBuilder();

        for (String value : list) {
            result.append(value).append(",");
        }

        assertEquals("Aristotle,Menger,Veatch,", result.toString());
    }

    @Test
    void iteratorThrowsWhenNoElementsRemain() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addLast("Aristotle");

        Iterator<String> iterator = list.iterator();

        assertEquals("Aristotle", iterator.next());

        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void iteratorDetectsStructuralModification() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        Iterator<String> iterator = list.iterator();

        list.addLast("Veatch");

        assertThrows(
            ConcurrentModificationException.class,
            iterator::next
        );
    }

    @Test
    void iteratorDetectsAddFirstAfterCreation() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Menger");

        Iterator<String> iterator = list.iterator();

        list.addFirst("Aristotle");

        assertThrows(
            ConcurrentModificationException.class,
            iterator::next
        );
    }

    @Test
    void iteratorDetectsRemoveFirstAfterCreation() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        Iterator<String> iterator = list.iterator();

        list.removeFirst();

        assertThrows(
            ConcurrentModificationException.class,
            iterator::next
        );
    }
}

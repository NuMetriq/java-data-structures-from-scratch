package com.kentbiler.datastructures.list;

import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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
        assertEquals("Menger", list.getLast());
    }

    @Test
    void getFirstThrowsWhenListIsEmpty() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        assertThrows(NoSuchElementException.class, list::getFirst);
    }

    @Test
    void removeFirstReturnsAndRemovesBeginningElement() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        String removed = list.removeFirst();

        assertEquals("Aristotle", removed);
        assertEquals("Menger", list.getFirst());
        assertEquals(1, list.size());
    }

    @Test
    void removeFirstThrowsWhenListIsEmpty() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        assertThrows(NoSuchElementException.class, list::removeFirst);
    }

    @Test
    void addLastStoresElementAtEnd() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        assertEquals("Aristotle", list.getFirst());
        assertEquals("Menger", list.getLast());
        assertEquals(2, list.size());
    }

    @Test
    void getLastThrowsWhenListIsEmpty() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        assertThrows(NoSuchElementException.class, list::getLast);
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
    void removeLastHandlesSingleElementList() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addLast("Aristotle");

        String removed = list.removeLast();

        assertEquals("Aristotle", removed);
        assertTrue(list.isEmpty());
    }

    @Test
    void removeLastThrowsWhenListIsEmpty() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        assertThrows(NoSuchElementException.class, list::removeLast);
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

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
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
    void addAtIndexInsertsAtBeginningMiddleAndEnd() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.add(0, "Menger");
        list.add(0, "Aristotle");
        list.add(2, "Veatch");
        list.add(2, "Rasmussen");

        assertArrayEquals(
            new Object[]{"Aristotle", "Menger", "Rasmussen", "Veatch"},
            list.toArray()
        );

        assertEquals(4, list.size());
    }

    @Test
    void indexedAddRejectsInvalidIndexes() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addLast("Aristotle");

        assertThrows(
            IndexOutOfBoundsException.class,
            () -> list.add(-1, "Menger")
        );

        assertThrows(
            IndexOutOfBoundsException.class,
            () -> list.add(2, "Menger")
        );
    }

    @Test
    void removeAtIndexReturnsElementAndReconnectsList() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");
        list.addLast("Veatch");

        String removed = list.remove(1);

        assertEquals("Menger", removed);
        assertArrayEquals(
            new Object[]{"Aristotle", "Veatch"},
            list.toArray()
        );
        assertEquals(2, list.size());
    }

    @Test
    void removeAtIndexHandlesFirstAndLastIndexes() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");
        list.addLast("Veatch");

        assertEquals("Aristotle", list.remove(0));
        assertEquals("Veatch", list.remove(1));

        assertEquals("[Menger]", list.toString());
    }

    @Test
    void removeRejectsInvalidIndexes() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addLast("Aristotle");

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
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
    void containsReturnsWhetherValueExists() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        assertTrue(list.contains("Menger"));
        assertFalse(list.contains("Veatch"));
    }

    @Test
    void searchOperationsSupportNullValues() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast(null);
        list.addLast("Menger");
        list.addLast(null);

        assertEquals(1, list.indexOf(null));
        assertEquals(3, list.lastIndexOf(null));
        assertTrue(list.contains(null));
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
        assertArrayEquals(
            new Object[]{"Aristotle", "Veatch", "Menger"},
            list.toArray()
        );
    }

    @Test
    void removeByValueReturnsFalseWhenValueIsAbsent() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        assertFalse(list.remove("Veatch"));
        assertEquals(2, list.size());
    }

    @Test
    void removeByValueSupportsNull() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast(null);
        list.addLast("Menger");

        assertTrue(list.remove(null));
        assertArrayEquals(
            new Object[]{"Aristotle", "Menger"},
            list.toArray()
        );
    }

    @Test
    void clearRemovesAllElements() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        list.clear();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertEquals("[]", list.toString());
    }

    @Test
    void toArrayReturnsElementsInOrder() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");
        list.addLast("Veatch");

        assertArrayEquals(
            new Object[]{"Aristotle", "Menger", "Veatch"},
            list.toArray()
        );
    }

    @Test
    void toArrayReturnsIndependentCopy() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        Object[] copy = list.toArray();
        copy[0] = "Veatch";

        assertEquals("Aristotle", list.get(0));
        assertEquals("Veatch", copy[0]);
    }

    @Test
    void toStringDisplaysElementsInOrder() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");
        list.addLast("Veatch");

        assertEquals(
            "[Aristotle, Menger, Veatch]",
            list.toString()
        );
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

        assertEquals(
            "Aristotle,Menger,Veatch,",
            result.toString()
        );
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
    void iteratorDetectsAddLastAfterCreation() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addLast("Aristotle");

        Iterator<String> iterator = list.iterator();
        list.addLast("Menger");

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
    void iteratorDetectsRemovalAfterCreation() {
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

    @Test
    void iteratorHasNextDetectsStructuralModification() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addLast("Aristotle");

        Iterator<String> iterator = list.iterator();
        list.clear();

        assertThrows(
            ConcurrentModificationException.class,
            iterator::hasNext
        );
    }

    @Test
    void setDoesNotInvalidateIterator() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        Iterator<String> iterator = list.iterator();
        list.set(0, "Veatch");

        assertEquals("Veatch", iterator.next());
        assertEquals("Menger", iterator.next());
    }
}

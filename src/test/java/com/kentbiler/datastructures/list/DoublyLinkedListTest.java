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

class DoublyLinkedListTest {

    @Test
    void newListIsEmpty() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    void addFirstStoresElementsAtBeginning() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addFirst("Menger");
        list.addFirst("Aristotle");

        assertEquals(2, list.size());
        assertEquals("Aristotle", list.getFirst());
        assertEquals("Menger", list.getLast());
    }

    @Test
    void addLastStoresElementsAtEnd() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        assertEquals(2, list.size());
        assertEquals("Aristotle", list.getFirst());
        assertEquals("Menger", list.getLast());
    }

    @Test
    void firstAndLastAccessRejectEmptyList() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        assertThrows(NoSuchElementException.class, list::getFirst);
        assertThrows(NoSuchElementException.class, list::getLast);
    }

    @Test
    void removeFirstReturnsAndRemovesBeginningElement() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        String removed = list.removeFirst();

        assertEquals("Aristotle", removed);
        assertEquals("Menger", list.getFirst());
        assertEquals("Menger", list.getLast());
        assertEquals(1, list.size());
    }

    @Test
    void removeLastReturnsAndRemovesFinalElement() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        String removed = list.removeLast();

        assertEquals("Menger", removed);
        assertEquals("Aristotle", list.getFirst());
        assertEquals("Aristotle", list.getLast());
        assertEquals(1, list.size());
    }

    @Test
    void removingOnlyElementMakesListEmpty() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.addLast("Aristotle");

        assertEquals("Aristotle", list.removeFirst());

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertThrows(NoSuchElementException.class, list::getFirst);
        assertThrows(NoSuchElementException.class, list::getLast);
    }

    @Test
    void endRemovalRejectsEmptyList() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        assertThrows(NoSuchElementException.class, list::removeFirst);
        assertThrows(NoSuchElementException.class, list::removeLast);
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
    void getRejectsInvalidIndexes() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.addLast("Aristotle");

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
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
    void indexedAddInsertsAtBeginningMiddleAndEnd() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

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
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
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
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

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
    void indexedRemoveHandlesFirstAndLastIndexes() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");
        list.addLast("Veatch");

        assertEquals("Aristotle", list.remove(0));
        assertEquals("Veatch", list.remove(1));

        assertEquals("[Menger]", list.toString());
        assertEquals("Menger", list.getFirst());
        assertEquals("Menger", list.getLast());
    }

    @Test
    void indexedRemoveRejectsInvalidIndexes() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.addLast("Aristotle");

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
    }

    @Test
    void indexOfReturnsFirstMatchingIndex() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");
        list.addLast("Veatch");
        list.addLast("Menger");

        assertEquals(1, list.indexOf("Menger"));
        assertEquals(-1, list.indexOf("Rand"));
    }

    @Test
    void lastIndexOfReturnsFinalMatchingIndex() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");
        list.addLast("Veatch");
        list.addLast("Menger");

        assertEquals(3, list.lastIndexOf("Menger"));
        assertEquals(-1, list.lastIndexOf("Rand"));
    }

    @Test
    void containsReturnsWhetherValueExists() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        assertTrue(list.contains("Menger"));
        assertFalse(list.contains("Veatch"));
    }

    @Test
    void searchOperationsSupportNullValues() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

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
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");
        list.addLast("Veatch");
        list.addLast("Menger");

        assertTrue(list.remove("Menger"));

        assertArrayEquals(
            new Object[]{"Aristotle", "Veatch", "Menger"},
            list.toArray()
        );
    }

    @Test
    void removeByValueReturnsFalseWhenValueIsAbsent() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        assertFalse(list.remove("Veatch"));
        assertEquals(2, list.size());
    }

    @Test
    void removeByValueSupportsNull() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

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
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        list.clear();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertEquals("[]", list.toString());
        assertThrows(NoSuchElementException.class, list::getFirst);
        assertThrows(NoSuchElementException.class, list::getLast);
    }

    @Test
    void toArrayReturnsElementsInOrder() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

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
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        Object[] copy = list.toArray();
        copy[0] = "Veatch";

        assertEquals("Aristotle", list.get(0));
        assertEquals("Veatch", copy[0]);
    }

    @Test
    void toStringDisplaysElementsInOrder() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");
        list.addLast("Veatch");

        assertEquals(
            "[Aristotle, Menger, Veatch]",
            list.toString()
        );
    }

    @Test
    void iteratorVisitsElementsFromHeadToTail() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

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
    void descendingIteratorVisitsElementsFromTailToHead() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");
        list.addLast("Veatch");

        Iterator<String> iterator = list.descendingIterator();
        StringBuilder result = new StringBuilder();

        while (iterator.hasNext()) {
            result.append(iterator.next()).append(",");
        }

        assertEquals(
            "Veatch,Menger,Aristotle,",
            result.toString()
        );
    }

    @Test
    void iteratorsThrowWhenNoElementsRemain() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.addLast("Aristotle");

        Iterator<String> forward = list.iterator();
        Iterator<String> backward = list.descendingIterator();

        assertEquals("Aristotle", forward.next());
        assertEquals("Aristotle", backward.next());

        assertThrows(NoSuchElementException.class, forward::next);
        assertThrows(NoSuchElementException.class, backward::next);
    }

    @Test
    void forwardIteratorDetectsStructuralModification() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.addLast("Aristotle");

        Iterator<String> iterator = list.iterator();
        list.addLast("Menger");

        assertThrows(
            ConcurrentModificationException.class,
            iterator::next
        );
    }

    @Test
    void descendingIteratorDetectsStructuralModification() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        Iterator<String> iterator = list.descendingIterator();
        list.removeFirst();

        assertThrows(
            ConcurrentModificationException.class,
            iterator::hasNext
        );
    }

    @Test
    void setDoesNotInvalidateIterator() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("Aristotle");
        list.addLast("Menger");

        Iterator<String> iterator = list.iterator();

        list.set(0, "Veatch");

        assertEquals("Veatch", iterator.next());
        assertEquals("Menger", iterator.next());
    }
}

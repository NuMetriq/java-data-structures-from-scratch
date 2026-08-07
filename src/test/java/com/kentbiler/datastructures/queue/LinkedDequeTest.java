package com.kentbiler.datastructures.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkedDequeTest {

    @Test
    void newDequeIsEmpty() {
        LinkedDeque<String> deque = new LinkedDeque<>();

        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    @Test
    void addFirstAndLastPlaceElementsAtCorrectEnds() {
        LinkedDeque<String> deque = new LinkedDeque<>();

        deque.addFirst("Menger");
        deque.addFirst("Aristotle");
        deque.addLast("Veatch");

        assertEquals(3, deque.size());
        assertEquals("Aristotle", deque.peekFirst());
        assertEquals("Veatch", deque.peekLast());
    }

    @Test
    void removeFirstAndRemoveLastReturnCorrectElements() {
        LinkedDeque<String> deque = new LinkedDeque<>();

        deque.addLast("Aristotle");
        deque.addLast("Menger");
        deque.addLast("Veatch");

        assertEquals("Aristotle", deque.removeFirst());
        assertEquals("Veatch", deque.removeLast());
        assertEquals("Menger", deque.peekFirst());
        assertEquals(1, deque.size());
    }

    @Test
    void peekAndRemoveRejectEmptyDeque() {
        LinkedDeque<String> deque = new LinkedDeque<>();

        assertThrows(NoSuchElementException.class, deque::peekFirst);
        assertThrows(NoSuchElementException.class, deque::peekLast);
        assertThrows(NoSuchElementException.class, deque::removeFirst);
        assertThrows(NoSuchElementException.class, deque::removeLast);
    }

    @Test
    void clearRemovesAllElements() {
        LinkedDeque<String> deque = new LinkedDeque<>();

        deque.addFirst("Aristotle");
        deque.addLast("Menger");
        deque.addLast("Veatch");

        deque.clear();

        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
        assertThrows(NoSuchElementException.class, deque::peekFirst);
    }

    @Test
    void iteratorVisitsElementsFromFirstToLast() {
        LinkedDeque<String> deque = new LinkedDeque<>();

        deque.addLast("Aristotle");
        deque.addLast("Menger");
        deque.addLast("Veatch");

        StringBuilder result = new StringBuilder();

        for (String value : deque) {
            result.append(value).append(",");
        }

        assertEquals("Aristotle,Menger,Veatch,", result.toString());
    }

    @Test
    void descendingIteratorVisitsElementsFromLastToFirst() {
        LinkedDeque<String> deque = new LinkedDeque<>();

        deque.addLast("Aristotle");
        deque.addLast("Menger");
        deque.addLast("Veatch");

        Iterator<String> iterator = deque.descendingIterator();

        assertEquals("Veatch", iterator.next());
        assertEquals("Menger", iterator.next());
        assertEquals("Aristotle", iterator.next());
    }
}

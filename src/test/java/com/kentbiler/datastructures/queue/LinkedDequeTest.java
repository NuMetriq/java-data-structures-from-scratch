package com.kentbiler.datastructures.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}

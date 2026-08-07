package com.kentbiler.datastructures.queue;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkedQueueTest {

    @Test
    void newQueueIsEmpty() {
        LinkedQueue<String> queue = new LinkedQueue<>();

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    void enqueueAddsElementToBack() {
        LinkedQueue<String> queue = new LinkedQueue<>();

        queue.enqueue("Aristotle");
        queue.enqueue("Menger");

        assertEquals(2, queue.size());
        assertEquals("Aristotle", queue.peek());
        assertFalse(queue.isEmpty());
    }

    @Test
    void dequeueRemovesAndReturnsFrontElement() {
        LinkedQueue<String> queue = new LinkedQueue<>();

        queue.enqueue("Aristotle");
        queue.enqueue("Menger");

        assertEquals("Aristotle", queue.dequeue());
        assertEquals("Menger", queue.peek());
        assertEquals(1, queue.size());
    }

    @Test
    void dequeueReturnsElementsInFirstInFirstOutOrder() {
        LinkedQueue<String> queue = new LinkedQueue<>();

        queue.enqueue("Aristotle");
        queue.enqueue("Menger");
        queue.enqueue("Veatch");

        assertEquals("Aristotle", queue.dequeue());
        assertEquals("Menger", queue.dequeue());
        assertEquals("Veatch", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    void peekAndDequeueRejectEmptyQueue() {
        LinkedQueue<String> queue = new LinkedQueue<>();

        assertThrows(NoSuchElementException.class, queue::peek);
        assertThrows(NoSuchElementException.class, queue::dequeue);
    }

    @Test
    void clearRemovesAllElements() {
        LinkedQueue<String> queue = new LinkedQueue<>();

        queue.enqueue("Aristotle");
        queue.enqueue("Menger");

        queue.clear();

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertThrows(NoSuchElementException.class, queue::peek);
    }

    @Test
    void iteratorVisitsElementsFromFrontToBack() {
        LinkedQueue<String> queue = new LinkedQueue<>();

        queue.enqueue("Aristotle");
        queue.enqueue("Menger");
        queue.enqueue("Veatch");

        StringBuilder result = new StringBuilder();

        for (String value : queue) {
            result.append(value).append(",");
        }

        assertEquals("Aristotle,Menger,Veatch,", result.toString());
    }

    @Test
    void iteratorDetectsStructuralModification() {
        LinkedQueue<String> queue = new LinkedQueue<>();

        queue.enqueue("Aristotle");
        queue.enqueue("Menger");

        Iterator<String> iterator = queue.iterator();

        queue.enqueue("Veatch");

        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    void iteratorThrowsWhenExhausted() {
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("Aristotle");

        Iterator<String> iterator = queue.iterator();

        assertEquals("Aristotle", iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}

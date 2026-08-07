package com.kentbiler.datastructures.queue;

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
}

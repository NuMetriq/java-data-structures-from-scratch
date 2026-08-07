package com.kentbiler.datastructures.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
}

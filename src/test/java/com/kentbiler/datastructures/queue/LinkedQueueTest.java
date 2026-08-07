package com.kentbiler.datastructures.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkedQueueTest {

    @Test
    void newQueueIsEmpty() {
        LinkedQueue<String> queue = new LinkedQueue<>();

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }
}

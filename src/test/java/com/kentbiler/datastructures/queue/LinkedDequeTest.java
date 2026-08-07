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
}

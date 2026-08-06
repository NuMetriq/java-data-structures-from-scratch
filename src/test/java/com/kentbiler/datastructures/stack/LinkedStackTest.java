package com.kentbiler.datastructures.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkedStackTest {

    @Test
    void newStackIsEmpty() {
        LinkedStack<String> stack = new LinkedStack<>();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void pushAddsElementsToTop() {
        LinkedStack<String> stack = new LinkedStack<>();

        stack.push("Aristotle");
        stack.push("Menger");

        assertEquals(2, stack.size());
        assertEquals("Menger", stack.peek());
        assertFalse(stack.isEmpty());
    }
}

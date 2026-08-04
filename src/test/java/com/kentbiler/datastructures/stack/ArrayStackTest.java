package com.kentbiler.datastructures.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayStackTest {

    @Test
    void newStackIsEmpty() {
        ArrayStack<String> stack = new ArrayStack<>();

        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
    }

    @Test
    void pushAddsElementToTop() {
        ArrayStack<String> stack = new ArrayStack<>();

        stack.push("Aristotle");
        stack.push("Menger");

        assertEquals(2, stack.size());
        assertEquals("Menger", stack.peek());
    }

    @Test
    void popRemovesAndReturnsTopElement() {
        ArrayStack<String> stack = new ArrayStack<>();

        stack.push("Aristotle");
        stack.push("Menger");

        String removed = stack.pop();

        assertEquals("Menger", removed);
        assertEquals("Aristotle", stack.peek());
        assertEquals(1, stack.size());
    }
}

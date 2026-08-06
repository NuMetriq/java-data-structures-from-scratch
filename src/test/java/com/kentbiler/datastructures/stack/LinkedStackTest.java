package com.kentbiler.datastructures.stack;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    void popRemovesAndReturnsTopElement() {
        LinkedStack<String> stack = new LinkedStack<>();

        stack.push("Aristotle");
        stack.push("Menger");

        assertEquals("Menger", stack.pop());
        assertEquals("Aristotle", stack.peek());
        assertEquals(1, stack.size());
    }

    @Test
    void peekAndPopRejectEmptyStack() {
        LinkedStack<String> stack = new LinkedStack<>();

        assertThrows(NoSuchElementException.class, stack::peek);
        assertThrows(NoSuchElementException.class, stack::pop);
    }

    @Test
    void clearRemovesAllElements() {
        LinkedStack<String> stack = new LinkedStack<>();

        stack.push("Aristotle");
        stack.push("Menger");

        stack.clear();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        assertThrows(NoSuchElementException.class, stack::peek);
    }

    @Test
    void popReturnsElementsInLastInFirstOutOrder() {
        LinkedStack<String> stack = new LinkedStack<>();

        stack.push("Aristotle");
        stack.push("Menger");
        stack.push("Veatch");

        assertEquals("Veatch", stack.pop());
        assertEquals("Menger", stack.pop());
        assertEquals("Aristotle", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void iteratorVisitsElementsFromTopToBottom() {
        LinkedStack<String> stack = new LinkedStack<>();

        stack.push("Aristotle");
        stack.push("Menger");
        stack.push("Veatch");

        StringBuilder result = new StringBuilder();

        for (String value : stack) {
            result.append(value).append(",");
        }

        assertEquals("Veatch,Menger,Aristotle,", result.toString());
    }

    @Test
    void iteratorDetectsStructuralModification() {
        LinkedStack<String> stack = new LinkedStack<>();

        stack.push("Aristotle");
        stack.push("Menger");

        Iterator<String> iterator = stack.iterator();

        stack.push("Veatch");

        assertThrows(ConcurrentModificationException.class, iterator::next);
    }
}

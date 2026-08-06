package com.kentbiler.datastructures.stack;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    void peekAndPopRejectEmptyStack() {
        ArrayStack<String> stack = new ArrayStack<>();

        assertThrows(NoSuchElementException.class, stack::peek);
        assertThrows(NoSuchElementException.class, stack::pop);
    }

    @Test
    void clearRemovesAllElements() {
        ArrayStack<String> stack = new ArrayStack<>();

        stack.push("Aristotle");
        stack.push("Menger");

        stack.clear();

        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        assertThrows(NoSuchElementException.class, stack::peek);
    }

    @Test
    void toStringDisplaysElementsFromBottomToTop() {
        ArrayStack<String> stack = new ArrayStack<>();

        stack.push("Aristotle");
        stack.push("Menger");
        stack.push("Veatch");

        assertEquals("[Aristotle, Menger, Veatch]", stack.toString());
    }

    @Test
    void popReturnsElementsInLastInFirstOutOrder() {
        ArrayStack<String> stack = new ArrayStack<>();

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
        ArrayStack<String> stack = new ArrayStack<>();

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
    void iteratorDetectsStructuralModifications() {
        ArrayStack<String> stack = new ArrayStack<>();

        stack.push("Aristotle");
        stack.push("Menger");

        Iterator<String> iterator = stack.iterator();

        stack.push("Veatch");

        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    void iteratorThrowsWhenExhausted() {
        ArrayStack<String> stack = new ArrayStack<>();
        stack.push("Aristotle");

        Iterator<String> iterator = stack.iterator();

        assertEquals("Aristotle", iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}

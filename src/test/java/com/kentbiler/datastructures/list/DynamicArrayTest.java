package com.kentbiler.datastructures.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DynamicArrayTest {

    @Test
    void newDynamicArrayHasSizeZero() {
        DynamicArray<String> array = new DynamicArray<>();

        assertEquals(0, array.size());
    }

    @Test
    void addStoresElementAndIncreasesSize() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Aristotle");

        assertEquals(1, array.size());
        assertEquals("Aristotle", array.get(0));
    }

    @Test
    void addGrowsArrayWhenCapacityIsReached() {
        DynamicArray<Integer> array = new DynamicArray<>();

        for (int i = 0; i < 11; i++) {
            array.add(i);
        }

        assertEquals(11, array.size());
        assertEquals(20, array.capacity());
        assertEquals(10, array.get(10));
    }

    @Test
    void setReplacesAndReturnsPreviousElement() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("Menger");

        String previousValue = array.set(0, "Veatch");

        assertEquals("Menger", previousValue);
        assertEquals("Veatch", array.get(0));
        assertEquals(1, array.size());
    }

    @Test
    void removeReturnsElementAndShiftsLaterElementsLeft() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Aristotle");
        array.add("Menger");
        array.add("Veatch");

        String removedValue = array.remove(1);

        assertEquals("Menger", removedValue);
        assertEquals("Veatch", array.get(1));
        assertEquals(2, array.size());
    }

    @Test
    void getThrowsExceptionForInvalidIndex() {
        DynamicArray<String> array = new DynamicArray<>();

        assertThrows(IndexOutOfBoundsException.class, () -> array.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(-1));
    }
}

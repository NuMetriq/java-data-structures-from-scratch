package com.kentbiler.datastructures.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}

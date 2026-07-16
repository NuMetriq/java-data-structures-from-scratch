package com.kentbiler.datastructures.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DynamicArrayTest {

    @Test
    void newDynamicArrayHasSizeZero() {
        DynamicArray<String> array = new DynamicArray<>();

        assertEquals(0, array.size());
    }
}

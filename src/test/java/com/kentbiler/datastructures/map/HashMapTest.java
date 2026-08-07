package com.kentbiler.datastructures.map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HashMapTest {

    @Test
    void newMapIsEmpty() {
        HashMap<String, Integer> map = new HashMap<>();

        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }
}

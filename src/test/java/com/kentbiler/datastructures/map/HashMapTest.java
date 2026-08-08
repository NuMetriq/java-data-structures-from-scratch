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

    @Test
    void putStoresValueByKey() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Aristotle", 1);

        assertEquals(1, map.size());
        assertEquals(1, map.get("Aristotle"));
    }
}

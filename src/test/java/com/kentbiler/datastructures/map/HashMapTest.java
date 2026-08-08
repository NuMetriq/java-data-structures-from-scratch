package com.kentbiler.datastructures.map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HashMapTest {

    private static class CollisionKey {
        private final String value;

        private CollisionKey(String value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return 42;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (!(obj instanceof CollisionKey other)) {
                return false;
            }

            return value.equals(other.value);
        }
    }

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

    @Test
    void putUpdatesExistingKeyWithoutIncreasingSize() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Aristotle", 1);
        map.put("Aristotle", 2);

        assertEquals(1, map.size());
        assertEquals(2, map.get("Aristotle"));
    }

    @Test
    void handlesHashCollisions() {
        HashMap<CollisionKey, Integer> map = new HashMap<>();

        CollisionKey first = new CollisionKey("Aristotle");
        CollisionKey second = new CollisionKey("Menger");

        map.put(first, 1);
        map.put(second, 2);

        assertEquals(2, map.size());
        assertEquals(1, map.get(first));
        assertEquals(2, map.get(second));
    }
}

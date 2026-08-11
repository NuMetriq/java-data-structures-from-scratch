package com.kentbiler.datastructures.map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    void containsKeyReportsWhetherKeyExists() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Aristotle", 1);

        assertTrue(map.containsKey("Aristotle"));
        assertFalse(map.containsKey("Menger"));
    }

    @Test
    void removeDeletesEntryAndReturnsValue() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Aristotle", 1);
        map.put("Menger", 2);

        assertEquals(1, map.remove("Aristotle"));
        assertEquals(1, map.size());
        assertFalse(map.containsKey("Aristotle"));
        assertEquals(2, map.get("Menger"));
    }

    @Test
    void removeHandlesHashCollisions() {
        HashMap<CollisionKey, Integer> map = new HashMap<>();

        CollisionKey first = new CollisionKey("Aristotle");
        CollisionKey second = new CollisionKey("Menger");
        CollisionKey third = new CollisionKey("Veatch");

        map.put(first, 1);
        map.put(second, 2);
        map.put(third, 3);

        assertEquals(2, map.remove(second));

        assertEquals(2, map.size());
        assertEquals(1, map.get(first));
        assertEquals(3, map.get(third));
        assertFalse(map.containsKey(second));
    }

    @Test
    void clearRemovesAllEntries() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Aristotle", 1);
        map.put("Menger", 2);
        map.put("Veatch", 3);

        map.clear();

        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
        assertFalse(map.containsKey("Aristotle"));
        assertFalse(map.containsKey("Menger"));
        assertFalse(map.containsKey("Veatch"));
    }

    @Test
    void growsWhenMapBecomesTooFull() {
        HashMap<Integer, String> map = new HashMap<>();

        int initialCapacity = map.capacity();

        for (int i = 0; i < 13; i++) {
            map.put(i, "Value " + i);
        }

        assertTrue(map.capacity() > initialCapacity);
    }

    @Test
    void resizingPreservesAllEntries() {
        HashMap<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 100; i++) {
            map.put(i, "Value " + i);
        }

        assertEquals(100, map.size());

        for (int i = 0; i < 100; i++) {
            assertEquals("Value " + i, map.get(i));
        }
    }

    @Test
    void rejectsNullKey() {
        HashMap<String, Integer> map = new HashMap<>();

        assertThrows(NullPointerException.class, () -> map.put(null, 1));
        assertThrows(NullPointerException.class, () -> map.get(null));
        assertThrows(NullPointerException.class, () -> map.containsKey(null));
        assertThrows(NullPointerException.class, () -> map.remove(null));
    }

    @Test
    void supportsNullValues() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Aristotle", null);

        assertEquals(1, map.size());
        assertTrue(map.containsKey("Aristotle"));
        assertEquals(null, map.get("Aristotle"));
    }

    @Test
    void removeMissingKeyReturnsNullAndDoesNotChangeSize() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Aristotle", 1);
        map.put("Menger", 2);

        assertEquals(null, map.remove("Veatch"));
        assertEquals(2, map.size());
        assertTrue(map.containsKey("Aristotle"));
        assertTrue(map.containsKey("Menger"));
    }

    @Test
    void updatesExistingKeyAfterResize() {
        HashMap<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 100; i++) {
            map.put(i, "Value " + i);
        }

        map.put(42, "Updated");

        assertEquals(100, map.size());
        assertEquals("Updated", map.get(42));
    }

    @Test
    void collisionsRemainCorrectAfterResize() {
        HashMap<CollisionKey, Integer> map = new HashMap<>();

        CollisionKey[] keys = new CollisionKey[100];

        for (int i = 0; i < keys.length; i++) {
            keys[i] = new CollisionKey("Key " + i);
            map.put(keys[i], i);
        }

        assertEquals(100, map.size());

        for (int i = 0; i < keys.length; i++) {
            assertEquals(i, map.get(keys[i]));
        }
    }

    @Test
    void clearWorksAfterResize() {
        HashMap<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 100; i++) {
            map.put(i, "Value " + i);
        }

        map.clear();

        assertTrue(map.isEmpty());
        assertEquals(0, map.size());

        for (int i = 0; i < 100; i++) {
            assertFalse(map.containsKey(i));
        }
    }

    @Test
    void mapCanBeReusedAfterClear() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Aristotle", 1);
        map.put("Menger", 2);

        map.clear();

        map.put("Veatch", 3);

        assertEquals(1, map.size());
        assertEquals(3, map.get("Veatch"));
    }

    @Test
    void toStringContainsStoredEntries() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Aristotle", 1);
        map.put("Menger", 2);

        String result = map.toString();

        assertTrue(result.startsWith("{"));
        assertTrue(result.endsWith("}"));
        assertTrue(result.contains("Aristotle=1"));
        assertTrue(result.contains("Menger=2"));
    }
}

package com.kentbiler.datastructures.map;

public class HashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;

    private final Entry<K, V>[] buckets;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private static class Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> next;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @SuppressWarnings("unchecked")
    public HashMap() {
        buckets = (Entry<K, V>[]) new Entry[DEFAULT_CAPACITY];
    }

    private int bucketIndex(K key) {
        return Math.floorMod(key.hashCode(), buckets.length);
    }

    public void put(K key, V value) {
        int index = bucketIndex(key);
        Entry<K, V> current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }

            current = current.next;
        }

        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = buckets[index];
        buckets[index] = newEntry;

        size++;
    }

    public V get(K key) {
        int index = bucketIndex(key);
        Entry<K, V> current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }

            current = current.next;
        }

        return null;
    }

    public boolean containsKey(K key) {
        int index = bucketIndex(key);
        Entry<K, V> current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public V remove(K key) {
        int index = bucketIndex(key);

        Entry<K, V> current = buckets[index];
        Entry<K, V> previous = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (previous == null) {
                    buckets[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                size--;
                return current.value;
            }

            previous = current;
            current = current.next;
        }

        return null;
    }
}

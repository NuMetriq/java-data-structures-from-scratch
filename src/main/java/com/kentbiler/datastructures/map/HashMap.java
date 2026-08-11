package com.kentbiler.datastructures.map;

import java.util.Objects;

public class HashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private Entry<K, V>[] buckets;
    private int size;

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

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    int capacity() {
        return buckets.length;
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

        if (size + 1 > buckets.length * LOAD_FACTOR) {
            resize();
            index = bucketIndex(key);
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

    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = null;
        }

        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        boolean first = true;

        for (Entry<K, V> bucket : buckets) {
            Entry<K, V> current = bucket;

            while (current != null) {
                if (!first) {
                    result.append(", ");
                }

                result.append(current.key)
                      .append("=")
                      .append(current.value);

                first = false;
                current = current.next;
            }
        }

        result.append("}");
        return result.toString();
    }

    private int bucketIndex(K key) {
        Objects.requireNonNull(key, "Key cannot be null");
        return Math.floorMod(key.hashCode(), buckets.length);
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Entry<K, V>[] oldBuckets = buckets;
        buckets = (Entry<K, V>[]) new Entry[oldBuckets.length * 2];

        for (Entry<K, V> bucket : oldBuckets) {
            Entry<K, V> current = bucket;

            while (current != null) {
                Entry<K, V> next = current.next;

                int newIndex = bucketIndex(current.key);
                current.next = buckets[newIndex];
                buckets[newIndex] = current;

                current = next;
            }
        }
    }
}

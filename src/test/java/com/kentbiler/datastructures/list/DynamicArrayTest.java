package com.kentbiler.datastructures.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

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

    @Test
    void addAtIndexShiftsLaterElementsRight() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Aristotle");
        array.add("Veatch");

        array.add(1, "Menger");

        assertEquals("Aristotle", array.get(0));
        assertEquals("Menger", array.get(1));
        assertEquals("Veatch", array.get(2));
        assertEquals(3, array.size());
    }

    @Test
    void indexOfReturnsFirstMatchingIndex() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Aristotle");
        array.add("Menger");
        array.add("Veatch");
        array.add("Menger");

        assertEquals(1, array.indexOf("Menger"));
        assertEquals(-1, array.indexOf("Rand"));
    }

    @Test
    void containsReturnsWhetherValueExists() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Aristotle");
        array.add("Menger");

        assertEquals(true, array.contains("Menger"));
        assertEquals(false, array.contains("Veatch"));
    }

    @Test
    void clearRemovesAllElements() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Aristotle");
        array.add("Menger");

        array.clear();

        assertEquals(0, array.size());
        assertEquals(true, array.isEmpty());
    }

    @Test
    void constructorUsesSpecifiedInitialCapacity() {
        DynamicArray<String> array = new DynamicArray<>(25);

        assertEquals(0, array.size());
        assertEquals(25, array.capacity());
    }

    @Test
    void zeroCapacityArrayCanStillGrow() {
        DynamicArray<String> array = new DynamicArray<>(0);

        array.add("Aristotle");

        assertEquals(1, array.size());
        assertEquals("Aristotle", array.get(0));
    }

    @Test
    void constructorRejectsNegativeCapacity() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new DynamicArray<String>(-1)
        );
    }

    @Test
    void removeByValueRemovesFirstMatchingElement() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Aristotle");
        array.add("Menger");
        array.add("Veatch");
        array.add("Menger");

        boolean removed = array.remove("Menger");

        assertEquals(true, removed);
        assertEquals(3, array.size());
        assertEquals("Veatch", array.get(1));
        assertEquals(2, array.indexOf("Menger"));
    }

    @Test
    void removeByValueReturnsFalsWhenValueIsAbsent() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Aristotle");
        array.add("Menger");

        boolean removed = array.remove("Veatch");

        assertEquals(false, removed);
        assertEquals(2, array.size());
    }

    @Test
    void indexOfAndRemoveSupportNullValues() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Aristotle");
        array.add(null);
        array.add("Menger");

        assertEquals(1, array.indexOf(null));
        assertEquals(true, array.remove(null));
        assertEquals(2, array.size());
        assertEquals("Menger", array.get(1));
    }

    @Test
    void lastIndexOfReturnsFinalMatchingIndex() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Aristotle");
        array.add("Menger");
        array.add("Veatch");
        array.add("Menger");

        assertEquals(3, array.lastIndexOf("Menger"));
        assertEquals(-1, array.lastIndexOf("Rand"));
    }

    @Test
    void toStringDisplaysLogicalElements() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Aristotle");
        array.add("Menger");
        array.add("Veatch");

        assertEquals("[Aristotle, Menger, Veatch]", array.toString());
    }

    @Test
    void toArrayReturnsOnlyLogicalElements() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Aristotle");
        array.add("Menger");
        array.add("Veatch");

        assertArrayEquals(
            new Object[]{"Aristotle", "Menger", "Veatch"},
            array.toArray()
        );
    }

    @Test
    void ensureCapacityIncreasesCapacityWithoutChangingSize() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Aristotle");
        array.ensureCapacity(25);

        assertEquals(1, array.size());
        assertEquals(25, array.capacity());
        assertEquals("Aristotle", array.get(0));
    }

    @Test
    void trimToSizeReducesCapacityToCurrentSize() {
       DynamicArray<String> array = new DynamicArray<>(25);

       array.add("Aristotle");
       array.add("Menger");

       array.trimToSize();

       assertEquals(2, array.size());
       assertEquals(2, array.capacity());
       assertEquals("Aristotle", array.get(0));
       assertEquals("Menger", array.get(1));
    }

    @Test
    void iteratorVisitsElementsInOrder() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Aristotle");
        array.add("Menger");
        array.add("Veatch");

        StringBuilder result = new StringBuilder();

        for (String value : array) {
            result.append(value).append(",");
        }

        assertEquals("Aristotle,Menger,Veatch,", result.toString());
    }

    @Test
    void iteratorThrowsWhenNoElementsRemain() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("Aristotle");

        Iterator<String> iterator = array.iterator();

        assertEquals("Aristotle", iterator.next());

        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void iteratorDetectsStructuralModification() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Aristotle");
        array.add("Menger");

        Iterator<String> iterator = array.iterator();

        array.add("Veatch");

        assertThrows(
            ConcurrentModificationException.class,
            iterator::next
        );
    }

    @Test
    void iteratorDetectsRemovalAfterCreation() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Aristotle");
        array.add("Menger");

        Iterator<String> iterator = array.iterator();

        array.remove(0);

        assertThrows(
            ConcurrentModificationException.class,
            iterator::next
        );
    }

    @Test
    void iteratorDetectsClearAfterCreation() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Aristotle");
        array.add("Menger");

        Iterator<String> iterator = array.iterator();

        array.clear();

        assertThrows(
            ConcurrentModificationException.class,
            iterator::next
        );
    }

    @Test
    void setDoesNotInvalidateIterator() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Aristotle");
        array.add("Menger");

        Iterator<String> iterator = array.iterator();

        array.set(0, "Veatch");

        assertEquals("Veatch", iterator.next());
    }

    @Test
    void ensureCapacityDoesNotReduceExistingCapacity() {
        DynamicArray<String> array = new DynamicArray<>(25);

        array.ensureCapacity(10);

        assertEquals(25, array.capacity());
    }

    @Test
    void indexedAddRejectsInvalidIndexes() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Aristotle");

        assertThrows(
            IndexOutOfBoundsException.class,
            () -> array.add(2, "Menger")
        );
    }

    @Test
    void ensureCapacityRejectsNegativeCapacity() {
        DynamicArray<String> array = new DynamicArray<>();

        assertThrows(
            IllegalArgumentException.class,
            () -> array.ensureCapacity(-1)
        );
    }

    @Test
    void iteratorHasNextDetectsStructuralModification() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Aristotle");

        Iterator<String> iterator = array.iterator();

        array.add("Menger");

        assertThrows(
            ConcurrentModificationException.class,
            iterator::hasNext
        );
    }

    @Test
    void clearRetainsExistingCapacity() {
        DynamicArray<String> array = new DynamicArray<>(25);

        array.add("Aristotle");
        array.add("Menger");

        array.clear();

        assertEquals(0, array.size());
        assertEquals(25, array.capacity());
    }

    @Test
    void removeRejectsInvalidIndexes() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Aristotle");

        assertThrows(
            IndexOutOfBoundsException.class,
            () -> array.remove(-1)
        );

        assertThrows(
            IndexOutOfBoundsException.class,
            () -> array.remove(1)
        );
    }
}

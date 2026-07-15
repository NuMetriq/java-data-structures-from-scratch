package com.kentbiler.datastructures.list;

public class DynamicArrayDemo {

    public static void main(String[] args) {
        DynamicArray<String> names = new DynamicArray<>();

        names.add("Aristotle");
        names.add("Menger");
        names.add("Veatch");

        String removedValue = names.remove(1);

        System.out.println("Removed value value: " + removedValue);
        System.out.println("New value at index 1: " + names.get(1));
        System.out.println("Size: " + names.size());
    }
}

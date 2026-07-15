package com.kentbiler.datastructures.list;

public class DynamicArrayDemo {

    public static void main(String[] args) {
        DynamicArray<String> names = new DynamicArray<>();

        names.add("Aristotle");
        names.add("Menger");

        String previousValue = names.set(1, "Veatch");

        System.out.println("Previous value: " + previousValue);
        System.out.println("Current value: " + names.get(1));
        System.out.println("Size: " + names.size());
        System.out.println("Capacity: " + names.capacity());
    }
}

package com.kentbiler.datastructures.list;

public class DynamicArrayDemo {

    public static void main(String[] args) {
        DynamicArray<String> names = new DynamicArray<>();

        names.add("Aristotle");
        names.add("Menger");

        System.out.println("Size: " + names.size());
        System.out.println("Capacity: " + names.capacity());
        System.out.println("First element: " + names.get(0));
        System.out.println("Second element: " + names.get(1));
    }
}

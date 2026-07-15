package com.kentbiler.datastructures.list;

public class DynamicArrayDemo {

    public static void main(String[] args) {
        DynamicArray<Integer> numbers = new DynamicArray<>();

        for (int i = 0; i < 11; i++) {
            numbers.add(i);
            System.out.println(
                "Added " + i
                    + " | Size: " + numbers.size()
                    + " | Capacity: " + numbers.capacity()
            );
        }
    }
}

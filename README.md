# Java Data Structures from Scratch

Educational implementations of fundamental data structures in Java, built from scratch to explore algorithms, complexity, and software design.

## Implemented Data Structures

### DynamicArray<T>

A generic resizable array implemented without using 'ArrayList'.

Current features:

- Default and custom initial capacities
- Automatic capacity growth
- Indexed access and replacement
- Appending and indexed insertion
- Removal by index and value
- Searching with `indexOf`, `lastIndexOf`, and `contains`
- `clear`, `toArray`, and `toString`
- Manual capacity management with `ensureCapacity` and `trimToSize`
- Enhanced `for` loop support through `Iterable<T>`
- Fail-fast iterator behavior
- JUnit test coverage for normal behavior and edge cases

### SinglyLinkedList<T>

A generic singly linked list built from nodes containing a value and a reference to the next node.

Current features:

- Front and end insertion
- Front and end removal
- Indexed access, insertion, replacement, and removal
- Searching with `indexOf`, `lastIndexOf`, and `contains`
- Removal by value
- Support for `null` values
- `clear`, `toArray`, and `toString`
- Enhanced `for` loop support through `Iterable<T>`
- Fail-fast iterator behavior
- JUnit coverage for normal operations, boundary cases, and exceptions

### DoublyLinkedList<T>

A generic doubly linked list whose nodes maintain references to both the previous and next nodes.

Current features:

- Constant-time insertion and removal at both ends
- Direct access to the first and last elements
- Indexed access, insertion, replacement, and removal
- Bidirectional traversal from either the head or tail
- Searching with `indexOf`, `lastIndexOf`, and `contains`
- Removal by value
- Support for `null` values
- `clear`, `toArray`, and `toString`
- Forward iteration through `Iterable<T>`
- Reverse iteration through `descendingIterator()`
- Fail-fast iterator behavior
- JUnit coverage for normal operations, boundary cases, and exceptions

### ArrayStack

An array-backed implementation of a generic stack using `DynamicArray` internally.

Current features:

- Generic element type
- Last-in, first-out ordering
- `push`, `pop`, and `peek`
- `size`, `isEmpty`, and `clear`
- Bottom-to-top string representation
- Top-to-bottom iteration
- Fail-fast iterator
- Empty-operation validation

### LinkedStack

A linked-list-backed implementation of a generic stack using `SinglyLinkedList` internally.

Current features:

- Generic element type
- Last-in, first-out ordering
- `push`, `pop`, and `peek`
- `size`, `isEmpty`, and `clear`
- Top-to-bottom iteration
- Fail-fast iteration
- Constant-time `push`, `pop`, and `peek`
- Empty-operation validation

### LinkedQueue

A linked-list-backed implementation of a generic queue using `DoublyLinkedList` internally.

Current features:

- Generic element type
- First-in, first-out ordering
- `enqueue`, `dequeue`, and `peek`
- `size`, `isEmpty`, and `clear`
- Front-to-back iteration
- Fail-fast iterator
- Constant-time `enqueue`, `dequeue`, and `peek`
- Empty-operation validation
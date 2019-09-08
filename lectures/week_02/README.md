# Week 2 - Stacks and Queues / Elementary Sorts

## Reading Notes

1. [1.3 - Stacks and Queues](./1.3_stacks_and_queues)
2. [2.1 - Elementary Sorts](./2.1_elementary_sorts)

## Stacks and Queues

### Definitions

- `Stack` - Examine the item most recently added, also known as **LIFO**, *last in first out*.
    + Push, add to end.
    + Pop, take from beginning.

- `Queue` - Examine the item least recently added, also known as **FIFO**, *first in first out*.
    + Queue, add to beginning.
    + Dequeue, take from end.

### Resizing Arrays

- Linked lists, every operation takes constant time in the worst case, but dealing with the links requires extra time and space, so it's slower.
- Resizing array, has good amortized time, so average time over the entire process is good. There is less wasted space and each operation is probably faster.

### Queues

> Suppose that you implement a queue using a null-terminated singly-linked list, maintaining a reference to the item least recently added (the front of the list) but not maintaining a reference to the item most recently added (the end of the list). What are the worst-case running times for enqueue and dequeue?

> **Linear time for enqueue and constant time for dequeue.**
>
> Removing a node from the front of a linked list takes constant time. However, it takes linear time to find the last node of a linked list (unless we maintain an explicit reference to it, which is not done here).

### Generics

> Which of the following statements is a type safe way to declare and initialize a Stack\verb#Stack#Stack of integers?

> **`Stack<Integer>stack=newStack<Integer>();`**.
>
> In Java 6, you must specify the concrete type both in the variable declaration (left-hand side) and the constructor call (right-hand side). Starting in Java 7, you can use the diamond operator instead:
>
> `Stack<Integer> stack = new Stack<>();`

### Iterators

> Suppose that we copy the iterator code from our linked list and resizing array implementations of a stack to the corresponding implementations of a queue.
>
> Which queue iterator(s) will correctly return the items in FIFO order?

> **Linked-list iterator only**.
>
> The linked-list iterator will work without modification because the items in the linked list are ordered in FIFO order (which is the main reason we dequeue from the front and enqueue to the back instead of vice versa).
>
> The array iterator will fail for two reasons. First, the the items should be iterated over in the opposite order. Second, the items won't typically be stored in the array as entries `0` to `n−1n-1n−1`.

### Dijkstra's Two-Stack Algorithm

- `Value`: push onto the value stack.
- `Operator`: push onto the operator stack.
- `Left parenthesis`: ignore.
- `Right parenthesis`: pop operator and two values; push the result of applying that operator to those values onto the operand stack.

```java
( 1 + ( ( 2 + 3 ) * (4 * 5 ) ) )
```

```java
["(", "1", "+", "(", "(", "2", "+", "3", ")", "*", "(", "4", "*", "5", ")", ")", ")"]
```

- Index 0:
    + Item is a `(`, ignore.
- Index 1:
    + Item is a `1`, which is a `value`.
    + Push to `value` stack (`[1]`).
- Index 2:
    + Item is a `+`, which is a `operator`.
    + Push to `operator` stack (`["+"]`).
- Index 3:
    + Item is a `(`, ignore.
- Index 4:
    + Item is a `(`, ignore.
- Index 5:
    + Item is a `2`, which is a `value`.
    + Push to `value` stack (`[1, 2]`).
- Index 6:
    + Item is a `+`, which is a `operator`.
    + Push to `operator` stack (`["+", "+"]`).
- Index 7:
    + Item is a `3`, which is a `value`.
    + Push to `value` stack (`[1, 2, 3]`).
- Index 8:
    + Item is a `)`, which means pop the operator and two values.
    + Pop `operator` stack, returns `+`, (`["+"]`).
    + Pop `value` stack, returns `3`, (`[1, 2]`).
    + Pop `value` stack, returns `2`, (`[1]`).
    + Evaluate `3 + 2` to `5`.
    + Push `5` to `value` stack (`[1, 5]`).
- Index 9:
    + Item is a `*`, which is a `operator`.
    + Push to `operator` stack (`["+", "*"]`).
- Index 10:
    + Item is a `(`, ignore.
- Index 11:
    + Item is a `4`, which is a `value`.
    + Push to `value` stack (`[1, 5, 4]`).
- Index 12:
    + Item is a `*`, which is a operator.
    + Push to `operator` stack (`["+", "*", "*"]`).
- Index 13:
    + Item is a `5`, which is a `value`.
    + Push to `value` stack (`[1, 5, 4, 5]`).
- Index 14:
    + Item is a `)`, which means pop the operator and two values.
    + Pop `operator` stack, returns `*` (`["+", "*"]`).
    + Pop `value stack`, returns `5` (`[1, 5, 4]`).
    + Pop `value stack`, returns `4` (`[1, 5]`).
    + Evaluate `5 * 4` to `20`.
    + Push `20` to `value` stack (`[1, 5, 20]`).
- Index 15:
    + Item is a `)`, which means pop the operator and two values.
    + Pop `operator` stack, returns `*` (`["*"]`).
    + Pop `value stack`, returns `20` (`[1, 5]`).
    + Pop `value stack`, returns `5` (`[1]`).
    + Evaluate `20 * 5` to `100`.
    + Push `100` to `value` stack (`[1, 100]`).
- Index 15:
    + Item is a `)`, which means pop the operator and two values.
    + Pop `operator` stack, returns `*` (`[]`).
    + Pop `value stack`, returns `100` (`[1]`).
    + Pop `value stack`, returns `1` (`[]`).
    + Evaluate `100 + 1` to `101`.
    + Push `101` to `value` stack (`[101]`).

### Interview Questions: Stacks and Queues

1. **Queue with two stacks. Implement a queue with two stacks so that each queue operations takes a constant amortized number of stack operations.**

- If you push elements onto a stack and then pop them all, they appear in reverse order. If you repeat this process, they're now back in order.

2. **Stack with max. Create a data structure that efficiently supports the stack operations (push and pop) and also a return-the-maximum operation. Assume the elements are real numbers so that you can compare them.**

- Use two stacks, one to store all of the items and a second stack to store the maximums.

3. **Java generics. Explain why Java prohibits generic array creation.**

-  Java arrays are *covariant* but Java generics are not: that is, `String[]` is a subtype of `Object[]`, but `Stack<String>` is not a subtype of `Stack<Object>`.

---

## Elementary Sorts

### Total Order

Binary relation <= that satisfies:

- `Antisymmetry`: if `v <= w` and `w >= v`, then `v = w`.
- `Transitivity`: if `v <= w` and `w <= x`, then `v <= x`.
- `Totality`: either `v <= w` or `w <= v` or both.

### Sorting Introduction

> Consider the data type `Temperature` defined below. Which of the following required properties of the `Comparable` interface does the `compareTo()` method *violate*?

```java
public class Temperature implements Comparable<Temperature> {
    private final double degrees;

    public Temperature(double degrees) {
        if (Double.isNaN(degrees))
            throw new IllegalArgumentException();
        this.degrees = degrees;
    }

    public int compareTo(Temperature that) {
        double EPSILON = 0.1;
        if (this.degrees < that.degrees - EPSILON) return -1;
        if (this.degrees > that.degrees + EPSILON) return +1;
        return 0;
    }
    ...
}
```

> **Transitivity**.
>
> Transitivity is violated. Suppose that `a`, `b`, and `c` refer to objects corresponding to temperatures of `10.16∘`, `10.18∘`, `10.00∘`, respectively. Then, `a.compareTo(b) <= 0` and `b.compareTo(c) <= 0`, but `a.compareTo(c) > 0`. For this reason, you must not introduce a fudge factor when comparing two floating-point numbers if you want to implement the `Comparable` interface.

### Selection Sort

Also known as bubble sort. For iteration `i`, find index `min` of smallest remaining entry and swap `array[i]` with `array[min]`.

- **Algorithm**, pointer scans from the left to right.
- **Invariants**:
    + Entries the left of pointer (including pointer) fixed and in ascending order.
    + No entry to the right of pointer is smaller than any entry to the left of the pointer.

> How many compares does selection sort make when the input array is already sorted?

> **Quadratic**.
>
> The number of compares and exchanges made by selection sort does not depend on the order of the input.

### Insertion Sort

For iteration `i`, swap `array[i]` with each larger entry to its left.

- **Algorithm**, pointer scans from left to right.
- **Invariants**:
    + Entries to the left of the pointer (including pointer) are in ascending order.
    + Entries to the right of the pointer have not been seen.

Sorting randomly-ordered array with distinct keys, insertion sort uses `~1/4N^2` compares and `~1/4N^2` exchanges on average.

For the **best case**, if the array is in ascending order, insertion sort makes `N-1` compares and `0` exchanges.

For the **worst case**, if the array is in descending order (and no duplicates), insertion sort makes `~1/2N^2` compares and `~1/2N^2` exchanges.

For partially sorted arrays, insertion sort runs in linear time. Number of exchanges equals the number of inversions (pair of keys that are out of order).

> How many compares does insertion sort make on an input array that is already sorted?

> **Linear**.
>
> Each item (except the first) is compared against the item to its left (and to no other items), for a total of `n - 1` compares.

### Shellsort

Developed by Shell in 1959, `h-sort` array for decreasing sequence of values of `h`.

Insertion sort is inefficient because elements really move only one position at a time even when we kind of know that they have a long way to go.

Shellsort moves entries several positions at a time, and this is known as `h-sorting` the array.

If `h = 4`, this means we would look at every 4th element. It is exactly like insertion sort but instead of going `1`-back, it goes `h`-back.

Recommended increment sequence is `3x+1`, by Knuth.

The **worst case** is `3x+1` increments (`O(N^3/2)`). Number of compares used by shellsort is at most by a small multiple of *N* times the `#` of increments used.

Useful in practice, unless:

- Fast unless array size is huge.
- Tiny, fixed footprint for code (used in embedded systems).
- Hardware sort prototype.

> How many compares does shellsort (using the `3x+1` increment sequence) make on an input array that is already sorted?

> **Linearithmic**.
>
> Since successive increment values of `h` differ by at least a factor of `3`, there are `∼log<sub>3</sub>⁡n` increment values. For each increment value `h`, the array is already `h`-sorted so it will make `∼n` compares.

### Shuffling

- **Knuth Shuffle**, linear time shuffling algorithm using randomness. For iteration `i`, pick integer `r` between `0` and `i` uniformly at random. Swap `array[i]` and `array[r]`.

> How many possible permutations are there of a deck of 52 playing cards?

> **52!**.
>
> The number of subsets of `n` distinct elements is `2^n`. The number of permutations of `n` distinct elements is `n! = 1×2×…×n`.

### Convex Hull

The smallest polygon that encloses all the points.

> What is the maximum number of vertices that can be on the convex hull of a set of `n` points?

> **Linear**.
>
> If the input points are points on the circumference of a circle (or vertices of a regular `n-gon`), then all `n` points will be on the convex hull.

### Interview Questions: Elementary Sorts

1. **Intersection of two sets. Given two arrays `a[]` and `b[]`, each containing nnn distinct 2D points in the plane, design a subquadratic algorithm to count the number of points that are contained both in array `a[]` and array `b[]`.**

- Shellsort (or any other subquadratic sort).

2. **Permutation. Given two integer arrays of size `n`, design a subquadratic algorithm to determine whether one is a permutation of the other. That is, do they contain exactly the same entries but, possibly, in a different order.**

- Sort both arrays.

3. **Dutch national flag. Given an array of `n` buckets, each containing a red, white, or blue pebble, sort them by color. The allowed operations are:**
    - `swap(i,j)`: swap the pebble in bucket `i` with the pebble in bucket `j`.
    - `color(i)`: determine the color of the pebble in bucket `i`.
    - The performance requirements are as follows:
        - At most `n` calls to `color()`.
        - At most `n` calls to `swap()`.
        - Constant extra space.

- 3-way partitioning.

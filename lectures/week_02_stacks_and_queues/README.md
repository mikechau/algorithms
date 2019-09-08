# Week 2 - Stacks and Queues

## Notes

1. [1.3 - Stacks and Queues](./1.3_stacks_and_queues)
2. [2.1 - Elementary Sorts](./2.1_elementary_sorts)

## Definitions

- `Stack` - Examine the item most recently added, also known as **LIFO**, *last in first out*.
    + Push, add to end.
    + Pop, take from beginning.

- `Queue` - Examine the item least recently added, also known as **FIFO**, *first in first out*.
    + Queue, add to beginning.
    + Dequeue, take from end.

## Resizing Arrays

- Linked lists, every operation takes constant time in the worst case, but dealing with the links requires extra time and space, so it's slower.
- Resizing array, has good amortized time, so average time over the entire process is good. There is less wasted space and each operation is probably faster.

## Queues

> Suppose that you implement a queue using a null-terminated singly-linked list, maintaining a reference to the item least recently added (the front of the list) but not maintaining a reference to the item most recently added (the end of the list). What are the worst-case running times for enqueue and dequeue?

> **Linear time for enqueue and constant time for dequeue.**
>
> Removing a node from the front of a linked list takes constant time. However, it takes linear time to find the last node of a linked list (unless we maintain an explicit reference to it, which is not done here).

## Generics

> Which of the following statements is a type safe way to declare and initialize a Stack\verb#Stack#Stack of integers?

> **`Stack<Integer>stack=newStack<Integer>();`**.
>
> In Java 6, you must specify the concrete type both in the variable declaration (left-hand side) and the constructor call (right-hand side). Starting in Java 7, you can use the diamond operator instead:
>
> `Stack<Integer> stack = new Stack<>();`

## Iterators

> Suppose that we copy the iterator code from our linked list and resizing array implementations of a stack to the corresponding implementations of a queue.
>
> Which queue iterator(s) will correctly return the items in FIFO order?

> **Linked-list iterator only**.
>
> The linked-list iterator will work without modification because the items in the linked list are ordered in FIFO order (which is the main reason we dequeue from the front and enqueue to the back instead of vice versa).
>
> The array iterator will fail for two reasons. First, the the items should be iterated over in the opposite order. Second, the items won't typically be stored in the array as entries `0` to `n−1n-1n−1`.

## Dijkstra's two-stack algorithm

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

## Interview Questions: Stacks and Queues

1. **Queue with two stacks. Implement a queue with two stacks so that each queue operations takes a constant amortized number of stack operations.**

- If you push elements onto a stack and then pop them all, they appear in reverse order. If you repeat this process, they're now back in order.

2. **Stack with max. Create a data structure that efficiently supports the stack operations (push and pop) and also a return-the-maximum operation. Assume the elements are real numbers so that you can compare them.**

- Use two stacks, one to store all of the items and a second stack to store the maximums.

3. **Java generics. Explain why Java prohibits generic array creation.**

-  Java arrays are *covariant* but Java generics are not: that is, `String[]` is a subtype of `Object[]`, but `Stack<String>` is not a subtype of `Stack<Object>`.

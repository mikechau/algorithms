# 1.4 - Analysis of Algorithms

- [Reference](https://algs4.cs.princeton.edu/14analysis/)

## Order-of-Growth Classifications

| description | order of growth | typical code framework | description | example |
| --- | --- | --- | --- | --- |
| *constant* | 1 | `a = b + c;` | statement | add two numbers |
| *logarithmic* | log *N* | [example](./examples/binary_search.java) | divide in half | binary search |
| *linear* | *N* | [example](./examples/linear.java) | loop | find the maximum |
| *linearithmic* | *N* log *N* | [example](https://algs4.cs.princeton.edu/22mergesort/index.php#2.4) | divide and conquer | mergesort |
| *quadratic* | *N*^2 | [example](./examples/quadratic.java) | double loop | check all pairs |
| *cubic* | *N*^3 | [example](./examples/cubic.java) | triple loop | check all triples |
| *exponential* | 2^*N* | [example](https://algs4.cs.princeton.edu/60context/) | exhaustive search | check all subsets |

## Memory Usage

The following estimates assume a 64-bit machine.

### Primitive Types

| type | bytes |
| --- | --- |
| `boolean` | `1` |
| `byte` | `1` |
| `char` | `2` |
| `int` | `4` |
| `float` | `4` |
| `long` | `8` |
| `double` | `8` |

### Objects

- 16 bytes for object overhead
- 4 bytes for padding (or a multiple of 8 bytes)
- Base: `20` bytes

### References

Typically a memory address, as it references another object.

- Total: `8` bytes

### Linked Lists

- 16 bytes for object overhead
- 8 bytes for extra overhead
- 8 bytes for `next` ref
- 8 bytes for `item` ref
- Total: `40` bytes

### Arrays

- 16 bytes of object overhead
- 4 bytes for length
- 4 bytes for padding
- Base: `24` bytes

| type | bytes |
| int[] | ~4N |
| double[] | ~8N |
| Date[] | ~40N |
| double[][] | ~8NM |

### Strings

- 32 bytes for string object
- 24 bytes for array
- 2N bytes for characters in the array
- Total: `56 + 2N` bytes

## Exercises

Give the order of growth (as a function of N) of the running times of each of the following code fragments:

```java
int sum = 0;
for (int n = N; n > 0; n /= 2)
   for (int i = 0; i < n; i++)
      sum++;
```

- N = 2
- Outerloop:
    - N starts at 2
        - N (2) is greater than 0
        - Subloop:
            - i starts at 0
                - i is less than n (2)
                - sum increases by 1 (total: 1)
                - i is increased to 1
            - i starts a 1
                - i is less than n (2)
                - sum increases by 1 (total: 2)
                - i is increased to 2
            - i starts at 2
                - i is not less than n (2)
                - subloop ends
        - n is divided by 2 and becomes 1
            - n (1) is greater than 0
            - Subloop:
                - i starts at 0
                    - i is less than n (1)
                    - sum increases by 1 (total: 3)
                    - i is increased to 1
                - i starts at 1
                    - i is not less than n (1)
                    - subloop ends
        - n is divided by 2 and becomes 0
            - n (0) is less than 0, and outerloop ends

This is linear because: `(N + N/2 + N/4 + ...)`.

```java
int sum = 0;
for (int i = 1; i < N; i *= 2)
   for(int j = 0; j < i; j++)
      sum++;
```

This is linear because: `(1 + 2 + 4 + 8 + ...)`.

```java
int sum = 0;
for (int i = 1; i < N; i *= 2)
   for (int j = 0; j < N; j++)
      sum++;
```

This is linearithmic because the outlerloop loops lg N times. The subloop iteration is halved because the parent loop is doubling `i`.

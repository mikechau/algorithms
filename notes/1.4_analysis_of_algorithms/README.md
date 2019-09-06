# 1.4 - Analysis of Algorithms

- [Reference](https://algs4.cs.princeton.edu/14analysis/)

## Order-of-growth classifications

| description | order of growth | typical code framework | description | example |
| --- | --- | --- | --- | --- |
| *constant* | 1 | `a = b + c;` | statement | add two numbers |
| *logarithmic* | log *N* | [example](./examples/binary_search.java) | divide in half | binary search |
| *linear* | *N* | [example](./examples/linear.java) | loop | find the maximum |
| *linearithmic* | *N* log *N* | [example](https://algs4.cs.princeton.edu/22mergesort/index.php#2.4) | divide and conquer | mergesort |
| *quadratic* | *N*^2 | [example](./examples/quadratic.java) | double loop | check all pairs |
| *cubic* | *N*^3 | [example](./examples/cubic.java) | triple loop | check all triples |
| *exponential* | 2^*N* | [example](https://algs4.cs.princeton.edu/60context/) | exhaustive search | check all subsets |

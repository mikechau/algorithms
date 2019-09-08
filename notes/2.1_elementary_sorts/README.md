# 2.1 - Elementary Sorts

- [Reference](https://algs4.cs.princeton.edu/21elementary/)

## Selection Sort

- Find the smallest item in the array and exchange it with the first entry.
- Find the next smallest item and exchange it with the second entry.
- Continue iteration until array is sorted.
- `~n^2/2` compares and `n` exchanges to sort an array of length `n`.

## Insertion Sort

- Make space for the current item by moving larger items one position to the right, before inserting the current item into the vacated position
- Randomly ordered arrays of length `N` wuth distrinct keys, uses `~N^2/4` compares and `~N^2/4` exchanges on average.
- Worst case is `~N^2/2` compares and `~N^2/2` exchanges.
- Best case is `N-1` compares and `0` exchanges.
- Works well for certain types of nonrandom arrays, even if they are huge.
- *Inversion* is a pair of keys that are out of order in the array.
- If the *inversions* in an array is less than a constant multiple of the array size, the array is *partially sorted*.

## Shellsort

- Simple extension of insertion sort that gains speed by allowing exchanges of entries that are far apart.
- Produces partially sorted arrays that can be efficiently sorted, by insertion sort.
- Idea is to rearrange the array to give to the property that taking every *h*th entry (starting anywhere) yields a sorted sequence. Array is said to be *h-sorted*.
- By h-sorting for some large values of h, we can move entries in the array long distances and thus make it easier to h-stort for smaller values of h. Using such a procedure for any increment sequence of values of h that ends in 1 will produce a sorted array.

## Notes

- Randomly ordered arrays of distinct values, the running times of insertion sort and selection sort are quadratic and within a small constant factor of one another.

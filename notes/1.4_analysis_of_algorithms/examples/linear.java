int[] a = new int[]{ 1,2,3,4,5,6,7,8,9,10 };

double max = a[0];
for (int i = 1; i < a.length; i++)
  if (a[i] > max) max = a[i];

int[] a = new int[]{ 1,2,3,4,5,6,7,8,9,10 };
double cnt = 0;

for (int i = 0; i < a.length; i++)
  for (int j = i+1; j < a.length; j++)
    if (a[i] + a[j] == 0)
      cnt++;


#include <stdio.h>

int binary_search(int *a,int n,int d)
{
  int lo = 0;
  int hi = n - 1;
  while(hi >= lo)
  {
    int middle = (lo + hi) / 2;
    if(a[middle] == d)
      return middle; // found it
    if(a[middle] < d)
      lo = middle;
    else
      hi = middle;
  }
  return -1; // not found
}

int main(void)
{
  int a[8] = { 1,3,8,11,18,19,23,27 };
  
  int i,d;

  for(d = 0;d <= 30;d++)
  {
    i = binary_search(a,8,d);
    if(i < 0)
      printf("%d not found\n",d);
    else
      printf("the index of %d is %d\n",d,i);
  }
  return 0;
}

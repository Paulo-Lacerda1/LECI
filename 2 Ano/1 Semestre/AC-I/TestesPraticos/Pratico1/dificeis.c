#include <stdio.h>
#define N 10

int ex1(void) {
    int a[N] = {1, 1, 3, 4, 4, 4, 5, 6, 6, 9};
    int i = 0, pairs = 0;

    while (i < N - 1) {
        if (a[i] == a[i + 1])
            pairs++;
        i++;
    }

    printf("Pares iguais = %d\n", pairs);
}
//----------------------------------------------------------------------
#include <stdio.h>
#define N 6

int main(void) {
    int v[N] = {1, 2, 3, 4, 5, 6};
    int tmp = v[N - 1];

    for (int i = N - 1; i > 0; i--)
        v[i] = v[i - 1];
    v[0] = tmp;

    for (int i = 0; i < N; i++)
        printf("%d ", v[i]);
}
//-----------------------------------------------------------------------
#include <stdio.h>
#define N 6

int main(void) {
    int a[N] = {-3, 4, -1, 9, 0, 7};
    int b[N];
    int *pa = a, *pb = b;

    while (pa < a + N) {
        if (*pa > 0)
            *pb++ = *pa;
        pa++;
    }

    for (pb = b; pb < b + N; pb++)
        printf("%d ", *pb);

}


//-------------------------------------------------------------------------


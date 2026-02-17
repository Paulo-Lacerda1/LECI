//Exercicio 1
//-----------------------------------------------------------------------

#include <stdio.h>
#define N 10

int main() {
    int a[N];
    int n_pos = 0, n_neg = 0;
    int i;

    for (i = 0; i < N; i++)
        scanf("%d", &a[i]);

    for (i = 0; i < N; i++) {
        if (a[i]> 0)
            n_pos++;
        else if (a[i] < 0)
            n_neg++;
    }

    printf("%d %d", n_pos, n_neg);
    return 0;   
}

//Exercicio 2
//-----------------------------------------------------------------------

#include <stdio.h>
#define N 12

int main() {
    int a[N];
    int i, soma = 0, max = -2147483648, min = 2147483647;

    for (i = 0; i < N; i++) {
        scanf("%d", &a[i]);
        soma += a[i];
        if (a[i] > max) max = a[i];
        if (a[i] < min) min = a[i];
    }

    printf("%d %d %d", max, min, soma / N);
    return 0;
}

//Exercicio 3
//-----------------------------------------------------------------------

#include <stdio.h>
#define N 8

int main() {
    int a[N] = {8,4,15,-1987,327,-9,27,16};
    int i, j, tmp;

    for (i = 0, j = N-1; i < j; i++, j--) {
        tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    for (i = 0; i < N; i++)
        printf("%d,", a[i]);

    return 0;
}

//Exercicio4 
//-----------------------------------------------------------------------

#include <stdio.h>
#define N 20

int main() {
    int a[N], pares[N], impares[N];
    int i, p = 0, q = 0;

    for (i = 0; i < N; i++)
        scanf("%d", &a[i]);

    for (i = 0; i < N; i++) {
        if (a[i] % 2 == 0)
            pares[p++] = a[i];
        else111
            impares[q++] = a[i];
    }

    for (i = 0; i < p; i++) printf("%d,", pares[i]);
    for (i = 0; i < q; i++) printf("%d,", impares[i]);
    return 0;
}

//Exercicio 5
//-----------------------------------------------------------------------

#include <stdio.h>
#define N 10

int main() {
    int a[N];
    int i, soma = 0;

    for (i = 0; i < N; i++)
        scanf("%d", &a[i]);

    for (i = 0; i < N; i++)
        if (a[i] > 0)
            soma += a[i];

    printf("%d", soma);
    return 0;
}


//---------------------------------------------------------
#include <stdio.h>

#define SIZE 8

int ex1() {
    int val[SIZE] = {8, 4, 15, -1987, 327, -9, 27, 16};
    int i, v;

    i = 0;
    do {
        v = val[i];                       // v = val[i]
        val[i] = val[i + SIZE / 2];       // val[i] = val[i + SIZE/2]
        val[i + SIZE / 2] = v;            // val[i + SIZE/2] = v
        i++;                              // ++i
    } while (i < SIZE / 2);               // enquanto i < SIZE/2
    
    printf("Result is: ");

    i = 0;
    do {
        printf("%d,", val[i]);            // print_int10(val[i])
        i++;                              // i++
    } while (i < SIZE);                   // enquanto i < SIZE

    return 0;
}
//---------------------------------------------------------

#include <stdio.h>

#define N 35

int ex2() {
    int a[N];
    int C[N];
    int n_even = 0;
    int n_odd = 0;
    int i;

    // ---- Leitura dos elementos ----
    for (i = 0; i < N; i++) {
        scanf("%d", &a[i]);     // lê inteiro e guarda em a[i]
    }

    // ---- Separação dos ímpares e contagem ----
    int *p1 = a;
    int *p2 = C;

    while (p1 < a + N) {
        if (*p1 % 2 != 0) {     // se for ímpar
            *p2 = *p1;          // copia para C
            p2++;               // incrementa ponteiro de C
            n_odd++;            // conta ímpares
        } else {
            n_even++;           // conta pares
        }
        p1++;                   // avança para o próximo elemento
    }

    // ---- Impressão dos ímpares ----
    p2 = C;
    for (i = 0; i < n_odd; i++) {
        printf("%d", *p2);      // imprime valor ímpar
        p2++;
    }

    return 0;
}

	
//----------------------------------------------------------	
int ex3() {

  int val, i;
    int sBits = 0;

    val = read_int();
    for(i = 0; i < 0x20; i++)
    {
        if ((val & 1) == 1)
            sBits++;
        val = val >> 1;
    }

    if (sBits == 0)
        print_string("No set bits found\n");
    else
        print_int10(sBits);
}
//----------------------------------------------------------	

void ex4(void)
{
    static char str[] = "Teste-Pratico-1";
    char *ms = str;
    char *pf = ms - 1;

    do {
        pf++;
    }while (*pf != '\0');

    while (ms < pf) {
        if (*ms < '0' || *ms > 'z') {
            *ms = '?';
        } else {
            *ms = *ms ^ 0x15;
        }
        ms++;
    }
}

//  ----------------------------------------------------------	

//Área de resposta (preencha o mapa de registos) / Answer area (fill in the register map)
#define SIZE 6

void ex5(void)
{
    static int in[SIZE] = {56, 11, 5, 72, 11, -15};
    static int out[2];
    int k, min1, min2;

    min1 = ~(1 << 31);
    min2 = min1;

    for (k = 0; k < SIZE; k++) {
        if (in[k] < min1) {
            min2 = min1;
            min1 = in[k];
        } else {
            if (in[k] < min2 && in[k] > min1)
                min2 = in[k];
        }
    }
    out[0] = min1;
    out[1] = min2;

    for (k = 0; k < 2; k++) {
        printf("%d\n", out[k]);
    }

}


    
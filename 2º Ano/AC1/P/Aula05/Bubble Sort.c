#include <stdio.h>

#define SIZE 10

void main(void) {
    static int lista[SIZE];
    int houveTroca;
    int aux;
    int *p, *pUltimo;

    // Leitura de valores e preenchimento do array
    printf("Digite %d valores inteiros para preencher o array:\n", SIZE);
    for (int i = 0; i < SIZE; i++) {
        printf("Valor %d: ", i + 1);
        scanf("%d", &lista[i]);
    }

    pUltimo = lista + (SIZE - 1);

    // Ordenação usando Bubble Sort
    do {
        houveTroca = 0;  // 0 é equivalente a FALSE
        for (p = lista; p < pUltimo; p++) {
            if (*p > *(p + 1)) {
                aux = *p;
                *p = *(p + 1);
                *(p + 1) = aux;
                houveTroca = 1;  // 1 é equivalente a TRUE
            }
        }
        pUltimo--;  // Reduz o limite do array pois o último elemento já está ordenado
    } while (houveTroca == 1);

    // Impressão do conteúdo do array ordenado
    printf("Array ordenado:\n");
    for (int i = 0; i < SIZE; i++) {
        printf("%d ", lista[i]);
    }
    printf("\n");
}

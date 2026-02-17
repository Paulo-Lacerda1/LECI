#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

static int count;  // var partilhada

void* thread_child(void* arg) {
    int N2; 
    do {
        printf("N2 entre 10 e 20: ");
        scanf("%d", &N2);
    } while (N2 < 10 || N2 > 20);

    printf("%d\n",count);
    // Incrementar até N2
    while (count < N2) {
        count++;
        printf("%d\n", count);
    }

    printf("Child thread terminou.\n");
    return NULL;
}

int main() {
    pthread_t child_thread;
    int N1;

    // Ler e validar N1
    do {
        printf("N1 entre 1 e 9: ");
        scanf("%d", &N1);
    } while (N1 < 1 || N1 > 9);

    // Inicializar contador partilhado
    count = N1;

    // Criar a thread filha
    pthread_create(&child_thread, NULL, thread_child, NULL);

    // Esperar pela thread filha
    pthread_join(child_thread, NULL);

    // Decrementar até 1
    while (count > 1) {
        count--;
        printf("%d\n", count);
    }

    printf("\nMain thread terminou.\n");
    return 0;
}

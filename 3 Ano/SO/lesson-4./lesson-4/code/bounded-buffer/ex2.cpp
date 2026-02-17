#include <stdio.h>
#include <stdlib.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
    int shmid;
    int *counter;

    //memória partilhada (1 inteiro)
    shmid = shmget(IPC_PRIVATE, sizeof(int), 0600 | IPC_CREAT);
    if (shmid == -1) {
        perror("Erro a criar memória partilhada");
        exit(EXIT_FAILURE);
    }

    // Liga memória partilhada
    counter = (int *) shmat(shmid, NULL, 0);
    if (counter == (void *) -1) {
        perror("Erro a ligar memória partilhada");
        exit(EXIT_FAILURE);
    }

    *counter = 1;

    pid_t pid = fork();

    if (pid == -1) {
        perror("Erro fork");
        exit(EXIT_FAILURE);
    }

    if (pid == 0) {
        //Processo filho
        int N;
        do {
            printf("Insere um valor entre 10 e 20: ");
            scanf("%d", &N);
        } while (N < 10 || N > 20);
        
        while (*counter <= N) {
            printf("Filho: %d\n", *counter);
            (*counter)++;
            usleep(20000);
        }

        shmdt(counter);     //processo filho desliga da
        exit(0);
    } 
    else {
        //Processo pai
        wait(NULL); // espera o filho terminar

        while (*counter > 1) {
            
            (*counter)--;
            printf("Pai: %d\n", *counter);
            usleep(20000);
        }

        // Liberta memória
        shmdt(counter);
        shmctl(shmid, IPC_RMID, NULL);
    }

    return 0;
}

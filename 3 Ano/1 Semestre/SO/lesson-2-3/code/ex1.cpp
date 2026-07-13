#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>

#include "process.h"


int main(void) {
    
    pid_t pid;

    for (int i = 0; i < 40; i++) putchar('=');
    putchar('\n');

    // cria filho
    pid = pfork();

    if (pid == 0) {
        execlp("ls", "ls", "-l", NULL);
        perror("execlp");
        exit(EXIT_FAILURE);
    } else {
        // pai espera o filho terminar
        pwait(NULL);

        for (int i = 0; i < 40; i++) putchar('=');
        putchar('\n');
    }

    return EXIT_SUCCESS;
}

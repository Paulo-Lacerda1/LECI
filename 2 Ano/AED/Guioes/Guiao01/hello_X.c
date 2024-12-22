#include <stdio.h>
#include <stdlib.h>

int main (void)
{
    char nome[20];

    printf("Nome? ");

    char *r =fgets(nome,20,stdin);

    if(r == NULL){   
        fprintf(stderr, "Erro no gets!\n"); 
        exit(1);
    }

    printf("Hello %s!\n",nome);

    return 0;
}   
#define SIZE 15

int toi( char * );
int avz( int *, int );

int func1(int *f1, int k, char *av[])
{
    int i;
    int res;

    if ((k >= 2) && (k <= SIZE)) {
        i = 2;
        do {
            f1[i] = toi(av[i]);
            i++;
        } while (i < k);

        res = avz(f1, k);
        print_int10(res);
    } else {
        print_string("Invalid argc");
        res = -1;
    }

    return res;
}

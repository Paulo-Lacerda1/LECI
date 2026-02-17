#define MAX 20

int conv(char *);
int soma(int *, int);

int funcA(int *v, int n, char *args[])
{
    int i;
    int total = 0;

    if (n >= 3 && n <= MAX) {
        for (i = 1; i < n; i++) {
            v[i] = conv(args[i]);
            total += v[i];
        }
        return soma(v, n);
    }
    return -1;
}

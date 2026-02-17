typedef struct {
    int id;
    char name[12];
    double value;
    char flag;
    int count;
} data_t;

double process(data_t *p, int n)
{
    int i;
    double sum = 0.0;

    for (i = 0; i < n; i++) {
        if (p[i].flag != 0) {
            sum += p[i].value * (double)p[i].count;
        }
    }
    return sum / (double)n;
}

typedef struct {
    int acc;
    unsigned char nm;
    double grade;
    char quest[14];
    int cq;
} t_kvd;

double func3(int nv, t_kvd *pt)
{
    int i, j;
    double sum = 0.0;

    for (i = 0; i < nv; i++, pt++) {
        j = 0;
        do {
            sum += (double) pt->quest[j];
            j++;
        } while (j < pt->nm);

        pt->acc = (int) (sum / pt->grade);
    }   

    return (pt->grade * (double) pt->cq);
}

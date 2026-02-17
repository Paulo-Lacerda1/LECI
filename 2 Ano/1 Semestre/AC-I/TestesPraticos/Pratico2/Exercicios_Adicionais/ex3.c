float calc(float *a, int n, float eps)
{
    int i = 0;
    float acc = 0.0f;

    while (i < n) {
        acc += a[i];
        if (acc > eps)
            break;
        i++;
    }
    return acc / (float)(i + 1);
}

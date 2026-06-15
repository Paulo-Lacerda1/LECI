#include <detpic32.h>

int main(void){
    while(1){}
    TRISEbits.TRISE0=0;
    LATEbits.LATE0=1;

}
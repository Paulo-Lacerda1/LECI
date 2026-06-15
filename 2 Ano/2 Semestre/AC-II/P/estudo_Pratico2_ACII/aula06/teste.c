#include <detpic32.h>


void delay(unsigned int ms){
    resetCoreTimer();
    while(readCoreTimer()<20000*ms);
}

int main(void){

    TRISBbits.TRISB4 = 1;
    AD1PCFGbits.PCFG4= 0;
    AD1CON1bits.SSRC = 7;
    AD1CON1bits.CLRASAM = 1;
    AD1CON3bits.SAMC = 16;
    AD1CON2bits.SMPI = 2-1; 
    AD1CHSbits.CH0SA = 4;
    AD1CON1bits.ON = 1;

    while(1) {
        AD1CON1bits.ASAM = 1;          //start conversion
        while( IFS1bits.AD1IF == 0 );  // Wait while conversion not done
        IFS1bits.AD1IF = 0;

        unsigned int avg = (ADC1BUF0+ADC1BUF1)/2;

        printInt(avg, 16 | 3<<16);
        putChar('\n');
        delay(167);                     //f = 6Hz

    }


}
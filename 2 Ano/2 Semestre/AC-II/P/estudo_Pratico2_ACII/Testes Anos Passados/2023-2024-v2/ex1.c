#include <detpic32.h>

void config(void)
{
    TRISBbits.TRISB2 = 1;
    TRISBbits.TRISB0 = 1;

    TRISCbits.TRISC14 = 0;   // LED D11

    T3CONbits.TCKPS = 2;     // 1:4
    PR3 = 41667;
    TMR3 = 0;
    T3CONbits.TON = 1;

    OC1CONbits.OCM = 6;
    OC1CONbits.OCTSEL = 1;   // Timer T3
    OC1RS = ((PR3 + 1) * 75) / 100;
    OC1CONbits.ON = 1;
}

int main(void)
{
    config();

    while(1) {
        
        if(PORTBbits.RB2 == 0 && PORTBbits.RB0 == 0)
        {
            OC1RS = ((PR3 + 1) * 30) / 100;
        }

        if(PORTBbits.RB2 == 1 && PORTBbits.RB0 == 1)
        {
            OC1RS = ((PR3 + 1) * 75) / 100;
        }

        LATCbits.LATC14 = PORTDbits.RD0;   // OC1 está em RD0

        resetCoreTimer();
        while(readCoreTimer()<5000); //250 micro

    }

    return 0;
}


#include <detpic32.h>

volatile int duty = 90;

void delay(unsigned int ms)
{
    resetCoreTimer();
    while(readCoreTimer() < 20000 * ms);
}

void setPWM(int duty)
{
    OC3RS = ((PR3 + 1) * duty) / 100;
}

void config(void)
{
    AD1PCFGbits.PCFG0 = 1;
    TRISBbits.TRISB0 = 1;    // switch RB0

    TRISDbits.TRISD7 = 0;    // LED em RD7 como saída

    // Timer3 para PWM a 200 Hz
    T3CONbits.TCKPS = 1;     // 1:2
    PR3 = 49999;
    TMR3 = 0;
    T3CONbits.TON = 1;

    // PWM no OC3 -> RD2
    OC3CONbits.OCM = 6;
    OC3CONbits.OCTSEL = 1;   // Timer3
    OC3R = ((PR3 + 1) * duty) / 100;
    OC3RS = ((PR3 + 1) * duty) / 100;
    OC3CONbits.ON = 1;
}

int main(void)
{
    config();

    while(1)
    {
        LATDbits.LATD7 = PORTDbits.RD2;   // copia PWM de OC3/RD2 para RD7

        if(PORTBbits.RB0 == 1)
        {
            delay(250);

            duty -= 10;

            if(duty < 30)
                duty = 90;

            setPWM(duty);
        }
    }

    return 0;
}

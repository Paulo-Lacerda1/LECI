#include <detpic32.h>

volatile unsigned int dutyCycle = 0;

void setPWM(unsigned int dutyCycle)
{
    if(dutyCycle > 100)
        dutyCycle = 100;

    OC1RS = ((PR3 + 1) * dutyCycle) / 100;
}

int main(void)
{
    int lastButton = 0;
    int currentButton;

    // Timer T3: 100 Hz, prescaler 1:4
    T3CONbits.TCKPS = 2;
    PR3 = 49999;
    TMR3 = 0;

    // OC1 PWM com Timer T3
    OC1CONbits.OCM = 6;
    OC1CONbits.OCTSEL = 1;

    OC1R = 0;
    setPWM(dutyCycle);

    OC1CONbits.ON = 1;
    T3CONbits.TON = 1;

    // Pulsador INT1 em RD8
    TRISDbits.TRISD8 = 1;

    // LED D11 em RC14
    TRISCbits.TRISC14 = 0;

    while(1)
    {
        currentButton = PORTDbits.RD8;

        // transição 0 -> 1
        if(currentButton == 1 && lastButton == 0)
        {
            dutyCycle += 10;

            if(dutyCycle > 100)
                dutyCycle = 0;

            setPWM(dutyCycle);
        }

        lastButton = currentButton;

        // copiar PWM de OC1/RD0 para RC14
        LATCbits.LATC14 = PORTDbits.RD0;
    }

    return 0;
}
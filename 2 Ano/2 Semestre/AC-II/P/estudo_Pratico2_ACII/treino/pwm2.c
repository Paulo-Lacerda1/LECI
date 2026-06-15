#include <detpic32.h>

#define CORE_1_3S 26000000   // 1.3 s * 20 MHz

void setPWM(unsigned int dutyCycle)
{
    if(dutyCycle > 100)
        dutyCycle = 100;

    OC4RS = ((PR3 + 1) * dutyCycle) / 100;
}

void config(void)
{
    // RB1 como entrada digital
    TRISBbits.TRISB1 = 1;

    // Timer3 para PWM a 130 Hz
    // PBCLK = 20 MHz
    // prescaler = 4
    // PR3 = 20MHz / (4 * 130) - 1 ≈ 38460
    T3CONbits.TCKPS = 2;   // 1:4
    PR3 = 38460;
    TMR3 = 0;
    T3CONbits.TON = 1;

    // OC4 usa Timer3
    OC4CONbits.OCM = 6;    // PWM mode, fault disabled
    OC4CONbits.OCTSEL = 1; // Timer3
    OC4R = ((PR3 + 1) * 50) / 100;
    OC4RS = ((PR3 + 1) * 50) / 100;
    OC4CONbits.ON = 1;
}

int main(void)
{
    unsigned int duty = 50;

    config();
    while(1) {
        
    delay(1300);

    if(PORTBbits.RB1 == 0)
    {
        setPWM(duty);

        if(sentido == 1)
        {
            duty += 5;

            if(duty >= 90)
            {
                duty = 90;
                sentido = 0;
            }
        }
        else
        {
            duty -= 10;

            if(duty <= 20)
            {
                duty = 20;
                sentido = 1;
            }
        }
    }
}

    return 0;
}

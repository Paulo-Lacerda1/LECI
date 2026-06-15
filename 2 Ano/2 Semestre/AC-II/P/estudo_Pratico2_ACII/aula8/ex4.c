#include <detpic32.h>

volatile unsigned char counter = 0;   // contador decimal: 0 até 29
volatile int cntT1 = 0;

unsigned char toBcd(unsigned char value)
{
    return ((value / 10) << 4) + (value % 10);
}

void send2displays(unsigned char value)
{
    static const char disp7Scodes[] = {
        0x3F, // 0
        0x06, // 1
        0x5B, // 2
        0x4F, // 3
        0x66, // 4
        0x6D, // 5
        0x7D, // 6
        0x07, // 7
        0x7F, // 8
        0x6F  // 9
    };

    static char displayFlag = 0;

    unsigned char digit_low;
    unsigned char digit_high;

    digit_low = value & 0x0F;
    digit_high = value >> 4;

    if(displayFlag == 0)
    {
        LATDbits.LATD5 = 1;     // display menos significativo
        LATDbits.LATD6 = 0;

        LATB = (LATB & 0x80FF) | (disp7Scodes[digit_low] << 8);
    }
    else
    {
        LATDbits.LATD5 = 0;
        LATDbits.LATD6 = 1;     // display mais significativo

        LATB = (LATB & 0x80FF) | (disp7Scodes[digit_high] << 8);
    }

    displayFlag = !displayFlag;
}

int main(void)
{
    // Configuração dos displays
    TRISB = TRISB & 0x80FF;     // RB8 a RB14 como saídas
    TRISD = TRISD & 0xFF9F;     // RD5 e RD6 como saídas

    // TIMER 1 - 2 Hz
    // Como o objetivo é incrementar a 1 Hz, incrementamos a cada 2 interrupções
    T1CONbits.TCKPS = 3;        // 1:256 prescaler
    PR1 = 39062;                // Fout ≈ 2 Hz
    TMR1 = 0;

    IPC1bits.T1IP = 3;          // prioridade maior que T2
    IEC0bits.T1IE = 1;          // enable interrupção T1
    IFS0bits.T1IF = 0;          // reset flag T1

    T1CONbits.TON = 1;          // ligar Timer 1

    // TIMER 2 - 100 Hz
    T2CONbits.TCKPS = 3;        // 1:8 prescaler
    PR2 = 24999;                // Fout = 20MHz / (8 * (24999 + 1)) = 100 Hz
    TMR2 = 0;

    IPC2bits.T2IP = 2;          // prioridade menor que T1
    IEC0bits.T2IE = 1;          // enable interrupção T2
    IFS0bits.T2IF = 0;          // reset flag T2

    T2CONbits.TON = 1;          // ligar Timer 2

    EnableInterrupts();

    while(1)
    {
        IdleMode();
    }

    return 0;
}

void _int_(4) isr_T1(void)
{
    cntT1++;

    if(cntT1 == 2)
    {
        cntT1 = 0;

        counter = (counter + 1) % 30;
    }

    IFS0bits.T1IF = 0;          // reset flag T1
}

void _int_(8) isr_T2(void)
{
    send2displays(toBcd(counter));

    IFS0bits.T2IF = 0;          // reset flag T2
}
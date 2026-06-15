#include <detpic32.h>

int main(void)
{
    // TIMER 1 - 5 Hz
    T1CONbits.TCKPS = 2;    // 1:64 prescaler
    PR1 = 62499;            // Fout = 20MHz / (64 * (62499 + 1)) = 5 Hz
    TMR1 = 0;               // Reset Timer 1 counter

    IPC1bits.T1IP = 2;      // Priority level 2
    IEC0bits.T1IE = 1;      // Enable Timer 1 interrupts
    IFS0bits.T1IF = 0;      // Reset Timer 1 interrupt flag

    T1CONbits.TON = 1;      // Enable Timer 1

    // TIMER 3 - 25 Hz
    T3CONbits.TCKPS = 4;    // 1:16 prescaler
    PR3 = 49999;            // Fout = 20MHz / (16 * (49999 + 1)) = 25 Hz
    TMR3 = 0;               // Reset Timer 3 counter

    IPC3bits.T3IP = 2;      // Priority level 2
    IEC0bits.T3IE = 1;      // Enable Timer 3 interrupts
    IFS0bits.T3IF = 0;      // Reset Timer 3 interrupt flag

    T3CONbits.TON = 1;      // Enable Timer 3
    EnableInterrupts();     // !!!

    while(1)
    {
        IdleMode();
    }

    return 0;
}

void _int_(4) isr_T1(void)
{
    putChar('1');

    IFS0bits.T1IF = 0;      // Reset Timer 1 interrupt flag
}

void _int_(12) isr_T3(void)
{
    putChar('3');

    IFS0bits.T3IF = 0;      // Reset Timer 3 interrupt flag
}

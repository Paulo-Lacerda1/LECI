#include <detpic32.h>

volatile int count = 0;

int main(void)
{
    // Configure Timer T3 for approximately 2 Hz
    T3CONbits.TCKPS = 7;    // 1:256 prescaler
    PR3 = 39062;            // Fout ≈ 2 Hz
    TMR3 = 0;               // Clear timer T3 count register

    // Configure Timer T3 interrupts
    IPC3bits.T3IP = 2;      // Interrupt priority
    IEC0bits.T3IE = 1;      // Enable Timer T3 interrupts
    IFS0bits.T3IF = 0;      // Reset Timer T3 interrupt flag

    T3CONbits.TON = 1;      // Enable Timer T3

    EnableInterrupts();

    while(1)
    {
        IdleMode();
    }

    return 0;
}

void _int_(12) isr_T3(void)
{
    count++;

    if(count == 2)
    {
        putChar('.');
        count = 0;
    }

    IFS0bits.T3IF = 0;      // Reset Timer T3 interrupt flag
}
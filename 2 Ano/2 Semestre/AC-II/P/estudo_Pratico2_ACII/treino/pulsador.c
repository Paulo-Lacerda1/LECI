#include <detpic32.h>

volatile int count = 0;

void config(void)
{
    // LED RE0 como saída
    TRISEbits.TRISE0 = 0;
    LATEbits.LATE0 = 0;

    // RD8 / INT1 como entrada
    TRISDbits.TRISD8 = 1;

    // Timer2 a 2 Hz
    // PBCLK = 20 MHz
    // prescaler = 256
    // PR2 = 20MHz / (256 * 2) - 1 = 39062
    T2CONbits.TCKPS = 7;     // 1:256
    PR2 = 39062;
    TMR2 = 0;
    T2CONbits.TON = 0;       // começa desligado

    // Interrupção Timer2
    IPC2bits.T2IP = 2;
    IFS0bits.T2IF = 0;
    IEC0bits.T2IE = 1;

    // INT1 na transição descendente
    INTCONbits.INT1EP = 1;   // falling edge

    // Interrupção INT1
    IPC1bits.INT1IP = 3;     // prioridade maior que T2
    IFS0bits.INT1IF = 0;
    IEC0bits.INT1IE = 1;
}

int main(void)
{
    config();
    EnableInterrupts();

    while(1)
    {
        IdleMode();
    }

    return 0;
}

// ISR Timer2
void _int_(8) isr_T2(void)
{
    count++;

    if(count >= 6)   // 6 intt5                       errupções a 2 Hz = 3 segundos
    {
        LATEbits.LATE0 = 0;   // apaga LED
        T2CONbits.TON = 0;    // para Timer2
        count = 0;  
    }

    IFS0bits.T2IF = 0;
}

// ISR INT1
void _int_(7) isr_INT1(void)
{
    LATEbits.LATE0 = 1;   // acende LED

    count = 0;
    TMR2 = 0;
    IFS0bits.T2IF = 0;
    T2CONbits.TON = 1;    // inicia temporização

    IFS0bits.INT1IF = 0;
}
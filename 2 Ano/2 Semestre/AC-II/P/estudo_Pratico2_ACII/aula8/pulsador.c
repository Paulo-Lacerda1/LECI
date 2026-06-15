#include <detpic32.h>

volatile unsigned int cnt = 0;

int main(void)
{
    // Configurar RD8 como entrada - botão INT1
    TRISDbits.TRISD8 = 1;

    // Configurar RE0 como saída - LED0
    TRISEbits.TRISE0 = 0;
    LATEbits.LATE0 = 0;

    // -------------------------
    // Configurar Timer2 a 10 Hz
    // -------------------------
    // Fout = PBCLK / (prescaler * (PR2 + 1))
    // Fout = 20 MHz / (32 * (62499 + 1)) = 10 Hz
    T2CONbits.TCKPS = 5;     // 1:32 prescaler
    PR2 = 62499;             // 10 Hz -> período de 100 ms
    TMR2 = 0;
    T2CONbits.TON = 0;       // Timer começa desligado

    // Interrupções do Timer2
    IPC2bits.T2IP = 2;       // prioridade do Timer2
    IFS0bits.T2IF = 0;       // limpar flag do Timer2
    IEC0bits.T2IE = 1;       // ativar interrupção do Timer2

    // -------------------------
    // Configurar interrupção INT1
    // -------------------------
    INTCONbits.INT1EP = 0;   // interrupção na transição descendente

    IPC1bits.INT1IP = 3;     // prioridade da INT1
    IFS0bits.INT1IF = 0;     // limpar flag da INT1
    IEC0bits.INT1IE = 1;     // ativar interrupção INT1

    EnableInterrupts();

    while(1)
    {
        IdleMode();
    }

    return 0;
}

// Vector Timer2 = 8
void _int_(8) isr_T2(void)bra
{
    cnt++;

    if(cnt == 30)            // 30 * 100 ms = 3000 ms = 3 s
    {
        LATEbits.LATE0 = 0;  // desliga LED0

        T2CONbits.TON = 0;   // para o Timer2
        TMR2 = 0;
        cnt = 0;

        IFS0bits.INT1IF = 0; // limpar possível flag pendente
        IEC0bits.INT1IE = 1; // voltar a aceitar botão
    }

    IFS0bits.T2IF = 0;       // limpar flag do Timer2
}

// Vector INT1 = 7
void _int_(7) isr_INT1(void)
{
    IEC0bits.INT1IE = 0;     // ignora novos pedidos durante os 3 s

    LATEbits.LATE0 = 1;      // liga LED0

    cnt = 0;
    TMR2 = 0;
    IFS0bits.T2IF = 0;
    T2CONbits.TON = 1;       // inicia temporização

    IFS0bits.INT1IF = 0;     // limpar flag da INT1
}
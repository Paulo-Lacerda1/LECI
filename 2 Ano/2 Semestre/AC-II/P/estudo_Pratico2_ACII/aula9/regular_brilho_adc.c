#include <detpic32.h>

/*
Escreva um programa para a placa DETPIC32 que permita controlar o brilho do LED D11 através de um sinal analógico aplicado à entrada AN4.

O programa deve:

Configurar a ADC para ler o canal analógico AN4.
Efetuar 4 amostras por sequência de conversão.
Calcular a média das 4 amostras.
Converter o valor médio da ADC, entre 0 e 1023, num duty-cycle entre 0% e 100%.
Gerar um sinal PWM na saída OC1, usando o Timer T3 como base de tempo.
Configurar o PWM com frequência de 100 Hz.
Atualizar o duty-cycle do PWM automaticamente sempre que houver uma nova conversão ADC.
Usar o Timer T1 para iniciar conversões ADC periodicamente a 20 Hz.
Copiar continuamente o sinal PWM presente em RD0/OC1 para o LED D11 ligado a RC14.

O brilho do LED deve variar em tempo real de acordo com a tensão aplicada em AN4.*/


volatile unsigned int dutyCycle = 0;

void setPWM(unsigned int dutyCycle)
{
    if(dutyCycle > 100)
        dutyCycle = 100;

    OC1RS = ((PR3 + 1) * dutyCycle) / 100;
}

void configADC(void)
{
    TRISBbits.TRISB4 = 1;
    AD1PCFGbits.PCFG4 = 0;

    AD1CHSbits.CH0SA = 4;
    AD1CON2bits.SMPI = 4 - 1;

    AD1CON1bits.SSRC = 7;
    AD1CON1bits.CLRASAM = 1;
    AD1CON3bits.SAMC = 16;

    IPC6bits.AD1IP = 2;
    IFS1bits.AD1IF = 0;
    IEC1bits.AD1IE = 1;

    AD1CON1bits.ON = 1;
}

int main(void)
{
    // PWM Timer T3: 100 Hz
    T3CONbits.TCKPS = 2;
    PR3 = 49999;
    TMR3 = 0;

    OC1CONbits.OCM = 6;
    OC1CONbits.OCTSEL = 1;
    OC1R = 0;
    setPWM(0);

    OC1CONbits.ON = 1;
    T3CONbits.TON = 1;

    TRISCbits.TRISC14 = 0;

    // Timer T1: pedir ADC a 20 Hz
    T1CONbits.TCKPS = 2;   // 1:64
    PR1 = 15624;           // 20 Hz
    TMR1 = 0;

    IPC1bits.T1IP = 3;
    IFS0bits.T1IF = 0;
    IEC0bits.T1IE = 1;

    configADC();

    EnableInterrupts();

    T1CONbits.TON = 1;

    while(1)
    {
        LATCbits.LATC14 = PORTDbits.RD0;
    }

    return 0;
}

void _int_(4) isr_T1(void)
{
    AD1CON1bits.ASAM = 1;
    IFS0bits.T1IF = 0;
}

void _int_(27) isr_adc(void)
{
    int average;

    average = (ADC1BUF0 + ADC1BUF1 + ADC1BUF2 + ADC1BUF3) / 4;

    dutyCycle = (average * 100) / 1023;
    setPWM(dutyCycle);

    IFS1bits.AD1IF = 0;
}
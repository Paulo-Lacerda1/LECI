#include <detpic32.h>

int main(void)
{
    // Configurar RB4 como entrada analógica AN4
    TRISBbits.TRISB4 = 1;        // RB4 como entrada
    AD1PCFGbits.PCFG4 = 0;       // AN4 configurado como analógico

    // Configuração da ADC
    AD1CON1bits.SSRC = 7;        // Conversão automática
    AD1CON1bits.CLRASAM = 1;     // Termina a amostragem quando começa a conversão
    AD1CON3bits.SAMC = 16;       // Tempo de amostragem = 16 TAD
    AD1CON2bits.SMPI = 0;        // Interrupção ao fim de 1 conversão
    AD1CHSbits.CH0SA = 4;        // Seleciona AN4 como entrada

    AD1CON1bits.ON = 1;          // Liga o módulo ADC

    // Configuração das interrupções da ADC
    IPC6bits.AD1IP = 2;          // Prioridade da interrupção ADC
    IFS1bits.AD1IF = 0;          // Limpa a flag da interrupção ADC
    IEC1bits.AD1IE = 1;          // Ativa interrupções da ADC

    EnableInterrupts();          // Ativa interrupções globalmente

    // Dá início à primeira conversão
    AD1CON1bits.ASAM = 1;

    while(1)
    {
        // Todo o processamento é feito na RSI
    }

    return 0;
}

// Rotina de Serviço à Interrupção da ADC
void _int_(27) isr_adc(void)
{
    int value;

    // Ler o resultado da conversão
    value = ADC1BUF0;

    // Imprimir o valor lido
    printInt(value, 10 | 4 << 16);
    putChar('\n');

    // Dar início a uma nova conversão
    AD1CON1bits.ASAM = 1;

    // Limpar a flag da interrupção
    IFS1bits.AD1IF = 0;
}
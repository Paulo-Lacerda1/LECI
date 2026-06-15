#include <detpic32.h>

void putc(char byte2send)
{
    while(U2STAbits.UTXBF==1); // wait while UTXBF == 1 (UxSTA register)
    U2TXREG = byte2send;       // Copy byte2send to the UxTXREG register
}

void _int_(32) isr_uart2(void){
    if(IFS1bits.U2RXIF == 1) {
        char c = U2RXREG;
        if(c!='?'){
            putc(c);
        }
        IFS1bits.U2RXIF = 0;    //limpar a flag
        if(c=='?'){
            printStr("AC2-Guiao 11");
        }
    }
}

int main(void)
{
    /*Guião 10*/

    // Configure UART2: 115200, N, 8, 1 && o fator de divisão 16 ou 4
    U2BRG = ((PBCLK + 8 * 115200) / (16 * 115200)) - 1;
    U2MODEbits.BRGH = 0;     // 16x baud clock

    // Dimensão da palavra a transmitir && tipo de paridade && número de stop bits 
    U2MODEbits.PDSEL = 0;    // 8 bits, no parity
    U2MODEbits.STSEL = 0;    // 1 stop bit
    
    // Módulos de transmissão e receção
    U2STAbits.UTXEN = 1;     // Enable transmitter
    U2STAbits.URXEN = 1;     // Enable receiver

    //Ativar a UART
    U2MODEbits.ON = 1;       // Enable UART2

    /*Guião 11*/


    //Configurações da Interrupção (ver o datashet da UART)
    U2STAbits.URXISEL = 0;      // RX interrupt when FIFO has at least 1 char
    U2STAbits.UTXISEL = 0;      //se as interrupções de escritas tiverem ativas

    // Configure UART2 interrupts
    IEC1bits.U2RXIE = 1;     // Enable UART2 RX interrupts
    IEC1bits.U2TXIE = 0;     // Disable UART2 TX interrupts
    
    //Prioridade
    IPC8bits.U2IP = 2;       // UART2 priority level
    
    //Limpar a flag
    IFS1bits.U2RXIF = 0;     // Clear UART2 RX interrupt flag

    
    
    //Ativar Interrupções
    EnableInterrupts();         // Enable global interrupts

    while(1)
    {
        IdleMode();
    }

    return 0;
}


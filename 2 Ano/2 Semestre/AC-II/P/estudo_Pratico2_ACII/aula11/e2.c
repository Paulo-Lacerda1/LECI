#include <detpic32.h>

typedef struct
{
    char mem[100];
    int nchar;
    int posrd;
} t_buf;

volatile t_buf txbuf;


void putstrInt(char *s)
{
    int i = 0;

    while(txbuf.nchar > 0);   // espera até o buffer estar vazio

    while(s[i] != '\0' && i < 100)
    {
        txbuf.mem[i] = s[i];
        i++;
    }

    txbuf.nchar = i;
    txbuf.posrd = 0;

    IEC1bits.U2TXIE = 1;      // ativa interrupções TX
}

void _int_(32) isr_uart2(void)
{
    if(IFS1bits.U2TXIF == 1)
    {
        if(txbuf.nchar > 0)
        {
            U2TXREG = txbuf.mem[txbuf.posrd];

            txbuf.posrd++;
            txbuf.nchar--;
        }
        else
        {
            IEC1bits.U2TXIE = 0;  // desativa TX interrupt
        }

        IFS1bits.U2TXIF = 0;
    }
}

int main(void)
{
    // Configure UART2: 115200, N, 8, 1
    U2BRG = ((PBCLK + 8 * 115200) / (16 * 115200)) - 1;

    U2MODEbits.BRGH = 0;
    U2MODEbits.PDSEL = 0;
    U2MODEbits.STSEL = 0;

    U2STAbits.UTXEN = 1;
    U2STAbits.URXEN = 1;

    U2MODEbits.ON = 1;

    // UART2 interrupts: RX e TX inicialmente desligadas
    IEC1bits.U2RXIE = 0;
    IEC1bits.U2TXIE = 0;
    

    IPC8bits.U2IP = 2;

    IFS1bits.U2RXIF = 0;
    IFS1bits.U2TXIF = 0;

    U2STAbits.UTXISEL = 0;   // TX interrupt quando há espaço no FIFO

    txbuf.nchar = 0;

    EnableInterrupts();

    while(1)
    {
        putstrInt("WHAT");
    }

    return 0;
}
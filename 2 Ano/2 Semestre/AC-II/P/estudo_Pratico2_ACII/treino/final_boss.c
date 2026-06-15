#include <detpic32.h>

void send2displays(unsigned char value);
unsigned char toBCD(unsigned int val);
void putc(char byte2send);
void putStr(char *str);
void putVoltage(unsigned int v);
void setPWM(unsigned int duty);

volatile unsigned int voltage = 0;      // 0..33 -> 0.0V..3.3V
volatile unsigned int duty_cycle = 0;   // 0..100
volatile int ledCounter = 0;            // 100 ticks = 1s, porque T3 = 100Hz

void config(void)
{
    // Displays
    TRISB &= 0x80FF;
    TRISD &= 0xFF9F;

    // LEDs RE0..RE7
    TRISE &= 0xFF00;
    LATE &= 0xFF00;

    // RC14 para ver PWM copiado de RD0
    TRISCbits.TRISC14 = 0;

    // ADC AN4
    TRISBbits.TRISB4 = 1;
    AD1PCFGbits.PCFG4 = 0;
    AD1CON1bits.SSRC = 7;
    AD1CON1bits.CLRASAM = 1;
    AD1CON3bits.SAMC = 16;
    AD1CON2bits.SMPI = 3;       // 4 conversões
    AD1CHSbits.CH0SA = 4;
    AD1CON1bits.ON = 1;

    // Interrupção ADC
    IPC6bits.AD1IP = 3;
    IFS1bits.AD1IF = 0;
    IEC1bits.AD1IE = 1;

    // Timer3 = 100Hz
    T3CONbits.TCKPS = 2;        // 1:4
    PR3 = 49999;                // 20MHz / (4 * 50000) = 100Hz
    TMR3 = 0;
    IPC3bits.T3IP = 4;
    IFS0bits.T3IF = 0;
    IEC0bits.T3IE = 1;
    T3CONbits.TON = 1;

    // Timer2 = 200Hz para multiplexagem
    T2CONbits.TCKPS = 2;        // 1:4
    PR2 = 24999;                // 20MHz / (4 * 25000) = 200Hz
    TMR2 = 0;
    IPC2bits.T2IP = 2;
    IFS0bits.T2IF = 0;
    IEC0bits.T2IE = 1;
    T2CONbits.TON = 1;

    // PWM OC1 em RD0, base Timer3
    OC1CONbits.OCM = 6;
    OC1CONbits.OCTSEL = 1;      // Timer3
    OC1RS = 0;
    OC1CONbits.ON = 1;

    // UART2: 115200,N,8,1
    U2MODEbits.BRGH = 0;
    U2BRG = 10;
    U2MODEbits.PDSEL = 0;
    U2MODEbits.STSEL = 0;
    U2STAbits.UTXEN = 1;
    U2STAbits.URXEN = 1;

    U2STAbits.URXISEL = 0;
    IPC8bits.U2IP = 1;
    IFS1bits.U2RXIF = 0;
    IEC1bits.U2RXIE = 1;
    IEC1bits.U2TXIE = 0;

    U2MODEbits.ON = 1;

    // INT1 em RD8, transição descendente
    TRISDbits.TRISD8 = 1;
    INTCONbits.INT1EP = 1;
    IPC1bits.INT1IP = 5;
    IFS0bits.INT1IF = 0;
    IEC0bits.INT1IE = 1;
}

void _int_(12) isr_T3(void)
{
    // dispara sequência ADC
    AD1CON1bits.ASAM = 1;

    // temporização dos LEDs
    if(ledCounter > 0)
    {
        ledCounter--;

        if(ledCounter == 0)
            LATE &= 0xFF00;     // apaga RE0..RE7
    }

    IFS0bits.T3IF = 0;
}

void _int_(27) isr_adc(void)
{
    int media;

    media = (ADC1BUF0 + ADC1BUF1 + ADC1BUF2 + ADC1BUF3) / 4;

    voltage = ((media * 33) + 511) / 1023;        // 0..33
    duty_cycle = ((media * 100) + 511) / 1023;    // 0..100

    setPWM(duty_cycle);

    IFS1bits.AD1IF = 0;
}

void _int_(8) isr_T2(void)
{
    send2displays(toBCD(voltage));
    IFS0bits.T2IF = 0;
}

void _int_(7) isr_INT1(void)
{
    LATE |= 0x00FF;       // acende RE0..RE7
    ledCounter = 100;     // 1 segundo a 100Hz

    IFS0bits.INT1IF = 0;
}

void _int_(32) isr_uart2(void)
{
    char c;

    if(IFS1bits.U2RXIF == 1)
    {
        c = U2RXREG;

        if(c == 'V')
        {
            putStr("V=");
            putVoltage(voltage);
            putStr("V\r\n");
        }
        IFS1bits.U2RXIF = 0;
    }
}

int main(void)
{
    config();
    EnableInterrupts();

    while(1)
    {
        // para ver o brilho no LED RC14
        LATCbits.LATC14 = PORTDbits.RD0;
    }

    return 0;
}

// Funcoes Auxiliares

void setPWM(unsigned int duty)
{
    OC1RS = ((PR3 + 1) * duty) / 100;
}

void putVoltage(unsigned int v)
{
    putc((v / 10) + '0');
    putc('.');
    putc((v % 10) + '0');
}

void putc(char byte2send)
{
    while(U2STAbits.UTXBF == 1);
    U2TXREG = byte2send;
}

void putStr(char *str)
{
    while(*str != '\0')
    {
        putc(*str);
        str++;
    }
}

void send2displays(unsigned char value) { 
    const unsigned char disp7Scodes[] = {
        0x3F, 0x06, 0x5B, 0x4F, 0x66, 0x6D, 0x7D, 0x07, 0x7F, 0x6F, // 0 .. 9
        0x77, 0x7C, 0x39, 0x5E, 0x79, 0x71                          // a .. f
    };
    
    static char displayFlag = 0;   // retains value between calls
    
    int dl = value & 0x000F;
    int dh = value >> 4;

    if (displayFlag == 0) {
        // select display low 
        LATD = (LATD & 0xFFBF) | 0x0020;
        // send digit_low (dl) to display: 
        LATB = (LATB & 0x80FF) | (disp7Scodes[dl] << 8); 
    } else {
        // select display high 
        LATD = (LATD & 0xFF9F) | 0x0040;
        // send digit_high (dh) to display: 
        LATB = (LATB & 0x80FF) | (disp7Scodes[dh] << 8); 
    }

    displayFlag = !displayFlag; // this alternates the display for the next call
}

unsigned char toBCD(unsigned int val)
{
    return ((val / 10) << 4) | (val % 10);
}

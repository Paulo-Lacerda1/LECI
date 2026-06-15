#include <detpic32.h>

void send2displays(unsigned char value);
void delay(unsigned int ms);
int toBCD(unsigned int value);

volatile int temperatura = 0;

void config(){

    TRISB &= 0x80FF; // segmentos
    TRISDbits.TRISD5=0;
    TRISDbits.TRISD6=0;


    TRISBbits.TRISB4 = 1;
    AD1PCFGbits.PCFG4= 0;
    AD1CON1bits.SSRC = 7;
    AD1CON1bits.CLRASAM = 1; 
    AD1CON3bits.SAMC = 16;
    AD1CON2bits.SMPI = 1; 
    AD1CHSbits.CH0SA = 4;
    AD1CON1bits.ON = 1;

    T3CONbits.TCKPS = 2; // 1:32 prescaler (i.e. fout_presc = 625 KHz)
    PR3 = 35714;
    TMR3 = 0;
    T3CONbits.TON = 1;
    
    IPC3bits.T3IP = 2;
    IEC0bits.T3IE = 1;
    IFS0bits.T3IF = 0;


            
}


int main(void){
    config();
    EnableInterrupts();

    while(1){
    AD1CON1bits.ASAM = 1;
    while( IFS1bits.AD1IF == 0 );
    delay(200);

    int media = (ADC1BUF0+ADC1BUF1) /2;
    temperatura = 10 + (((media*65) + 511) / 1023); 
    IFS1bits.AD1IF = 0;
    
    }
    return 0;
}


void delay(unsigned int ms){
    resetCoreTimer();
    while(readCoreTimer()<20000*ms);
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

// Interrupt service routine (interrupt handler)
void _int_(12) isr_t3(void)
{
    send2displays(toBCD(temperatura));
    IFS0bits.T3IF = 0;

}

int toBCD(unsigned int value){
    return (((value / 10) << 4) | value %10);
}

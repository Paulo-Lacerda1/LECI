#include <detpic32.h>

volatile int contador=0;

int tobcd(unsigned int value);
void send2displays(unsigned char value);

void config(){

    TRISB &= 0x80FF;    //segmentos
    TRISDbits.TRISD6=0;
    TRISDbits.TRISD5=0;

    TRISE = (TRISE & 0xFF00); //leds

    //Timer 1
    T1CONbits.TCKPS = 3;   // 1:256
    PR1 = 39062;           // 20MHz / (256 * (39062 + 1)) ≈ 2 Hz
    TMR1 = 0;
    T1CONbits.TON = 1;

    IPC1bits.T1IP = 2;
    IEC0bits.T1IE = 1;
    IFS0bits.T1IF = 0;

    //Timer2 100HZ
    T2CONbits.TCKPS = 2; 
    PR2 = 49999;          
    TMR2 = 0;
    T2CONbits.TON = 1;

    IPC2bits.T2IP = 1;
    IEC0bits.T2IE = 1;
    IFS0bits.T2IF = 0;


}

void _int_(8) isr_timer2(void)
{
    send2displays(tobcd(contador));         //segmentos
    LATE = ((LATE & 0xFF00) | contador);    //displays
    IFS0bits.T2IF = 0;
}

void _int_(4) isr_timer1(void)
{
    static int cnt = 0;
    cnt++;
    if(cnt==2){                         //fica a 1 hz, pq o timer está gerar interrupcoes com 2Hz
        contador = (contador+1) % 60;
        cnt=0;
    }
    
    IFS0bits.T1IF = 0;
}

int tobcd(unsigned int value){
    return (value/10)<<4 | (value %10);
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


int main(void){
    
    config();
    EnableInterrupts();

    while(1){
        IdleMode();
    }

}

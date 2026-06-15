#include <detpic32.h>

volatile int count=0;

void config(){
    
    TRISEbits.TRISE2=0;
    TRISEbits.TRISE3=0;
    TRISEbits.TRISE4=0;
    TRISEbits.TRISE5=0;

    U2BRG=32;
    U2MODEbits.BRGH=0;
    U2MODEbits.PDSEL= 1;
    U2MODEbits.STSEL= 1;

    U2STAbits.UTXEN=1;
    U2STAbits.URXEN=1;
    
    
    U2STAbits.URXISEL=0; 
    U2STAbits.UTXISEL=0;

    IFS1bits.U2RXIF=0;
    IEC1bits.U2RXIE=1;
    IPC8bits.U2IP=2;

    U2MODEbits.ON = 1;

}

void putc(char byte2send)
{
    while(U2STAbits.UTXBF==1); // wait while UTXBF == 1 (UxSTA register)
    U2TXREG = byte2send;       // Copy byte2send to the UxTXREG register
}

void putStr(char *string){
    while(*string != '\0'){
        putc(*string);
        string++;
    }
}


void _int_(32) isr_uar2(void)
{
    if(IFS1bits.U2RXIF){
        char c = U2RXREG;
        if(c=='D'){
            count = (count + 15) % 16;
            LATE = (LATE & 0xFF03) | (count << 2);   // RE5..RE2

        } 

        if(c=='R'){
            count=15;
            LATE = (LATE & 0xFF03) | (count << 2);   // RE5..RE2
            putStr("MAXIMO");
        }
    }
    
    IFS1bits.U2RXIF=0; // Reset UxRXIF flag
}


int main(void){

    config();
    EnableInterrupts();

    while(1){
        IdleMode();
    }
    return 0;   

}

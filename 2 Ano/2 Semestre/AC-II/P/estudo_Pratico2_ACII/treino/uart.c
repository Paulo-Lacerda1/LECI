#include <detpic32.h>

void config(){

    U2MODEbits.BRGH =0;
    U2BRG = 10;
    U2MODEbits.PDSEL= 0;
    U2MODEbits.STSEL= 0;

    U2STAbits.UTXEN =1;
    U2STAbits.URXEN =1;
    U2MODEbits.ON=1;

    U2STAbits.UTXISEL=0;
    U2STAbits.URXISEL=1;
    U2STAbits.URXISEL = 0;

    IEC1bits.U2TXIE = 0;
    IEC1bits.U2RXIE=1;

    IPC8bits.U2IP=2;
    IFS1bits.U2RXIF=0;
    
    TRISCbits.TRISC14=0; 
}

void putc(char byte2send)
{
    while(U2STAbits.UTXBF==1);// wait while UTXBF == 1 (UxSTA register)
    U2TXREG = byte2send;// Copy byte2send to the UxTXREG register
}


void putStr(char *string){
    while(((*string) != '\0')){ 
        putc(*string);
        string++;
    }
}

void _int_(32) isr_uart2(void){     //receção

    if(IFS1bits.U2RXIF){
        char c = U2RXREG;
        if(c=='T'){
            LATCbits.LATC14=1; //acende
        }
        if(c=='t'){
            LATCbits.LATC14=0; //apaga
        }
        if(c=='?'){
            putStr("AC2-Exame");
        }
    }
    IFS1bits.U2RXIF = 0; //reset da flag
}

int main(void){

    config();
    EnableInterrupts();

    while(1){
        IdleMode();
    }
    return 0;   

}


unsigned char toBcd(unsigned char value) 
{
    return ((value / 10) << 4) + (value % 10);
}
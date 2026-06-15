#include <detpic32.h>

#define SPEED 16
#define BAUDRATE 9600

void putc(char value2send){
	while(U2STAbits.UTXBF==1);
	U2TXREG = value2send;
}

void putStr(char* s){
	while(*s != '\0'){
		putc(*s);
		s++;
	}
}

unsigned char toBCD(int val){
	return ((val/10)<<4) | (val%10);
}

void configUART(){
	
	U2BRG = (PBCLK + (SPEED/2)*BAUDRATE) / (SPEED*BAUDRATE) - 1;
	
	U2MODEbits.BRGH = 0;
	U2MODEbits.PDSEL = 2;
	U2MODEbits.STSEL = 1;

	U2STAbits.UTXEN = 1;
	U2STAbits.URXEN = 1;

	U2MODEbits.ON = 1;

	IEC1bits.U2RXIE = 1;
	IEC1bits.U2TXIE = 0;

	IPC8bits.U2IP = 2;

	IFS1bits.U2RXIF = 0;

	IEC1bits.U2EIE=1;

	U2STAbits.URXISEL = 0;
}



void _int_(32) isr_UART2(){
	if(IFS1bits.U2RXIF){
		char c = U2RXREG;
		LATEbits.LATE7 = !LATEbits.LATE7;
		if(c == 'D'){ 
			int valueOnSwitch = (PORTB & 0xF); 
											               
			char val = toBCD(valueOnSwitch);
			putStr("DSD=");

			putc('0' + (val>>4)); // Dezenas
			putc('0' + (val & 0xF)); // Unidades
	
		}
		putc(c);
		IFS1bits.U2RXIF=0;
	}
	
}


int main(void){
	configUART();

	TRISEbits.TRISE7 = 0; // led as output
	LATEbits.LATE7 = 0;

	TRISBbits.TRISB0 = 1;
	TRISBbits.TRISB1 = 1;
	TRISBbits.TRISB2 = 1;
	TRISBbits.TRISB3 = 1;


	EnableInterrupts();


	while(1){
		IdleMode();
	};

	return 0;
}

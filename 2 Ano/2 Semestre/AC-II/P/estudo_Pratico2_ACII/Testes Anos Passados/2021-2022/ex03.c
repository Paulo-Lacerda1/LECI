#include <detpic32.h>

#define BAUDRATE 9600
#define SPEED 16


/*

1. Parâmetros de comunicação : 9600 BPS, ODD parity, 8 data bits, 
2 stop bits

2. Quando for recebido o caractér "U" deve ser incrementado um contador
módulo 16 e o resultado deve ser mostrado nos 4 LEDs ligados aos portos
RE4 a RE1; o valor inicial do contador deve ser o valor máximo 

3. Quando for recebido o caracter 'R' o valor do contador deve ser reposto
com o seu valor minimo, o resultado deve ser mostrado nos 4 LEDs e deve
ser transmitida a string "RESET";

! O processamento de receção de um caracter deve ser feito, obrigatoriamente,
! por interupção. O processamento de transmissão deve ser feito por polling.
*/

unsigned int counter = 15;

void putc(char byte){
	while(U2STAbits.UTXBF);
	U2TXREG = byte;
}

void putstr(char* str){
	while(*str != '\0'){
		putc(*str);
		str++;
	}
}


void configUART(void){
	U2BRG = (PBCLK + (SPEED/2) * BAUDRATE) / (SPEED * BAUDRATE) - 1;
	U2MODEbits.BRGH = 0; 			// 0 = speed 16; 1 = speed 4
	U2MODEbits.PDSEL = 2; 			// '10' = Odd parity, 8 data bits
	U2MODEbits.STSEL = 1;			//  2 stop bits

	U2STAbits.UTXEN = 1;			// enable Transmitt module
	U2STAbits.URXEN = 1;			// enable Receive Module 
		
	U2MODEbits.ON = 1;			// turn on UART

	IEC1bits.U2RXIE = 1;			// interrupt enable
	IEC1bits.U2TXIE = 0;			// interrupt disable on transmitter

	IPC8bits.U2IP = 2;			// priority levl UART

	IFS1bits.U2RXIF = 0;			// reset da flag de interrupt

	U2STAbits.URXISEL = 0;
}


void config(){
	// 1111 1111 1110 0001
	TRISE &= 0xFFE1; 
}



void _int_(32) isr_uart(void){

	if(IFS1bits.U2RXIF){
		char c = U2RXREG;
		if(c == 'U') counter = (counter + 1) % 16;

		if(c == 'R'){
			counter = 0;
			putstr("RESET");
		}
		LATE = (LATE & 0xFFE1) | counter << 1;
		IFS1bits.U2RXIF = 0;
	}
}

int main(void){
	config();
	configUART();
	EnableInterrupts();

	while(1){
		IdleMode();
	}

	return 0;
}


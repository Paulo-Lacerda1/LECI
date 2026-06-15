#include <detpic32.h>

void delay(unsigned int ms){
	resetCoreTimer();
	while (readCoreTimer() < 20000 * ms);
}

/**
 * Converte um valor decimal (0..99) para BCD de 8 bits
 * Exemplo: decimal 45 => BCD 0x45
 */
unsigned char toBCD(unsigned char value){
	return ((value / 10) << 4) + (value % 10);
}


void configure(void){
	// Define RD5 and RD6 as OUTPUT
	TRISDbits.TRISD5 = 0;
	TRISDbits.TRISD6 = 0;

	// Define RE0-7 as OUTPUT (LEDS)
	TRISE = TRISE & 0xFF00;

	// Define RB8 - RB14 as OUTPUTS (DISPLAYS)
	TRISB = (TRISB & 0x80FF);

	TRISBbits.TRISB0 = 1;
}

void send2displays(unsigned char value){
	static const char disp7Scodes[] = {
	    // 432 1098
	    0x3F, // 011 1111 = '0'
	    0x06, // 000 0110 = '1'
	    0x5B, // 101 1011 = '2'
	    0x4F, // 100 1111 = '3'
	    0x66, // 110 0110 = '4'
	    0x6D, // 110 1101 = '5'
	    0x7D, // 111 1101 = '6'
	    0x07, // 000 0111 = '7'
	    0x7F, // 111 1111 = '8'
	    0x6F, // 110 1111 = '9'
	    0x77, // 111 0111 = 'A'
	    0x7C, // 111 1100 = 'b'
	    0x39, // 011 1001 = 'C'
	    0x5E, // 101 1110 = 'd'
	    0x79, // 111 1001 = 'E'
	    0x71, // 111 0001 = 'F'
	};
	unsigned int dh, dl;
	static char displayFlag = 0;

	dh = (value >> 4);     // isolate digit high	
	dl = (value & 0x000F); // isolate digit low

	if (displayFlag == 1){
		LATDbits.LATD6 = 1; // Select display high;
		LATDbits.LATD5 = 0;
		LATB = (LATB & 0x80FF) | (disp7Scodes[dh] << 8); // Send digit_high
		displayFlag = 0;
	}else{
		LATDbits.LATD6 = 0; // Select display low;
		LATDbits.LATD5 = 1;
		LATB = (LATB & 0x80FF) | (disp7Scodes[dl] << 8); // send digit_low
		displayFlag = 1;
	}
	LATE = (LATE & 0xFF00) | toBCD(value);
}

int main(void){
	configure();
	unsigned int tempo;
	unsigned int counter = 0;
	while (1)
	{
		if (PORTBbits.RB0==1){
			tempo = 10;		//50HZ
		} else {
			tempo=1;
		}
		
		unsigned int i = 0;
		do{
			send2displays(toBCD(counter));
			delay(tempo); //10ms
		} while (++i < 100); //10hz
		counter = (counter + 1) % 50;
	}

	return 0;
}




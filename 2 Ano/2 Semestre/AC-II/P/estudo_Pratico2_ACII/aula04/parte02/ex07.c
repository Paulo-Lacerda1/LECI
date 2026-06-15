#include <detpic32.h>

void delay(unsigned int ms){
	resetCoreTimer();
	while (readCoreTimer() < 20000 * ms);
}


int main(void){	
	TRISD = TRISD & 0xFF9F; // RD5 - RD6 as OUTPUTS
	LATD = (LATD & 0xFF9F) | 0x0020; // RD5 as 1, RD6 as 0 (0010 0000)
	TRISB = ( TRISB & 0x80FF) | 0x000F; // RB8 - RB14 as OUTPUTS and RB0-3 as INPUTS

	static const char disp7Scodes[] = {
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


	while(1){
		// 0000 0000 0000 1111 
		unsigned int val = PORTB & 0x000F;
		unsigned int code = disp7Scodes[val];
		LATB = (LATB & 0x80FF) | code << 8;
	}

	return 0;
}


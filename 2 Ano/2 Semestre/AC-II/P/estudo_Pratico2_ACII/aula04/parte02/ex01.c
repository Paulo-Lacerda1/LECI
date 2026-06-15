#include <detpic32.h>

int main(void){
	TRISB = TRISB & 0x80FF; //Configure RD8 to RB14 as OUTPUT
	TRISD = TRISD & 0xFF9F; //Configure RD5 e RD6 as OUTPUT

	LATDbits.LATD5 = 0;
	LATDbits.LATD6 = 1;

	char ch;
	while(1){
		ch = getChar();	
		// 80FF  == 1000 0000 1111 1111
		if(ch == 'a') LATB = (LATB & 0x80FF) | 0x0100; 	// (0x0100) = 1 << 8
		if(ch == 'b') LATB = (LATB & 0x80FF) | 0x0200; 	// (0x0200) = 1 << 9	
		if(ch == 'c') LATB = (LATB & 0x80FF) | 0x0400;  		
		if(ch == 'd') LATB = (LATB & 0x80FF) | 0x0800;  
		if(ch == 'e') LATB = (LATB & 0x80FF) | 0x1000;  
		if(ch == 'f') LATB = (LATB & 0x80FF) | 0x2000;  
		if(ch == 'g') LATB = (LATB & 0x80FF) | 0x4000;  

	}
	return 0;
}
#include <detpic32.h>

int main(void){	
	TRISB = TRISB & 0x80FF;
	TRISD = TRISD & 0xFF9F;

	LATDbits.LATD6 = 1;
	LATDbits.LATD5 = 0;
	char ch;
	while(1){
		ch = getChar();
		if(ch >= 'a' && ch <= 'g'){
			ch = ch - 'a'; 	
			LATB = (LATB & 0x80FF) | 1 << (ch + 8);
		}
	}
	return 0;
}

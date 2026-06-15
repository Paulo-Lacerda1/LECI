#include <detpic32.h>

void delay(unsigned int ms){
	resetCoreTimer();
	while(readCoreTimer() < 20000 * ms);
}

int main(void){
	unsigned int segment;
	TRISB = TRISB & 0x80FF; // RB8-14 as OUTPUTs
	TRISD = TRISD & 0xFF9F; // RD5-6 as OUTPUT

	LATDbits.LATD5 = 1;
	LATDbits.LATD6 = 0;
	while(1){
		segment = 0x0100;
		unsigned int i;
		for(i=0; i < 7; i++){
			LATB = (LATB & 0x80FF) | segment;
			// 500ms == 2Hz
			delay(500);
			segment = segment << 1;
		}
		LATDbits.LATD5 = !LATDbits.LATD5;
		LATDbits.LATD6 = !LATDbits.LATD6;
	}
	return 0;
}

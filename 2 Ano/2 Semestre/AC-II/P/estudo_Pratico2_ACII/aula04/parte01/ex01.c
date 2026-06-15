#include <detpic32.h>

void delay(int ms){
	resetCoreTimer();
	while(readCoreTimer() < 20000 * ms);
}

int main (void){
	TRISCbits.TRISC14 = 0; 	// Configure RC14 as OUTPUT
	LATCbits.LATC14 = 0;
	while(1){
		delay(500); // wait 0.5s
		LATCbits.LATC14 = !LATCbits.LATC14;
	}
	return 0;
}

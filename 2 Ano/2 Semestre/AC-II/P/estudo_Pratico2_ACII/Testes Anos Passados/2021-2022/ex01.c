#include <detpic32.h>


/*

!!! f_out = 150Hz

f_pre_scl = PBCLK / k 

f_out = PBCLK / (PRx + 1)*k


1º Determinar k
150 = 20000000/(65536)*k = 2,0345052083
!! <=> k = 4

2º Determinar PR2
!! f_pre_scl = 5 000 000

150 = 5 000 000/(PR2 + 1)
PR2 + 1 = 33 334
!! PR2 = 33 333


33 333 ---- 100%
OC2	 ---- 25%

!! OC2 = (25*33333)/100


250 us = 4000 Hz
*/


void setPWM(unsigned int dutyCycleVal){
	if(dutyCycleVal > 100) return;
	OC2RS = (dutyCycleVal * (PR2 + 1) + 50) / 100;
	printInt(OC2RS, 10 | 4 << 16);
}

void delay(unsigned int val){
	resetCoreTimer();
	while(readCoreTimer() < val);
}

void configT2(){
	T2CONbits.TCKPS = 2;
	PR2 = 33333;
	TMR2 = 0;
	T2CONbits.TON = 1;

	OC2CONbits.OCM = 6;
	OC2CONbits.OCTSEL = 0;
	setPWM(25);			// initial value set to 25%
	OC2CONbits.ON = 1;
}

void config(){
	TRISBbits.TRISB0 = 1;
	TRISBbits.TRISB3 = 1;
}

int main(void){
	configT2();
	config();	

	while(1){
		if(PORTBbits.RB3 == 0 && PORTBbits.RB1 == 1) setPWM(25);
		if(PORTBbits.RB3 == 1 && PORTBbits.RB1 == 0) setPWM(70);
		delay(5000);	// 20 000 000 / 4000
	}

	return 0;
}


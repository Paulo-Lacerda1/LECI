#include <detpic32.h>

/*
T3 - Tipo B - [1, 2, 4, 8, 16, 32, 64, 256]

f_out = 120Hz
f_out = f_pre_scaler / (PRx + 1)

f_pre_scaler = 20 000 000 / k


! Determinar o valor de k
120 = 20 000 000 / (65536)*k
k = 20 000 000/ 65536*120
k = 2,5431315104
k = 4

! Determinar o valor correto de PRx
f_pre_scaler = 20 000 000 / 4 = 5 000 000

120 = 5 000 000 / (PRx+1)
PRx + 1 = 5 000 000 /120
Prx + 1= 41 666,6666666667
Prx + 1= 41 667
Prx = 41 666

! Calcular OC2RS
41 667 --- 100%
OC2RS	 --- dutycycle


*/

void delay(unsigned int val){
	resetCoreTimer();
	while(readCoreTimer() < val);
}

void setPWM(unsigned int dutycycle){
	if(dutycycle > 100) return;
	OC2RS =((PR3 * dutycycle) + 50)/100;
}

void configT3(){
	T3CONbits.TCKPS = 2;
	PR3 = 41666;
	TMR3 = 0;
	T3CONbits.TON = 1;

	OC2CONbits.OCM = 6; 
	OC2CONbits.OCTSEL = 1;
	setPWM(75);
	OC2CONbits.ON = 1;
}


int main(void){
	configT3();
	TRISBbits.TRISB0 = 1;
	TRISBbits.TRISB2 = 1;
	while(1){
		if(PORTBbits.RB2 == 1 && PORTBbits.RB2 == 1) setPWM(55);
		if(PORTBbits.RB2 == 0 && PORTBbits.RB0 == 0) setPWM(30);
		delay(7199);
	};


	return 0;
}
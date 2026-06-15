#include <detpic32.h>


/*
Timer 3 Tipo B
[1, 2, 4, 8, 16, 32, 64, 256]


f_out = 130Hz
f_pre_scaler = 20 000 000/k

f_out = f_pre_scaler / (PRx + 1);
f_out = 20 000 000 / (k*(PRx+1))


! 1º Determinar o valor de k
130 = 20 000 000 / k*65536
k = 20 0000 000 / 8 519 680
k = 2,3475060096
k = 4

! 2º Determianr o valor real de PRx
f_pre_scaler = 20 000 000 / 4 = 5 000 000
130 = 5 000 000 / (PRx + 1)
PRx + 1 = 38 461,5384615385
PRx = 38 461

! 3º Determianr o OC

38 461 --- 100%
OC4	--- PWWMval

*/

void delay(unsigned int val){
	resetCoreTimer();
	while(readCoreTimer() < val);
}

void setPWM(unsigned int val){
	if(val > 100) return;
	OC4RS = (((PR3+1) * val) + 50) / 100;
}

void configT3(){
	T3CONbits.TCKPS = 2;
	PR3 = 38461;
	TMR3 = 0;
	T3CONbits.TON = 1;

	OC4CONbits.OCM = 6;
	OC4CONbits.OCTSEL = 1;

	OC4CONbits.ON = 1;
	setPWM(50);
}

int main(void){
	configT3();
	TRISBbits.TRISB1 = 1;

	int flag = 0;
	while(1){
		if(PORTBbits.RB1 == 0){
			if(flag){ 
				setPWM(25);
				delay(26000000);
			}

		} 
		if(PORTBbits.RB1 == 0){
			if(flag == 0){
				setPWM(75);
				delay(26000000);
			}
		} 
		flag ^= 1;
		
		
	}

	return 0;
}

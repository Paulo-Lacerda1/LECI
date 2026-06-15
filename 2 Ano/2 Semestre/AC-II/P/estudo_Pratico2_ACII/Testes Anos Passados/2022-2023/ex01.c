#include <detpic32.h>

/*

Escreva e teste um programa que faça todas as configurações
necessária e que gere na saída OC2 do PIC32 um sinal com uma
frequência de 120Hz e Duty-Cycle dependente do estado dos switches
DS3 e DS1 (ligados aos portos RB2 e RB0, respetivamente) de acordo com
a especificação seguinte (valor inicial duty-cycle 75%)

	RB2: 0, RB0: 0 - 30%
	RB2: 1, RB0; 1 - 55%
	Restantes combinações - mantém o valor de "duty-cycle" anterior

O programa deve verificar a cada 360us, o valor presente nos "switches"
e alterar o "duty-cycle" do sinal de saída, em conformidade (para esta
temporização deve ser usado o Core Timer).
Deve ser usado o T3 como referência e a sua configuração deve permitir
a geração do sinal PWM com a máxima resolução possível


f_out = 120Hz
f_pre_scaler = 20 000 000 / k

1º Determinar o valor de K
f_out = 20 000 000 / (PR3+1)*k
120 = 20 000 000 / ((65536)*k)
k = 20 000 000 / (65536*120)
k = 2,5431315104
k = 4

2º Determinar o valor de PR3
f_pre_scl = 20 000 000 / 4 = 5 000 000

120 = 5 000 000/ (PR3+1)
PR3+1 = 5 000 000/ 120
PR3+1 = 41 666,6666666667
PR3+1 = 41 667
PR3 = 41 666


3º

41 667 --- 100%
OC2RS --- dutyCycleVal

OC2RS = ((dutyCycleVal * 41667) + 50) / 100

*/

void delay(unsigned int val){
	resetCoreTimer();
	while(readCoreTimer() < val);
}

void setPWM(unsigned int dutyCycleVal){
	if(dutyCycleVal > 100) return;
	OC2RS = ((dutyCycleVal * 41667) + 50) / 100;
}


void configT3(){
	// Timer Tipo B = [1, 2, 4, 8, 16, 32, 64, 256]
	T3CONbits.TCKPS = 2;
	PR3 = 41666;
	TMR3 = 0;
	T3CONbits.TON = 1;

	OC2CONbits.OCM = 6;
	OC2CONbits.OCTSEL = 1; // Use timer3
	setPWM(75);
	OC2CONbits.ON = 1;
}



int main(void){
	configT3();
	TRISBbits.TRISB2 = 1;	// RB2 as INPUT
	TRISBbits.TRISB0 = 1;	// RB0 as INPUT

	while(1){
		if(PORTBbits.RB2 == 0 && PORTBbits.RB0 == 0){
			setPWM(30);
		}

		if(PORTBbits.RB2 == 1 && PORTBbits.RB0 == 1){
			setPWM(55);
		}

		delay(7200);	// 360Us = 2 777,7777777778 Hz
	}

	return 0;
}

 

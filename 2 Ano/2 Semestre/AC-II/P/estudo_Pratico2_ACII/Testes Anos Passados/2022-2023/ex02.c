#include <detpic32.h>

/*
Consideree que a gama de tensão na entrada AN4/RB4 da ADC(0 a 3.3V)
da placa DETPIC32 representa um valor de temperatura na gama 10ºC
a 75ªC. Escreva e teste um programa que realize todas as configurações 
necessárias e que apresente continuamente o valor da temperatura nos dois
displays de sete segmentos, em decimal.

Especificações:

1. A ADC deve fazer duas (2) conversões por cada amostra
2. A frequência de amostragem daADC deve ser 5Hz, obtida 
através da utlização do Core Timer
3. O fim de conversão da ADC deve ser processada por polling
4. A frequência de refrescamento dos displays deve ser 140Hz
o refrescamento dos displays deve ser feito por interrupção, usando
o timer T3.



* f_out = 140Hz
* f_out = 20 000 000 / ((PR3 + 1)*k) <=> f_out = f_pre_scaler / (PR3 + 1);
* f_pre_scaler = 20 000 000 / k
* Timer Tipo B = [1, 2, 4, 8, 16, 32, 64, 256]


? Determinar o valor de k 
140 = 20 000 000/ (65536)*k
k = 2,1798270089
logo k = 4

? Determinar o valor real de PR3
f_pre_scaler = 20 000 000/4 = 5 000 000
140 = 5 000 000 / (PR3+1)
PR3+1 = 35 714
PR3 = 35 713


*/

//!
//! NEEDS TO BE CHECKED IF CORRECT
//!


char toBCD(unsigned int val){
	return ((val / 10) << 4) | (val % 10);
}

void delay(unsigned int val){
	resetCoreTimer();
	while(readCoreTimer() < val);
}

void send2displays(unsigned char val){
	static const char disp7segment[] = {0x3F, 0x06, 0x5B, 0x4F, 0x66, 0x6D, 0x7D, 0x07, 0x7F, 0x6F, 0x77, 0x7C, 0x39, 0x5E, 0x79, 0x71};
	static int flag;

	char dl = disp7segment[val & 0xF];
	char dh = disp7segment[val >> 4];

	if(flag){
		LATEbits.LATE6 = 1; // Turn on most sign display
		LATEbits.LATE5 = 0;
		LATB = (LATB & 0x80FF) | dh << 8;
	} else {
		LATEbits.LATE6 = 0; // Turn off most sign display
		LATEbits.LATE5 = 1;
		LATB = (LATB & 0x80FF) | dl << 8;
	}
	flag = !flag;
}


void config(){
	TRISB &= 0x80FF;		// Segmentos dos displays como outpht
	TRISDbits.TRISD6 = 0;	// DH as out
	TRISDbits.TRISD5 = 0;	// DL as out
}

void configADC(){
	TRISBbits.TRISB4 = 1;
	AD1PCFGbits.PCFG4 = 0;
	AD1CON1bits.SSRC = 7;
	AD1CON1bits.CLRASAM = 1;
	AD1CON3bits.SAMC = 16;
	AD1CON2bits.SMPI = 2-1;		// 1)
	AD1CHSbits.CH0SA = 4;
	AD1CON1bits.ON = 1;
}


void configT3(){
	T3CONbits.TCKPS = 2;
	PR3 = 35713;
	TMR3 = 0;
	T3CONbits.TON = 1;

	IEC0bits.T3IE = 1;		// interrupt enable
	IPC3bits.T3IP = 2;
	IFS0bits.T3IF = 0;

}


void _int_(12) asr_T3(){

	int average = (ADC1BUF0 + ADC1BUF1) / 2;
	int scaled = ((average * (75 - 10) + 511) / 1023) + 10;
	send2displays(toBCD(scaled));		// 4)
	IFS0bits.T3IF = 0;
}

int main(void){
	config();
	configADC();
	configT3();

	EnableInterrupts();

	while(1){

		AD1CON1bits.ASAM = 1;

		// 3)
		while(IFS1bits.AD1IF); 
		IFS1bits.AD1IF = 0; 		// reset interrupt flag 

		// 2)
		delay(4000000); // 20 000 000 / 5
	}

	return 0;
}
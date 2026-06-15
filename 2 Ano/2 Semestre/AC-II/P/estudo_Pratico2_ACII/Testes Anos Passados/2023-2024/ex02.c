#include <detpic32.h>

/*
Timer 3, tipo B [1, 2, 4, 8, 16, 32, 64, 256]

f_out = 250Hz
f_pre_scaler = 20 000 000 / k
f_out = 20 000 000 / (PRx+1)*k;


1º Determianr o valor de k
250 = 20 000 000 / 65536*k
k = 1,220703125
k = 2;

2º Determinar o valor de PRx
f_pre_scaler = 20 000 000 / 2 = 10 000 000

250 = 10 000 000/(PRx+1)
PRx+1 = 40 000
PRx = 39 999;
*/

volatile int scaledValue;

void delay(unsigned int val){
	resetCoreTimer();
	while(readCoreTimer() < val);
}

void configADC(){
	TRISBbits.TRISB4 = 1;
	AD1PCFGbits.PCFG4 = 0;
	AD1CON1bits.SSRC = 7;
	AD1CON1bits.CLRASAM = 1;
	AD1CON3bits.SAMC = 16;
	AD1CON2bits.SMPI = 2-1;
	AD1CHSbits.CH0SA = 4;
	AD1CON1bits.ON = 1;
}


char toBCD(unsigned int val){
	return ((val / 10) << 4) | (val % 10);
}

void send2displays(unsigned char val){
	static const char disp7segment[] = {0x3F, 0x06, 0x5B, 0x4F, 0x66, 0x6D, 0x7D, 0x07, 0x7F, 0x6F, 0x77, 0x7C, 0x39, 0x5E, 0x79, 0x71};

	static int flag = 0;
	unsigned char dh = disp7segment[val >> 4];
	unsigned char dl = disp7segment[val & 0xF];

	if(flag){
		LATDbits.LATD6 = 1;
		LATDbits.LATD5 = 0;
		LATB = (LATB & 0x80FF) | dh << 8;	
		flag = 0;	
	} else {
		LATDbits.LATD6 = 0;
		LATDbits.LATD5 = 1;
		LATB = (LATB & 0x80FF) | dl << 8;
		flag = 1;	
	}
	
}


void configT3(){
	T3CONbits.TCKPS = 1;
	PR3 = 39999;
	TMR3 = 0;
	T3CONbits.TON = 1;

	IPC3bits.T3IP = 2;
	IEC0bits.T3IE = 1;
	IFS0bits.T3IF = 0;

}

void _int_(12) isr_T3(){
	send2displays(toBCD(scaledValue));
	IFS0bits.T3IF = 0;
}

int main(void){
	configT3();
	configADC();

	EnableInterrupts();

	TRISB &= 0x80FF;
	TRISDbits.TRISD6 = 0;	//display high
	TRISDbits.TRISD5 = 0;	// display low

	while(1){
		AD1CON1bits.ASAM = 1;
		while(IFS1bits.AD1IF == 0);

		int average = (ADC1BUF0 + ADC1BUF1)/2;
		scaledValue = (((average)*(73-7)+511)/1023)+7;

		IFS1bits.AD1IF = 0;
		delay(4000000);
	}

	return 0;
}

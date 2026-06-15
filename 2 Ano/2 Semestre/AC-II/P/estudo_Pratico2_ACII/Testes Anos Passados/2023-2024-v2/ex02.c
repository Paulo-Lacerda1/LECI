#include <detpic32.h>

/*

f_out = 140Hz
f_out = f_prescaler / (PRx+1)
f_pre_scaler = 20 000 000 / k

! Determinar valor de k
140 = 20 000 000 / 65536*k
k = 2,1798270089
k = 4

! Determinar o valor real de PRx
f_pre_scalaer = 5 000 000

140 = 5 000 000 / (PRx+1)
PRx+1 = 35 714,2857142857
PRx+1 = 35 714
PRx = 35 713
*/

volatile unsigned int scaledVal;


char toBCD(unsigned int val){
	return ((val / 10) << 4) | (val % 10);
}


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

void configT3(){
	T3CONbits.TCKPS = 2;
	PR3 = 35713;
	TMR3 = 0;
	T3CONbits.TON = 1;

	IPC3bits.T3IP = 2;
	IEC0bits.T3IE = 1;
	IFS0bits.T3IF = 0;
}

void send2displays(unsigned int val){
	static const char disp7segment[] = {0x3F, 0x06, 0x5B, 0x4F, 0x66, 0x6D, 0x7D, 0x07, 0x7F, 0x6F, 0x77, 0x7C, 0x39, 0x5E, 0x79, 0x71};
	static int flag;
	char dh = disp7segment[val >> 4];
	char dl = disp7segment[val & 0xF];

	if(flag){
		LATDbits.LATD6 = 1;
		LATDbits.LATD5 = 0;
		LATB = (LATB & 0x80FF) | (dh << 8);
	} else {
		LATDbits.LATD6 = 0;
		LATDbits.LATD5 = 1;
		LATB = (LATB & 0x80FF) | (dl << 8);
	}
	flag ^= 1;
}


void _int_(12) isr_t3(){
	send2displays(toBCD(scaledVal));
	IFS0bits.T3IF = 0;
}

int main(void){
	configADC();
	configT3();
	TRISB &= 0x80FF;
	TRISDbits.TRISD6 = 0;
	TRISDbits.TRISD5 = 0;

	EnableInterrupts();

	while(1){
		AD1CON1bits.ASAM = 1;
		while(IFS1bits.AD1IF == 0);

		int average = (ADC1BUF0 + ADC1BUF1)/2;
		scaledVal = ((average*65 + 511)/1023) + 10;

		IFS1bits.AD1IF = 0;
		delay(4000000);
	}

	return 0;
}

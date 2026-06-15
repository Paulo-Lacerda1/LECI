#include <detpic32.h>

/*

1. A ADC deve fazer duas (2) conversões para cada amostra
2. A frequência de amostragem da ADC deve ser 10Hz, obtida através do Core Timer
3. O fim de conversão da ADC deve ser processado por pooling
4. A frequência de refrescamento dos displays deve ser 120Hz
5. O refrescamento dos displays deve ser feito por interrupção, usando o Timer 2


f_out = 120Hz

f_pre_scl = 20 000 000 / k

CALCULAR O VALOR DE K
120 = PBCLK / (PRx + 1)*k
<=> 120 = 20 000 000 / (65536) * k
<=> k = 2,5431315104
k = 4

CALCULAR O VALOR DE PR2
f_pre_scl = 20 000 000 / 4 = 5 000 000

120 = 5 000 000 / (PR2 + 1)
<=> PR2 + 1 = 41 667
<=> PR2 = 41 666

*/

void delay(unsigned int val){
	resetCoreTimer();
	while(readCoreTimer() < val);
}

unsigned char toBCD(unsigned int val){
	return ((val/10)<<4) | (val%10);
}

void send2displays(unsigned char val){
	static const char disp7segment[] = {0x3F, 0x06, 0x5B, 0x4F, 0x66, 0x6D, 0x7D, 0x07, 0x7F, 0x6F, 0x77, 0x7C, 0x39, 0x5E, 0x79, 0x71};

	unsigned int dh = val >> 4;
	unsigned int dl = val & 0xF;
	static char displayFlag = 0;

	if(displayFlag == 1){
		LATDbits.LATD6 = 1; 	// select display high
		LATDbits.LATD5 = 0;
		LATB = (LATB & 0x80FF) | disp7segment[dh] << 8;
	} else {
		LATDbits.LATD6 = 0; 	
		LATDbits.LATD5 = 1;	// select display low
		LATB = (LATB & 0x80FF) | disp7segment[dl] << 8;
	}
	displayFlag = !displayFlag;
}


void configT2(){
	T2CONbits.TCKPS = 2;
	PR2 = 41666;
	TMR2 = 0;
	T2CONbits.TON = 1;

	IPC2bits.T2IP = 2; 	// priority
	IEC0bits.T2IE = 1;	// interrupt enable
	IFS0bits.T2IF = 0;	// interrup flag 
}


void configADC(){
	TRISBbits.TRISB4 = 1;
	AD1PCFGbits.PCFG4 = 0;
	AD1CON1bits.SSRC = 7;
	AD1CON1bits.CLRASAM = 1;
	AD1CON3bits.SAMC = 16;
	AD1CON2bits.SMPI = 2-1;	// 1)
	AD1CHSbits.CH0SA = 4;
	AD1CON1bits.ON = 1;
}

void config(){
	// 1000 0000 1111 1111
	TRISB &= 0x80FF;
	TRISDbits.TRISD6 = 0;
	TRISDbits.TRISD5 = 0;
}

void _int_(8) isr_t2(void){
	int average = (ADC1BUF0 + ADC1BUF1)/2;
	int scaledVal = (((average*(65-15)) + 511) / 1023) + 15;
	send2displays(toBCD(scaledVal));
	IFS0bits.T2IF = 0;	// interrup flag 
}

int main(void){
	configADC();
	configT2();
	config();

	EnableInterrupts();

	while(1){
		AD1CON1bits.ASAM = 1; // Start conversion
		while(IFS1bits.AD1IF == 0); // wait while  conversion not done  // ALINEA 3)
		IFS1bits.AD1IF = 0;
		delay(2000000); // 20 000 000 / 10 	// ALINEA 2)
	}

	return 0;
}

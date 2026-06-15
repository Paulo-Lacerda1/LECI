#include <detpic32.h>

/*
	1. Fazer 5 sequências de A/D por segundo
		A cada *200ms* o programa deve iniciar  1 sequência de 4 conversões analógicas
		A ADC vai gerar 4 valores em ADC1BUF0, ADC1BUF1, ADC1BUF2, ADC1BUF3
		Após a sequência, calcular a média

	2. Atualizar o display a cada 10ms (100 Hz)
*/


unsigned char toBCD(unsigned char value){
	return ((value/10)<<4) + (value % 10);
}

void delay(unsigned int val){
	resetCoreTimer();
	while(readCoreTimer() < val);
}

void adcConfig(){
	TRISBbits.TRISB4 = 1; 		// disconnect digital output
	AD1PCFGbits.PCFG4 = 0;		// enable analog output 

	AD1CHSbits.CH0SA = 4; 		// Seleciona AN4 como INPUT
	AD1CON2bits.SMPI = 4-1;		// 4 samples connect across ADC1BUF0 - ADC1BUF3

	AD1CON1bits.SSRC = 7;		// Selecionar canal 7
	AD1CON1bits.CLRASAM = 1; 	// Parar conversão após a primeira interrupção
	AD1CON3bits.SAMC = 16;		
						
	AD1CON1bits.ON = 1;		// Ligar a ADC
}


void send2displays(unsigned char value){
	static const char disp7Scodes[] = {
	    // 432 1098
	    0x3F, // 011 1111 = '0'
	    0x06, // 000 0110 = '1'
	    0x5B, // 101 1011 = '2'
	    0x4F, // 100 1111 = '3'
	    0x66, // 110 0110 = '4'
	    0x6D, // 110 1101 = '5'
	    0x7D, // 111 1101 = '6'
	    0x07, // 000 0111 = '7'
	    0x7F, // 111 1111 = '8'
	    0x6F, // 110 1111 = '9'
	    0x77, // 111 0111 = 'A'
	    0x7C, // 111 1100 = 'b'
	    0x39, // 011 1001 = 'C'
	    0x5E, // 101 1110 = 'd'
	    0x79, // 111 1001 = 'E'
	    0x71, // 111 0001 = 'F'
	};
	unsigned int dh, dl;
	static char displayFlag = 0;

	dh = (value >> 4);     // isolate digit high	
	dl = (value & 0x000F); // isolate digit low

	if (displayFlag == 1){
		LATDbits.LATD6 = 1; // Select display high;
		LATDbits.LATD5 = 0;
		LATB = (LATB & 0x80FF) | (disp7Scodes[dh] << 8); // Send digit_high
		displayFlag = 0;
	}else{
		LATDbits.LATD6 = 0; // Select display low;
		LATDbits.LATD5 = 1;
		LATB = (LATB & 0x80FF) | (disp7Scodes[dl] << 8); // send digit_low
		displayFlag = 1;
	}
	LATE = (LATE & 0xFF00) | toBCD(value);
}


void config(){
	TRISB = TRISB & 0x80FF; // 1000 0000 1111 1111
	TRISDbits.TRISD5 = 0; // CNTL_DISPL_L - OUTPUT
	TRISDbits.TRISD6 = 0; // CNTL_DISPL_H - OUTPUT

}

// A cada 200ms fazer uma conversão
// Atualizar o display de 10ms em 10ms
int main(void){
	adcConfig();
	config();
	int i = 0;
	while(1){
		int vBCD;
		if(i == 0){
			AD1CON1bits.ASAM = 1; // Inicia a conversão
			while(IFS1bits.AD1IF == 0); // Espera fim da conversão
			IFS1bits.AD1IF = 0;
			
			int average = (ADC1BUF0 + ADC1BUF1 + ADC1BUF2 + ADC1BUF3)/4;
			int amplitude = (average*33+511)/1023; // Utilizamos a formula de cáclulo da amplitude da tensão

			vBCD = toBCD(amplitude);
		}
		send2displays(vBCD); 
		delay(200000);		// 20 000 000 / 100
		i = (i+1) % 20;
	}

	return 0;
}
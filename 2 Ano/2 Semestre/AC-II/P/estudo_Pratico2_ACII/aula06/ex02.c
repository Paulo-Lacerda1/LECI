#include <detpic32.h>

void config(void){
	// AD1: Analog-To-Digital Converter 1
	// PCFGx: Pin Configure (x)

	TRISBbits.TRISB4 = 1;		// Desligar componente digital de saída do porto
	AD1PCFGbits.PCFG4 = 0; 		// Configurar o porto como INPUT ANALOG

	TRISDbits.TRISD11 = 0;		//* Confgurar o porto RD11 como digital OUTPUT

	AD1CON1bits.SSRC = 7;		// Configurar o trigger para "AUTO CONVERT"

	AD1CON1bits.CLRASAM = 1; 	// Parar conversões quando o 1º interrupt
						// é gerado. Hardware limpa o bit ASAM.

	AD1CON3bits.SAMC = 16;		// Tempo da sample é 16TAD (1 TAD = 100ns)

	AD1CON2bits.SMPI = 1-1; 	// 1 Samples vão ser convertidas e stored
						// localização buffer: ADC1BUF0 até ADC1BUF3

	AD1CHSbits.CH0SA = 4;		// Selecionar AN4 como o input para o A/D Converter

	AD1CON1bits.ON = 1;		// Ativa o A/D Converter
						// Deve ser o ÚLTIMO comando
}	

int main(void) {
	volatile int aux;
	config();
    	while(1) {
      	AD1CON1bits.ASAM = 1;			// Start conversion
		LATDbits.LATD11 = 1;			// Set LATD11 = 1;
        	while (IFS1bits.AD1IF == 0);		// Wait while conversion not done (AD1IF == 0)
		LATDbits.LATD11 = 0;			// Set LATD11 = 1;
        	aux = ADC1BUF0; 				//* Read conversion result (ADC1BUF0 value) to "aux"
        	IFS1bits.AD1IF = 0;			// Reset AD1IF (should be done after reading the conversation result);
    	}

    	return 0;
}

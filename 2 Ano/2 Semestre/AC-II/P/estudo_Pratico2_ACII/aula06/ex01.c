#include <detpic32.h>

void config(void){

	//! 1) Configurar um dos portos de I/O do Porto B como entrada Analogica
	//* Registos TRISB e AD1PCFG
	TRISBbits.TRISB4 = 1;	// Desligar componente digital de saída do porto
	AD1PCFGbits.PCFG4 = 0; 	// Configurar o porto como entrada ANALÓGICA
	AD1CON1bits.SSRC = 7;		// Configurar o trigger para "AUTO CONVERT"

	AD1CON1bits.CLRASAM = 1; 	// Parar conversões quando o 1º interrupt
						// é gerado. Hardware limpa o bit ASAM.
						
	AD1CON3bits.SAMC = 16;		// Tempo da sample é 16TAD (1 TAD = 100ns)

	unsigned int N = 1;		// N = NÚMERO DE SAMPLES CONSECUTIVAS
	AD1CON2bits.SMPI = N-1; 	// 4 Samples vão ser convertidas e stored
						// localização buffer: ADC1BUF0 até ADC1BUF3

	AD1CHSbits.CH0SA = 4;		// Selecionar AN4 como o input para o A/D Converter

	AD1CON1bits.ON = 1;		// Ativa o A/D Converter
						// Deve ser o ÚLTIMO comando
}	

#include <detpic32.h>

int main(void) {

	config();

    	while(1) {
        	AD1CON1bits.ASAM = 1;
        	while (IFS1bits.AD1IF == 0);	// pooling
        	printInt(ADC1BUF0, 16 | 3 << 16);
			putChar('\n');
        	IFS1bits.AD1IF = 0;
    	}

    return 0;
}
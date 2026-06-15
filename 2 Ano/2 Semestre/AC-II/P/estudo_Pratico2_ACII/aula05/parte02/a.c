/*
Escreva e teste um programa, em linguagem C, que faça todas as configurações necessárias e que mostre nos 6 LEDs ligados aos portos RE5 a RE0, o 
padrão 000001, 000010, 000100, …, 100000, 000001, 000010, …, 100000, 000001, …
O padrão deve mudar com uma frequência que depende do estado lógico do "switch" DS3 (ligado ao porto RB3): DS3 OFF, f = 2Hz; DS3 ON, f = 1Hz. O valor 
da frequência deve ser obtido com o menor erro possível.
*/

#include <detpic32.h>


void delay(unsigned int ms){
    resetCoreTimer();
    while (readCoreTimer()<20000 * ms);
}


int main(void){
    TRISE = TRISE & 0xFFC0; 
    TRISBbits.TRISB3 = 1;       //ds configurado como entrada
    int bit = 0;
    unsigned int ms;

    while(1) {
        if(PORTBbits.RB3 == 1){
            ms=1000;        //1s
        } else {
            ms=500;         //2hz
        }
        LATE = (LATE & 0xFFC0) | (++bit);

        delay(ms);
    }
    return 0;
}




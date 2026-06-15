#include <detpic32.h>

int main(void){
	TRISE = TRISE & 0xFF87; //Configure ReE6-RE3 as OUTPUT
	int counter = 0;
	while(1){
		// 1111 1111 1000 0111
		LATE = (LATE & 0xFF87) | counter << 3;
		resetCoreTimer(); while(readCoreTimer() < 4347826);
		counter = (counter + 1) % 10; // Whenever the counter reaches 10 resets back to 0
	}
	return 0;
}

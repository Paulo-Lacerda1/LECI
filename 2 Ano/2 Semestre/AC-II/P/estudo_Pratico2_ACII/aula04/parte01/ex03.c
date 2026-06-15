#include <detpic32.h>


int main(void){
	//				     7654 3210
	// RE6-3 as OUTPUT(=0) -> FF 1000 0111
	TRISE = TRISE & 0xFF87;

	int counter = 9;
	while(1){
		LATE = (LATE & 0xFF87) | counter << 3;
		counter = counter > 0 ? counter - 1 : 9;
		// 2.7Hz
		resetCoreTimer(); while(readCoreTimer() < 7407407);
	}
	return 0;
}
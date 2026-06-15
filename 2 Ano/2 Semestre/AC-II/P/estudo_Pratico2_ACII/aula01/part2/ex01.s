#   int main void(){
#       char c;
#       int cnt = 0;
#       do{
#           c = getChar();
#           putChar(c);
#           cnt++;
#       } while(c != '\n');
#       printInt(cnt, 10);
#       return 0;


    .data
    .text
    .globl main
############ 
# $t0: cnt 
# $t1: c

main:                           # int main(void){
    li      $t0, 0              #   int cnt = 0;
while:                          #   do{
    li      $v0, 2              #
    syscall  
    move    $t1, $v0           #        c = getChar(); Lê o caracter do input
    li      $v0, 3
    move    $a0, $t1
    syscall                     #       putChar( c );
    addi    $t0, $t0, 1         #       cnt++;
    beq     $t1, '\n', endw     #
    j       while
endw:                           #   }while( c != '\n' )
    li      $v0, 6
    move    $a0, $t0
    li      $a1, 10
    jr      $ra
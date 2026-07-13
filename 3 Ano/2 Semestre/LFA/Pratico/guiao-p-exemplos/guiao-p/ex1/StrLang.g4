grammar StrLang;

program: stat* EOF;

stat:
    'print' expr    #StatPrint
    | ID ':' expr   #StatAssign
    ;
expr:
    'input' '(' STRING ')' #ExprInput 
    | ID            #ExprID
    |STRING          #ExprString
    ;
ID: [a-zA-Z][a-zA-Z0-9]* ;

STRING: '"' ~["\r\n]* '"' ;

COMMENT: '//' ~[\r\n]* -> skip ;

WS: [ \r\n\t]+ -> skip;


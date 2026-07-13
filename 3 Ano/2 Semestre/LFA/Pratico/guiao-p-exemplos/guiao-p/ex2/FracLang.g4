grammar FracLang;

prog: stat* EOF;

stat
    : 'display' expr ';'      #StatDisplay
    | var=ID '<=' expr ';'    #StatAssign
    ;

expr
    : INT '/' INT             #ExprFrac
    | INT                     #ExprInt
    | ID                      #ExprID
    ;

INT: [0-9]+;
ID: [a-z]+;
WS: [ \r\n\t]+ -> skip;
COMMENT: '--' ~[\n\r]* -> skip;
grammar BigIntCalc;

program
    : stat* EOF
    ;

stat
    : 'show' expr ';'       #StatShow
    | expr '->' ID ';'      #StatAssign
    ;

expr
    : op=('+' | '-') expr                   #ExprUnary
    | expr op=('*' | 'div' | 'mod') expr    #ExprMulDivMod
    | expr op=('+' | '-') expr              #ExprAddSub
    | '(' expr ')'                          #ExprParent
    | INT                                   #ExprInt
    | ID                                    #ExprId
    ;

INT
    : [0-9]+
    ;

ID
    : [a-zA-Z] [a-zA-Z0-9]*
    ;

WS
    : [ \t\r\n]+ -> skip
    ;

COMMENT
    : '#' ~[\r\n]* -> skip
    ;
grammar Vector;

program
    : stat* EOF
    ;

stat
    : 'show' expr ';'        #StatShow
    | expr '->' ID ';'       #StatAssign
    ;

expr
    : op=('+'|'-') expr      #ExprUnary
    | expr op=('+'|'-') expr #ExprAddSub
    | '(' expr ')'           #ExprParent
    | vector                 #ExprVector
    | number                 #ExprNumber
    | ID                     #ExprID
    ;

vector
    : '[' number (',' number)* ']'
    ;

number
    : REAL
    | INT
    ;

REAL
    : [0-9]+ '.' [0-9]+
    ;

INT
    : [0-9]+
    ;

ID
    : [a-z][a-z0-9]*
    ;

WS
    : [ \t\r\n]+ -> skip
    ;

COMMENT
    : '#' ~[\r\n]* -> skip
    ;
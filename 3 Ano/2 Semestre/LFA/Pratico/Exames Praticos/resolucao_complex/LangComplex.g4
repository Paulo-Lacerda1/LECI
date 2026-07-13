grammar LangComplex;

program
    : stat* EOF
    ;

stat
    : 'display' expr ';'       #StatDisplay
    | ID '<=' expr ';'         #StatAssign
    ;

expr
    : expr op=('+'|'-') atom   #ExprAddSub
    | atom                     #ExprAtom
    ;

atom
    : NUMBER IMAG              #AtomImagNumber   // 3i
    | IMAG                     #AtomImagUnit     // i
    | NUMBER                   #AtomReal         // 4 ou 4.3
    | ID                       #AtomID           // c
    ;

NUMBER
    : [0-9]+ ('.' [0-9]+)?
    ;

IMAG
    : 'i'
    ;

ID
    : [a-zA-Z] [a-zA-Z0-9]*
    ;

COMMENT
    : '*COM*' ~[\r\n]* -> skip
    ;

WS
    : [ \t\r\n]+ -> skip
    ;
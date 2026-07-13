grammar LangComplex;

program
    : stat* EOF
    ;

stat
    : 'display' expr ';'       #StatDisplay
    | ID '<=' expr ';'         #StatAssign
    ;

expr
    : op=('+'|'-') expr        #ExprUnary
    | op=('re'|'im') expr      #ExprExtract
    | expr '*'                 #ExprConj
    | '|' expr '|'             #ExprMod
    | expr op=('*'|':') expr   #ExprMultDiv
    | expr op=('+'|'-') expr   #ExprAddSub
    | NUMBER 'i'               #ExprImagNumber
    | NUMBER                   #ExprRealNumber
    | 'i'                      #ExprI
    | ID                       #ExprID
    | '(' expr ')'             #ExprParent
    ;

NUMBER
    : [0-9]+ ('.' [0-9]+)?
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
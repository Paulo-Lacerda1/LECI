grammar CalcComplex;

program
    : stat* EOF
    ;

stat
    : 'output' expr ';'       #StatOutput
    | expr '=>' ID ';'        #StatAtribuicao
    ;

expr
    : expr op=('*'|':') expr  #ExprMultDiv
    | expr op=('+'|'-') expr  #ExprAddSub
    | '(' expr ')'            #ExprParent
    | complex                 #ExprComplex
    | 'read'                  #ExprRead
    | ID                      #ExprID
    ;

complex
    : INT op=('+'|'-') IMG    #ComplexRealImag
    | INT                     #ComplexReal
    | IMG                     #ComplexImag
    ;

INT
    : [0-9]+
    ;

IMG
    : [0-9]* 'i'
    ;

ID
    : [a-z] [a-zA-Z0-9]*
    ;

WS
    : [ \t\r\n]+ -> skip
    ;

COMMENT
    : '**' ~[\n\r]* -> skip
    ;
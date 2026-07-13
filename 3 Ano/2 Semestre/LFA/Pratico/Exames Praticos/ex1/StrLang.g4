grammar StrLang;

program: statement* EOF;
statement: assignment | print;
assignment: ID ':' expression;
print: 'print' expression;

expression: STRING                                              #ExprString
            | ID                                                #ExprID
            | '('expression')'                                  #ExprParent
            | 'input' expression                                #ExprInput
            | expression '+' expression                         #ExprAdd
            | expression '-' expression                         #ExprSub
            | 'trim' expression                                 #ExprTrim
            | expression '/' expression '/' expression          #ExprReplace
            ;

STRING: '"' .*? '"';
ID: [a-zA-Z0-9]+;
WS: [ \t\r\n]+ -> skip;
COMMENT: '//' .*? '\n' -> skip;
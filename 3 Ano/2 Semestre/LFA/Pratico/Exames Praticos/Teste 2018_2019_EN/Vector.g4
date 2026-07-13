grammar Vector;

program: statment* EOF;
statment: (show | assignment) ';';
show: 'show' expression;
assignment: expression '->' ID;

expression: 
        op=('+' | '-') expression                   #UnaryExpr
        | expression op = ('*' | '.') expression	#MultExpr
        | expression op=('+' | '-') expression      #AddSubExpr
        | '(' expression ')'                        #ParentExpr
        | VECTOR                                    #VectorExpr
        | NUMBER                                    #NumberExpr
        | ID                                        #IdExpr
        ;

VECTOR: '[' (NUMBER (',' NUMBER)*?)? ']';
NUMBER: [0-9]+ ('.' [0-9]+)?;
ID: [a-z]+ [0-9]*;
WS: [ \r\t\n] -> skip;
COMMENT: '#' .*? '\n' -> skip;
ERROR: .;

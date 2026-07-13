grammar CalComplex;

program: stat* EOF;
stat: (output | assignment) ';';
output: 'output' expression;
assignment: expression '=>' ID;

expression:
        op = ('+' | '-') expression			# UnaryExpr
        | expression op = ('*' | ':') expression	# MultDivExpr
	| expression op = ('+' | '-') expression	# AddSubExpr
	| '(' expression ')'			        # ParentExpr
        | 'input' STRING                                # InputExpr
        | COMPLEX                                       # ComplexExpr                  
        | NUMBER                                        # NumberExpr
        | ID                                            # IdExpr
        ;

STRING: '"' .*? '"';
COMPLEX: NUMBER? ('+'|'-')? NUMBER? 'i';
NUMBER: [0-9]+;
ID: [a-zA-Z]+ [0-9]*;
WS: [ \r\t\n] -> skip;
COMMENT: '##' .*? '\n' -> skip;
ERROR: .;
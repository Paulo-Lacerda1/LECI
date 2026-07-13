grammar BigIntCalc;

program: stat* EOF;
stat: (show | assignment) ';';
show: 'show' expression;
assignment: expression '->' ID;

expression:
        op = ('+' | '-') expression			        # UnaryExpr
	    | expression op = ('*' | 'div') expression	# MultDivExpr
	    | expression op = ('+' | '-') expression	# AddSubExpr
	    | expression 'mod' expression				# ModExpr
	    | '(' expression ')'					    # ParentExpr
	    | NUMBER						            # NumberExpr
	    | ID							            # IdExpr
        ;

ID: [a-zA-Z][a-zA-Z0-9]*;
NUMBER: [0-9]+;
WS: [ \t\r\n] -> skip;
COMMENT: '#' .*? '\n';
ERROR: .;
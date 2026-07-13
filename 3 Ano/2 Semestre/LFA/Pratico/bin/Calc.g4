grammar Calc;

program: stat* EOF;

stat: assignment;
assignment: ID '=' expr;
expr: Number;

ID: [a-zA-Z]+;
Number: [0-9]+;

WS:[ \n\t\r]+ -> skip;
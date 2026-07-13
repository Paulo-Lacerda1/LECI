grammar FracLang;

prgram: (command? ';')* EOF;
command: display | assignment;
display: 'display' expression;
assignment: ID '<=' expression;

expression: op = ('+' | '-') expression                 # UnaryExpression
          | expression op = ('*' | ':') expression      # MultiplicativeExpression
          | expression op = ('+' | '-') expression      # AdditiveExpression
          | 'reduce' expression                         # ReduceExpression
          | '(' expression ')'                          # ParenthesizedExpression
          | 'read' STRING                               # ReadExpression
          | FRACTION                                    # FractionExpression
          | ID                                          # IdentifierExpression
          ;

ID: [a-z]+;
FRACTION: [0-9]+ ('/' [0-9]+)?;
STRING: '"' .*? '"';
WS: [ \t\n\r]+ -> skip;
COMMENT: '--' .*? '\n' -> skip;
ERROR: .;
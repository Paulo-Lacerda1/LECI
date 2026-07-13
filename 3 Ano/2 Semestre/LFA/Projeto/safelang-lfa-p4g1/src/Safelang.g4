grammar Safelang;

program:
    (stat ';')* 
    (stat ';'?)? EOF
    ;

stat:
      assign                                                                                          #StatAssign
    | write                                                                                           #StatWrite
    | expr                                                                                            #StatExpr
    | type                                                                                            #StatType
    | if                                                                                              #StatIf
    | try                                                                                             #StatTry
    | for                                                                                             #StatFor
    | while                                                                                           #StatWhile
    | assert                                                                                          #StatAssert
    | listadd                                                                                         #StatListAdd
    | fail                                                                                            #StatFail
    ;

commonStat:
      assign                                                                                          #CommonStatAssign
    | write                                                                                           #CommonStatWrite
    | expr                                                                                            #CommonStatExpr
    | if                                                                                              #CommonStatIf
    | try                                                                                             #CommonStatTry
    | for                                                                                             #CommonStatFor
    | while                                                                                           #CommonStatWhile
    | assert                                                                                          #CommonStatAssert
    | listadd                                                                                         #CommonStatListAdd
    | fail                                                                                            #CommonStatFail
    ;

fail:
      FAILCMD
    ;

write:
      WRITE expr                                                                                      #WriteExpr
    | WRITELN expr?                                                                                   #WriteLnExpr
    ;

assign:
      ID ':=' expr ':' (TYPEINT|TYPESTR|TYPEREAL|TYPEBOOL|ID)                                         #AssignValType
    | ID ':=' expr                                                                                    #AssignVal
    | ID ':=?' expr                                                                                   #AssignTryVal
    | ID ':=?' expr ':' (TYPEINT|TYPESTR|TYPEREAL|TYPEBOOL|ID)                                        #AssignTryValType
    | ID ':' (TYPEINT|TYPESTR|TYPEREAL|TYPEBOOL|ID)                                                   #AssignType
    | ID ':=' ('new' 'list' '[' (TYPEINT|TYPESTR|TYPEREAL|TYPEBOOL|ID) ']' | ID) ':'
      'list' '[' (TYPEINT|TYPESTR|TYPEREAL|TYPEBOOL|ID) ']'                                           #AssignListValType
    | ID ':' 'list' '[' (TYPEINT|TYPESTR|TYPEREAL|TYPEBOOL|ID) ']'                                    #AssignListType
    | ID ':=' ('new' 'list' '[' (TYPEINT|TYPESTR|TYPEREAL|TYPEBOOL|ID) ']' | ID)                      #AssignListVal
    ;
  
type:
      TYPECMD ID '[' ID ']' ':' (TYPEINT|TYPESTR|TYPEREAL)                                            #TypeUnit
    | TYPECMD ID '[' ID ',' ID ']' ':' (TYPEINT|TYPESTR|TYPEREAL)                                     #TypeUnitSuffix
    | TYPECMD ID ':=' type_def_expr ':' (TYPEINT|TYPEREAL)                                            #TypeDependent
    | TYPECMD ID '[' ID ']' ':=' type_def_expr ':' (TYPEINT|TYPEREAL)                                 #TypeDependentUnit
    | TYPECMD ID '[' ID ',' ID ']' ':=' type_def_expr':' (TYPEINT|TYPEREAL)                           #TypeDependentUnitSuffix
    | TYPECMD ID ':' (TYPEINT|TYPEREAL) '[' IntegerLiteral ']'                                        #TypeByteRange
    | UNITCMD ID '[' ID ']:=' number                                                                  #DimensionUnit
    | UNITCMD ID '[' ID ',' ID ']' ':=' number                                                        #DimensionUnitSuffix
    ;

if:
      'if' booleans 'then' ((commonStat ';')* (commonStat ';'?)?) else 'end'                          #IfElse
    | 'if' booleans 'then' ((commonStat ';')* (commonStat ';'?)?) 'end'                               #IfEnd
    ;

else:
      'elseif' booleans 'then'((commonStat ';')* (commonStat ';'?)?) else?                            #ElseIf
    | 'else' ((commonStat ';')* (commonStat ';'?)?)                                                   #ElseNorm
    ;

try:
      'try' ((commonStat ';')* (commonStat ';'?)?) 'end'                                              #TryNorm
    | 'try' ((commonStat ';')* (commonStat ';'?)?) rescue 'end'                                       #TryRescue
    ;

rescue:
      'rescue' ((commonStat ';')* (commonStat ';'?)?)                                                 #RescueNorm
    | 'rescue' (commonStat ';')* 'retry' ';'?                                                         #RescueRetry
    ;

for:
      'for' ID ':=' number 'to' number 'do' ((commonStat ';')* (commonStat ';'?)?) 'end'              #ForAssign
    | 'for' number 'to' number 'do' ((commonStat ';')* (commonStat ';'?)?) 'end'                      #ForNorm
    ;

while:
      'while' booleans 'do' ((commonStat ';')* (commonStat ';'?)?) 'end'                              #WhileNorm
    | 'until' booleans 'do' ((commonStat ';')* (commonStat ';'?)?) 'end'                              #WhileUntil
    ;

assert:
      ASSERTCMD booleans
    ;

listadd:
      expr '>>' ID
    ;

type_def_expr:
      type_def_expr op=( '*' | '/' ) type_def_expr                                                    #TypeDefExpr
    | ID                                                                                              #TypeDefID
    ; 

expr:
      expr ',' expr                                                                                   #StringConcat
    | FORMATCMD '(' expr ',' number ',' op=('"left"'|'"center"'|'"right"') ')'                        #ExprFormatCommandPlacement
    | FORMATCMD '(' expr ',' number ')'                                                               #ExprFormatCommand
    | ID '[' number ']'                                                                               #ExprListRetrieveElement
    | ID                                                                                              #ExprID
    | string                                                                                          #ExprString
    | number                                                                                          #ExprNumber
    | booleans                                                                                        #ExprBoolean
    ;

string:
      StringLiteral                                                                                   #StringLiteral
    | TYPESTR '(' expr ')'                                                                            #ConvertToString
    | READ string                                                                                     #ReadCmd
    | ID '[' number ']'                                                                               #StringListRetrieveElement
    | ID                                                                                              #StringID
    ;

number:
      '(' expr ')'                                                                                    #NumberParent
    | number ID                                                                                       #NumberSuffix
    | number '*' number                                                                               #NumberMult
    | number '/' number                                                                               #NumberDivReal
    | number op=( '//' | '\\\\' ) number                                                              #NumberQuotModInt
    | number op=('+'|'-') number                                                                      #NumberAddSub
    | op=('+'|'-') number                                                                             #NumberUnary
    | ID '[' number ']'                                                                               #NumberListRetrieveElement
    | LENGTHCMD '(' ID ')'                                                                            #NumberListLength
    | TYPEINT '(' expr ')'                                                                            #ConvertToInt
    | TYPEREAL '(' expr ')'                                                                           #ConvertToReal
    | ID '(' expr ')'                                                                                 #ConvertToType
    | IntegerLiteral                                                                                  #NumberIntLiteral
    | NumberDecimal                                                                                   #NumberDecimal
    | NumberScientific                                                                                #NumberScientific
    | ID                                                                                              #NumberID
    ;

booleans:
      'not' booleans                                                                                  #BooleanNot
    | '(' booleans ')'                                                                                #BooleanParent
    | booleans '=' booleans                                                                           #BooleanEqual
    | number '<' number                                                                               #BooleanLesser
    | number '>' number                                                                               #BooleanGreater
    | number '<=' number                                                                              #BooleanLesserEqual
    | number '>=' number                                                                              #BooleanGreaterEqual
    | booleans '<>' booleans                                                                          #BooleanNotEqual
    | booleans 'and' booleans                                                                          #BooleanAnd
    | booleans 'or' booleans                                                                          #BooleanOr
    | ID '[' (IntegerLiteral|ID) ']'                                                                  #BooleanListRetrieveElement
    | ID                                                                                              #BooleanID
    | op=(TRUEKEY|FALSEKEY)                                                                           #BooleanLiteral
    | number                                                                                          #BooleanNumber
    | string                                                                                          #BooleanString
    ;

MULTLINECOMMENT: '##' .*? '##' -> skip;
COMMENT: '#' ~[\n]* -> skip;

NEWLINE: '\r'? '\n' -> skip;

StringLiteral:                                                 // Rever dps
      '\'' ~['\n]* '\''
    | '"' ~["\n]* '"';

READ: 'read';
WRITE: 'write';
WRITELN: 'writeln';
TYPECMD: 'type';
UNITCMD: 'unit';
FORMATCMD: 'format';
LENGTHCMD: 'length';
ASSERTCMD: 'assert';
FAILCMD: 'fail';

WS: [ \t]+ -> skip;

TYPESTR: 'string';
TYPEINT: 'integer';
TYPEREAL: 'real';
TYPEBOOL: 'boolean';

TRUEKEY: 'true';
FALSEKEY: 'false';

ID: [a-zA-Z_][a-zA-Z0-9_]*;                               

NumberDecimal: IntegerLiteral '.' IntegerLiteral;
NumberScientific: IntegerLiteral '.' IntegerLiteral 'e' '-'? IntegerLiteral;
IntegerLiteral: [0-9]+;                                        
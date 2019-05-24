// by najme_habibi

grammar jython;

program : importclass* (classDec)? ;

importclass : ('import' USER_TYPE) ;

classDec : 'class'  USER_TYPE ('(' USER_TYPE ')')? '{' class_body* '}' ;

class_body : varDec
           | methodDec
           | constructor
           | arrayDec
           ;

varDec :   type  ID  ;

arrayDec : type '['expression']' ID  ;

methodDec : 'def'  (type|'void'|type '['']')   ID  '(' parameters* ')''{' ( statment)* '}';

constructor : 'def'  USER_TYPE '(' parameters* ')''{' ( statment)* '}' ;

parameter : (varDec | arrayDec);

parameters : parameter (',' parameter)* ;

statment :
          while_statment
         | if_else_statment
         | for_statment
         | varDec
         | assignment
         | print_statment
         | method_call
         | return_statment
         | arrayDec
         ;

return_statment : 'return'  expression ;

condition_list : expression (('or'|'and')  expression)* ;

while_statment : 'while' '(' condition_list ')' '{' statment* '}' ;

if_else_statment :'if' '(' condition_list ')' '{' statment* '}'
                 ('elif' '(' condition_list ')' '{' statment* '}')*
                 ('else' '{' statment* '}')?  ;

print_statment : 'print' '('  expression ')' ;

for_statment : 'for' ID 'in' leftExp '{' statment* '}'
             | 'for' ID 'in' 'range''('expression (',' expression)? (',' expression)? ')' '{' statment* '}'
             ;

method_call : 'self' '.' method_call
            | ID args
            | leftExp '.' ID args;

assignment  : leftExp assignment_operators  expression
            | varDec assignment_operators  expression
            | arrayDec '='  type '('')' ('['expression']')
            | leftExp '=' type '(' ')' ('['expression']')
            ;


expression  :
              expression mult_mod_div expression
            | expression add_sub expression
            | expression eq_neq  expression
            | expression relation_operators expression
            | rightExp
            ;

rightExp :
              'none'
            | BOOL
            | INTEGER
            | STRING
            | FLOAT
            | USER_TYPE args
            | leftExp
            ;

leftExp :    ID
          |  '(' expression')'
          |  ID args
          |  leftExp '[' expression ']'
          |  leftExp '.' ID
          |  leftExp '.' ID args
          |  'self' '.' leftExp  ;

args :  '(' (explist)? ')' ;
explist  :  expression (',' expression)*;


assignment_operators : '=' | '+=' | '-=' | '*=' | '/=' ;
eq_neq               : '==' | '!=' ;
relation_operators   : '>' | '<' | '>=' | '<=';
add_sub              : '+' | '-';
mult_mod_div         : '*' | '/' | '%';
type                 : jythonType | USER_TYPE ;
USER_TYPE            : UpperCaseChar (LowerCaseChar|UpperCaseChar|DIGIT|'_')*  ;
jythonType           : 'float'|'int'|'bool'|'string';
ID                   : (LowerCaseChar)(LowerCaseChar|UpperCaseChar|DIGIT|'_' )*;
INTEGER              : CDIGIT(DIGIT)* | [0] ;
STRING               : '"' ~('\r' | '\n' | '"')* '"';
BOOL                 : 'false' |'true';
FLOAT                : DIGIT*'.'(DIGIT)+;
LowerCaseChar        : [a-z];
UpperCaseChar        : [A-Z];
DIGIT                : [0-9];
CDIGIT               : [1-9];
WS                   : [ \t]+ -> skip ;
NEWLINE              : ( '\r' '\n'?| '\n') -> skip ;
BlockComment         : '#*' .*? '*#'  -> skip  ;
LineComment          : '#' ~('\r'|'\n')* -> skip ;
grammar Drawer;

parse
:
    (
        statement ';'
    )* EOF
;

ID
:
    [a-zA-Z_] [a-zA-Z_0-9]*
;

INT
:
    [0-9]+
;

expr
:
    ID # idExpr
    | INT # litExpr
    | '(' expr ')' # parenExpr
    | left = expr '*' right = expr # mulExpr
    | left = expr '/' right = expr # divExpr
    | left = expr '+' right = expr # addExpr
    | left = expr '-' right = expr # subExpr
;

statement
:
    'rectangle' x = expr y = expr width = expr height = expr red = expr green =
    expr blue = expr # rectangle
    | 'oval' x = expr y = expr width = expr height = expr red = expr green =
    expr blue = expr # oval
    | ID '=' expr # assign
;

WS
:
    [ \t\r\n]+ -> skip
; // skip spaces, tabs, newlines

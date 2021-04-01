grammar Drawer;

parse
:
    (
        statement ';'
    )* EOF
;

INT
:
    [0-9]+
;

width
:
    INT
;

height
:
    INT
;

red
:
    INT
;

green
:
    INT
;

blue
:
    INT
;

x
:
    INT
;

y
:
    INT
;

statement
:
    'rectangle' x y width height red green blue # rectangle
    | 'oval' x y width height red green blue # oval
;

WS
:
    [ \t\r\n]+ -> skip
; // skip spaces, tabs, newlines

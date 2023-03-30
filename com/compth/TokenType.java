package com.compth;

/**
 * Superclass of all token types, providing an easy print method.
 */
public enum TokenType {
    /* book-keeping tokens */
    ENDFILE, ERROR,

    /* reserved words */
    IF, ELSE, INT, RETURN, VOID, WHILE,

    /* multicharacter tokens */
    ID, NUM, COMMENT,

    /* special symbols */
    ASSIGN, EQ, LT, GT, PLUS, MINUS, TIMES, LPAREN, RPAREN, SEMI, COMMA, DIV, LTE, GTE, NEQ, LBRACKET, RBRACKET, RCURLY, LCURLY
}

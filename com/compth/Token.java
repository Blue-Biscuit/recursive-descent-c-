package com.compth;

public class Token {
    private TokenType type;
    private String text;

    public Token(TokenType type, String text) {
        this.type = type;
        this.text = text;
    }

    public Token(TokenType type) {
        this(type, null);
    }

    public TokenType getType() {
        return type;
    }

    public String getText() {
        return text;
    }
    
    @Override
    public String toString() {
        if (this.text == null) {
            return type.toString();
        }
        else {
            return String.format("%s\t\t\"%s\"", type.toString(), text);
        }
    }
}

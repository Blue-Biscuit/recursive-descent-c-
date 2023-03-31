package com.compth;

public abstract class Declaration { // Implemented
    protected final String id;
    protected final TokenType dataType;
    
    public Declaration(String id, TokenType dataType) {
        this.id = id;
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public abstract String toString(int numTabs, int tabSpaces);
}

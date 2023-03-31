package com.compth;

public abstract class Statement {
    protected final Expression expression;

    public Statement(Expression expr) {
        this.expression = expr;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public abstract String toString(int tabs, int spcInTab);
}

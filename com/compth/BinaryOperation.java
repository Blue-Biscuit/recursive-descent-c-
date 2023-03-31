package com.compth;

public class BinaryOperation extends Expression {
    private Expression lhs;
    private Expression rhs;
    TokenType op;

    public BinaryOperation(TokenType op, Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.op = op;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    @Override
    public String toString(int tabs, int spcInTab) {
        StringBuilder sb = new StringBuilder();

        // Open bracket
        Common.addTabs(sb, tabs, spcInTab);
        sb.append(this.op);
        sb.append(" {\n");

        // LHS
        sb.append(lhs.toString(tabs + 1, spcInTab));

        // RHS
        sb.append(rhs.toString(tabs + 1, spcInTab));

        // Close bracket
        Common.addTabs(sb, tabs, spcInTab);
        sb.append("}\n");

        return sb.toString();
    }
}

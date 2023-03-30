package com.compth;

public class Selection {
    private ExpressionStatement expression;
    private Statement statement;

    public Selection(ExpressionStatement expression, Statement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public String toString(int numTabs, int tabSpaces) {
        StringBuilder sb = new StringBuilder();
        
        Common.addTabs(sb, numTabs, numTabs);
        sb.append("Statement {\n");

        sb.append(expression.toString(numTabs + 1, tabSpaces));

        sb.append(statement.toString(numTabs + 1, tabSpaces));

        Common.addTabs(sb, numTabs, numTabs);
        sb.append("}\n");

        return sb.toString();
    }
}
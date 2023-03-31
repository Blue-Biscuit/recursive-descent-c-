package com.compth;

public class ExpressionStatement {
    private Expression expression;

    public ExpressionStatement(Expression e) {
        expression = e;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public String toString(int numTabs, int spacesInTab) {
        StringBuilder sb = new StringBuilder();

        // Open bracket
        Common.addTabs(sb, numTabs, spacesInTab);
        sb.append("ExpressionStatement {\n");

        // The expression, if extant.
        if (expression != null) {
            sb.append(expression.toString(numTabs + 1, spacesInTab));
        }

        // Close bracket
        Common.addTabs(sb, numTabs, spacesInTab);
        sb.append("}\n");

        return sb.toString();
    }
}
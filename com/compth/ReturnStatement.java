package com.compth;

public class ReturnStatement extends Statement { // COMPLETE
    private final Expression expression;

    public ReturnStatement(Expression expression) {
        this.expression = expression;
    }

    public String toString(int tabs, int spacesInTab) {
        StringBuilder sb = new StringBuilder();

        // Print open bracket
        Common.addTabs(sb, tabs, spacesInTab);
        sb.append("ReturnStatement {\n");

        // Print expression, if non-null
        if (expression != null) {
            sb.append(expression.toString(tabs + 1, spacesInTab));
        }

        // Print close bracket
        Common.addTabs(sb, tabs, spacesInTab);
        sb.append("}\n");

        return sb.toString();
    }
}

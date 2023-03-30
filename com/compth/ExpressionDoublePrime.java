package com.compth;

public class ExpressionDoublePrime {
    private Express expression;
    private SimpleExpressionPrime expressionPrime;

    public ExpressionDoublePrime(Express expression, SimpleExpressionPrime expressionPrime) {
        this.expression = expression;
        this.expressionPrime = expressionPrime;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public String toString(int tabs, int numSpacesInTab) {
        StringBuilder sb = new StringBuilder();

        // Open bracket
        Common.addTabs(sb, tabs, numSpacesInTab);
        sb.append("Expression'' {\n");

        if (expression != null) {
            sb.append(expression.toString(tabs + 1, numSpacesInTab));
        }
        else {
            sb.append(expressionPrime.toString(tabs + 1, numSpacesInTab));
        }

        // Close bracket
        Common.addTabs(sb, tabs, numSpacesInTab);
        sb.append("}\n");

        return sb.toString();
    }
}

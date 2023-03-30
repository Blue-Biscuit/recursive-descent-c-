package com.compth;

// expression’ → = expression | \[ expression \] expression’’ | simple-expression’ ( follow factor ) | ( args )
public class ExpressionPrime { // Implemented
    private Express expression;
    private ExpressionDoublePrime expressionDoublePrime;
    private SimpleExpressionPrime simpleExpressionPrime;
    private Args args;

    public ExpressionPrime(Express expression, ExpressionDoublePrime exprDoublePrime, SimpleExpressionPrime simpleExpressionPrime, Args args) {
        this.expression = expression;
        this.expressionDoublePrime = exprDoublePrime;
        this.simpleExpressionPrime = simpleExpressionPrime;
        this.args = args;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    // expression’ → = expression | \[ expression \] expression’’ | simple-expression’ ( follow factor ) | ( args )
    public String toString(int tabs, int spacesInTab) {
        StringBuilder sb = new StringBuilder();

        // Open bracket
        Common.addTabs(sb, tabs, spacesInTab);
        sb.append("Expression' {\n");

        if (expression != null) {
            sb.append(expression.toString(tabs + 1, spacesInTab));
        }
        if (expressionDoublePrime != null) {
            sb.append(expressionDoublePrime.toString(tabs + 1, spacesInTab));
        }
        if (simpleExpressionPrime != null) {
            sb.append(simpleExpressionPrime.toString(tabs + 1, spacesInTab));
        }
        if (args != null) {
            sb.append(args.toString(tabs + 1, spacesInTab));
        }

        // Close bracket
        Common.addTabs(sb, tabs, spacesInTab);
        sb.append("}\n");

        return sb.toString();
    }
}

package com.compth;

public class Express { // IMplemented
    private String identifierString;
    private String num;
    ExpressionPrime ep;
    Express expression;
    SimpleExpressionPrime sep;

    public Express(String identifierString, String num, ExpressionPrime ep, Express expression, SimpleExpressionPrime sep) {
        this.identifierString = identifierString;
        this.num = num;
        this.ep = ep;
        this.expression = expression;
        this.sep = sep;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public String toString(int numTabs, int spacesPerTab) {
        final StringBuilder sb = new StringBuilder();

        Common.addTabs(sb, numTabs, spacesPerTab);
        sb.append("Expression {\n");

        if (identifierString != null) {
            Common.addTabs(sb, numTabs + 1, spacesPerTab);
            sb.append(identifierString + "\n");
        }
        else if (num != null) {
            Common.addTabs(sb, numTabs + 1, spacesPerTab);
            sb.append(num + "\n");
        }
        else if (ep != null) {
            sb.append(ep.toString(numTabs + 1, spacesPerTab));
        }
        else if(expression != null) {
            sb.append(expression.toString(numTabs + 1, spacesPerTab));
        }
        else {
            sb.append(sep.toString(numTabs + 1, spacesPerTab));
        }

        Common.addTabs(sb, numTabs, spacesPerTab);
        sb.append("}\n");

        return sb.toString();
    }
}

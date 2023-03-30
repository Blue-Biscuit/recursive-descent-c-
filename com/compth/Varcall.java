package com.compth;

public class Varcall {
    private Express expression;
    private Args args;

    public Varcall(Express e, Args a) {
        expression = e;
        args = a;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public String toString(int numTabs, int spacesPerTab) {
        final StringBuilder sb = new StringBuilder();
        
        // Print open bracket
        Common.addTabs(sb, numTabs, spacesPerTab);
        sb.append("Varcall {\n");

        // If expression is non-null, print that.
        if (expression != null) {
            sb.append(expression.toString(numTabs + 1, spacesPerTab));
        }

        // If args is non-null, print that.
        else if (args != null) {
            sb.append(expression.toString(numTabs + 1, spacesPerTab));
        }

        // Print close bracket
        Common.addTabs(sb, numTabs, spacesPerTab);
        sb.append("}\n");

        return sb.toString();
    }
}
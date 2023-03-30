package com.compth;

public class Factor {
    private String idString;
    private Express expression;
    private Varcall varcall;
    private String numString;

    public Factor(Express expression, String idString, Varcall varcall, String numString) {
        this.idString= idString;
        this.expression = expression;
        this.varcall = varcall;
        this.numString = numString;   
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public String toString(int numTabs, int spacesPerTab) {
        StringBuilder sb = new StringBuilder();

        // Open brace
        Common.addTabs(sb, numTabs, spacesPerTab);
        sb.append("Factor {\n");

        if (expression != null) {
            sb.append(expression.toString(numTabs + 1, spacesPerTab));
        }
        else if (varcall != null) {
            Common.addTabs(sb, numTabs + 1, spacesPerTab);
            sb.append("id: " + idString);
            sb.append(varcall.toString(numTabs + 1, spacesPerTab));
        }
        else {
            Common.addTabs(sb, numTabs + 1, spacesPerTab);
            sb.append(numString + "\n");
        }

        Common.addTabs(sb, numTabs, spacesPerTab);
        sb.append("}\n");
     
        return sb.toString();
    }
}

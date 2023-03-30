package com.compth;

public class SelectionStatement {
    private Express expr;
    private Statement ifPart;
    private Statement elsePart; 

    public SelectionStatement(Express expr, Statement ifPart, Statement elsePart) {
        this.expr = expr;
        this.ifPart = ifPart;
        this.elsePart = elsePart;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public String toString(int tabs, int spacesInTab) {
        StringBuilder sb = new StringBuilder();

        // Open Bracket
        Common.addTabs(sb, tabs, spacesInTab);
        sb.append("Selection Statement {\n");

        // Print expr and if part
        sb.append(expr.toString(tabs + 1, spacesInTab));
        sb.append(ifPart.toString(tabs + 1, spacesInTab));

        // Print the else-part, if non-null.
        if (elsePart != null) {
            sb.append(elsePart.toString(tabs + 1, spacesInTab));
        }

        // Close Bracket
        Common.addTabs(sb, tabs, spacesInTab);
        sb.append("}\n");

        return sb.toString();
    }
}

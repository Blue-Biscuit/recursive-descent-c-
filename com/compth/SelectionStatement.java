package com.compth;

public class SelectionStatement extends Statement {
    private final Statement ifPart;
    private final Statement elsePart;

    public SelectionStatement(Expression expr, Statement ifPart, Statement elsePart) {
        super(expr);
        this.ifPart = ifPart;
        this.elsePart = elsePart;
    }
    
    @Override
    public String toString(int tabs, int spacesInTab) {
        StringBuilder sb = new StringBuilder();

        Common.addTabs(sb, tabs, spacesInTab);
        sb.append("Selection Statement {\n");

        sb.append(this.expression.toString(tabs + 1, spacesInTab));
        sb.append(this.ifPart.toString(tabs + 1, spacesInTab));

        if (this.elsePart != null) {
            sb.append(this.elsePart.toString(tabs + 1, spacesInTab));
        }

        Common.addTabs(sb, tabs, spacesInTab);
        sb.append("}\n");

        return sb.toString();
    }
}

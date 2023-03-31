package com.compth;

public class IterationStatement extends Statement {
    private final Statement stmt;

    public IterationStatement(Expression exp, Statement statement) {
        super(exp);
        this.stmt = statement;
    }

    public String toString(int tabs, int spacesInTabs) {
        StringBuilder sb = new StringBuilder();

        // Open bracket
        Common.addTabs(sb, tabs, spacesInTabs);
        sb.append("Iteration Statement {\n");

        // Expression and Statement
        sb.append(this.expression.toString(tabs + 1, spacesInTabs));
        sb.append(this.stmt.toString(tabs + 1, spacesInTabs));

        // Close bracket
        Common.addTabs(sb, tabs, spacesInTabs);
        sb.append("}\n");

        return sb.toString();
    }
}

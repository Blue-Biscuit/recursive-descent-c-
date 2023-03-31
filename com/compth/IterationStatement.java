package com.compth;

// iteration-stmt → while ( expression ) statement
public class IterationStatement {
    private Expression exp;
    private Statement statement;

    public IterationStatement(Expression exp, Statement statement) {
        this.exp = exp;
        this.statement = statement;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public String toString(int tabs, int spacesInTabs) {
        StringBuilder sb = new StringBuilder();

        // Open bracket
        Common.addTabs(sb, tabs, spacesInTabs);
        sb.append("Iteration Statement {\n");

        // Expression and Statement
        sb.append(exp.toString(tabs + 1, spacesInTabs));
        sb.append(statement.toString(tabs + 1, spacesInTabs));

        // Close bracket
        Common.addTabs(sb, tabs, spacesInTabs);
        sb.append("}\n");

        return sb.toString();
    }
}

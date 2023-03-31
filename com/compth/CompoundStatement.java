package com.compth;
import java.util.ArrayList;

public class CompoundStatement extends Statement {
    private ArrayList<String> identifierNames;
    private ArrayList<Statement> statements;

    public CompoundStatement(ArrayList<String> identifierNames, ArrayList<Statement> statements) {
        this.identifierNames = identifierNames;
        this.statements = statements;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public String toString(int numTabs, int spacesInTabs) {
        final StringBuilder sb = new StringBuilder();

        // Print the open bracket
        Common.addTabs(sb, numTabs, spacesInTabs);
        sb.append("CompoundStatement {\n");

        // Print the identifiers
        for (String id : identifierNames) {
            Common.addTabs(sb, numTabs + 1, spacesInTabs);
            sb.append("int " + id);
            sb.append('\n');
        }

        // Print the statements
        for (Statement s : statements) {
            sb.append(s.toString(numTabs + 1, spacesInTabs));
        }

        // Print the close bracket
        Common.addTabs(sb, numTabs, spacesInTabs);
        sb.append("}\n");

        return sb.toString();
    }
}

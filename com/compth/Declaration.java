package com.compth;

public class Declaration { // Implemented
    private String idString;
    private Params params;
    private CompoundStatement compoundStatement;
    private DeclarationPrime declarationPrime;
    
    public Declaration(String idString, Params params, CompoundStatement compoundStatement, DeclarationPrime declarationPrime) {
        this.idString = idString;
        this.params = params;
        this.compoundStatement = compoundStatement;
        this.declarationPrime = declarationPrime;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public String toString(int numTabs, int tabSpaces) {
        StringBuilder sb = new StringBuilder();
        
        // Open bracket
        Common.addTabs(sb, numTabs, tabSpaces);
        sb.append("Declaration {\n");

        // Print the 'id'
        Common.addTabs(sb, numTabs + 1, tabSpaces);
        sb.append(idString + "\n");

        // If decl' is non-null, print that.
        if (declarationPrime != null) {
            sb.append(declarationPrime.toString(numTabs + 1, tabSpaces));
        }

        // Otherwise, print params and compound-stmt.
        if (params != null) {
            sb.append(params.toString(numTabs + 1, tabSpaces));
        }
        if (compoundStatement != null) {
            sb.append(compoundStatement.toString(numTabs + 1, tabSpaces));
        }

        // Close bracket
        Common.addTabs(sb, numTabs, tabSpaces);
        sb.append("}");

        return sb.toString();
    }
}

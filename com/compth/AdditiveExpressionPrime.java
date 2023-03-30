package com.compth;

import java.util.ArrayList;

// additive-expression’ → term’ {additive-expression term }
public class AdditiveExpressionPrime {
    private TermPrime lhs;
    private ArrayList<AdditiveExpression> additiveExpressions;
    private ArrayList<Term> rhs;

    public AdditiveExpressionPrime(TermPrime lhs, ArrayList<AdditiveExpression> additiveExpressions, ArrayList<Term> rhs) {
        this.lhs = lhs;
        this.additiveExpressions = additiveExpressions;
        this.rhs = rhs;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public String toString(int tabs, int spacesInTab) {
        StringBuilder sb = new StringBuilder();
        
        // Open brace
        Common.addTabs(sb, tabs, spacesInTab);
        sb.append("Additive Expression' {\n");
        
        sb.append(lhs.toString(tabs + 1, spacesInTab));
        for (int i = 0; i < rhs.size(); i++) {
            sb.append(additiveExpressions.get(i).toString(tabs + 1, spacesInTab));
            sb.append(rhs.get(i).toString(tabs + 1, spacesInTab));
        }

        // Close brace
        Common.addTabs(sb, tabs, spacesInTab);
        sb.append("}\n");

        return sb.toString();
    }
}

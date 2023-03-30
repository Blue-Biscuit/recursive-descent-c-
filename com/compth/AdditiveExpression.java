package com.compth;

import java.util.ArrayList;

public class AdditiveExpression { // Implemented
    private Term lhs;
    private ArrayList<Term> rhs;
    private ArrayList<Addop> ops; 

    public AdditiveExpression(Term lhs, ArrayList<Addop> ops, ArrayList<Term> rhs) {
        this.lhs = lhs;
        this.ops = ops;
        this.rhs = rhs;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public String toString(int numTabs, int spacesPerTab) {
        final StringBuilder sb = new StringBuilder();

        // Open bracket
        Common.addTabs(sb, numTabs, spacesPerTab);
        sb.append("Additive Expression {\n");

        // Print the lhs
        sb.append(lhs.toString(numTabs + 1, spacesPerTab));

        // Print the rhs's
        for (int i = 0; i < ops.size(); i++) {
            sb.append(ops.get(i).toString(numTabs + 1, spacesPerTab));
            sb.append(rhs.get(i).toString(numTabs + 1, spacesPerTab));
        }

        // Close bracket
        Common.addTabs(sb, numTabs, spacesPerTab);
        sb.append("}\n");
        
        return sb.toString();
    }   
}

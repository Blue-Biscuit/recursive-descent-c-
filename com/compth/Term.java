package com.compth;

import java.util.ArrayList;

// term → { mulop factor }
public class Term {
    private ArrayList<Mulop> ops;
    private ArrayList<Factor> factors;

    public Term(ArrayList<Mulop> ops, ArrayList<Factor> factors) {
        this.ops = ops;
        this.factors = factors;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public String toString(int tabs, int spacesInTab) {
        StringBuilder sb = new StringBuilder();

        // Open brace
        Common.addTabs(sb, tabs, spacesInTab);
        sb.append("Term {\n");

        for (int i = 0; i < factors.size(); i++) {
            sb.append(ops.get(i).toString(tabs + 1, spacesInTab));
            sb.append(factors.get(i).toString(tabs + 1, spacesInTab));
        }

        // Close brace
        Common.addTabs(sb, tabs, spacesInTab);
        sb.append("}\n");

        return sb.toString();
    }
}

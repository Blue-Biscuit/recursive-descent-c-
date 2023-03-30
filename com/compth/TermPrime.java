package com.compth;

import java.util.ArrayList;

public class TermPrime {
    private Factor lhs;
    private ArrayList<Mulop> ops;
    private ArrayList<Factor> rhs;

    public TermPrime(Factor lhs, ArrayList<Mulop> ops, ArrayList<Factor> rhs) {
        this.lhs = lhs;
        this.ops = ops;
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
        sb.append("Term' {\n");

        sb.append(lhs.toString(tabs + 1, spacesInTab));
        for (int i = 0; i < rhs.size(); i++) {
            sb.append(ops.get(i).toString(tabs + 1, spacesInTab));
            sb.append(rhs.get(i).toString(tabs, spacesInTab));
        }

        // Close brace
        Common.addTabs(sb, tabs, spacesInTab);
        sb.append("}\n");

        return sb.toString();
    }
}

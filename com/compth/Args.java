package com.compth;

import java.util.ArrayList;

public class Args {
    private ArrayList<Express> expressions;

    public Args(ArrayList<Express> exp) {
        this.expressions = exp;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public String toString(int numTabs, int spacesInTabs) {
        StringBuilder sb = new StringBuilder();

        // Print the open bracket.
        Common.addTabs(sb, numTabs, spacesInTabs);
        sb.append("Args {\n");

        // Print all sub-expressions
        for (Express e : expressions) {
            sb.append(e.toString(numTabs + 1, spacesInTabs));
        }

        // Print the close bracket.
        Common.addTabs(sb, numTabs, spacesInTabs);
        sb.append("}\n");

        return sb.toString();
    }
}
package com.compth;
import java.util.ArrayList;

public class Params {
    private ArrayList<String> names;
    private ArrayList<Boolean> areArrays;

    public Params(ArrayList<String> names, ArrayList<Boolean> areArrays) {
        this.names = names;
        this.areArrays = areArrays;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public String toString(int numTabs, int numSpacesInTab) {
        final StringBuilder sb = new StringBuilder();

        // Print the opening brace
        Common.addTabs(sb, numTabs, numSpacesInTab);
        sb.append("Params {\n");

        // If there are no paramaters, the content is "void"

        if (names.size() == 0) {
            Common.addTabs(sb, numTabs + 1, numSpacesInTab);
            sb.append("void\n");
        }

        // Otherwise, print all parameters.

        else {
            for (int i = 0; i < names.size(); i++) {
                Common.addTabs(sb, numTabs + 1, numSpacesInTab);
                sb.append(names.get(i));    
                if (areArrays.get(i)) {
                    sb.append("[]");
                }
                sb.append("\n");
            }
        }

        // Print the closing brace.
        Common.addTabs(sb, numTabs, numSpacesInTab);
        sb.append("}\n");

        return sb.toString();
    }
}

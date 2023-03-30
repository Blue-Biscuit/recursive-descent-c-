package com.compth;

public class Relop {
    private String specialSymbol;

    public Relop(String specialSymbol) {
        this.specialSymbol = specialSymbol;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public String toString(int numTabs, int spacesPerTab) {
        final StringBuilder sb = new StringBuilder();

        Common.addTabs(sb, numTabs, spacesPerTab);
        sb.append("Relop {\n");

       Common.addTabs(sb, numTabs + 1, spacesPerTab);
       sb.append(specialSymbol);

       Common.addTabs(sb, numTabs, spacesPerTab);
       sb.append("}\n");

       return sb.toString();
    }
}

package com.compth;

public class Addop {
    private String specialSymbol;

    public Addop(String specialSymbol) {
        this.specialSymbol = specialSymbol;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public String toString(int numTabs, int spacesPerTab) {
        final StringBuilder sb = new StringBuilder();

        Common.addTabs(sb, numTabs, spacesPerTab);
        sb.append("Addop {\n");

       Common.addTabs(sb, numTabs + 1, spacesPerTab);
       sb.append(specialSymbol);

       Common.addTabs(sb, numTabs, spacesPerTab);
       sb.append("}\n");
       
       return sb.toString();
    }
}

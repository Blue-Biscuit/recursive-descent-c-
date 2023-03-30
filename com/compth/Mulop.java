package com.compth;

public class Mulop {
    private String specialMulop;

    public Mulop(String specialMulop) {
        this.specialMulop = specialMulop;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public String toString(int numTabs, int spacesPerTab) {
        final StringBuilder sb = new StringBuilder();

        Common.addTabs(sb, numTabs, spacesPerTab);
        sb.append("Mulop {\n");

       Common.addTabs(sb, numTabs + 1, spacesPerTab);
       sb.append(specialMulop);

       Common.addTabs(sb, numTabs, spacesPerTab);
       sb.append("}\n");
       
       return sb.toString();
    }
}

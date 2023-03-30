package com.compth;

public class DeclarationPrime { // Implemented
    private String num;
    private CompoundStatement cmpd;
    private Params params;

    public DeclarationPrime(String num, Params params, CompoundStatement cmpd) {
        this.num = num;
        this.params = params;
        this.cmpd = cmpd;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public String toString(int tabs, int spacesInTab) {
        StringBuilder sb = new StringBuilder();

        // Open bracket

        Common.addTabs(sb, tabs, spacesInTab);
        sb.append("DeclarationPrime {\n");

        // Print num, if non-null.
        if (this.num != null) {
            Common.addTabs(sb, tabs + 1, spacesInTab);
            sb.append(this.num);
            sb.append('\n');
        }


        // Otherwise, print params and cmpd
        if (this.params != null) {
            sb.append(this.params.toString(tabs + 1, spacesInTab));
        }
        if (this.cmpd != null) {
            sb.append(this.cmpd.toString(tabs + 1, spacesInTab));
        }

        // Close bracket
        Common.addTabs(sb, tabs, spacesInTab);
        sb.append("}\n");

        return sb.toString();
    }
}

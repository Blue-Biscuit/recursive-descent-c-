package com.compth;

public class Indexed extends Expression {
    private final String id;
    private final Expression index;

    public Indexed(String id, Expression index) {
        this.id = id;
        this.index = index;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    @Override
    public String toString(int tabs, int spcInTab) {
        StringBuilder sb = new StringBuilder();

        Common.addTabs(sb, tabs, spcInTab);
        sb.append(id);
        sb.append(" [\n");
        sb.append(index.toString(tabs + 1, spcInTab));
        Common.addTabs(sb, tabs, spcInTab);
        sb.append("]\n");

        return sb.toString();
    }
}

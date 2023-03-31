package com.compth;

public class Var extends Expression {
    private final String id;

    public Var(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public String toString(int tabs, int spcInTab) {
        StringBuilder sb = new StringBuilder();

        Common.addTabs(sb, tabs, spcInTab);
        sb.append(id);
        sb.append('\n');

        return sb.toString();
    }
}

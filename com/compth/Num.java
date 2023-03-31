package com.compth;

public class Num extends Expression{
    private final int val;

    public Num(int val) {
        this.val = val;
    }

    @Override
    public String toString(int tabs, int spcInTab) {
        StringBuilder sb = new StringBuilder();

        Common.addTabs(sb, tabs, spcInTab);
        sb.append(val);
        sb.append('\n');

        return sb.toString();
    }

}

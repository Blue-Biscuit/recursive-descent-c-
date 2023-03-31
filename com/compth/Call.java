package com.compth;

import java.util.ArrayList;

public class Call extends Expression{
    private final String id;
    private final ArrayList<Expression> args;

    public Call(String id, ArrayList<Expression> args) {
        this.id = id;
        this.args = args;
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
        sb.append("(\n");
        for (Expression e : args) {
            sb.append(e.toString(tabs + 1, spcInTab));
        }
        sb.append(")\n");

        return sb.toString();
    }
}

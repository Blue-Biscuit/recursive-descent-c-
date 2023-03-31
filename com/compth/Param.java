package com.compth;

public class Param {
    private final String id;
    private final boolean isArray;

    public Param(String id, boolean isArray) {
        this.id = id;
        this.isArray = isArray;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public String toString(int tabs, int spcInTabs) {
        StringBuilder sb = new StringBuilder();

        Common.addTabs(sb, tabs, spcInTabs);
        sb.append("int ");
        sb.append(id);

        if (isArray) {
            sb.append("[]");
        }

        return sb.toString();
    }
}

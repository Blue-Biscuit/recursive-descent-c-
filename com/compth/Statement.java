package com.compth;

public abstract class Statement {
    @Override
    public String toString() {
        return toString(0, 3);
    }

    public abstract String toString(int tabs, int spcInTab);
}

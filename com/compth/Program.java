package com.compth;
import java.util.ArrayList;

public class Program {
    private ArrayList<Declaration> declarations;

    public Program(ArrayList<Declaration> decls) {
        this.declarations = decls;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Program {\n");
        for (Declaration d : declarations) {
            sb.append(d.toString(1, 3) + "\n");
        }
        sb.append("}");

        return sb.toString();
    }
}

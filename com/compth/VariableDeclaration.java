package com.compth;

public class VariableDeclaration extends Declaration {
    private final Integer index;

    public VariableDeclaration(String id, int index) {
        super(id, TokenType.INT);
        this.index = index;
    }

    public VariableDeclaration(String id) {
        super(id, TokenType.INT);
        this.index = null;
    }

    public String toString() {
        return toString(0, 3);
    }

    @Override
    public String toString(int tabs, int tabSpaces) {
        StringBuilder sb = new StringBuilder();

        Common.addTabs(sb, tabs, tabSpaces);
        sb.append("Variable Declaration {\n");

        Common.addTabs(sb, tabs + 1, tabSpaces);
        sb.append("int ");
        sb.append(this.id);
        
        if (index != null) {
            sb.append("[");
            sb.append(index);
            sb.append("]\n");
        }

        Common.addTabs(sb, tabs, tabSpaces);
        sb.append("}\n");

        return sb.toString();
    }
}

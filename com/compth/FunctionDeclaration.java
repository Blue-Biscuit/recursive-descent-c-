package com.compth;

import java.util.ArrayList;

public class FunctionDeclaration extends Declaration {
    private final ArrayList<Param> params;
    private final CompoundStatement stmts;

    public FunctionDeclaration(String id, TokenType dataType, ArrayList<Param> params, CompoundStatement statements) {
        super(id, dataType);
        this.params = params;
        this.stmts = statements;
    }

    @Override
    public String toString(int tabs, int spcInTab) {
        StringBuilder sb = new StringBuilder();

        Common.addTabs(sb, tabs, spcInTab);
        sb.append("Function Declaration {\n");

        Common.addTabs(sb, tabs, spcInTab);
        sb.append("identifier: ");
        sb.append(this.id);
        sb.append('\n');

        Common.addTabs(sb, tabs, spcInTab);
        sb.append("Params {\n");
        for (int i = 0; i < params.size(); i++) {
            sb.append(this.params.get(i).toString(tabs + 1, spcInTab));
        }

        sb.append(stmts.toString(tabs + 1, spcInTab));

        Common.addTabs(sb, tabs, spcInTab);
        sb.append("}\n");

        return sb.toString();
    }
}

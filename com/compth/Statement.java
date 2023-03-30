package com.compth;

public class Statement {
    private ExpressionStatement es;
    private SelectionStatement ss;
    private IterationStatement is;
    private ReturnStatement rs;

    public Statement(ExpressionStatement es, SelectionStatement ss, IterationStatement is, ReturnStatement rs) {
        this.es = es;
        this.ss = ss;
        this.is = is;
        this.rs = rs;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public String toString(int numTabs, int spacesPerTab) {
        final StringBuilder sb = new StringBuilder();

        // Open brace
        Common.addTabs(sb, numTabs, spacesPerTab);
        sb.append("Statement {\n");

        // Print whichever type of statement is non-null.
        if (es != null) {
            sb.append(es.toString() + "\n");
        }
        else if (ss != null) {
            sb.append(ss.toString() + "\n");
        }
        else if (is != null) {
            sb.append(is.toString() + "\n");
        }
        else {
            sb.append(rs.toString() + "\n");
        }

        // Close brace
        Common.addTabs(sb, numTabs, spacesPerTab);
        sb.append("}\n");

        return sb.toString();
    }
}

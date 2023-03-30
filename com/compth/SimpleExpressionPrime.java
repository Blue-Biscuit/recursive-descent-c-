package com.compth;

// simple-expression’ → additive-expression’ [ relop additive-expression ]
public class SimpleExpressionPrime { // Implemented
    private AdditiveExpressionPrime aep;
    private AdditiveExpression ae;
    private Relop rel;

    public SimpleExpressionPrime(AdditiveExpressionPrime aep, Relop rel, AdditiveExpression ae) {
        this.aep = aep;
        this.rel = rel;
        this.ae = ae;
    }

    @Override
    public String toString() {
        return toString(0, 3);
    }

    public String toString(int numTabs, int spacesPerTab) {
        final StringBuilder sb = new StringBuilder();

        Common.addTabs(sb, numTabs, spacesPerTab);
        sb.append("Simple Expresion' {\n");

        sb.append(aep.toString(numTabs + 1, spacesPerTab) + "\n");


        if (rel != null) {
            sb.append(rel.toString(numTabs + 1, spacesPerTab));
        }
        
        if (ae != null) {
            sb.append(ae.toString(numTabs + 1, spacesPerTab));
        }

        Common.addTabs(sb, numTabs, spacesPerTab);
        sb.append("}\n");
        
        return sb.toString();
    }
}

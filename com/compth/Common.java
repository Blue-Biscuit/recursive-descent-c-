package com.compth;

public class Common {
    /**
     * Adds the specified number of spaces to the stringbuilder.
     * @param sb The string builder.
     * @param numSpaces The number of spaces to add.
     */
    public static void addSpaces(StringBuilder sb, int numSpaces) {
        for (int i = 0; i < numSpaces; i++) {
            sb.append(' ');
        }
    }

    public static void addTabs(StringBuilder sb, int tabs, int numSpacesInTab) {
        Common.addSpaces(sb, tabs * numSpacesInTab);
    }
}

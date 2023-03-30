package com.compth;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{
        CMinusParser cmp = new CMinusParser("test");
        Program p = cmp.parse();
        System.out.println(p);
    }
}

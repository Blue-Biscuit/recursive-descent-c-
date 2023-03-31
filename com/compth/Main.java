package com.compth;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{
        Scanner s = new Scanner(System.in);
        System.out.print("File to test >> ");
        final String filename = s.nextLine();
        CMinusParser cmp = new CMinusParser(filename);
        Program p = cmp.parse();
        System.out.println(p);
        s.close();
    }
}
